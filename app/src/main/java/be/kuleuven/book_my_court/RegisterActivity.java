package be.kuleuven.book_my_court;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.Objects;

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

        EditText username = (EditText) findViewById(R.id.register_username);
        EditText password = (EditText) findViewById(R.id.register_password);
        EditText email = (EditText) findViewById(R.id.register_email);
        MaterialButton login_button = (MaterialButton) findViewById(R.id.register_submit_button);


    }
    //TODO insert new account into database
    public void onBtnSubmit_Clicked(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void onBtnShow_Clicked(View view){

    }

}