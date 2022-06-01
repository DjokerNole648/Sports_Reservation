package be.kuleuven.book_my_court;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import be.kuleuven.book_my_court.calendarFragment.CalendarFragment;
import be.kuleuven.book_my_court.homeFragement.HomeFragment;
import be.kuleuven.book_my_court.loginActivities.LoginActivity;
import be.kuleuven.book_my_court.searchFragment.SearchFragment;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.calendar:
                            selectedFragment = new CalendarFragment();
                            break;
                        case R.id.search:
                            selectedFragment = new SearchFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_button, menu);
        return true;
    }

    // Determines if Action bar item was selected. If true then do corresponding action.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            try {
                updateLoginTxt();
            } catch (IOException e) {
                e.printStackTrace();
            }
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateLoginTxt() throws IOException {
        String s = getFilesDir() + "/" + "login";
        BufferedReader reader = new BufferedReader(new FileReader(s));
        String json = "";
        json = getJSONString(reader);
        BufferedWriter output = new BufferedWriter(new FileWriter(s));
        try {
            JSONObject object = new JSONObject(json);
            object.put("username", "");
            output.write(object.toString());
            output.close();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getJSONString(BufferedReader reader) throws IOException {
        String json = "";
        try {
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = reader.readLine();
            }
            json = sb.toString();
        } finally {
            reader.close();
        }
        return json;
    }
}