package pt.ipleiria.estg.dei.amsi.fixbyte;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {
    private UserRegisterTask mAuthTask = null;

    private EditText mFirstNameView;
    private EditText mLastNameView;
    private EditText mEmailView;
    private EditText mNifView;
    private EditText mDateOfBirthView;
    private EditText mAddressView;
    private EditText mUsernameView;
    private EditText mPasswordView;

    private DatePickerDialog.OnDateSetListener mDateOfBirthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_register);

        mFirstNameView = (EditText) findViewById(R.id.first_name);
        mLastNameView = (EditText) findViewById(R.id.last_name);
        mEmailView = (EditText) findViewById(R.id.email);
        mNifView = (EditText) findViewById(R.id.nif);
        mDateOfBirthView = (EditText) findViewById(R.id.dateOfBirth);

        mDateOfBirthView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        RegisterActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateOfBirthListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateOfBirthListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date =  day + "/" + month + "/" + year;
                mDateOfBirthView.setText(date);
            }
        };

        mAddressView = (EditText) findViewById(R.id.address);
        mUsernameView = (EditText) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptRegister();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignUpButton = (Button) findViewById(R.id.email_sign_up_button);
        mEmailSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });

        Button mCancelButton = (Button) findViewById(R.id.email_cancel_button);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    /**
     * Attempts to register the account specified in the form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual account is created.
     */
    private void attemptRegister()
    {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String firstName = mFirstNameView.getText().toString();
        String lastName = mLastNameView.getText().toString();
        String email = mEmailView.getText().toString();

        int nif = 0;
        try{
            nif = Integer.parseInt(mNifView.getText().toString());
        }catch(NumberFormatException e){
            mNifView.setError("Can not be empty.");

        }
        String dateString = mDateOfBirthView.getText().toString();

        String address = mAddressView.getText().toString();
        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();
        boolean cancel = false;
        View focusView = null;

        //Password Validation
        if (password.trim().isEmpty()){
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }else if (password.length() < 6) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (firstName.length()<3){
            mFirstNameView.setError("First name is to short.");
            focusView = mFirstNameView;
            cancel = true;
        }
        if (lastName.length()<3){
            mLastNameView.setError("Last name is to short.");
            focusView = mLastNameView;
            cancel = true;
        }
        if (address.length()<5){
            mAddressView.setError("Adress is to short.");
            focusView = mAddressView;
            cancel = true;
        }
        if (username.length()<3){
            mUsernameView.setError("Username is to short.");
            focusView = mUsernameView;
            cancel = true;
        }

        try {
            //date comes in DD/MM/YYYY - String
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(dateString); // convert string to date
            sdf.applyPattern("yyyy/MM/dd"); // convert to yyyy/mm/dd

            //convert to origin YYYY-MM-DD
            SimpleDateFormat origin = new SimpleDateFormat("yyyy/MM/dd");
            Date dateOrigin = origin.parse(sdf.format(date)); // convert string to date
            sdf.applyPattern("yyyy-MM-dd");// convert to yyyy-mm-dd
            //sdf.format(dateOrigin) convert date to string

            int age = getAge(sdf.format(dateOrigin)); // send string, get int
            System.out.println("converted age2 " +age);
            if (age<12){
                mDateOfBirthView.setError("You must be that least 12 years old.");
                focusView = mDateOfBirthView;

                Context contexto = getApplicationContext();

                Toast.makeText(contexto, "You must be that least 12 years old.", Toast.LENGTH_SHORT).show();

                focusView = mDateOfBirthView;
                cancel = true;
            }

        } catch (ParseException e) {
            mDateOfBirthView.setError("Something went wrong.");
            Context contexto = getApplicationContext();

            Toast.makeText(contexto, "Something went wrong", Toast.LENGTH_SHORT).show();
            focusView = mDateOfBirthView;
            cancel = true;
        }

        // Email validation
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
        int length = String.valueOf(nif).length();
        if (!(length == 9)){
            mNifView.setError("Only 9 digits");
            cancel = true;
            focusView = mNifView;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mAuthTask = new UserRegisterTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }


    private boolean isEmailValid(String email)
    {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPasswordValid(String password)
    {
        return Patterns.EMAIL_ADDRESS.matcher(password).matches();
    }

    private int getAge(String birthday){
        try{

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(birthday);
            System.out.println(format.format(date));

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.get(Calendar.YEAR);

            return Calendar.getInstance().get(Calendar.YEAR)-calendar.get(Calendar.YEAR);

        } catch (ParseException e) {
            return 0;
        }

    }


    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserRegisterTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            if (success) {
                finish();
                Intent intent = new Intent (getApplication(), HomeActivity.class);
                //intent.putExtra(HomeActivity.DADOS_EMAIL, mEmail);
                startActivity(intent);
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }
}
