package com.example.cats;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<com.example.cats.FavoritesAdapter.FavoritesViewHolder>{

        private List<Cat> catsToAdapt;

        public void setData(List<Cat> catsToAdapt) {
            this.catsToAdapt = catsToAdapt;
        }

        @NonNull
        @Override
        public com.example.cats.FavoritesAdapter.FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view =
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.cat, parent, false);

            com.example.cats.FavoritesAdapter.FavoritesViewHolder favoritesViewHolder = new com.example.cats.FavoritesAdapter.FavoritesViewHolder(view);
            return favoritesViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull com.example.cats.FavoritesAdapter.FavoritesViewHolder holder, int position) {
            final Cat catAtPosition = catsToAdapt.get(position);

            holder.bind(catAtPosition);

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();

                    Intent intent = new Intent(context, FavoritesDetailActivity.class);
                    intent.putExtra("catID", catAtPosition.getId());
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return catsToAdapt.size();
        }

        public static class FavoritesViewHolder extends RecyclerView.ViewHolder {
            public View view;

            public TextView nameTextView;


            // This constructor is used in onCreateViewHolder
            public FavoritesViewHolder(View v) {
                super(v);  // runs the constructor for the ViewHolder superclass
                view = v;
                nameTextView = v.findViewById(R.id.nameText);


            }

            // See comment in onBindViewHolder
            public void bind(final Cat cat) {
                nameTextView.setText(cat.getName());


            }
        }

}
