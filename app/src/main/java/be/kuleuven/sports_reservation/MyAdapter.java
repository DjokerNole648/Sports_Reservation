package be.kuleuven.sports_reservation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.concurrent.TimeUnit;

//RecyclerView.ViewHolder: holds the view (UI elements)
//RecyclerView.Adapter: binds the date to the viewHolder
//myAdapter is the adapter for recycler view
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List<TimeSlot> timeSlotList;

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
        be.kuleuven.sports_reservation.TimeSlot timeSlot = timeSlotList.get(position);

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

                Intent intent = new Intent(context, BookMessageActivity.class);
                intent.putExtra("isBook", holder.btnBook.isEnabled());
                intent.putExtra("beginTime", timeSlot.getBeginTime());
                intent.putExtra("endTime", timeSlot.getEndTime());
                context.startActivity(intent);

                holder.btnUnbook.setEnabled(true);
                holder.btnBook.setEnabled(false);

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
