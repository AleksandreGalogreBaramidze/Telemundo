package com.example.telemundo21;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    public void updateUI(FirebaseUser account){
        if(account != null){
            Toast.makeText(this,"U Signed In successfully",Toast.LENGTH_LONG).show();

        }
        else {
            Toast.makeText(this,"U Didnt signed in",Toast.LENGTH_LONG).show();
        }
    }

    private FirebaseAuth mAuth;
    public void Registration(View View) {
        Intent RegistrationChoiceIntent = new Intent(this, RegViaMail.class);
        startActivity(RegistrationChoiceIntent);
    }
    public void LogIn(View View){
        EditText LogMail = (EditText) findViewById(R.id.logMail);
        EditText LogPass = (EditText) findViewById(R.id.logPass);
        String mail = LogMail.getText().toString();
        String password = LogPass.getText().toString();
        if (LogMail.getText().toString().isEmpty()){
            LogMail.setError("გთხოვთ შეიყვანოთ თქვენი მეილი!");
            LogMail.requestFocus();
            return;
        }
        else if (LogPass.getText().toString().isEmpty()){
            LogPass.setError("გთხოვთ შეიყვანოთ თქვენი პაროლი!");
            LogPass.requestFocus();
            return;
        }
        else{
            mAuth.signInWithEmailAndPassword(mail, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("TAG", "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("TAG", "signInWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }

                            // ...
                        }
                    });
            Intent RegistrationChoiceIntent = new Intent(this, Menu.class);
            startActivity(RegistrationChoiceIntent);

        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

}