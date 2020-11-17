package com.demo.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;


import android.app.Fragment;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.assignmentmobileprogramming.LoginActivity;
import com.demo.assignmentmobileprogramming.MainActivity;
import com.demo.assignmentmobileprogramming.R;
import com.demo.database.UserDatabase;
import com.demo.object.info.User;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SettingFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    private Button btnSignOut;
    private ImageView image;
    private TextView nameV;
    private TextView Email;
    private List<User> list = new ArrayList();
    UserDatabase Userdb;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.setting_fragment, container, false);

        nameV = v.findViewById(R.id.txtNameV2);
        Email = v.findViewById(R.id.txtEmailV2);
        /*birthday = v.findViewById(R.id.txtBirthV2);
        phone = v.findViewById(R.id.txtBirthV3);*/
        /*image = v.findViewById(R.id.imageView);*/
        btnSignOut = v.findViewById(R.id.btnLogout);
        Userdb = new UserDatabase(getActivity());

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UpdateFragment.logout();
                gotoLogin();
            }
        });
        return v;

    }

    public boolean isExist(String email) {
        list = new ArrayList<>();
        list = Userdb.getAllUser();
        for (User user : list) {
            if (user.getGmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private void handlerSigninResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount in = result.getSignInAccount();
            nameV.setText(in.getDisplayName());
            Email.setText(in.getEmail());
            /*birthday.setText(Userdb.);*/

            /* Picasso.get().load(in.getPhotoUrl()).placeholder(R.mipmap.ic_launcher).into(image);*/
        } else {
            gotoLogin();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void gotoLogin() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }


}
