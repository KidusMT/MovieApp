package com.example.kidusmt.movieapp.ui.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kidusmt.movieapp.data.Cast;

import java.util.List;
import com.example.kidusmt.movieapp.R;
import com.example.kidusmt.movieapp.ui.home.MovieAdapter;
import com.squareup.picasso.Picasso;

/**
 * Created by KidusMT on 12/24/2017.
 */

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.MyViewHolder> {

    private Context mContext;
    private List<Cast> castList;

    public CastAdapter(Context context, List<Cast> casts){
        this.mContext = context;
        this.castList = casts;
    }

    @Override
    public CastAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_cast, parent,false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(CastAdapter.MyViewHolder holder, int position) {
        Cast cast = castList.get(position);

        holder.castCharacterName.setText(cast.getCharacter());
        holder.castRealName.setText(cast.getName());

        Picasso.with(mContext)
                .load(cast.getProfilePath())
                .placeholder(R.color.colorAccent)
                .into(holder.castImg);

    }

    @Override
    public int getItemCount() {
        return (castList!=null)?castList.size():0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView castImg;
        TextView castRealName, castCharacterName;
        public MyViewHolder(View itemView) {
            super(itemView);
            castImg = itemView.findViewById(R.id.iv_cast_thumbnail);
            castCharacterName = itemView.findViewById(R.id.tv_cast_character_name);
            castRealName = itemView.findViewById(R.id.tv_cast_real_name);
        }
    }
}
