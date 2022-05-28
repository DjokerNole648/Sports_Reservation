package be.kuleuven.book_my_court;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private static String userName;
    private EditText username;
    private EditText password;
    private MaterialButton login_button;
    private MaterialButton forget_button;
    private MaterialButton register_button;
    private CheckBox rememberMe;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //These two lines hide the top bar of the page
        //must before the super onCreate method
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //This line hides the action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_button = (MaterialButton) findViewById(R.id.login_button);
        forget_button = (MaterialButton) findViewById(R.id.forget_button);
        register_button = (MaterialButton) findViewById(R.id.register_button);
        rememberMe = (CheckBox) findViewById(R.id.rememberMe);
    }

    public void onBtnForget_Clicked(View view){
        Intent intent = new Intent(this, ForgetActivity.class);
        startActivity(intent);
    }

    public void onBtnRegister_Clicked(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public static String getUserName() {
        return userName;
    }

    public void onBtnLogin_Clicked(View view) {
        username = (EditText) findViewById(R.id.login_username);
        password = (EditText) findViewById(R.id.login_password);
        userName = username.getText().toString();
        String passWord = password.getText().toString();
        String url = "https://studev.groept.be/api/a21pt101/findPassword/";

        requestQueue = Volley.newRequestQueue(this);

        Intent intent = new Intent(this, FragmentActivity.class);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url + userName, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray responseArray = new JSONArray(response);
                    JSONObject curObject = responseArray.getJSONObject(0);
                    String responseString = curObject.getString("password");

                    if (passWord.equals(responseString)) {
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "The username or password is incorrect. " +
                                "Please verify and try again.", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.e("Database", e.getMessage(), e);
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(stringRequest);
    }
}