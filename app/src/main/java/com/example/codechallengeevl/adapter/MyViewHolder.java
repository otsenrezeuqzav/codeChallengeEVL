package com.example.codechallengeevl.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.codechallengeevl.R;

public class MyViewHolder extends RecyclerView.ViewHolder {


    ImageView imageView;
    TextView nameView, taglineView;
    CardView cardView;

    // constructor
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        nameView = itemView.findViewById(R.id.name);
        taglineView = itemView.findViewById(R.id.tagline);
        cardView = itemView.findViewById(R.id.cardView);
    }
}
