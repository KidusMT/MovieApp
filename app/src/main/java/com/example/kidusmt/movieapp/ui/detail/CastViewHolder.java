package com.example.kidusmt.movieapp.ui.detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.kidusmt.movieapp.R;
import com.example.kidusmt.movieapp.data.movie.Cast;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by KidusMT on 1/4/2018.
 */

public class CastViewHolder extends  RecyclerView.ViewHolder  {

    ImageView castImg;
    TextView castRealName, castCharacterName;

    public CastViewHolder(View itemView, MovieDetailContract.Presenter presenter, List<Cast> casts) {
        super(itemView);

        castImg = itemView.findViewById(R.id.iv_cast_thumbnail);
        castCharacterName = itemView.findViewById(R.id.tv_cast_character_name);
        castRealName = itemView.findViewById(R.id.tv_cast_real_name);

        castImg.setOnClickListener(
                v -> presenter.onCardClicked(getAdapterPosition(),
                        casts.get(getAdapterPosition()).getProfilePath()));
    }

    //when update is requested with new lists of Casts
    public void update(Cast cast) {
//        cast = casts.get(getAdapterPosition());

        Picasso.with(castImg.getContext())
                .load(cast.getProfilePath())
                .fit()
                .centerCrop()
                .into(castImg);

        castCharacterName.setText(cast.getCharacter());
        castRealName.setText(cast.getName());
      }
}
