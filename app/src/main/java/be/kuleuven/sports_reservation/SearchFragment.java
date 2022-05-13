package be.kuleuven.sports_reservation;
import android.content.Intent;
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

    private Button btnSearch;
    private Spinner spBadminton;
    private Spinner spTennis;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        btnSearch = (Button) view.findViewById(R.id.btnSearch);
        spBadminton = (Spinner) view.findViewById(R.id.spBadminton);
        spTennis = (Spinner) view.findViewById(R.id.spTennis);

        btnSearch.setEnabled(false);

        //spinner没有onclick，得通过setOnItemSelectedListener(OnItemSelectedListener)添加监听事件。
        spBadminton.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //这个方法里可以对点击事件进行处理
                //i指的是点击的位置,通过i可以取到相应的数据源
                String type_badminton = spBadminton.getSelectedItem().toString();
                String type_tennis = spTennis.getSelectedItem().toString();

                if (!type_badminton.equals("None")) {
                    spTennis.setSelection(0); //reset to default value
                    btnSearch.setEnabled(true);
                } else if (type_tennis.equals("None")) {
                    btnSearch.setEnabled(false);
                }

                String info = adapterView.getItemAtPosition(i).toString();//获取i所在的文本
                Toast.makeText(getContext(), info, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spTennis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //这个方法里可以对点击事件进行处理
                //i指的是点击的位置,通过i可以取到相应的数据源
                String type_badminton = spBadminton.getSelectedItem().toString();
                String type_tennis = spTennis.getSelectedItem().toString();
                if (!type_tennis.equals("None")) {
                    spBadminton.setSelection(0); //reset to default value
                    btnSearch.setEnabled(true);
                } else if (type_badminton.equals("None")) {
                    btnSearch.setEnabled(false);
                }

                String info = adapterView.getItemAtPosition(i).toString();//获取i所在的文本
                Toast.makeText(getContext(), info, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChooseTimeActivity.class);
                String results[] = new String[2];
                String chosenType = null;
                results[0] = spBadminton.getSelectedItem().toString();
                results[1] = spTennis.getSelectedItem().toString();
                for (int i = 0; i < 2; i++) {
                    if (!results[i].equals("None")) {
                        chosenType = results[i];
                    }
                }
                intent.putExtra("type", chosenType);
                startActivity(intent);
            }
        });


        return view;
    }


}



