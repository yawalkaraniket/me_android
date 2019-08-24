package com.avenjr.me.me.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.avenjr.me.me.R;
import com.avenjr.me.me.ui.Utils.UiUtil;

import java.util.ArrayList;

public class WelcomeScreenAdapter extends RecyclerView.Adapter<WelcomeScreenAdapter.WelcomeScreenAdapterViewHolder>{

    private ArrayList<String> list;
    private Context context;

    public WelcomeScreenAdapter(Context context, ArrayList<String> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public WelcomeScreenAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.welcome_cards, parent, false);
        ConstraintSet cs = new ConstraintSet();
        ConstraintLayout layout = (ConstraintLayout)parent;
        cs.clone(layout);
        cs.constrainHeight(parent.getId(), UiUtil.getScreenHeightInDp(context) / 3);
        cs.constrainWidth(parent.getId(), UiUtil.getScreenWidthInPixel(context) / 3);
        cs.applyTo(layout);
        return new WelcomeScreenAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WelcomeScreenAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class WelcomeScreenAdapterViewHolder extends RecyclerView.ViewHolder {
        WelcomeScreenAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
