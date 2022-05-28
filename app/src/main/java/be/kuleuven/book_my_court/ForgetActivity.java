package be.kuleuven.book_my_court;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class ForgetActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private EditText secondPassword;
    private EditText email;
    private MaterialButton submit;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //These two lines hide the top bar of the page
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //This line hides the action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        submit = findViewById(R.id.forget_button);
    }

    public void onBtnSubmit_Clicked(View view){
        username = findViewById(R.id.forget_username);
        password = findViewById(R.id.first_password);
        secondPassword = findViewById(R.id.confirm_password);
        email = findViewById(R.id.forget_email);
        String newPassword;
        String url;
        requestQueue = Volley.newRequestQueue(this);

        Intent intent = new Intent(this, LoginActivity.class);

        if(password.getText().toString().equals(secondPassword.getText().toString())){
            newPassword = secondPassword.getText().toString();
        }
        else {
            Toast.makeText(this, "Sorry the passwords do not match each other.", Toast.LENGTH_SHORT).show();
        }

        startActivity(intent);
    }
}