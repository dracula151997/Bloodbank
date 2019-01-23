package com.internship.ipda3.semicolon.bloodbank.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Button;

import com.internship.ipda3.semicolon.bloodbank.R;
import com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod;
import com.internship.ipda3.semicolon.bloodbank.ui.fragment.LoginFragment;
import com.jaeger.library.StatusBarUtil;


public class LoginActivity extends AppCompatActivity {
    private ViewGroup mContainer;
    private Button mRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatusBarUtil.setTransparent(this);

        FragmentManager manager = getSupportFragmentManager();
        HelperMethod.commitFragment(R.id.frame, new LoginFragment(), manager);



    }


}
