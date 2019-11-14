package com.example.cats;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

public class FavoritesDetailActivity extends AppCompatActivity {

    public TextView nameTextView;
    public TextView temperamentTextView;
    public TextView lifeSpanTextView;
    public TextView weightTextView;
    public TextView originTextView;
    public TextView wikiTextView;
    public TextView friendlyTextView;
    public TextView descriptionTextView;
    public CatImage[] current_cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_detail);

        // Get the intent that was used to travel to this activity
        Intent intent = getIntent();

        // Get the extra identified by "catID" that was put into the intent at the origin.
        String catID = intent.getStringExtra("catID");


        final Cat cat = CatDatabase.getCatByCatId(catID);

        nameTextView = findViewById(R.id.catName);
        temperamentTextView = findViewById(R.id.catTemp);
        lifeSpanTextView = findViewById(R.id.catLifeSpan);
        weightTextView = findViewById(R.id.catWeight);
        originTextView = findViewById(R.id.catOrigin);
        wikiTextView = findViewById(R.id.catWiki);
        friendlyTextView = findViewById(R.id.catFriendly);
        descriptionTextView = findViewById(R.id.catDescription);

        // avoid null response
        nameTextView.setText(cat.getName());
        if(cat.getTemperament() != null){
            temperamentTextView.setText(cat.getTemperament());
        }
        if(cat.getLife_span() != null){
            lifeSpanTextView.setText(cat.getLife_span());
        }
        if(cat.getWeight_imperial() != null){
            weightTextView.setText(cat.getWeight_imperial());
        }
        if(cat.getOrigin() != null){
            originTextView.setText(cat.getOrigin());
        }
        if(cat.getWikipedia_url() != null){
            wikiTextView.setText(cat.getWikipedia_url());
        }
        if(cat.getDog_friendly() != null){
            friendlyTextView.setText(String.valueOf(cat.getDog_friendly()));
        }
        if(cat.getDescription() != null){
            descriptionTextView.setText(cat.getDescription());
        }

        String url = "https://api.thecatapi.com/v1/images/search?breed_id=";
        url = url + catID;


        final RequestQueue requestQueue =  Volley.newRequestQueue(this);

        final Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                CatImage[] current_cat = gson.fromJson(response, CatImage[].class);
                // in case of no image available
                try {
                    setData(current_cat);
                } catch (Exception e) {
                    Log.e("Exception", "onResponse: image not available");
                }


                requestQueue.stop();
            }

        };

        final Response.ErrorListener errorListener = new Response.ErrorListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("!!!", error.getMessage());
                requestQueue.stop();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener,
                errorListener);


        requestQueue.add(stringRequest);



    }

    public void setData(CatImage[] current_cat) {
        this.current_cat = current_cat;
        if(this.current_cat[0].getUrl() != null){
            String imageURL = this.current_cat[0].getUrl();

            ImageView catImageView = findViewById(R.id.catImage);
            Glide.with(this).load(imageURL).into(catImageView);
        }

    }

}
