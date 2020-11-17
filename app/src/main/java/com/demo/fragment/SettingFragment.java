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
import android.widget.EditText;
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

    private Button btnEdit;
    private ImageView image;
    private TextView txtLogout, txtAbout;
    private EditText editName, editBirthday, editAddress, editEmail, editPhone;
    private List<User> list = new ArrayList();
    UserDatabase Userdb;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_fragment, container, false);

        editName = view.findViewById(R.id.editName);
        editAddress = view.findViewById(R.id.editAddress);
        editBirthday = view.findViewById(R.id.editBirthday);
        editPhone = view.findViewById(R.id.editPhone);
        editEmail = view.findViewById(R.id.editEmail);
        txtLogout = view.findViewById(R.id.txtLogout);
        txtAbout = view.findViewById(R.id.txtAbout);
        Userdb = new UserDatabase(getActivity());

        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UpdateFragment.logout();
                gotoLogin();
            }
        });
        return view;

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
