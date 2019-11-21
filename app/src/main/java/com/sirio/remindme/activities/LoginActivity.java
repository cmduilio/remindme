package com.sirio.remindme.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.sirio.remindme.R;
import com.sirio.remindme.utils.SharedPreference;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(SharedPreference.isLoggedIn(getApplicationContext())){
            goToMaiActivity();
        }

        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "public_profile");

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken currentAccessToken = loginResult.getAccessToken();
                if(currentAccessToken == null){
                    SharedPreference.clear(getApplicationContext());
                    Toast.makeText(LoginActivity.this,"User logged out", Toast.LENGTH_LONG).show();
                }else{
                    loadUserProfile(currentAccessToken);
                }
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(SharedPreference.isLoggedIn(getApplicationContext())){
            goToMaiActivity();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void loadUserProfile(AccessToken accessToken){
        GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String firstName = object.getString("first_name");
                    String lastName = object.getString("last_name");
                    String email = object.getString("email");
                    String id = object.getString("id");
                    String imageUrl = "https://graph.facebook.com/" + id + "/picture?type=normal";

                    //Saving loggin
                    SharedPreference.setUsername(getApplicationContext(), firstName + " " + lastName);
                    SharedPreference.setId(getApplicationContext(), id);

                    goToMaiActivity();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        Bundle bundle = new Bundle();
        bundle.putString("fields", "first_name,last_name,email,id");
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();
    }

    public void onClickAsGuest(View v){
        goToMaiActivity();
    }

    private void goToMaiActivity(){
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }
}
