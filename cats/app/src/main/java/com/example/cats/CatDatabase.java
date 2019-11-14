package com.example.cats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CatDatabase {
    public static ArrayList<String> favorites = new ArrayList<>();
    public static HashMap<String, Cat> cats = new HashMap<>();

    /***
     * Retrieves an Cat object using the provided id.
     */
    public static Cat getCatByCatId(String catID) {
        return cats.get(catID);
    }


    public ArrayList<String> getFavorites() { return favorites;}



    public static void saveCatsToFakeDatabase(List<Cat> catsToSave) {
        for(int i = 0; i < catsToSave.size(); i++) {
            Cat cat = catsToSave.get(i);
            cats.put(cat.getId(), cat);
        }
    }

    public void addToFavorites(String catID){
        favorites.add(catID);

    }

}
