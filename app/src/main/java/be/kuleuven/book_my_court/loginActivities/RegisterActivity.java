package be.kuleuven.book_my_court.loginActivities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.regex.Pattern;

import be.kuleuven.book_my_court.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //These two lines hide the top bar of the page
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //This line hides the action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        MaterialButton login_button = (MaterialButton) findViewById(R.id.register_submit_button);
    }

    public void onBtnSubmit_Clicked(View view) {
        EditText username = (EditText) findViewById(R.id.register_username);
        EditText password = (EditText) findViewById(R.id.register_password);
        EditText email = (EditText) findViewById(R.id.register_email);
        
        String userName = username.getText().toString().trim();
        String passWord = password.getText().toString().trim();
        String emailAddress = email.getText().toString().trim();

        String url = "https://studev.groept.be/api/a21pt101/createAccount/";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        Intent intent = new Intent(this, LoginActivity.class);

        if (!userName.isEmpty() && !passWord.isEmpty() && !emailAddress.isEmpty()) {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url + userName + "/" + hashPassword(passWord)
                    + "/" + emailAddress,
                    response -> {
                        finish();
                        startActivity(intent);
                    },
                    error -> Toast.makeText(RegisterActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show()
            );
            requestQueue.add(stringRequest);
        }

        else {
            Toast.makeText(RegisterActivity.this, "Note: all the fields are necessary for the registration " +
                    "and the two passwords should match each other!", Toast.LENGTH_LONG).show();
        }
    }

    public static String hashPassword(String password){
        String hashedString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16)
                        .substring(1));
            }
            hashedString = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedString;
    }
}