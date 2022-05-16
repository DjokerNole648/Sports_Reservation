package be.kuleuven.sports_reservation;

import android.os.Bundle;
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

public class BookMessageActivity extends AppCompatActivity {

    ImageView mainImageView;
    TextView txtTime1, txtTime2, txtCourtName, txtInfo;

    boolean isBook;

    String data1, data2;
    int myImage;

    private String book_URL = "https://studev.groept.be/api/a21pt101/addBooking/";
    private String unbook_URL = "https://studev.groept.be/api/a21pt101/cancelBooking/";

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

        getData();
        setData();

        if(isBook){
            addBooking("Alice", txtCourtName.getText().toString(), txtTime1.getText().toString());//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!传username
        }
        else{
            cancelBooking("Alice", txtCourtName.getText().toString(), txtTime1.getText().toString());//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!传username
        }



//        final Intent localIntent=new Intent(this, ChooseTimeActivity.class);
//        Timer timer=new Timer();
//        TimerTask task=new TimerTask(){
//            @Override
//            public void run(){
//                startActivity(localIntent);
//            }
//        };
//        timer.schedule(task,3000);

    }
    private void getData(){

//        if(getIntent().hasExtra("beginTime") && getIntent().hasExtra("endTime")){
//            data1 = getIntent().getStringExtra("beginTime");
//            data2 = getIntent().getStringExtra("endTime");
//            isBook = getIntent().getBooleanExtra("isBook");
//
////            myImage = getIntent().getIntExtra("myImage", 1);
//        }
//        else{
//            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
//        }

        Bundle extras = getIntent().getExtras();
        data1 = (String) extras.get("beginTime");
        data2 = (String) extras.get("endTime");
        isBook = (boolean) extras.get("isBook");
    }

    private void setData(){
        txtTime1.setText(data1);
        txtTime2.setText(data2);
        if(isBook){
            txtInfo.setText("Book Succeeded!");
        }
        else{
            txtInfo.setText("Cancel Booking!");
        }
//        description.setText(data2);
//        mainImageView.setImageResource(myImage);
    }



    private void addBooking(String userName, String courtname, String beginTime) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, book_URL + userName + "/" + courtname + "/" + beginTime , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(BookMessageActivity.this, "Book succeeded.", Toast.LENGTH_SHORT).show();

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


}