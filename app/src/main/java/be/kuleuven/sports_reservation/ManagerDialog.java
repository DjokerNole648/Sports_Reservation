package be.kuleuven.sports_reservation;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ManagerDialog extends DialogFragment {

    private static final String TAG = "ManagerDialog";

    private Button btnManager;

    private String createTimeSlot_URL = "https://studev.groept.be/api/a21pt101/addTimeByCourtName/";
    private String truncate_URL ="https://studev.groept.be/api/a21pt101/truncateTimeSlotCourt";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_manager, container, false);
        btnManager = view.findViewById(R.id.btnNewTimeSlot);


        btnManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Closing dialog");
                getDialog().dismiss();

                //todo 判断code是否正确

                //todo DB
                // 1.courts补全
                String[] courts = new String[]{"Badminton1", "Badminton2"};
                BeforeCreateTimeSlot(courts);

            }
        });

        return view;
    }

    private void BeforeCreateTimeSlot(String[] str) {
        System.out.println("create truncate request");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, truncate_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //todo
                System.out.println("truncate finished");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError c) {
                //todo
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
        System.out.println("send truncate request ");


        SystemClock.sleep(1000);


        for (int i = 0; i < str.length; i++) {
            stringRequest = new StringRequest(Request.Method.GET, createTimeSlot_URL + str[i], new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //todo
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError c) {
                    //todo
                }
            });
            requestQueue.add(stringRequest);
        }

    }




}
