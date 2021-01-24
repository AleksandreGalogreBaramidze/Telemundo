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

public class RegViaMail extends AppCompatActivity {
    public void updateUI(FirebaseUser account){
        if(account != null){
        }
        else {
        }
    }
    private FirebaseAuth mAuth;
    public void RegistrationViaMail (View View){
        EditText RegViaMailName = (EditText) findViewById(R.id.regMailName);
        EditText RegViaMailMail = (EditText) findViewById(R.id.regMailMail);
        EditText RegViaMailPass = (EditText) findViewById(R.id.regMailPass);
        String email = (String) RegViaMailMail.getText().toString();
        String password = (String) RegViaMailMail.getText().toString();
        if (RegViaMailMail.getText().toString().isEmpty()) {
            RegViaMailMail.setError("გთხოვთ შეიყვანოთ თქვენი მეილი");
            RegViaMailMail.requestFocus();
            return;
        }
        else if (RegViaMailPass.getText().toString().isEmpty()){
            RegViaMailPass.setError("გთხოვთ შეიყვანოთ თქვენი პაროლი");
            RegViaMailPass.requestFocus();
            return;
        }
        else if (RegViaMailName.getText().toString().isEmpty()){
            RegViaMailName.setError("გთხოვთ შეიყვანოთ თქვენი სახელი");
            RegViaMailName.requestFocus();
            return;
        }
        else{
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                                Intent RegistrationIntent = new Intent(getApplicationContext(), Menu.class);
                                startActivity(RegistrationIntent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(RegViaMail.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }

                            // ...
                        }
                    });


        }
    }

    public void Back(View View) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_via_mail);
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