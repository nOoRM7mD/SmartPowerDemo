package com.example.lenovo.smartpowerdemo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.smartpowerdemo.model.ManagedContext;


public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    private static ManagedContext managedContext;
    EditText userName;
    EditText password;
    FloatingActionButton loginButton;
    CheckBox rememberMe;
    TextView forgot_password;
    SharedPreferences sharedPreferences;
    CheckBox demoCheckBox;
    private SharedPreferencesUtilities sharedPreferencesUtilities;
    private String blockCharacterSet = "$~";
    public Boolean demoFlag = false;

    public static void setManagedContext(ManagedContext managedContext) {
        LogInActivity.managedContext = managedContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        userName = (EditText) findViewById(R.id.login_username);
        password = (EditText) findViewById(R.id.login_password);
        loginButton = (FloatingActionButton) findViewById(R.id.login_button);
        rememberMe = (CheckBox) findViewById(R.id.rememberMe);
        forgot_password = (TextView) findViewById(R.id.forgot_password);
        demoCheckBox = (CheckBox) findViewById(R.id.demo_checkbox);

        //Shared preference initialize
        sharedPreferences = getSharedPreferences(ManagedContext.SHARED_PREFRENECES_NAME, MODE_PRIVATE);
        sharedPreferencesUtilities = new SharedPreferencesUtilities(getApplicationContext());

        demoCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                demoFlag = demoCheckBox.isChecked();
                sharedPreferencesUtilities.saveDemoModeFlag(demoCheckBox.isChecked());

            }
        });
        loginButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red_action_bar)));

        try {
            demoFlag = sharedPreferencesUtilities.getDemoModeFlag();

        } catch (Exception e) {

        }

        demoCheckBox.setChecked(sharedPreferencesUtilities.getDemoModeFlag());
        restoreUserName();

        loginButton.setOnClickListener(this);
        forgot_password.setOnClickListener(this);

        rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(ManagedContext.REMEMBER_ME, isChecked);
                editor.apply();
            }
        });
        //Add filter to the Login edit text to prevent ^ $ ~ and set it to max 30 character

        InputFilter filter = new InputFilter() {

            @Override
            public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
                if (charSequence != null && blockCharacterSet.contains(("" + charSequence))) {
                    return "";
                }
                return null;
            }
        };
        userName.setFilters(new InputFilter[]{filter, new InputFilter.LengthFilter(30)});
        password.setFilters(new InputFilter[]{filter, new InputFilter.LengthFilter(30)});


    }

    @Override
    public void onClick(View view) {
        if (view == loginButton) {
            performLogin();
            Intent intent=new Intent(LogInActivity.this,CreateUserActivity.class);
            startActivity(intent);
        }
        if (view == forgot_password) {
            ForgotPasswordUserNameActivity activity = new ForgotPasswordUserNameActivity();
            StartNewActivity(activity);
            finish();
        }

    }

    //start new Activity
    public void StartNewActivity(Activity activity) {
        Intent intent = new Intent(LogInActivity.this, activity.getClass());
        startActivity(intent);

    }

    //when click login button performLogin
    private void performLogin() {
        final String uName = userName.getText().toString();
        final String pass = password.getText().toString();


        if (uName.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "User Name and Password can't be empty", Toast.LENGTH_LONG).show();
            return;
        }
          /* Check if it is the first login and if yes then go to security
                    questions else go to main activity*/
        storeUserName(uName, pass);
        Toast.makeText(this, "login Success", Toast.LENGTH_SHORT).show();
    }

    //method to store user name and password in shared preference
    private void storeUserName(String uName, String pass/*, int rol*/) {
        //Store the First Login attempt
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ManagedContext.SHARED_PREFRENECES_USER_NAME, uName);
        editor.putString(ManagedContext.SHARED_PREFRENECES_PASSWORD, pass);
        editor.apply();
    }

    private void restoreUserName() {

        String uName = sharedPreferences.getString(ManagedContext.SHARED_PREFRENECES_USER_NAME, null);
        String pass = sharedPreferences.getString(ManagedContext.SHARED_PREFRENECES_PASSWORD, null);//"No name defined" is the default value.
        Boolean rememberme = sharedPreferences.getBoolean(ManagedContext.REMEMBER_ME, false);//"No name defined" is the default value.
        if (uName != null) {
            userName.setText(uName);
            if (pass != null)
                password.setText(pass);
        }
        if (rememberme) {
            rememberMe.setChecked(true);
        } else {
            rememberMe.setChecked(false);
        }
    }
}
