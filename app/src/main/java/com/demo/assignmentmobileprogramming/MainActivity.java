package com.demo.assignmentmobileprogramming;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.demo.fragment.HistoryFragment;
import com.demo.fragment.HomeFragment;
import com.demo.fragment.NotificationFragment;
import com.demo.fragment.OrderDetailFragment;
import com.demo.fragment.ProductDetailFragment;
import com.demo.fragment.SettingFragment;
import com.demo.fragment.UpdateFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    static FragmentManager manager;
    private ActionBar toolbar;
    public static int userId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener());

        Intent intent = getIntent();
        if (!intent.getBooleanExtra("isExists", false)) {
            manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.mainframe, new UpdateFragment());
            transaction.commit();
        } else {
            manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.mainframe, new HomeFragment());
            transaction.commit();
        }
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
                    case R.id.navigation_map:
                        fragment = new SettingFragment();
                        break;
                }

                transaction.replace(R.id.mainframe, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
                return true;
            }
        };
    }

    public static void openDetailFragment(int productId) {
        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("productId", productId);
        productDetailFragment.setArguments(bundle);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.mainframe, productDetailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void openHistoryFragment() {
        HistoryFragment historyFragment = new HistoryFragment();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.mainframe, historyFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void openDetailOrderFragment(int orderId) {
        OrderDetailFragment orderDetailFragment = new OrderDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("orderId", orderId);
        orderDetailFragment.setArguments(bundle);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.mainframe, orderDetailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}