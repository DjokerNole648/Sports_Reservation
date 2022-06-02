package be.kuleuven.book_my_court.searchFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import be.kuleuven.book_my_court.R;
import be.kuleuven.book_my_court.TimeSlot;
import be.kuleuven.book_my_court.loginActivities.LoginActivity;

//RecyclerView.ViewHolder: holds the view (UI elements)
//RecyclerView.Adapter: binds the date to the viewHolder
//myAdapter is the adapter for recycler view
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List<TimeSlot> timeSlotList;
    private int numberOfBooking;

    private String countBookings_URL = "https://studev.groept.be/api/a21pt101/countBookingByUser/";


    public MyAdapter(Context mCtx, List<TimeSlot> timeSlotList) {
        this.context = mCtx;
        this.timeSlotList = timeSlotList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context); //Instantiates a layout XML file into its corresponding View objects.
        View view = inflater.inflate(R.layout.my_row, parent,false);//把my_row.xml初始化成相应的view objects
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TimeSlot timeSlot = timeSlotList.get(position);

        //给my_row里面的组件设值
        holder.txtBeginTime.setText(timeSlot.getBeginTime());
        holder.txtEndTime.setText(timeSlot.getEndTime());


        Glide.with(context)
                .load(timeSlot.getImage())
                .into(holder.imageView);

        holder.btnUnbook.setEnabled(false);

        //btnBook listener
        holder.btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.GET, countBookings_URL + LoginActivity.getUserName(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray counts = new JSONArray(response);
                            JSONObject o = counts.getJSONObject(0);
                            String number = o.getString("numberOfBookings");
                            numberOfBooking = Integer.parseInt(number);
                            System.out.println("numberOfBookings: " + numberOfBooking);

                            Intent intent = new Intent(context, BookMessageActivity.class);
                            intent.putExtra("isBook", holder.btnBook.isEnabled());
                            intent.putExtra("beginTime", timeSlot.getBeginTime());
                            intent.putExtra("endTime", timeSlot.getEndTime());
                            context.startActivity(intent);

                            if(numberOfBooking <= BookMessageActivity.getMaximumNumber() - 1){  //because to the response delay, the numberOfBooking is 1 smaller than the exact one.

                                holder.btnUnbook.setEnabled(true);
                                holder.btnBook.setEnabled(false);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(view.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
                Volley.newRequestQueue(view.getContext()).add(stringRequest);



//                if(numberOfBooking <= maximumNumber - 1){  //because to the response delay, the numberOfBooking is 1 smaller than the exact one.
//                    System.out.println("numberOfBookings: " + numberOfBooking);
//                    System.out.println("maximum: " + maximumNumber);
//
//                    Intent intent = new Intent(context, BookMessageActivity.class);
//                    intent.putExtra("isBook", holder.btnBook.isEnabled());
//                    intent.putExtra("beginTime", timeSlot.getBeginTime());
//                    intent.putExtra("endTime", timeSlot.getEndTime());
//                    context.startActivity(intent);
//
//                    holder.btnUnbook.setEnabled(true);
//                    holder.btnBook.setEnabled(false);
//                }

            }
        });

        holder.btnUnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(context, BookMessageActivity.class);
                intent.putExtra("isBook", holder.btnBook.isEnabled());
                intent.putExtra("beginTime", timeSlot.getBeginTime());
                intent.putExtra("endTime", timeSlot.getEndTime());
                context.startActivity(intent);

                holder.btnUnbook.setEnabled(false);
                holder.btnBook.setEnabled(true);


            }
        });


    }


    @Override
    public int getItemCount() {
        return timeSlotList.size();
    } //MyViewHolder is a inner class in MyAdapter


    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView txtBeginTime, txtEndTime;
        Button btnBook;
        Button btnUnbook;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageCourt);
            txtBeginTime = itemView.findViewById(R.id.txtBeginTime);
            txtEndTime = itemView.findViewById(R.id.txtEndTime);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            btnBook = itemView.findViewById(R.id.btnBook);
            btnUnbook = itemView.findViewById(R.id.btnUnbook);
        }
    }

}
