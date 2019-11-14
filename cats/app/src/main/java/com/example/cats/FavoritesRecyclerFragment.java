package com.example.cats;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class FavoritesRecyclerFragment extends Fragment {
    private RecyclerView recyclerView;

    public FavoritesRecyclerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites_recycler, container, false);
        recyclerView = view.findViewById(R.id.rv_favs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        // create list of cat favorites using the string list of cat ids
        CatDatabase db = new CatDatabase();
        FavoritesAdapter favoritesAdapter = new FavoritesAdapter();
        ArrayList<Cat> cats = new ArrayList<Cat>();
        for(int i = 0; i < db.getFavorites().size(); i++){
            cats.add(db.getCatByCatId((db.getFavorites()).get(i)));
        }

        favoritesAdapter.setData(cats);
        recyclerView.setAdapter(favoritesAdapter);

        return view;
    }

}
