
package com.example.lenovo.smartpowerdemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.smartpowerdemo.model.ManagedContext;

public class CreateUserActivity extends AppCompatActivity {
    private EditText userNameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private Button createUserBtn;
    private static ManagedContext managedContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        userNameEditText = (EditText) findViewById(R.id.input_name);
        emailEditText = (EditText) findViewById(R.id.input_email);
        phoneEditText = (EditText) findViewById(R.id.input_phone);
        passwordEditText = (EditText) findViewById(R.id.input_password);
        confirmPasswordEditText = (EditText) findViewById(R.id.input_password_confirm);
        createUserBtn = (Button) findViewById(R.id.createUserBtn);

        applyFilter();
        createUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginRunnable.run();
            }
        });

    }

    FCRunnable loginRunnable = new FCRunnable() {
        @Override
        public FCRunnable run() {
            validatePassword();
            validateUserEmail();
            validateUserName();
            validateUserPhone();

            return null;
        }
    };

    public boolean validatePassword() {
        String passwordString = passwordEditText.getText().toString();
        String confirmPasswordString = confirmPasswordEditText.getText().toString();
        boolean userNameValidation=passwordString.matches(".*\\d+.*");
    /*    if(!userNameValidation){
            Toast.makeText(this, "plz enter valid password", Toast.LENGTH_SHORT).show();
        }
*/
        if (passwordString.isEmpty()&&userNameValidation) {
            passwordEditText.setError("Password can't be empty");
            return false;
        } else if (confirmPasswordString.isEmpty()) {
            confirmPasswordEditText.setError("Confirm Password is Empty");
            return false;
        }
        if (passwordString.length() < 3) {
            passwordEditText.setError("You must have 3 characters in your password");
            return false;
        }

        if (passwordString.equals(confirmPasswordString)) {
            return true;
        } else {
            passwordEditText.setError("Password does not match the confirm password");
            return false;
        }

    }

    public boolean validateUserName() {

        String userNameString = userNameEditText.getText().toString();

        if (userNameString.isEmpty() || userNameString.toString().trim().isEmpty()) {
            userNameEditText.setError("user name can't be empty");
            return false;
        } else {
            return true;
        }

    }

    public boolean validateUserEmail() {
        String emailString = emailEditText.getText().toString();

        if (emailString.isEmpty()) {
            emailEditText.setError("Email name can't be empty");
            return true;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailString).matches()) {
            emailEditText.setError("Invalid Email Address");
            return false;
        } else {
            return true;
        }

    }

    public boolean validateUserPhone() {
        String phoneString = phoneEditText.getText().toString();

        if (phoneString.isEmpty()) {
            phoneEditText.setError("Phone number  can't be empty");
            return true;
        }

        if (!Patterns.PHONE.matcher(phoneString).matches()) {
            phoneEditText.setError("Invalid Phone number");
            return false;
        } else {
            return true;
        }

    }

    public void applyFilter() {
        final String blockCharacterSet = "$~";
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source != null && blockCharacterSet.contains(("" + source))) {
                    return "";
                }
                return null;
            }
        };
        userNameEditText.setFilters(new InputFilter[]{filter, new InputFilter.LengthFilter(30)});
        emailEditText.setFilters(new InputFilter[]{filter, new InputFilter.LengthFilter(30)});
        phoneEditText.setFilters(new InputFilter[]{filter, new InputFilter.LengthFilter(30)});
        passwordEditText.setFilters(new InputFilter[]{filter, new InputFilter.LengthFilter(30)});
        confirmPasswordEditText.setFilters(new InputFilter[]{filter, new InputFilter.LengthFilter(30)});
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LogInActivity.setManagedContext(managedContext);
        Intent intent = new Intent(CreateUserActivity.this, LogInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}





