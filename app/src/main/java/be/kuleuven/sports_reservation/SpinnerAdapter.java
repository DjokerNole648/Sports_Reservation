package be.kuleuven.sports_reservation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter {

    LayoutInflater layoutInflater;

    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Court> courts) {
        super(context, resource, courts);
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = layoutInflater.inflate(R.layout.court_adapter, null, true);
        Court court = (Court) getItem(position);

        TextView textView = rowView.findViewById(R.id.txtSpinner);
        ImageView imageView = rowView.findViewById(R.id.imgSpinner);
        textView.setText(court.getName());
        imageView.setImageResource(court.getImage());
        return rowView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.court_adapter, parent, false);
        }

        Court court = (Court) getItem(position);

        TextView textView = convertView.findViewById(R.id.txtSpinner);
        ImageView imageView = convertView.findViewById(R.id.imgSpinner);
        textView.setText(court.getName());
        imageView.setImageResource(court.getImage());
        return convertView;
    }
}
