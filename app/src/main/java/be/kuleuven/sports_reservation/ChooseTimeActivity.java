package be.kuleuven.sports_reservation;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChooseTimeActivity extends AppCompatActivity {



    private TextView txtType;
    private static String searchType;
    RecyclerView recyclerView;

    List<TimeSlot> timeSlotList;

    private static final String checkByCourtName_URL = "https://studev.groept.be/api/a21pt101/checkByCourtName/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_time);

        txtType = findViewById(R.id.txtTimeSlot);

        if(!(getIntent().getExtras() == null))
        {
            Bundle extras = getIntent().getExtras();
            searchType = (String) extras.get("type");
            txtType.setText(searchType);
        }
        else
        {
            txtType.setText(searchType);
        }

        timeSlotList = new ArrayList<>();

        recyclerView = findViewById(R.id.myCalendar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadTimeSlot();

    }

    private void loadTimeSlot(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, checkByCourtName_URL + searchType, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray timeSlots = new JSONArray(response);

                    Calendar calendar = Calendar.getInstance();
//                    int year = calendar.get(Calendar.YEAR);
//                    int month = calendar.get(Calendar.MONTH)+1;
//                    int day = calendar.get(Calendar.DAY_OF_MONTH);
//                    int minute = calendar.get(Calendar.MINUTE);
//                    int second = calendar.get(Calendar.SECOND);
                    int hour = calendar.get(Calendar.HOUR_OF_DAY);



                    for (int i = 0; i <timeSlots.length() ; i++) {
                        JSONObject o = timeSlots.getJSONObject(i);


                        String beginTime = o.getString("beginTime");
                        String endTime = o.getString("endTime");
                        String courtName = o.getString("courtName");
                        String image = o.getString("image");

                        String strHour = endTime.substring(0,2);
                        int intHour = Integer.parseInt(strHour);

                        if(intHour > hour){
                            TimeSlot timeSlot = new TimeSlot(beginTime, endTime, courtName, image);
                            timeSlotList.add(timeSlot);
                        }

                    }

                    MyAdapter adapter = new MyAdapter(ChooseTimeActivity.this, timeSlotList);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ChooseTimeActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    public static String getSearchType() {
        return searchType;
    }
}