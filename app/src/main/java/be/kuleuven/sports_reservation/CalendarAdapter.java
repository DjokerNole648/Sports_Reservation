package be.kuleuven.sports_reservation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {

    private Context mCtx;
    private List<TimeSlot> timeSlotList;

    public CalendarAdapter(Context mCtx, List<TimeSlot> timeSlotList) {
        this.mCtx = mCtx;
        this.timeSlotList = timeSlotList;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx); //Instantiates a layout XML file into its corresponding View objects.
        View view = inflater.inflate(R.layout.calendar_row, parent,false);//把my_row.xml初始化成相应的view objects
        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        TimeSlot timeSlot = timeSlotList.get(position);

        holder.txtBeginTime.setText(timeSlot.getBeginTime());
        holder.txtEndTime.setText(timeSlot.getEndTime());
        holder.txtCourtName.setText(timeSlot.getCourtName());

        Glide.with(mCtx)
                .load(timeSlot.getImage())
                .into(holder.imageView);


        holder.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mCtx, BookMessageActivity.class);
                intent.putExtra("isCancel", holder.btnCancel.isEnabled());
                intent.putExtra("beginTime", timeSlot.getBeginTime());
                intent.putExtra("endTime", timeSlot.getEndTime());
                mCtx.startActivity(intent);

                holder.btnCancel.setEnabled(false);

            }
        });
    }

    @Override
    public int getItemCount() {
        return timeSlotList.size();
    }

    class CalendarViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView txtCourtName, txtBeginTime, txtEndTime;
        Button btnCancel;

        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            txtBeginTime = itemView.findViewById(R.id.txtTime3);
            txtEndTime = itemView.findViewById(R.id.txtTime4);
            txtCourtName = itemView.findViewById(R.id.txtCourtName);
            btnCancel = itemView.findViewById(R.id.btnCancel);
        }
    }
}
