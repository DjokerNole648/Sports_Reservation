package be.kuleuven.sports_reservation;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookMessageActivity extends AppCompatActivity {

    ImageView mainImageView;
    TextView txtTime1, txtTime2;

    String data1, data2;
    int myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mainImageView = findViewById(R.id.mainImageView);
        txtTime1 = findViewById(R.id.txtTime1);
        txtTime2 = findViewById(R.id.txtTime2);


        getData();
        setData();

//        final Intent localIntent=new Intent(this, ChooseTimeActivity.class);
//        Timer timer=new Timer();
//        TimerTask task=new TimerTask(){
//            @Override
//            public void run(){
//                startActivity(localIntent);
//            }
//        };
//        timer.schedule(task,3000);

    }

    private void getData(){
//        if(getIntent().hasExtra("myImage") && getIntent().hasExtra("time") && getIntent().hasExtra("data2")){
        if(getIntent().hasExtra("beginTime") && getIntent().hasExtra("endTime")){
            data1 = getIntent().getStringExtra("beginTime");
            data2 = getIntent().getStringExtra("endTime");
//            myImage = getIntent().getIntExtra("myImage", 1);
        }
        else{
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }

    }

    private void setData(){
        txtTime1.setText(data1);
        txtTime2.setText(data2);
//        description.setText(data2);
//        mainImageView.setImageResource(myImage);
    }
}