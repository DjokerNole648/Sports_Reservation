package be.kuleuven.book_my_court.searchFragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import be.kuleuven.book_my_court.R;

//密码是0879107
public class ManagerDialog extends DialogFragment {

    private static final String TAG = "ManagerDialog";
    private EditText code;
    private Button btnSubmit;

    private String createTimeSlot_URL = "https://studev.groept.be/api/a21pt101/addTimeByCourtName/";
    private String truncate_URL ="https://studev.groept.be/api/a21pt101/truncateTimeSlotCourt";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_manager, container, false);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        code = view.findViewById(R.id.code);
        String url = "https://studev.groept.be/api/a21pt101/checkCode";

        String codeInput = hashPassword(code.getText().toString());

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                RequestQueue requestQueue = Volley.newRequestQueue(getContext());

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url ,
                        response -> {
                    try{
                        JSONArray responseArray = new JSONArray(response);
                        JSONObject curObject = responseArray.getJSONObject(0);
                        String responseString = curObject.getString("code");
                        if(codeInput.equals(responseString)){
                            String[] courts = new String[]{"Badminton1", "Badminton2","Badminton3",
                            "Rod Laver Arena", "Court Philippe Chatrier", "Centre Court", "Arthur Ashe Stadium",
                            "Beach Volleyball", "Indoor Hard",
                            "Indoor Basketball", "Outdoor Basketball",
                            "Football1" ,"Football2", "Football3"};
                            BeforeCreateTimeSlot(courts);
                            Toast.makeText(getContext(), "Update time slots successfully!", Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(getContext(), "Please enter a correct code!", Toast.LENGTH_LONG).show();
                    }catch (JSONException e){
                        Log.e("Database", e.getMessage(), e);
                    }
                },

                        error -> Toast.makeText(getContext(), error.getLocalizedMessage(), Toast.LENGTH_LONG).show()
                );
                requestQueue.add(stringRequest);

                SystemClock.sleep(1000);

                Log.d(TAG, "Closing dialog");
                getDialog().dismiss();
            }
            });

        return view;
    }

    public static String hashPassword(String password){
        String hashedString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16)
                        .substring(1));
            }
            hashedString = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedString;
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
