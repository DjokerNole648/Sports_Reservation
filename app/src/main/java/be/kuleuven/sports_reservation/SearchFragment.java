package be.kuleuven.sports_reservation;
import android.content.Intent;
import android.nfc.Tag;
import android.util.Log;
import android.view.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SearchFragment extends Fragment {

    private static int chosenNumber;
    private Button btnSearch;
    private Spinner spBadminton, spTennis ,spBasketball, spVolleyBall, spFootBall;

    String type_badminton, type_tennis, type_basketball, type_volleyball, type_football;

    private static final String TAG = "SearchFragment";

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        spBadminton = (Spinner) view.findViewById(R.id.spBadminton);
        spTennis = (Spinner) view.findViewById(R.id.spTennis);
        spBasketball = (Spinner) view.findViewById(R.id.spBasketball);
        spVolleyBall = (Spinner) view.findViewById(R.id.spVolleyball);
        spFootBall = (Spinner) view.findViewById(R.id.spFootball);

        btnSearch = (Button) view.findViewById(R.id.btnSearch);


//        btnSearch.setEnabled(false);

//        //spinner没有onclick，得通过setOnItemSelectedListener(OnItemSelectedListener)添加监听事件。
//        spBadminton.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                //这个方法里可以对点击事件进行处理
//                //i指的是点击的位置,通过i可以取到相应的数据源
//                getValue();
//
//                if (!type_badminton.equals("None")) {
//                    spTennis.setSelection(0); //reset to default value
//                    spBasketball.setSelection(0);
//                    spVolleyBall.setSelection(0);
//                    spFootBall.setSelection(0);
//
//                    btnSearch.setEnabled(true);
//                }
//                else if(type_tennis.equals("None") &&
//                        type_basketball.equals("None") &&
//                        type_volleyball.equals("None") &&
//                        type_football.equals("None")){
//                    btnSearch.setEnabled(false);
//                }
////                String info = adapterView.getItemAtPosition(i).toString();//获取i所在的文本
////                Toast.makeText(getContext(), info, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//            }
//        });
//
//        spTennis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                //这个方法里可以对点击事件进行处理
//                //i指的是点击的位置,通过i可以取到相应的数据源
//                getValue();
//
//                if (!type_tennis.equals("None")) {
//                    spBadminton.setSelection(0); //reset to default value
//                    spBasketball.setSelection(0);
//                    spVolleyBall.setSelection(0);
//                    spFootBall.setSelection(0);
//
//                    btnSearch.setEnabled(true);
//                }
//                else if (type_basketball.equals("None") &&
//                        type_volleyball.equals("None") &&
//                        type_football.equals("None") &&
//                        type_badminton.equals("None")){
//                    btnSearch.setEnabled(false);
//                }
////                String info = adapterView.getItemAtPosition(i).toString();//获取i所在的文本
////                Toast.makeText(getContext(), info, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//            }
//        });
//
//
//
//        spBasketball.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                //这个方法里可以对点击事件进行处理
//                //i指的是点击的位置,通过i可以取到相应的数据源
//                getValue();
//
//                if (!type_basketball.equals("None")) {
//                    spBadminton.setSelection(0); //reset to default value
//                    spTennis.setSelection(0);
//                    spVolleyBall.setSelection(0);
//                    spFootBall.setSelection(0);
//
//                    btnSearch.setEnabled(true);
//                }
////                else if (type_tennis.equals("None")) {
////                    btnSearch.setEnabled(false);
////                }
//                else if (type_tennis.equals("None") &&
//                        type_volleyball.equals("None") &&
//                        type_football.equals("None") &&
//                        type_badminton.equals("None")){
//                    btnSearch.setEnabled(false);
//                }
//
////                String info = adapterView.getItemAtPosition(i).toString();//获取i所在的文本
////                Toast.makeText(getContext(), info, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//            }
//        });
//
//
//        spVolleyBall.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                //这个方法里可以对点击事件进行处理
//                //i指的是点击的位置,通过i可以取到相应的数据源
//                getValue();
//
//                if (!type_volleyball.equals("None")) {
//                    spBadminton.setSelection(0); //reset to default value
//                    spBasketball.setSelection(0);
//                    spTennis.setSelection(0);
//                    spFootBall.setSelection(0);
//
//                    btnSearch.setEnabled(true);
//                }
//                else if (type_tennis.equals("None") &&
//                        type_basketball.equals("None") &&
//                        type_football.equals("None") &&
//                        type_badminton.equals("None")){
//                    btnSearch.setEnabled(false);
//                }
//
////                String info = adapterView.getItemAtPosition(i).toString();//获取i所在的文本
////                Toast.makeText(getContext(), info, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//            }
//        });
//
//
//        spFootBall.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                //这个方法里可以对点击事件进行处理
//                //i指的是点击的位置,通过i可以取到相应的数据源
//                getValue();
//
//                if (!type_football.equals("None")) {
//                    spBadminton.setSelection(0); //reset to default value
//                    spBasketball.setSelection(0);
//                    spVolleyBall.setSelection(0);
//                    spTennis.setSelection(0);
//
//                    btnSearch.setEnabled(true);
//                }
//
//                else if (type_tennis.equals("None") &&
//                        type_basketball.equals("None") &&
//                        type_volleyball.equals("None") &&
//                        type_badminton.equals("None")){
//                    btnSearch.setEnabled(false);
//                }
//
////                String info = adapterView.getItemAtPosition(i).toString();//获取i所在的文本
////                Toast.makeText(getContext(), info, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//            }
//        });


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), ChooseTimeActivity.class);
//                String results[] = new String[5];
//                String chosenType = null;
//                results[0] = spBadminton.getSelectedItem().toString();
//                results[1] = spTennis.getSelectedItem().toString();
//                results[2] = spBasketball.getSelectedItem().toString();
//                results[3] = spVolleyBall.getSelectedItem().toString();
//                results[4] = spFootBall.getSelectedItem().toString();
//                for (int i = 0; i < 5; i++) {
//                    if (!results[i].equals("None")) {
//                        chosenType = results[i];
//                    }
//                }
//                intent.putExtra("type", chosenType);
//                startActivity(intent);

                String results[] = new String[5];
                results[0] = spBadminton.getSelectedItem().toString();
                results[1] = spTennis.getSelectedItem().toString();
                results[2] = spBasketball.getSelectedItem().toString();
                results[3] = spVolleyBall.getSelectedItem().toString();
                results[4] = spFootBall.getSelectedItem().toString();

                chosenNumber = 0;
                for (int i = 0; i < 5; i++) {
                    if(!results[i].equals("None")){
                        chosenNumber++;
                    }
                }

                if(chosenNumber == 1) {
                    String chosenType = null;
                    for (int i = 0; i < 5; i++) {
                        if (!results[i].equals("None")) {
                            chosenType = results[i];
                        }
                    }
                    Intent intent = new Intent(getActivity(), ChooseTimeActivity.class);
                    intent.putExtra("type", chosenType);
                    startActivity(intent);
                }
                else{
                    Log.d(TAG, "open dialog");
                    MyDialog myDialog = new MyDialog();
                    myDialog.show(getFragmentManager(), "MyDialog");

                    spBadminton.setSelection(0);//reset to default value
                    spTennis.setSelection(0);
                    spBasketball.setSelection(0);
                    spVolleyBall.setSelection(0);
                    spFootBall.setSelection(0);
                }
            }
        });


        return view;
    }

    public void getValue(){
        type_badminton = spBadminton.getSelectedItem().toString();
        type_tennis = spTennis.getSelectedItem().toString();
        type_basketball = spBasketball.getSelectedItem().toString();
        type_volleyball = spVolleyBall.getSelectedItem().toString();
        type_football = spFootBall.getSelectedItem().toString();
    }

    public static int getChosenNumber() {
        return chosenNumber;
    }
}



