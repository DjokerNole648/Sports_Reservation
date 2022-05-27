package be.kuleuven.book_my_court;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {

    RecyclerView recyclerView;

    List<TimeSlot> timeSlotList;

    private static final String checkBooking_URL = "https://studev.groept.be/api/a21pt101/checkBookings/";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        timeSlotList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.myCalendar);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        loadMyCalendar();


        return view;
    }

    private void loadMyCalendar() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, checkBooking_URL + "Alice", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray timeSlots = new JSONArray(response);

                    for (int i = 0; i <timeSlots.length() ; i++) {
                        JSONObject o = timeSlots.getJSONObject(i);

                        String beginTime = o.getString("beginTime");
                        String endTime = o.getString("endTime");
                        String courtName = o.getString("courtName");
                        String image = o.getString("image");

                        TimeSlot timeSlot = new TimeSlot(beginTime, endTime, courtName, image);
                        timeSlotList.add(timeSlot);

                    }

                    be.kuleuven.book_my_court.CalendarAdapter adapter = new be.kuleuven.book_my_court.CalendarAdapter(getContext(), timeSlotList);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        Volley.newRequestQueue(getContext()).add(stringRequest);

    }


}
