package com.example.cats;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class Cat {
    String id;
    String name;
    String temperament;
    String life_span;
    String wikipedia_url;
    String origin;
    String weight_imperial;
    Integer dog_friendly;
    String description;

    public Cat(String id, String name, String temperament,
               String life_span,
               String wikipedia_url,
               String origin,
               String weight_imperial,
               Integer dog_friendly,
               String description) {
        this.id = id;
        this.name = name;
        this.temperament = temperament;
        this.life_span = life_span;
        this.wikipedia_url = wikipedia_url;
        this.origin = origin;
        this.weight_imperial = weight_imperial;
        this.dog_friendly = dog_friendly;
        this.description = description;
    }

    public String getId() {
        return id;
    }
    public String getName() { return name; }
    public String getTemperament() { return temperament; }
    public String getLife_span() { return life_span; }


    public String getWikipedia_url() {
        return wikipedia_url;
    }

    public String getOrigin() {
        return origin;
    }

    public String getWeight_imperial() {
        return weight_imperial;
    }

    public Integer getDog_friendly() {
        return dog_friendly;
    }

    public String getDescription() {
        return description;
    }


}
