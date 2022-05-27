package be.kuleuven.sports_reservation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyDialog extends DialogFragment {

    private static final String TAG = "MyDialog";

    private Button btnReturn;
    private TextView txtInfo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_warning, container, false);
        btnReturn = view.findViewById(R.id.btnReturn);
        txtInfo = view.findViewById(R.id.txtDialog);

        if(SearchFragment.getChosenNumber() == 0){
            txtInfo.setText("Please choose one courtName:");
        }
        else{
            txtInfo.setText("You've picked more than one. \nPlease choose again:");
        }

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Closing dialog");
                getDialog().dismiss();
            }
        });

        return view;
    }
}
