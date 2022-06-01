package be.kuleuven.book_my_court.homeFragement;

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

import be.kuleuven.book_my_court.R;

public class HomeFragment extends Fragment{

    RecyclerView recyclerView;
    be.kuleuven.book_my_court.homeFragement.recyclerViewAdapter recyclerViewAdapter;
    List<be.kuleuven.book_my_court.homeFragement.recyclerView> recyclerViewList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerViewList = new ArrayList<>();
        recyclerViewList.add(new recyclerView(R.drawable.hardau, "Rod Laver Arena", "Tennis Hard Court", "Open from 8:00 to 22:00"));
        recyclerViewList.add(new recyclerView(R.drawable.clay, "Court Philippe Chatrier", "Tennis Clay Court", "Open from 8:00 to 22:00"));
        recyclerViewList.add(new recyclerView(R.drawable.grass, "Centre Court", "Tennis Grass Court", "Open from 8:00 to 22:00"));
        recyclerViewList.add(new recyclerView(R.drawable.hardus, "Arthur Ashe Stadium", "Tennis Hard Court", "Open from 8:00 to 22:00"));
        recyclerViewList.add(new recyclerView(R.drawable.badminton, "Badminton", "Badminton Court", "Open from 8:00 to 22:00"));
        recyclerViewList.add(new recyclerView(R.drawable.basketball, "Basketball", "Basketball Court", "Open from 8:00 to 22:00"));
        recyclerViewList.add(new recyclerView(R.drawable.volleyball, "Volleyball", "Volleyball Court", "Open from 8:00 to 22:00"));

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerViewAdapter = new recyclerViewAdapter(recyclerViewList, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(recyclerViewAdapter);

        return view;
    }
}