package in.nilnet.nilneteducation;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    private String TAG = "LoginActivity";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_fragment);
        TextInputEditText emailOrUsernameField = (TextInputEditText) findViewById(R.id.login_fragment_emailOrUsername);
        TextInputEditText loginPasswordField = (TextInputEditText) findViewById(R.id.login_fragment_loginPassword);
        loginPasswordField.setTransformationMethod(PasswordTransformationMethod.getInstance());
        Button loginButton = (Button) findViewById(R.id.login_fragment_loginButton);
        Button loginUsingGoogleButton = (Button) findViewById(R.id.login_fragment_loginUsingGoogle);
        Button newUserSignUpButton = (Button) findViewById(R.id.login_fragment_newUser);

        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailOrUsernameField.getText().toString().toLowerCase(Locale.ROOT).trim();
                String password = loginPasswordField.getText().toString();
                if(email.isEmpty()) {
                    emailOrUsernameField.setError("Email cannot be empty");
                    return;
                }
                if(password.length() < 6) {
                    loginPasswordField.setError("Password cannot be less than 6 characters");
                    return;
                }

                //manual login with email and password
                mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        newUserSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
                // don't use finish(). Currently it will take back to the login page
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
