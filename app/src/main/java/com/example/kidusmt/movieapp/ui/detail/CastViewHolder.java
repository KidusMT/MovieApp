package com.example.kidusmt.movieapp.ui.detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kidusmt.movieapp.R;
import com.example.kidusmt.movieapp.data.model.Cast;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by KidusMT on 1/4/2018.
 */

public class CastViewHolder extends RecyclerView.ViewHolder {

    ImageView castImg;
    TextView castRealName, castCharacterName;

    public CastViewHolder(View itemView) {
        super(itemView);
        castImg = itemView.findViewById(R.id.iv_cast_thumbnail);
        castCharacterName = itemView.findViewById(R.id.tv_cast_character_name);
        castRealName = itemView.findViewById(R.id.tv_cast_real_name);
    }

    public void update(Cast cast) {
        Picasso.with(castImg.getContext()).load(cast.getProfilePath()).into(castImg);
        castCharacterName.setText(cast.getCharacter());
        castRealName.setText(cast.getName());
    }
}
