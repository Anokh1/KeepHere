package com.example.keephere;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginRegisterActivity extends AppCompatActivity {

    CardView fittingRoom1;
    CardView fittingRoom2;
    CardView fittingRoom3;
    CardView fittingRoom4;
    CardView fittingRoom5;
    CardView fittingRoom6;

    TextView userName;

    Button btnLogOut;
    Button btnReport;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        fittingRoom1 = findViewById(R.id.fittingRoom1);
        fittingRoom2 = findViewById(R.id.fittingRoom2);
        fittingRoom3 = findViewById(R.id.fittingRoom3);
        fittingRoom4 = findViewById(R.id.fittingRoom4);
        fittingRoom5 = findViewById(R.id.fittingRoom5);
        fittingRoom6 = findViewById(R.id.fittingRoom6);

        userName = findViewById(R.id.userName);

        fittingRoom1.setOnClickListener(new View.OnClickListener() {
            @Override
            // show toast
//            public void onClick(View view) {
//                showToast("Fitting Room 1 Clicked");
//            }
            // from MultiplePages
            public void onClick(View view) {
                Intent intent = new Intent(LoginRegisterActivity.this, FittingRoom1.class);
                startActivity(intent);
            }
        });

        fittingRoom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginRegisterActivity.this, FittingRoom2.class);
                startActivity(intent);
            }
        });

        fittingRoom3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginRegisterActivity.this, FittingRoom3.class);
                startActivity(intent);
            }
        });

        fittingRoom4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginRegisterActivity.this, FittingRoom4.class);
                startActivity(intent);
            }
        });

        fittingRoom5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginRegisterActivity.this, FittingRoom5.class);
                startActivity(intent);
            }
        });

        fittingRoom6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginRegisterActivity.this, FittingRoom6.class);
                startActivity(intent);
            }
        });

        btnLogOut = findViewById(R.id.btnLogout);
        mAuth = FirebaseAuth.getInstance();

        btnLogOut.setOnClickListener(view ->{
            mAuth.signOut();
            startActivity(new Intent(LoginRegisterActivity.this, LoginActivity.class));
        });

        btnReport = findViewById(R.id.btnReport);

        btnReport.setOnClickListener(view -> {
            startActivity(new Intent(LoginRegisterActivity.this, ReportActivity.class ));
        });
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        // What is this for?
        //userName.setText(user.getEmail()); // if i put this here got NullPointerException error

        if (user == null){
            startActivity(new Intent(LoginRegisterActivity.this, LoginActivity.class));
        } else{
            userName.setText(user.getEmail());
        }

        //userName.setText(user.getEmail());
    }
}