package com.demo.assignmentmobileprogramming;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.demo.fragment.AboutUsFragment;
import com.demo.fragment.HistoryFragment;
import com.demo.fragment.HomeFragment;
import com.demo.fragment.MapsFragment;
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


        try {
            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener());

            Intent intent = getIntent();
            if (!intent.getBooleanExtra("isExists", true)) {
                manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.mainframe, new UpdateFragment());
                transaction.commit();
            } else {
                manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.mainframe, new UpdateFragment());
                transaction.commit();

                manager = getFragmentManager();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.mainframe, new HomeFragment());
                transaction.commit();
            }
        } catch (Exception e){

        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener() {
        return new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                try {
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
                            Uri gmmIntentUri = Uri.parse("geo:10.0120585,105.7310684?z=15");
                            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                            mapIntent.setPackage("com.google.android.apps.maps");
                            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                                startActivity(mapIntent);
                            }
                            break;
                    }

                    transaction.replace(R.id.mainframe, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                } catch (Exception e){

                }
                return true;
            }
        };
    }

    public static void openDetailFragment(int productId) {
        try {
            ProductDetailFragment productDetailFragment = new ProductDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("productId", productId);
            productDetailFragment.setArguments(bundle);
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.mainframe, productDetailFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } catch (Exception e){

        }
    }

    public static void openHistoryFragment() {
        try {
            HistoryFragment historyFragment = new HistoryFragment();

            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.mainframe, historyFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } catch (Exception e){

        }
    }

    public static void openDetailOrderFragment(int orderId) {
        try {
            OrderDetailFragment orderDetailFragment = new OrderDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("orderId", orderId);
            orderDetailFragment.setArguments(bundle);
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.mainframe, orderDetailFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } catch (Exception e){

        }
    }

    public static void openHomeFragment() {
        try {
            HomeFragment homeFragment = new HomeFragment();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.mainframe, homeFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } catch (Exception e){

        }
    }
    public static void openSettingFrament()
    {
        try {
            SettingFragment settingFragment = new SettingFragment();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.mainframe, settingFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } catch (Exception e){

        }
    }

    public static void openAboutUsFrament()
    {
        try {
            AboutUsFragment aboutUsFragment = new AboutUsFragment();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.mainframe, aboutUsFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } catch (Exception e){

        }
    }




}