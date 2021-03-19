package es.jsm.mvvm.beer.ui.publicviews.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.core.ui.loading.LoadingViewModel;
import es.jsm.mvvm.beer.core.utils.ModalMessage;
import es.jsm.mvvm.beer.repositories.BeersRepository;
import es.jsm.mvvm.beer.ui.privateviews.main.MainActivity;

public class LoginViewModel extends LoadingViewModel  {

    private MutableLiveData<String> password=new MutableLiveData<>();
    private MutableLiveData<Boolean> passwordIsValid=new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public void setPassword(MutableLiveData<String> password) {
        this.password = password;
    }

    public MutableLiveData<Boolean> getPasswordIsValid() {
        return passwordIsValid;
    }


    /**
     * Inicia el proceso de validación del login
     */
    public void processLogin(){
        BeersRepository.validatePassword(passwordIsValid, password.getValue());
    }

    /**
     * Procesa el resultado del login
     * @param context
     * @param resultOk
     */
    public void processLoginResult(Context context, boolean resultOk){
        if(!resultOk){
            ModalMessage.showError(context, context.getString(R.string.validate_password_ko_error), null, null, null, null);
        }else{
            Intent i = new Intent(context, MainActivity.class);
            context.startActivity(i);
        }
    }


    /**
     * Resetea el viewmodel de validación de password, necesario para controlar el caso de uso de deslogarse y cambiar orientación de pantalla
     */
    public void resetPasswordValidation() {
        passwordIsValid = new MutableLiveData<>();
    }
}
