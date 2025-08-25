// Russ Savela
// russell.savela@snhu.edu
// August 15, 2025
// CS-360 Event App
//

// Main Activity Class

package com.example.eventapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Toast;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.view.View;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // SMS Permission
    private static final int SMS_PERMISSION_CODE = 100;

    // SMS Auth State

    private boolean SMS_USER_AUTH = false;
    EditText userName, password;
    // Sign In Screen Buttons
    Button signInButton, gotoRegisterAccountButton;

    // Database
    LoginDatabaseUtils loginDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // from activity_login.xml
            // Sign in screen buttons
        signInButton = (Button) findViewById(R.id.buttonSignIn);
        gotoRegisterAccountButton = (Button) findViewById(R.id.buttonGotoRegisterAccount);
            // Sign in screen data fields
        userName = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password = (EditText) findViewById(R.id.editTextTextPassword);

        // login database
        loginDatabase = new LoginDatabaseUtils(this,"login_database", null,1);

        // Get SMS Permission
        getSMSPermission();

        signInButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                String inputPassword = userName.getText().toString();
                                                String inputUserName = password.getText().toString();

                                                // Check that the input matches the database
                                                if (loginDatabase.checkUserCredentials(inputUserName, inputPassword)) {
                                                    // Login successful
                                                    Toast.makeText(MainActivity.this, "Login approved!", Toast.LENGTH_SHORT).show();
                                                    // Go to the tasks screen
                                                    //startActivity();
                                                } else {
                                                    // login failed
                                                    Toast.makeText(MainActivity.this, "Credentials invalid", Toast.LENGTH_SHORT).show();
                                                }
                                            }

    });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signIn), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void getSMSPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    SMS_PERMISSION_CODE);
        }
        else {
            Toast.makeText(this, "Already have SMS permission.", Toast.LENGTH_SHORT).show();
        }
    }

}