package com.example.codechallengeevl.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.codechallengeevl.R;
import com.example.codechallengeevl.activity.DetailActivity;
import com.example.codechallengeevl.model.BeersModel;
import com.squareup.picasso.Picasso;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    private List<BeersModel> lstBeers;

    // constructor
    public MyAdapter(Context context, List<BeersModel> lstBeers) {
        this.context = context;
        this.lstBeers = lstBeers;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nameView.setText(lstBeers.get(position).getName());
        holder.taglineView.setText(lstBeers.get(position).getTagline());
        Picasso.get().load(lstBeers.get(position).getImage_url()).into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // se pasa la data a la actividad DetailActivity
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(context.getString(R.string.keyImage),lstBeers.get(holder.getBindingAdapterPosition()).getImage_url());
                intent.putExtra(context.getString(R.string.keyName),lstBeers.get(holder.getBindingAdapterPosition()).getName());
                intent.putExtra(context.getString(R.string.keyTagLine),lstBeers.get(holder.getBindingAdapterPosition()).getTagline());
                intent.putExtra(context.getString(R.string.keyFirstBrewed),lstBeers.get(holder.getBindingAdapterPosition()).getFirst_brewed());
                intent.putExtra(context.getString(R.string.keyDescription), lstBeers.get(holder.getBindingAdapterPosition()).getDescription());
                intent.putExtra(context.getString(R.string.keyAbv), lstBeers.get(holder.getBindingAdapterPosition()).getAbv());
                intent.putExtra(context.getString(R.string.keyIbu), lstBeers.get(holder.getBindingAdapterPosition()).getIbu());
                intent.putExtra(context.getString(R.string.keyTargetfg), lstBeers.get(holder.getBindingAdapterPosition()).getTarget_fg());
                intent.putExtra(context.getString(R.string.keyTargetog), lstBeers.get(holder.getBindingAdapterPosition()).getTarget_og());
                intent.putExtra(context.getString(R.string.keyEbc), lstBeers.get(holder.getBindingAdapterPosition()).getEbc());
                intent.putExtra(context.getString(R.string.keySrm), lstBeers.get(holder.getBindingAdapterPosition()).getSrm());
                intent.putExtra(context.getString(R.string.keyPh), lstBeers.get(holder.getBindingAdapterPosition()).getPh());
                intent.putExtra(context.getString(R.string.keyAttenuationlevel), lstBeers.get(holder.getBindingAdapterPosition()).getAttenuation_level());

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstBeers.size();
    }

}
