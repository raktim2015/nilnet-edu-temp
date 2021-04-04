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


public class SignupActivity extends AppCompatActivity {

    private String TAG = "SignupActivity";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_fragment);

        TextInputEditText signupEmailIdField = (TextInputEditText) findViewById(R.id.signup_fragment_emailId);
        TextInputEditText signupUsernameField = (TextInputEditText) findViewById(R.id.signup_fragment_username);
        TextInputEditText signupPasswordField = (TextInputEditText) findViewById(R.id.signup_fragment_password);
        signupPasswordField.setTransformationMethod(PasswordTransformationMethod.getInstance());

        Button signupButton = (Button) findViewById(R.id.signup_fragment_signupButton);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailId = signupEmailIdField.getText().toString().toLowerCase().trim();
                String username = signupUsernameField.getText().toString().trim();
                String password = signupPasswordField.getText().toString();
                mAuth = FirebaseAuth.getInstance();

                mAuth.createUserWithEmailAndPassword(emailId, password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
