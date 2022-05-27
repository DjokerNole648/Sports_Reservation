package be.kuleuven.book_my_court;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.MyViewHolder> {

    Context context;
    List<recyclerView> list;

    public recyclerViewAdapter(List<recyclerView> list, Context context){
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        recyclerView view = list.get(position);
        holder.myImage.setImageResource(view.getCourt());
        holder.myText1.setText(view.getTitle());
        holder.myText2.setText(view.getDescription());

        holder.itemView.setOnClickListener(view1 -> {
            Intent intent = new Intent(context, DescriptionActivity.class);
            intent.putExtra("court",list.get(position).getTitle());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView myText1, myText2;
        ImageView myImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.tennis_clay_title);
            myText2 = itemView.findViewById(R.id.tennis_clay_description);
            myImage = itemView.findViewById(R.id.tennis_clay);
        }
    }
}
