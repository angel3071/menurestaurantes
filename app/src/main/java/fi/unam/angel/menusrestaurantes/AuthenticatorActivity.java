package fi.unam.angel.menusrestaurantes;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.mobile.auth.core.SignInStateChangeListener;
import com.amazonaws.mobile.auth.ui.SignInUI;
import com.amazonaws.mobile.client.AWSMobileClient;

public class AuthenticatorActivity extends Activity {

    private static final String LOG_TAG = "abe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticator);

        AWSMobileClient.getInstance().initialize(this).execute();

        // Sign-in listener
        IdentityManager.getDefaultIdentityManager().addSignInStateChangeListener(new SignInStateChangeListener() {
            @Override
            public void onUserSignedIn() {
                Log.d(LOG_TAG, "User Signed In");
            }

            // Sign-out listener
            @Override
            public void onUserSignedOut() {

                Log.d(LOG_TAG, "User Signed Out");
                showSignIn();
            }
        });

        showSignIn();
    }
    private void showSignIn() {

        Log.d(LOG_TAG, "showSignIn");

        SignInUI signin = (SignInUI) AWSMobileClient.getInstance().getClient(AuthenticatorActivity.this, SignInUI.class);
        signin.login(AuthenticatorActivity.this, MenuActivity.class).execute();
    }

}
