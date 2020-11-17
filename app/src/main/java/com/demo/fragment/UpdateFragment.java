package com.demo.fragment;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

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

public class UpdateFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {
    private static GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;
    private EditText editName, editBirthday, editAddress, editEmail, editPhone;
    private RadioButton raMale, raFemale;
    private Button btnSave, btnCancle;
    UserDatabase dbUser;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.update_profile, container, false);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(getContext()).enableAutoManage((FragmentActivity) getActivity(), (GoogleApiClient.OnConnectionFailedListener) this).
                addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        editName = view.findViewById(R.id.editName);
        editAddress = view.findViewById(R.id.editAddress);
        editBirthday = view.findViewById(R.id.editBirthday);
        editPhone = view.findViewById(R.id.editPhone);
        editEmail = view.findViewById(R.id.editEmail);
        btnSave = view.findViewById(R.id.btnSave);
        dbUser = new UserDatabase(getActivity());
        raMale = view.findViewById(R.id.raMale);
        btnCancle = view.findViewById(R.id.btnCancle);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int id, String username, String password, String fullname,
//                String birthday, boolean gender, String gmail, String phone, String address, String dateJoin, boolean status
                dbUser.updateUser(new User(1, "", "", editName.getText().toString(), editBirthday.getText().toString(),
                        raMale.isChecked(), editEmail.getText().toString(), editPhone.getText().toString(), editAddress.getText().toString(), "", true));
                MainActivity.userId = dbUser.getAllUser().get(dbUser.getAllUser().size() - 1).getId();
                MainActivity.openHomeFragment();

            }
        });

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.openHomeFragment();
            }
        });
        return view;
    }


    private void handlerSigninResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount in = result.getSignInAccount();

            editName.setText(in.getDisplayName());
            editEmail.setText(in.getEmail());
            editEmail.setEnabled(false);
            User u = dbUser.getUserById(MainActivity.userId);
            if(u == null)
                dbUser.addUser(new User(editName.getText().toString(), editEmail.getText().toString()));
            /* Picasso.get().load(in.getPhotoUrl()).placeholder(R.mipmap.ic_launcher).into(image);*/
        } else {

        }
    }

    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()) {
            GoogleSignInResult result = opr.get();
            handlerSigninResult(result);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handlerSigninResult(googleSignInResult);
                }
            });
        }
    }

    public static void logout() {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {

                    MainActivity.openHomeFragment();
                } else {


                }
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
