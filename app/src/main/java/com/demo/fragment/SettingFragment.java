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
import com.demo.assignmentmobileprogramming.R;
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

public class SettingFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener  {
    private TextView name ;
    private TextView email;
    private Button btnSignOut;
    private ImageView image ;

    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso ;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=  inflater.inflate(R.layout.setting_fragment, container, false);
        name = v.findViewById(R.id.name);
        email = v.findViewById(R.id.Email);
        image = v.findViewById(R.id.imageView);
        btnSignOut = v.findViewById(R.id.btnLogout);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(getContext()).enableAutoManage((FragmentActivity) getActivity(),this).
                addApi(Auth.GOOGLE_SIGN_IN_API , gso).build();
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        if(status.isSuccess())
                            gotoLogin();
                        else
                            Toast.makeText(getActivity(), "Log out failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return v;
    }
    private void handlerSigninResult(GoogleSignInResult result){
        if(result.isSuccess()){
            GoogleSignInAccount in =  result.getSignInAccount();
            name.setText(in.getDisplayName());
            email.setText(in.getEmail());
            Picasso.get().load(in.getPhotoUrl()).placeholder(R.mipmap.ic_launcher).into(image);
        }else{
            gotoLogin();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
        private void gotoLogin(){
            startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finish();
        }

    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if(opr.isDone()){
            GoogleSignInResult result = opr.get();
            handlerSigninResult(result);
        }else{
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handlerSigninResult(googleSignInResult);
                }
            });
        }
    }
}
