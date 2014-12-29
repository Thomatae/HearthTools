package com.tcapps.hearthtools.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tcapps.hearthtools.R;

import java.util.ArrayList;

public class DeckListAdapter extends RecyclerView.Adapter<DeckListAdapter.ViewHolder> {

    private ArrayList<Object> mData;

    public DeckListAdapter(ArrayList<Object> data) {
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int index) {
        CardView row = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_deck_list, viewGroup, false);

        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        //ToDo - Bind Mock Data when avialable

        viewHolder.deckName.setText("Deck " + i);
        viewHolder.deckClass.setText(i % 2 == 0 ? "Shaman" : "Warrior");
        viewHolder.winLossRatio.setText(String.format("W: %d/ L: %d", (int) (Math.random() * 100), (int) (Math.random() * 100)));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView deckName;
        public TextView deckClass;
        public TextView winLossRatio;

        public ViewHolder(View itemView) {
            super(itemView);

            deckName = (TextView) itemView.findViewById(R.id.deck_name);
            deckClass = (TextView) itemView.findViewById(R.id.deck_class);
            winLossRatio = (TextView) itemView.findViewById(R.id.win_loss_ratio);
        }

    }
}
