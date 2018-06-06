package com.example.lenovo.smartpowerdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.lenovo.smartpowerdemo.model.ManagedContext;

public class ForgotPasswordUserNameActivity extends AppCompatActivity {
    private static ManagedContext managedContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_user_name);
        Toast.makeText(this, "this a forget password user name activity", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LogInActivity.setManagedContext(managedContext);
        Intent intent = new Intent(ForgotPasswordUserNameActivity.this, LogInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
