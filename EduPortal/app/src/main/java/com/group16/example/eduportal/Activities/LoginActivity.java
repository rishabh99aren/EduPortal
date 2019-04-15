package com.group16.example.eduportal.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.group16.example.eduportal.R;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    EditText pwd_edit;
    EditText mail_edit;
    TextView sign;
    EditText mail_d;
    EditText pwd_d;
    EditText name_d;
    EditText pwd_con_d;
    Button sign_d;
    Button all_btn;
    Button ver_btn;
    Button google_btn;
    LinearLayout layout;
    static SQLiteDatabase database;
    ProgressBar pb;
    TextView pwd_check_view;
    TextView signupBtn;
    ImageView view;
    ImageView view_con;
    private static int RC_SIGN_IN = 100;
    static GoogleSignInAccount account;
    int Total;
    public static GoogleSignInClient mGoogleSignInClient;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    Intent intent;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    static String mail_user;
    public static int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Logging in...");
        progressDialog.setMessage("It will take few seconds!!");
        intent=new Intent(LoginActivity.this,MainActivity.class);
        signupBtn= (TextView)findViewById(R.id.signup);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null)
        {
            startActivity(intent);
            finish();
        }
        google_btn=(Button)findViewById(R.id.google);
        google_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    signInWithGoogle();
            }
        });
        //gsign in part over

    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            if (account != null) {
                startActivity(intent);
                finish();
            }
            // Signed in successfully, show authenticated UI.

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.w("Api exception",e.getStatusCode()+"");
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void signInWithGoogle(){

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
//the ac
    }

}











//
//        all_btn=(Button)findViewById(R.id.all);
//        mail_edit=(EditText)findViewById(R.id.mail1);
//        pwd_edit=(EditText) findViewById(R.id.pwd1);
//        sign=(TextView)findViewById(R.id.signup);
//        ImageView pwd_login = (ImageView) findViewById(R.id.show_pwd_login);
//
//        final Dialog dialogs = new Dialog(Login.this);
//
//        dialogs.setContentView(R.layout.dialog_signup);
//        dialogs.setTitle("Create an account");
//
//        mail_d= (EditText) dialogs.findViewById(R.id.mail_dialog);
//        name_d= (EditText) dialogs.findViewById(R.id.name_dialog);
//        pwd_d= (EditText) dialogs.findViewById(R.id.pwd_dialog);
//        pwd_con_d= (EditText) dialogs.findViewById(R.id.pwd_con_dialog);
//        sign_d= (Button) dialogs.findViewById(R.id.signup_dialog);
//        layout=(LinearLayout)dialogs.findViewById(R.id.lays);
//        pwd_check_view=(TextView)dialogs.findViewById(R.id.pwd_view);
//        view = (ImageView) dialogs.findViewById(R.id.view_pwd);
//        view_con = (ImageView) dialogs.findViewById(R.id.view_con_pwd);
//
//
//
//
//        pb=(ProgressBar)dialogs.findViewById(R.id.pbar);
//        ver_btn=(Button)findViewById(R.id.versatile1);
//        pwd_d.setText(null);
//        pwd_con_d.setText(null);
//        mail_d.setText(null);
//        name_d.setText(null);
//
//        google_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (logged_in_from_facebook)
//                    Toast.makeText(Login.this, "You are already logged in through facebook", Toast.LENGTH_SHORT).show();
//                else
//                    signInWithGoogle();
//            }
//        });
//        all_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Login.this, ListDatabase.class));
//            }
//        });
//        ver_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                    String passdb=pwd_edit.getText().toString();
//                    Cursor log=readFromDB();
//                if(log!=null && log.getCount()>0) {
//                    if (passdb.equals(log.getString(0))){
//                        Toast.makeText(Login.this, "Login succesful", Toast.LENGTH_SHORT).show();
//                        userId = log.getInt(1);
//                        logged_in_from_app = true;
//                        mail_user = mail_edit.getText().toString();
//                        SharedPreferences sspref = getSharedPreferences("wingss", MODE_PRIVATE);
//                        sspref.edit().putBoolean("isLoggedIn", true).apply();
//                        startActivity(intent);
//                        finish();
//
//                    } else {
//                        pwd_edit.setText("");
//                        Toast.makeText(Login.this, "Please enter the right password", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                else
//                {
//                    Toast.makeText(Login.this, "No such username exists", Toast.LENGTH_SHORT).show();
//                }
//
//                }
//
//        });
//
//
//        pwd_login.setOnTouchListener(new View.OnTouchListener() {
//            public boolean onTouch(View v, MotionEvent event) {
//
//                switch (event.getAction()) {
//
//                    case MotionEvent.ACTION_UP:
//                        pwd_edit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                        break;
//
//                    case MotionEvent.ACTION_DOWN:
//                        pwd_edit.setInputType(InputType.TYPE_CLASS_TEXT);
//
//                        break;
//
//                }
//                return true;
//            }
//        });
//
//        view.setOnTouchListener(new View.OnTouchListener() {
//            public boolean onTouch(View v, MotionEvent event) {
//
//                switch (event.getAction()) {
//
//                    case MotionEvent.ACTION_UP:
//                        pwd_d.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                        break;
//
//                    case MotionEvent.ACTION_DOWN:
//                        pwd_d.setInputType(InputType.TYPE_CLASS_TEXT);
//
//                        break;
//
//                }
//                return true;
//            }
//        });
//        view_con.setOnTouchListener(new View.OnTouchListener() {
//            public boolean onTouch(View v, MotionEvent event) {
//
//                switch (event.getAction()) {
//
//                    case MotionEvent.ACTION_UP:
//                        pwd_con_d.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                        break;
//
//                    case MotionEvent.ACTION_DOWN:
//                        pwd_con_d.setInputType(InputType.TYPE_CLASS_TEXT);
//
//                        break;
//
//                }
//                return true;
//            }
//        });
//
//
//        sign.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                dialogs.show();
//                Window window = dialogs.getWindow();
//
//
//                assert window != null;
//                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//
//            }
//        });
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            sign_d.setBackgroundColor(getResources().getColor(R.color.dull,getTheme()));
//        }
//
//
//
//
//
//
//
//
//        name_d.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//                if (name_d.getText().toString().length() == 0) {
//                    name_d.setError("This field is mandatory");
//                }
//                else {
//                    if(name_d.getText().toString().charAt(0)!=32) {
//                        if (pwd_d.getText().toString().length() > 0 && mail_d.getText().toString().length() > 0
//                                && name_d.getText().toString().length() > 0 && pwd_con_d.getText().toString().length() > 0) {
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                                sign_d.setBackgroundColor(getResources().getColor(R.color.btn, getTheme()));
//                            }
//                        }
//                    }
//                    else
//                    {
//                        name_d.setError("Invalid username");
//                    }
//                }
//            }
//        });
//        mail_d.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//                if (mail_d.getText().toString().length() == 0) {
//                    mail_d.setError("This field is mandatory");
//
//                }
//                else {
//
//                    if(!validate(mail_d.getText().toString())) {
//                        mail_d.setError("mail is not in proper format");
//                    }
//
//
//                }
//                if(pwd_d.getText().toString().length()>0 && mail_d.getText().toString().length()>0
//                        && name_d.getText().toString().length()>0 && pwd_con_d.getText().toString().length()>0) {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                        sign_d.setBackgroundColor(getResources().getColor(R.color.btn,getTheme()));
//                    }
//                }
//            }
//        });
//        pwd_d.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                check_pwd(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//                if (pwd_d.getText().toString().length() == 0) {
//                    pwd_d.setError("This field is mandatory");
//                }
//                if(pwd_d.getText().toString().length()>0 && mail_d.getText().toString().length()>0
//                        && name_d.getText().toString().length()>0 && pwd_con_d.getText().toString().length()>0) {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                        sign_d.setBackgroundColor(getResources().getColor(R.color.btn,getTheme()));
//                    }
//                }
//            }
//        });
//        pwd_con_d.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//                if (pwd_con_d.getText().toString().length() == 0) {
//                    pwd_con_d.setError("This field is mandatory");
//                }
//                if(pwd_d.getText().toString().length()>0 && mail_d.getText().toString().length()>0
//                        && name_d.getText().toString().length()>0 && pwd_con_d.getText().toString().length()>0) {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                        sign_d.setBackgroundColor(getResources().getColor(R.color.btn,getTheme()));
//                    }
//                }
//            }
//        });
//
//
//        sign_d.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               char mail_first= mail_d.getText().toString().charAt(0);
//                char name_first= mail_d.getText().toString().charAt(0);
//
//
//
//                if(pwd_d.getText().toString().length()>0
//                        && mail_d.getText().toString().length()>0
//                        && mail_first!=32 && name_first!=32
//                        && name_d.getText().toString().length()>0
//                        && pwd_con_d.getText().toString().length()>0) {
//                   if(check()) {
//                       if (pwd_d.getText().toString().equals(pwd_con_d.getText().toString())) {
//                           if (Total >= 56) {
//                                long row = saveToDB();
//                               Toast.makeText(Login.this, "Successfully signed up as user " + row, Toast.LENGTH_LONG).show();
//                               logged_in_from_app = true;
//                                startActivity(intent);
//                                finish();
//                            }
//                            else
//                            {
//                                pwd_d.setError("password not strong enough");
//                                pwd_con_d.setError("password not strong enough");
//                            }
//
//
//
//                        } else {
//                            Toast.makeText(Login.this, "typed passwords do not match ", Toast.LENGTH_SHORT).show();
//                            pwd_d.setError("Typed Passwords do not match");
//                            pwd_con_d.setError("Typed Passwords do not match");
//
//                        }
//                    }
//                   else {
//                        mail_d.setError("The email-address is already been taken");
//                    }
//                }
//                else
//                {
//                    mail_d.setError("Invalid mail");
//                    name_d.setError("Invalid name");
//                }
//
//
//
//            }
//
//        });
//
//    }
////
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        finish();
//    }
//
//
//    private long saveToDB() {
//        SQLiteDatabase database = new Dbhelper(this).getWritableDatabase();
//        mail_user = mail_d.getText().toString();
//        ContentValues values = new ContentValues();
//        values.put(Dbcontract.Dbentry.COLUMN_NAME, name_d.getText().toString());
//        values.put(Dbcontract.Dbentry.COLUMN_PWD, pwd_d.getText().toString());
//        values.put(Dbcontract.Dbentry.COLUMN_MAIL, mail_d.getText().toString());
//
//        return database.insert(Dbcontract.Dbentry.TABLE_NAME, null, values);
//
//    }
//    private Cursor readFromDB() {
//        String mail = mail_edit.getText().toString();
//
//
//
//
//        String[] projection = {
//
//                Dbcontract.Dbentry.COLUMN_PWD,
//                Dbcontract.Dbentry.COLUMN_ID
//
//
//        };
//
//        String selection =
//                Dbcontract.Dbentry.COLUMN_MAIL + " like ? " ;
//        String[] selectionArgs = { mail};
//        Cursor cursor;
//         cursor = database.query(
//                Dbcontract.Dbentry.TABLE_NAME,
//                projection,
//                selection,
//                selectionArgs,
//                null,
//                null,
//                null
//        );
//
//        cursor.moveToFirst();
//
////        cursor.close();
//            return cursor;
//
//
//
//    }
//    private boolean check() {
//        String mail_id=mail_d.getText().toString();
//        String[] args={mail_id};
//        Cursor curse=database.query(Dbcontract.Dbentry.TABLE_NAME,null,Dbcontract.Dbentry.COLUMN_MAIL + " like ? ",args,null,null,null);
//        return curse.getCount() <= 0;
//    }
//    public static boolean validate(String emailStr) {
//        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
//        return matcher.find();
//    }
//
//    @SuppressLint("SetTextI18n")
//    public void check_pwd(String s) {
//
//
//        int length, uppercase = 0, lowercase = 0, digits = 0, symbols = 0, bonus = 0, requirements = 0;
//
//        int lettersonly = 0, numbersonly = 0, cuc = 0, clc = 0;
//
//        length = s.length();
//        for (int i = 0; i < s.length(); i++) {
//            if (Character.isUpperCase(s.charAt(i)))
//                uppercase++;
//            else if (Character.isLowerCase(s.charAt(i)))
//                lowercase++;
//            else if (Character.isDigit(s.charAt(i)))
//                digits++;
//
//            symbols = length - uppercase - lowercase - digits;
//
//        }
//
//        for (int j = 1; j < s.length() - 1; j++) {
//
//            if (Character.isDigit(s.charAt(j)))
//                bonus++;
//
//        }
//
//        for (int k = 0; k < s.length(); k++) {
//
//            if (Character.isUpperCase(s.charAt(k))) {
//                k++;
//
//                if (k < s.length()) {
//
//                    if (Character.isUpperCase(s.charAt(k))) {
//
//                        cuc++;
//                        k--;
//
//                    }
//
//                }
//
//            }
//
//        }
//
//        for (int l = 0; l < s.length(); l++) {
//
//            if (Character.isLowerCase(s.charAt(l))) {
//                l++;
//
//                if (l < s.length()) {
//
//                    if (Character.isLowerCase(s.charAt(l))) {
//
//                        clc++;
//                        l--;
//
//                    }
//
//                }
//
//            }
//
//        }
//
//        System.out.println("length" + length);
//        System.out.println("uppercase" + uppercase);
//        System.out.println("lowercase" + lowercase);
//        System.out.println("digits" + digits);
//        System.out.println("symbols" + symbols);
//        System.out.println("bonus" + bonus);
//        System.out.println("cuc" + cuc);
//        System.out.println("clc" + clc);
//
//        if (length > 7) {
//            requirements++;
//        }
//
//        if (uppercase > 0) {
//            requirements++;
//        }
//
//        if (lowercase > 0) {
//            requirements++;
//        }
//
//        if (digits > 0) {
//            requirements++;
//        }
//
//        if (symbols > 0) {
//            requirements++;
//        }
//
//        if (bonus > 0) {
//            requirements++;
//        }
//
//        if (digits == 0 && symbols == 0) {
//            lettersonly = 1;
//        }
//
//        if (lowercase == 0 && uppercase == 0 && symbols == 0) {
//            numbersonly = 1;
//        }
//
//         Total = (length * 4) + ((length - uppercase) * 2)
//                + ((length - lowercase) * 2) + (digits * 4) + (symbols * 6)
//                + (bonus * 2) + (requirements * 2) - (lettersonly * length * 2)
//                - (numbersonly * length * 3) - (cuc * 2) - (clc * 2);
//
//        System.out.println("Total" + Total);
//
//        if (Total < 30) {
//            pb.setProgress(Total - 15);
//            pwd_check_view.setText("Very Weak");
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                pwd_check_view.setTextColor(getResources().getColor(R.color.error,getTheme()));
//            }
//        } else if (Total >= 40 && Total < 50) {
//            pb.setProgress(Total - 20);
//            pwd_check_view.setText(" Weak");
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                pwd_check_view.setTextColor(getResources().getColor(R.color.colorAccent,getTheme()));
//            }
//        } else if (Total >= 56 && Total < 70) {
//            pb.setProgress(Total - 25);
//            pwd_check_view.setText("Strong");
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                pwd_check_view.setTextColor(getResources().getColor(R.color.btn,getTheme()));
//            }
//        } else if (Total >= 76) {
//            pb.setProgress(Total - 30);
//            pwd_check_view.setText("Very Strong");
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                pwd_check_view.setTextColor(getResources().getColor(R.color.colorPrimary,getTheme()));
//            }
//        } else {
//            pb.setProgress(Total - 20);
//            pwd_check_view.setText(" ");
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                pwd_check_view.setTextColor(getResources().getColor(R.color.colorPrimaryDark,getTheme()));
//            }
//        }
//    }
//
//
//
//
////
//
////
////
//
////}
//
