package be.kuleuven.sports_reservation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment{

    RecyclerView recyclerView;
    recyclerViewAdapter recyclerViewAdapter;
    List<recyclerView> recyclerViewList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerViewList = new ArrayList<>();
        recyclerViewList.add(new recyclerView(R.drawable.hardau, "Rod Laver Arena", "Tennis Hard Court"));
        recyclerViewList.add(new recyclerView(R.drawable.clay, "Court Philippe Chatrier", "Tennis Clay Court"));
        recyclerViewList.add(new recyclerView(R.drawable.grass, "Centre Court", "Tennis Grass Court"));
        recyclerViewList.add(new recyclerView(R.drawable.hardus, "Arthur Ashe Stadium", "Tennis Hard Court"));
        recyclerViewList.add(new recyclerView(R.drawable.badminton, "Badminton", "Badminton Court"));
        recyclerViewList.add(new recyclerView(R.drawable.basketball, "Basketball", "Basketball Court"));
        recyclerViewList.add(new recyclerView(R.drawable.volleyball, "Volleyball", "Volleyball Court"));

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerViewAdapter = new recyclerViewAdapter(recyclerViewList, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(recyclerViewAdapter);

        return view;
    }
}
