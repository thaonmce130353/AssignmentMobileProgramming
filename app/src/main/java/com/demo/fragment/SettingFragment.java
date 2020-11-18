package com.demo.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.demo.assignmentmobileprogramming.LoginActivity;
import com.demo.assignmentmobileprogramming.MainActivity;
import com.demo.assignmentmobileprogramming.R;
import com.demo.database.UserDatabase;
import com.demo.object.info.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

import java.util.ArrayList;
import java.util.List;

public class SettingFragment extends Fragment {
    //    private static GoogleApiClient googleApiClient;
//    private GoogleSignInOptions gso;
    private Button btnEdit;
    private ImageView image;
    private TextView txtLogout, txtAbout;
    private RadioButton raMale, raFemale;
    private EditText editName, editBirthday, editAddress, editEmail, editPhone;
    private List<User> list = new ArrayList();
    UserDatabase dbUser;
    User user;
    boolean isEdit = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_fragment, container, false);
//        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
//        googleApiClient = new GoogleApiClient.Builder(getContext()).enableAutoManage((FragmentActivity) getActivity(), (GoogleApiClient.OnConnectionFailedListener) this).
//                addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        editName = view.findViewById(R.id.editName);
        editAddress = view.findViewById(R.id.editAddress);
        editBirthday = view.findViewById(R.id.editBirthday);
        editPhone = view.findViewById(R.id.editPhone);
        editEmail = view.findViewById(R.id.editEmail);
        txtLogout = view.findViewById(R.id.txtLogout);
        txtAbout = view.findViewById(R.id.txtAbout);
        btnEdit = view.findViewById(R.id.btnSave);
        raFemale = view.findViewById(R.id.raFemale);
        raMale = view.findViewById(R.id.raMale);

        dbUser = new UserDatabase(getActivity());
        inactive();

        try {

            user = dbUser.getUserById(MainActivity.userId);

            editName.setText(String.valueOf(user.getFullname()));
            editAddress.setText(String.valueOf(user.getAddress()));
            editBirthday.setText(String.valueOf(user.getBirthday()));
            editPhone.setText(String.valueOf(user.getPhone()));
            editEmail.setText(String.valueOf(user.getGmail()));

            raMale.setChecked(user.isGender());
        } catch (Exception e) {

        }

        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    UpdateFragment.logout();
                } catch (Exception e) {
//                    MainActivity.openHomeFragment();
                }
                gotoLogin();
            }
        });

        txtAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MainActivity.openAboutUsFrament();
                } catch (Exception e) {

                }
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEdit) {
                    try {
                        inactive();
                        isEdit = !isEdit;
                        btnEdit.setText("Edit");
                        dbUser.updateUser(new User(1, "", "", editName.getText().toString(), editBirthday.getText().toString(),
                                raMale.isChecked(), editEmail.getText().toString(), editPhone.getText().toString(), editAddress.getText().toString(), "", true));
                    } catch (Exception e) {

                    }
                } else {
                    active();
                    isEdit = !isEdit;
                    btnEdit.setText("Save");
                }
            }
        });
        return view;

    }

    private void inactive() {
        editName.setEnabled(false);
        editAddress.setEnabled(false);
        editBirthday.setEnabled(false);
        editPhone.setEnabled(false);
        editEmail.setEnabled(false);
        raMale.setEnabled(false);
        raFemale.setEnabled(false);

    }

    private void active() {
        editName.setEnabled(true);
        editAddress.setEnabled(true);
        editBirthday.setEnabled(true);
        editPhone.setEnabled(true);
//        editEmail.setEnabled(true);
        raMale.setEnabled(true);
        raFemale.setEnabled(true);

    }

    private void handlerSigninResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount in = result.getSignInAccount();
        } else {
            gotoLogin();
        }
    }

//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }

    private void gotoLogin() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }

//    public static void logout() {
//        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
//            @Override
//            public void onResult(@NonNull Status status) {
//                if (status.isSuccess()) {
//                    MainActivity.openHomeFragment();
//                } else {
//                }
//            }
//        });
//    }
}
