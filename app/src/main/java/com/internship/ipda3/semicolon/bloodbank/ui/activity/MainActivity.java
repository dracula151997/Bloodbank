package com.internship.ipda3.semicolon.bloodbank.ui.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.internship.ipda3.semicolon.bloodbank.Constant;
import com.internship.ipda3.semicolon.bloodbank.R;
import com.internship.ipda3.semicolon.bloodbank.adapter.MainPagerAdapter;
import com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.internship.ipda3.semicolon.bloodbank.Constant.SharedPreferenceKeys.UserKeys.REMEMBER_ME;
import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.intent;
import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.readFileFromAsset;
import static com.internship.ipda3.semicolon.bloodbank.util.SharedPreferenceUtil.clearCheckBoxState;
import static com.internship.ipda3.semicolon.bloodbank.util.SharedPreferencesManger.LoadIntegerData;
import static com.internship.ipda3.semicolon.bloodbank.util.SharedPreferencesManger.SaveData;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent(MainActivity.this, BloodRequestActivity.class);
            }
        });



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setupViewPager();
    }

    private void setupViewPager() {
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_notification) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_my_info:
                HelperMethod.intentWithExtra(this, NavigationActivity.class, Constant.ExtrasId.ID, "1");
                break;

            case R.id.nav_notification_settings:
                HelperMethod.intentWithExtra(this, NavigationActivity.class, Constant.ExtrasId.ID, "2");
                break;

            case R.id.nav_favorite:
                HelperMethod.intentWithExtra(this, NavigationActivity.class, Constant.ExtrasId.ID, "3");
                break;


            case R.id.nav_usage_help:
//                HelperMethod.intentWithExtra(this, NavigationActivity.class, Constant.ExtrasId.ID, "5");
                showUsageInstructionDialog();
                break;

            case R.id.nav_contact:
                HelperMethod.intentWithExtra(this, NavigationActivity.class, Constant.ExtrasId.ID, "6");
                break;

            case R.id.nav_about_app:
//                HelperMethod.intentWithExtra(this, NavigationActivity.class, Constant.ExtrasId.ID, "7");
                showUsageInstructionDialog();
                break;

            case R.id.nav_rate_app:
                HelperMethod.intentWithExtra(this, NavigationActivity.class, Constant.ExtrasId.ID, "8");
                break;
            case R.id.nav_log_out:
                SaveData(this, REMEMBER_ME, 0);
                intent(this, LoginActivity.class);

        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showUsageInstructionDialog() {
        String text = readFileFromAsset(this, "usage_instruction");
        final Dialog dialog = new Dialog(this, R.style.AppBaseTheme);
        View layout = LayoutInflater.from(this).inflate(R.layout.custom_full_screen_dialog, null);
        dialog.setContentView(layout);

        TextView usageText = layout.findViewById(R.id.usage_instruction_text_view);
        usageText.setText(text);

        dialog.show();


        Button dismissButton = layout.findViewById(R.id.dismiss_button);
        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });





    }
}
