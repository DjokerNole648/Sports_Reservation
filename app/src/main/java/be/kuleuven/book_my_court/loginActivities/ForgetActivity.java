package be.kuleuven.book_my_court.loginActivities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import be.kuleuven.book_my_court.R;

public class ForgetActivity extends AppCompatActivity {
    private EditText secondPassword;
    private MaterialButton submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //These two lines hide the top bar of the page
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //This line hides the action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        submit = findViewById(R.id.forget_button);
    }

    public void onBtnSubmit_Clicked(View view) {

        EditText username = findViewById(R.id.forget_username);
        EditText password = findViewById(R.id.forget_password);
        EditText email = findViewById(R.id.forget_email);
        EditText password_confirm = findViewById(R.id.forget_confirm_password);
        //This url update the password according to the username
        String findPassword = "https://studev.groept.be/api/a21pt101/updatePassword/";
        String findEmail = "https://studev.groept.be/api/a21pt101/findEmail/";
        String passWord = password.getText().toString().trim();
        String userName = username.getText().toString().trim();
        String emailAddress = email.getText().toString().trim();
        String password_confirmation = password_confirm.getText().toString().trim();
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        Intent intent = new Intent(this, LoginActivity.class);

        if(!userName.isEmpty() && !emailAddress.isEmpty() && !passWord.isEmpty() && !password_confirmation.isEmpty() && password_confirmation.equals(passWord)){
            StringRequest strRequest = new StringRequest(Request.Method.GET, findEmail + userName,
                    response -> {
                        try {
                            JSONArray responseArray = new JSONArray(response);
                            JSONObject curObject = responseArray.getJSONObject(0);
                            String responseString = curObject.getString("email");

                            if (emailAddress.equals(responseString)) {
                                StringRequest stringRequest = new StringRequest(Request.Method.GET, findPassword + hashPassword(passWord)
                                        + "/" + userName,
                                        response_second -> {
                                            finish();
                                            startActivity(intent);
                                        },
                                        error -> Toast.makeText(ForgetActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show()
                                );
                                requestQueue.add(stringRequest);
                            }
                            else {
                                Toast.makeText(ForgetActivity.this, "Note: all the fields are necessary for the reset " +
                                        "and the two passwords should match each other!", Toast.LENGTH_LONG).show();
                            }
                        }
                        catch (JSONException e){
                            Log.e("Database", e.getMessage(), e);
                        }
                    },
                    error -> Toast.makeText(ForgetActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show()
            );
            requestQueue.add(strRequest);
        }
    }

    public static String hashPassword(String password){
        String hashedString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            hashedString = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedString;
    }
}