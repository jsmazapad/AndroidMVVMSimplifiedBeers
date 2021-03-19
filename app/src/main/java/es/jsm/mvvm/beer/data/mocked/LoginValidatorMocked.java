package es.jsm.mvvm.beer.data.mocked;

import androidx.lifecycle.MutableLiveData;

import es.jsm.mvvm.beer.providers.PreferencesProvider;

/**
 * Simula un servicio mocckeado sin delay
 */
public class LoginValidatorMocked {

    public static void validatePassword(MutableLiveData<Boolean> isValid, String password){
        String validPassword = PreferencesProvider.getPassword();
        if (password!= null  ){
           isValid.setValue(password.equals(validPassword));
        }else{
            isValid.setValue(false);
        }

    }

}
