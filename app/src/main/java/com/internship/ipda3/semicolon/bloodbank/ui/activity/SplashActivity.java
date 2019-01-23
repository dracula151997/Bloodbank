package com.internship.ipda3.semicolon.bloodbank.ui.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.internship.ipda3.semicolon.bloodbank.R;
import com.internship.ipda3.semicolon.bloodbank.ui.fragment.SplashFragment;
import com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod;
import com.jaeger.library.StatusBarUtil;

public class SplashActivity extends AppCompatActivity {
    private Context mContext = SplashActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        StatusBarUtil.setTransparent(this);

        HelperMethod.commitFragment(R.id.frame, new SplashFragment(), getSupportFragmentManager());





    }
}
