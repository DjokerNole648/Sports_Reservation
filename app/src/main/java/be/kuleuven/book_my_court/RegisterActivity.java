package be.kuleuven.book_my_court;

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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText email;
    private MaterialButton login_button;
    private RequestQueue requestQueue;

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
        username = (EditText) findViewById(R.id.register_username);
        password = (EditText) findViewById(R.id.register_password);
        email = (EditText) findViewById(R.id.register_email);
        String userName = username.getText().toString();
        String passWord = password.getText().toString();
        String emailAddress = email.getText().toString();

        String url = "https://studev.groept.be/api/a21pt101/createAccount/";

        requestQueue = Volley.newRequestQueue(this);

        Intent intent = new Intent(this, LoginActivity.class);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url + userName + "/" + passWord
                + "/" + emailAddress, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );

        requestQueue.add(stringRequest);
    }

}