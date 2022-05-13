package be.kuleuven.sports_reservation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//RecyclerView.ViewHolder: holds the view (UI elements)
//RecyclerView.Adapter: binds the date to the viewHolder
//myAdapter is the adapter for recycler view
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List<be.kuleuven.sports_reservation.TimeSlot> timeSlotList;

    public MyAdapter(Context mCtx, List<be.kuleuven.sports_reservation.TimeSlot> timeSlotList) {
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
        be.kuleuven.sports_reservation.TimeSlot timeSlot = timeSlotList.get(position);

        //给my_row里面的组件设值
        holder.txtBeginTime.setText(timeSlot.getBeginTime());
        holder.txtEndTime.setText(timeSlot.getEndTime());
//        holder.courtName.setText(timeSlot.getCourtName());
        holder.btnUnbook.setEnabled(false);
        //holder.btnUnbook.setVisibility(View.VISIBLE); !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1待处理

//        //btnBook enable
//        if(timeSlot.getCourtName().equals("null")){
//            holder.btnBook.setEnabled(false);
//        }
//        else{
//            holder.btnBook.setEnabled(true);
//        }
        //btnBook listener
        holder.btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(!timeSlot.getCourtName().equals("null")){
//                    Intent intent = new Intent(context, BookMessageActivity.class);
//                    intent.putExtra("time", timeSlot.getBeginTime());
//                    context.startActivity(intent);
//                }
                Intent intent = new Intent(context, BookMessageActivity.class);
                intent.putExtra("beginTime", timeSlot.getBeginTime());
                intent.putExtra("endTime", timeSlot.getEndTime());
                context.startActivity(intent);

//                TimeUnit.SECONDS.sleep(1);//秒 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

                holder.btnUnbook.setEnabled(true);
                holder.btnBook.setEnabled(false);
            }
        });

        holder.btnUnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BookMessageActivity.class);
                intent.putExtra("beginTime", timeSlot.getBeginTime());
                intent.putExtra("endTime", timeSlot.getEndTime());
                context.startActivity(intent);

//                TimeUnit.SECONDS.sleep(1);//秒 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

                holder.btnUnbook.setEnabled(true);
                holder.btnBook.setEnabled(false);
            }
        });

        holder.btnUnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

        TextView txtBeginTime, txtEndTime, courtName;
        //TextView txtAvailability;
        Button btnBook;
        Button btnUnbook;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtBeginTime = itemView.findViewById(R.id.txtBeginTime);
            txtEndTime = itemView.findViewById(R.id.txtEndTime);
//            txtAvailability = itemView.findViewById(R.id.txtAvailability);
//            courtName = itemView.findViewById(R.id.imgSun);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            btnBook = itemView.findViewById(R.id.btnBook);
            btnUnbook = itemView.findViewById(R.id.btnUnbook);
        }
    }


//    private Context context;
//    private String data1[], data2[];
//    int images[];
//
//
//    //constructor
//    public MyAdapter(Context ct, String s1[], String s2[], int images[]){
//        this.context = ct;
//        this.data1 = s1;
//        this.data2 = s2;
//        this.images = images;
//    }
//    @NonNull
//    @Override
//    //return a instance of viewHolder
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context); //Instantiates a layout XML file into its corresponding View objects.
//        View view = inflater.inflate(R.layout.my_row, parent,false);//把my_row.xml初始化成相应的view objects
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    //binds the date to the viewHolder
//    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
//        //给my_row里面的组件设值
//        holder.txtTime.setText(data1[position]);
//        holder.txtAvailability.setText(data2[position]);
//        holder.imgSun.setImageResource(images[position]);
//        holder.btnUnbook.setVisibility(View.INVISIBLE);
//        //holder.btnUnbook.setVisibility(View.VISIBLE); !!!!!!!!!!!!!!!!!!!!待处理
//
//        //btnBook enable
//        if(data2[position].equals("unavailable")){
//            holder.btnBook.setEnabled(false);
//        }
//        else{
//            holder.btnBook.setEnabled(true);
//        }
//
//
//        //btnBook listener
//        holder.btnBook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(data2[position].equals("available")){
//                    Intent intent = new Intent(context, BookMessageActivity.class);
//                    intent.putExtra("time", data1[position]);
//                    context.startActivity(intent);
//
//
//                }
//            }
//        });
//
//    }
//
//    @Override
//    //return the size of the list
//    public int getItemCount() {//pass number of items in array
//        return images.length;
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder{
//
//        TextView txtTime, txtAvailability;
//        ImageView imgSun;
//        Button btnBook;
//        Button btnUnbook;
//        ConstraintLayout mainLayout;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            txtTime = itemView.findViewById(R.id.txtTime);
//            txtAvailability = itemView.findViewById(R.id.txtAvailability);
//            imgSun = itemView.findViewById(R.id.imgSun);
//            mainLayout = itemView.findViewById(R.id.mainLayout);
//            btnBook = itemView.findViewById(R.id.btnBook);
//            btnUnbook = itemView.findViewById(R.id.btnUnbook);
//        }
//    }
}
