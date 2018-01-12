package com.example.kidusmt.movieapp.ui.detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kidusmt.movieapp.data.movie.Cast;

import java.util.ArrayList;
import java.util.List;
import com.example.kidusmt.movieapp.R;

/**
 * Created by KidusMT on 12/24/2017.
 */

public class CastAdapter extends RecyclerView.Adapter<CastViewHolder> {

    private List<Cast> castList;
    private MovieDetailContract.Presenter presenter;

    public CastAdapter(MovieDetailContract.Presenter presenter){
        castList = new ArrayList<>();
        this.presenter = presenter;
    }

    @Override
    public CastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_cast, parent,false);
        return new CastViewHolder(viewItem, presenter, castList);
    }

    @Override
    public void onBindViewHolder(CastViewHolder holder, int position) {
        holder.update(castList.get(position));
    }

    public void update(List<Cast> data) {
        castList.clear();
        castList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (castList!=null)?castList.size():0;
    }

}
