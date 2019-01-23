package com.internship.ipda3.semicolon.bloodbank.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.internship.ipda3.semicolon.bloodbank.Constant;
import com.internship.ipda3.semicolon.bloodbank.R;
import com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod;
import com.internship.ipda3.semicolon.bloodbank.ui.fragment.AboutFragment;
import com.internship.ipda3.semicolon.bloodbank.ui.fragment.ContactFragment;
import com.internship.ipda3.semicolon.bloodbank.ui.fragment.FavoriteFragment;
import com.internship.ipda3.semicolon.bloodbank.ui.fragment.InfoSettingFragment;
import com.internship.ipda3.semicolon.bloodbank.ui.fragment.NotificationSettingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);


        if (getIntent() != null) {
            id = getIntent().getStringExtra(Constant.ExtrasId.ID);

        }

        if (id.equals("1")) {
            HelperMethod.commitFragment(R.id.frame, new InfoSettingFragment(), getSupportFragmentManager());
            title.setText("تعديل المعلومات");
        } else if (id.equals("2")) {
            HelperMethod.commitFragment(R.id.frame, new NotificationSettingFragment(), getSupportFragmentManager());
            title.setText("إعدادات الإشعارات");

        } else if (id.equals("3")) {
            HelperMethod.commitFragment(R.id.frame, new FavoriteFragment(), getSupportFragmentManager());
            title.setText("المفضلة");

        } else if (id.equals("6")) {
            HelperMethod.commitFragment(R.id.frame, new ContactFragment(), getSupportFragmentManager());
            title.setText("تواصل معنا");


        } else if (id.equals("7")) {
            HelperMethod.commitFragment(R.id.frame, new AboutFragment(), getSupportFragmentManager());
            title.setText("عن التطبيق");
        }


    }
}
