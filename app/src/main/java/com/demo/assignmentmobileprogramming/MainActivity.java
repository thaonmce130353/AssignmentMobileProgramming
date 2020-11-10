package com.demo.assignmentmobileprogramming;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.demo.fragment.HistoryFragment;
import com.demo.fragment.HomeFragment;
import com.demo.fragment.NotificationFragment;
import com.demo.fragment.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    FragmentManager manager;
    private ActionBar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener());

        manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.mainframe, new HomeFragment());
        transaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener() {
        return new BottomNavigationView.OnNavigationItemSelectedListener() {



            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_shop:
                        fragment = new HomeFragment();
                        break;
                    case R.id.navigation_gifts:
                        fragment = new HistoryFragment();
                        break;
                    case R.id.navigation_cart:
                        fragment = new NotificationFragment();
                        break;
                    case R.id.navigation_profile:
                        fragment = new SettingFragment();
                        break;
                }

                transaction.replace(R.id.mainframe, fragment);
                transaction.commit();
                return true;
            }
        };
    }
}