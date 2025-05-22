package com.example.tuliphotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    EditText signupName, signupPhoneNumber, signupEmail, signupPassword;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupName = findViewById(R.id.signup_et_name);
        signupPhoneNumber = findViewById(R.id.signup_et_phone);
        signupEmail = findViewById(R.id.signup_et_email);
        signupPassword = findViewById(R.id.signup_et_password);
        signupButton = findViewById(R.id.signup_btn_signup);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String name = signupName.getText().toString();
                String phone = signupPhoneNumber.getText().toString();
                String email = signupEmail.getText().toString();
                String password = signupPassword.getText().toString();

                if (validateInput(name, phone, email, password)) {
                    HelperClass helperClass = new HelperClass(name, phone, email, password);
                    reference.child(name).setValue(helperClass);

                    Toast.makeText(SignupActivity.this, "You have signed up successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateInput(String name, String phone, String email, String password) {
        if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        // Add more validation if needed (e.g., email format, password strength)
        return true;
    }
}
