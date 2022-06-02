package be.kuleuven.book_my_court.searchFragment;

import android.os.Bundle;
import android.view.View;
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

    ImageView mainImageView, imageView3;
    TextView txtTime1,txtSign, txtTime2, txtCourtName, txtInfo;

    boolean isBook;
    boolean isCancel;

    String data1, data2, courtName;

    private int numberOfBooking;
    private static int maximumNumber = 3;



    private String book_URL = "https://studev.groept.be/api/a21pt101/addBooking/";
    private String unbook_URL = "https://studev.groept.be/api/a21pt101/cancelBooking/";
    private String countBookings_URL = "https://studev.groept.be/api/a21pt101/countBookingByUser/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_message);

        mainImageView = findViewById(R.id.mainImageView);
        imageView3 = findViewById(R.id.imageView3);
        txtTime1 = findViewById(R.id.txtTime1);
        txtSign = findViewById(R.id.textView3);
        txtTime2 = findViewById(R.id.txtTime2);
        txtCourtName = findViewById(R.id.textViewCourtName);
        txtCourtName.setText(ChooseTimeActivity.getSearchType());
        txtInfo = findViewById(R.id.txtInfo);

        getData();
        getNumberOfBookings(LoginActivity.getUserName());


//        if(getIntent().hasExtra("isBook")){
//            if(isBook){
//                if(numberOfBooking <= 1){
//                    addBooking(LoginActivity.getUserName(), txtCourtName.getText().toString(), txtTime1.getText().toString());//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!传username
//                }
//                else{
//                }
//            }
//            else{
//                cancelBooking(LoginActivity.getUserName(), txtCourtName.getText().toString(), txtTime1.getText().toString());//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!传username
//            }
//        }
//
//        if(getIntent().hasExtra("isCancel")){
//            if(isCancel){
//                cancelBooking(LoginActivity.getUserName(), txtCourtName.getText().toString(), txtTime1.getText().toString());//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!传username
//            }
//        }


    }
    private void getData(){
        Bundle extras = getIntent().getExtras();
        data1 = (String) extras.get("beginTime");
        data2 = (String) extras.get("endTime");
        courtName = (String) extras.get("courtName");

        if(getIntent().hasExtra("isBook")){
            isBook = (boolean) extras.get("isBook");
        }
        if(getIntent().hasExtra("isCancel")){
            isCancel = (boolean) extras.get("isCancel");
        }

    }

    private void setData(){
        if(isBook){
            if(numberOfBooking <= maximumNumber - 1){
                txtCourtName.setVisibility(View.VISIBLE);
                txtTime1.setVisibility(View.VISIBLE);
                txtTime2.setVisibility(View.VISIBLE);
                txtSign.setVisibility(View.VISIBLE);
                imageView3.setVisibility(View.INVISIBLE);
                txtTime1.setText(data1);
                txtTime2.setText(data2);
                txtInfo.setText("Book Succeeded!");
                mainImageView.setImageResource(R.drawable.book_success);
            }
            else{
                txtCourtName.setVisibility(View.INVISIBLE);
                txtTime1.setVisibility(View.INVISIBLE);
                txtTime2.setVisibility(View.INVISIBLE);
                txtSign.setVisibility(View.INVISIBLE);
                imageView3.setVisibility(View.VISIBLE);
                txtInfo.setText("You'd booked more than '" + maximumNumber + "'!");
                mainImageView.setImageResource(R.drawable.too_much1);
            }
        }
        else{
            txtCourtName.setVisibility(View.VISIBLE);
            txtTime1.setVisibility(View.VISIBLE);
            txtTime2.setVisibility(View.VISIBLE);
            txtSign.setVisibility(View.VISIBLE);
            imageView3.setVisibility(View.INVISIBLE);
            txtCourtName.setText(courtName);
            txtInfo.setText("Cancel Booking!");
            txtTime1.setText(data1);
            txtTime2.setText(data2);
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
                    numberOfBooking = Integer.parseInt(number);

                    setData();
                    bookOrCancel();

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


    }


    private void addBooking(String userName, String courtname, String beginTime) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, book_URL + userName + "/" + courtname + "/" + beginTime , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(BookMessageActivity.this, "Book succeeded! NO." + (numberOfBooking + 1), Toast.LENGTH_SHORT).show();

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

    private void bookOrCancel(){
        if(getIntent().hasExtra("isBook")){
            if(isBook){
                if(numberOfBooking <= maximumNumber -1){
                    addBooking(LoginActivity.getUserName(), txtCourtName.getText().toString(), txtTime1.getText().toString());//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!传username
                }
            }
            else{
                cancelBooking(LoginActivity.getUserName(), txtCourtName.getText().toString(), txtTime1.getText().toString());//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!传username
            }
        }

        if(getIntent().hasExtra("isCancel")){
            if(isCancel){
                cancelBooking(LoginActivity.getUserName(), txtCourtName.getText().toString(), txtTime1.getText().toString());//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!传username
            }
        }
    }

    public static int getMaximumNumber() {
        return maximumNumber;
    }

}