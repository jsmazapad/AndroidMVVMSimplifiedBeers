package es.jsm.mvvm.beer.ui.publicviews.login;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.core.ui.base.BaseFragment;
import es.jsm.mvvm.beer.databinding.FragmentLoginBinding;

public class LoginFragment extends BaseFragment<FragmentLoginBinding, LoginViewModel> {

    @Override
    protected LoginViewModel getViewModel() {
        return new ViewModelProvider(this).get(LoginViewModel.class);
    }

    @Override
    protected FragmentLoginBinding getDataBinding(@NonNull LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        viewModel.getPasswordIsValid().removeObservers(getViewLifecycleOwner());
        viewModel.resetPasswordValidation();
        viewModel.getPasswordIsValid().observe(getViewLifecycleOwner(), (isValid) -> {
                    viewModel.processLoginResult(getContext(), isValid);
                }
        );
        return v;
    }


}
