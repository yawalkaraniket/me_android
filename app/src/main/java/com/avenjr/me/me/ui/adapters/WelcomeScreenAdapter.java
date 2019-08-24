package com.avenjr.me.me.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
    int parentHeight;
    int parentWidth;

    public WelcomeScreenAdapter(Context context, ArrayList<String> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public WelcomeScreenAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.welcome_cards, parent, false);

        parentHeight = UiUtil.dp(parent.getHeight());
        parentWidth = UiUtil.dp(parent.getWidth());

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

            LinearLayout layout = (LinearLayout) itemView;
            ViewGroup.LayoutParams params = layout.getLayoutParams();
            params.height = UiUtil.getScreenHeightInDp(context) / 2;
            layout.setLayoutParams(params);
        }
    }
}
