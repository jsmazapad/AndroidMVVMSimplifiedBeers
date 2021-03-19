package es.jsm.mvvm.beer.ui.privateviews.friends;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;

import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.loader.content.CursorLoader;
import androidx.navigation.NavController;

import java.util.Collections;
import java.util.List;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.config.SortListConfig;
import es.jsm.mvvm.beer.core.data.repositories.responses.ListResponse;
import es.jsm.mvvm.beer.core.exceptions.BaseException;
import es.jsm.mvvm.beer.core.utils.ExternalActionsManager;
import es.jsm.mvvm.beer.core.utils.ModalMessage;
import es.jsm.mvvm.beer.model.Friend;
import es.jsm.mvvm.beer.repositories.BeersRepository;
import es.jsm.mvvm.beer.ui.privateviews.baseprivate.BasePrivateRecyclerViewModel;

public class FriendsViewModel extends BasePrivateRecyclerViewModel<Friend, Friend> implements FriendActionsListener{


    /**
     * Array con las columnas a bindear en el adapter del spinner
     */
    private final static String[] FROM_COLUMNS = {
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
    };

    /**
     * Array con los ids de los componentes de la vista a los que se bindearan los datos en el adapter del spinner
     */
    private final static int[] TO_IDS = {
            android.R.id.text1
    };
    /**
     * Proyección (datos a devolver) que se usará en la consulta al proveedor de contactos
     */
    private static final String[] PROJECTION =
            {
                    ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.LOOKUP_KEY,
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
                    ContactsContract.Contacts.HAS_PHONE_NUMBER

            };

    /**
     * Condición que se empleará para filtrar la consulta
     */
    private static final String SELECTION =
            ContactsContract.Contacts.HAS_PHONE_NUMBER + " = 1" ;
    //ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " LIKE ?";
    //no se usa en esta aplicación pero se deja por motivos de enseñanza
    /**
     * Define las variables que se usaran en el criterio de selección de la consulta,
     */
    private String searchString;
    //no se usa en esta aplicación pero se deja por motivos de enseñanza
    /**
     * Array con variables de selección
     */
    private String[] selectionArgs = { searchString };

    /**
     * Criterio de orden, se usa COLLATE LOCALIZED para que ordene usando los criterios basados en localización del dispositivo (lneguaje y convenciones)
     */
    private String SORT_BY = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";


    /**
     * Adapter que bindea los datos de la consulta a la vista
     */
    private SimpleCursorAdapter cursorAdapter;

//LiveData para detectar si se ha hecho un cambio en los datos (inserción de registro)
 private MutableLiveData<Boolean> changeEnded = new MutableLiveData<>();
 //Mediador para el livedata
    private MediatorLiveData<Boolean> changeEndedMediator = new MediatorLiveData<>();

    public FriendsViewModel(Application app) {
        super(app);
        changeEndedMediator.removeSource(changeEnded);
        changeEndedMediator.addSource(changeEnded, response -> {
            isLoading.setValue(false);
           if(response != null ? response : false){
               callRepositoryForData();
           }
        });


    }

    public MediatorLiveData<Boolean> getChangeEndedMediator() {
        return changeEndedMediator;
    }

    public SimpleCursorAdapter getCursorAdapter() {
        return cursorAdapter;
    }



    @Override
    public List<Friend> transformResponse(ListResponse<Friend> response) {
        List<Friend> list = response.getResultList();
        //Ordenamos según criterio de configuración el listado
        Collections.sort(list, SortListConfig.FRIENDS_COMPARATOR);
        return list;
    }

    @Override
    public void onItemSelected(int position, NavController navController, Context context) {
    }

    @Override
    public void setConstructorParameters(Object... args) {
    }

    @Override
    public void callRepositoryForData() {

        BeersRepository.getFriends(apiResponseRepositoryHolder);
    }

    /**
     * Inicializa el cursor
     * @param act
     */
    public void initCursor(Activity act){
        cursorAdapter = new SimpleCursorAdapter(
                act,
                android.R.layout.simple_list_item_1,
                null,
                FROM_COLUMNS, TO_IDS,
                0);
        // Sets the adapter for the ListView
    }

    /**
     * Listener para cuando se seleccioana un contacto en el spinner
     * @param parent
     * @param item
     * @param position
     * @param rowID
     */
    public void onContactSelectedToInsert(AdapterView<?> parent, View item, int position, long rowID) {

            // Obtiene el cursor
            Cursor cursor = ((SimpleCursorAdapter)parent.getAdapter()).getCursor();
            // Se mueve hasta la posición del contacto
            cursor.moveToPosition(position);
            // Get the _ID value
            long contactId = cursor.getLong(cursor.getColumnIndex(
                    ContactsContract.Contacts._ID));
            String name = cursor.getString(cursor.getColumnIndex(
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
            //Condicion para que no sea el elemento en blanco al principio del spinner
        if (contactId != -1) {
            //Si tiene teléfono apuntamos cursor a datos del contacto para obtener el teléfono
            if (cursor.getInt(cursor.getColumnIndex(
                    ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                Cursor pCur = parent.getContext().getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        new String[]{String.valueOf(contactId)}, null);
                //Es una demo, coge el primero, debería de cogerlos todos o dejar seleccionar uno
                String phoneNumber = "";
                while (pCur.moveToNext()) {
                    String cursorPhoneNumber = pCur.getString(pCur.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER));
                    if (cursorPhoneNumber != null && !"".equals(cursorPhoneNumber)) {
                        phoneNumber = cursorPhoneNumber;
                        break;
                    }

                }

                pCur.close();
                this.isLoading.setValue(true);

                insertFriend(parent.getContext(), new Friend(name, phoneNumber));


            }
        }

    }

    /**
     * inserta un amigo en la fuente de datos que esté definida en el repositorio
     * Si el teléfono ya existía, muestra un alert
     */
    public void insertFriend(Context context, Friend friend){
        Friend friendRecordFromRepository = BeersRepository.getFriendByPhoneNumber(friend);
        if( friendRecordFromRepository == null) {
            BeersRepository.insertFriend(friend, changeEnded);
        }else{
            isLoading.setValue(false);
            String duplicatedFriendName = friendRecordFromRepository.getName() != null ? friendRecordFromRepository.getName() : "";
            ModalMessage.showError(context, String.format(context.getString(R.string.duplicated_contact_error), duplicatedFriendName) , null, null, null, null);
        }
    }


    /**
     * Llama a un amigo
     * @param c
     * @param friend
     */
    @Override
    public void callFriend(Context c, Friend friend) {

        boolean resultOk = ExternalActionsManager.callPhone(friend.getPhoneNumber(), c);
        if(!resultOk){
            error.setValue(new BaseException(c.getString(R.string.phone_error), false));
        }

    }

    /**
     * Elimina el registro de un amigo de la fuente de datos del repositorio
     * @param c
     * @param friend
     */
    @Override
    public void deleteFriend(Context c, Friend friend) {
        ModalMessage.showModalMessage(c, getApplication().getString(R.string.configuration_modal_confirmation_title), getApplication().getString(R.string.configuration_modal_friends_delete), null, (dialog, which) -> {
            BeersRepository.deleteFriend(friend);
            callRepositoryForData();
        }, null, (dialog, which)->{});


    }

    /**
     * Obtiene un cargador de cursor
     * @param act
     * @return
     */
    public CursorLoader getCursorLoader(Activity act){
        return new CursorLoader(
               act,
                ContactsContract.Contacts.CONTENT_URI,
                PROJECTION,
                SELECTION,
                null,
                SORT_BY
        );
    }

    /**
     * Carga los datos del cursor en el adapter
     * @param cursor
     */
    public void loadContactData(Cursor cursor){
        //Agregamos elemento "dummy" al principio del cursor,
        // este es necesario para detectar cuando no hay selección
        MatrixCursor matrixCursor = new MatrixCursor(PROJECTION);
        matrixCursor.addRow(new Object[] { -1,-1, "" , false});
        // Put the result Cursor in the adapter for the ListView

        cursorAdapter.swapCursor( new MergeCursor(new Cursor[]{matrixCursor,cursor}));
    }

    /**
     * Resetea los datos del adapter, eliminando la referencia al cursor
     */
    public void resetContactData(){
        // Delete the reference to the existing Cursor
        cursorAdapter.swapCursor(null);
    }






}
