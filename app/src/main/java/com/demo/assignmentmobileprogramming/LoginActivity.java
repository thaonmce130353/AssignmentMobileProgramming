package com.demo.assignmentmobileprogramming;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.demo.database.UserDatabase;
import com.demo.fragment.HomeFragment;
import com.demo.object.info.User;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    SignInButton signInButton;
    private List<User> list = new ArrayList();
    UserDatabase Userdb;
    private GoogleApiClient googleApiClient;
    private static final int SIGN_IN = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        Userdb = new UserDatabase(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        signInButton = findViewById(R.id.Sign_In);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, SIGN_IN);
            }
        });



    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                result.get
                GoogleSignInAccount inAccount = result.getSignInAccount();
                intent.putExtra("isExists", isExist(inAccount.getEmail()));
                intent.putExtra("name", inAccount.getDisplayName());
//                intent.putExtra("name", inAccount.get);

                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Login with Google fail", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public boolean isExist(String email)
    {
        list = new ArrayList<>();
        list = Userdb.getAllUser();
        for (User user : list)
        {
            if (user.getGmail().equals(email))
            {
                return true;
            }
        }
        return false;
    }

}