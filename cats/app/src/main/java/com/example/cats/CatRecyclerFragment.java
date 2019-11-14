package com.example.cats;


import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//add button that onclick setdata for recyler view and notify data change to refresh recycler with search

public class CatRecyclerFragment extends Fragment {
    private RecyclerView recyclerView;
    String search = "";
    Button searchButton;
    String url = "https://api.thecatapi.com/v1/breeds/search?q=";
    String result = "";



    public CatRecyclerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_cat_recycler, container, false);
        recyclerView = view.findViewById(R.id.rv_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        final CatAdapter catAdapter = new CatAdapter();

        final RequestQueue requestQueue =  Volley.newRequestQueue(getActivity());


        // when search button clicked create new request with new url and update recycler view
        searchButton = (Button) view.findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText edit = (EditText) view.findViewById(R.id.search_bar);
                result = edit.getText().toString();
                String url = "https://api.thecatapi.com/v1/breeds/search?q=";
                url = url + result;
                final RequestQueue requestQueue =  Volley.newRequestQueue(getActivity());

                final Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Gson gson = new Gson();
                        Cat[] cats = gson.fromJson(response, Cat[].class);

                        List<Cat> catList = (List<Cat>) Arrays.asList(cats);

                        catAdapter.setData(catList);
                        CatDatabase.saveCatsToFakeDatabase(catList);
                        recyclerView.setAdapter(catAdapter);

                        requestQueue.stop();
                    }
                };

                final Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("!!!", error.getMessage());
                        requestQueue.stop();
                    }
                };

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener,
                        errorListener);


                requestQueue.add(stringRequest);

            }
        });


        // original request with no data- empty recyclerview
        final Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                Cat[] cats = gson.fromJson(response, Cat[].class);

                List<Cat> catList = (List<Cat>) Arrays.asList(cats);

                catAdapter.setData(catList);
                CatDatabase.saveCatsToFakeDatabase(catList);
                recyclerView.setAdapter(catAdapter);

                requestQueue.stop();
            }
        };

        final Response.ErrorListener errorListener = new Response.ErrorListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("!!!", error.getMessage());
                requestQueue.stop();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener,
                errorListener);



        requestQueue.add(stringRequest);

        return view;

    }


}
