package be.kuleuven.book_my_court.searchFragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import be.kuleuven.book_my_court.R;
import be.kuleuven.book_my_court.loginActivities.LoginActivity;

public class BookMessageActivity extends AppCompatActivity {

    ImageView mainImageView;
    TextView txtTime1, txtTime2, txtCourtName, txtInfo;

    boolean isBook;
    boolean isCancel;

    String data1, data2;

    private int numberOfBookings;
    int number;


    private String book_URL = "https://studev.groept.be/api/a21pt101/addBooking/";
    private String unbook_URL = "https://studev.groept.be/api/a21pt101/cancelBooking/";
    private String countBookings_URL = "https://studev.groept.be/api/a21pt101/countBookingByUser/";
    private String checkBook_URL = "https://studev.groept.be/api/a21pt101/checkBookings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_message);

        mainImageView = findViewById(R.id.mainImageView);
        txtTime1 = findViewById(R.id.txtTime1);
        txtTime2 = findViewById(R.id.txtTime2);
        txtCourtName = findViewById(R.id.textViewCourtName);
        txtCourtName.setText(ChooseTimeActivity.getSearchType());
        txtInfo = findViewById(R.id.txtInfo);


        getNumberOfBookings(LoginActivity.getUserName());
        SystemClock.sleep(1000);

        getData();
        setData();

        if(getIntent().hasExtra("isBook")){
            if(isBook){
                if(getNumberOfBookings() <= 1){
                    addBooking(LoginActivity.getUserName(), txtCourtName.getText().toString(), txtTime1.getText().toString());//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!传username
                }
//                if(number <= 2){
//                    addBooking(LoginActivity.getUserName(), txtCourtName.getText().toString(), txtTime1.getText().toString());//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!传username
//                    number++;
//                }

            }
            else{
                cancelBooking(LoginActivity.getUserName(), txtCourtName.getText().toString(), txtTime1.getText().toString());//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!传username
                number--;
            }
        }

        if(getIntent().hasExtra("isCancel")){
            if(isCancel){
                cancelBooking(LoginActivity.getUserName(), txtCourtName.getText().toString(), txtTime1.getText().toString());//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!传username
            }
        }


    }
    private void getData(){


        Bundle extras = getIntent().getExtras();
        data1 = (String) extras.get("beginTime");
        data2 = (String) extras.get("endTime");

        if(getIntent().hasExtra("isBook")){
            isBook = (boolean) extras.get("isBook");
        }
        if(getIntent().hasExtra("isCancel")){
            isCancel = (boolean) extras.get("isCancel");
        }

    }

    private void setData(){
        txtTime1.setText(data1);
        txtTime2.setText(data2);

        if(isBook){
            if(getNumberOfBookings() <= 1){
                txtInfo.setText("Book Succeeded!");
            }
            else{
                txtInfo.setText("Book exceed maximum number '2'!");
            }

        }
        else{
            txtInfo.setText("Cancel Booking!");
        }

    }

    private void getNumberOfBookings(String userName){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, countBookings_URL + userName, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray counts = new JSONArray(response);


                    JSONObject o = counts.getJSONObject(0);
                    String number = o.getString("numberOfBookings");

                    setNumberOfBookings(Integer.parseInt(number));
                    System.out.println("numberOfBookings: " + getNumberOfBookings());

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BookMessageActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    Volley.newRequestQueue(this).add(stringRequest);

//        StringRequest stringRequest = new StringRequest(Request.Method.GET, checkBook_URL + userName, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONArray counts = new JSONArray(response);
//
//                    numberOfBookings = counts.length();
//                    System.out.println("numberOfBookings: " + numberOfBookings);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(BookMessageActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        Volley.newRequestQueue(this).add(stringRequest);



    }



    private void addBooking(String userName, String courtname, String beginTime) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, book_URL + userName + "/" + courtname + "/" + beginTime , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(BookMessageActivity.this, "Book succeeded." + numberOfBookings, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError c) {
                Toast.makeText(BookMessageActivity.this, "Book failed.", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void cancelBooking(String userName, String courtname, String beginTime) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, unbook_URL + userName + "/" + courtname + "/" + beginTime , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(BookMessageActivity.this, "Cancel booking succeeded.", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError c) {
                Toast.makeText(BookMessageActivity.this, "Cancel booking failed.", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public int getNumberOfBookings() {
        return numberOfBookings;
    }

    public void setNumberOfBookings(int numberOfBookings) {
        this.numberOfBookings = numberOfBookings;
    }
}