package com.tcapps.hearthtools.adapter;

import android.support.v4.view.ViewCompat;
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
    private Callback mCallback;

    public DeckListAdapter(ArrayList<Object> data, Callback callback) {
        mData = data;
        mCallback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView deckName;
        public TextView deckClass;
        public TextView winLossRatio;

        public ViewHolder(View itemView) {
            super(itemView);

            deckName = (TextView) itemView.findViewById(R.id.deck_name);
            deckClass = (TextView) itemView.findViewById(R.id.deck_class);
            winLossRatio = (TextView) itemView.findViewById(R.id.win_loss_ratio);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mCallback.onDeckClicked(getPosition());
        }
    }

    public interface Callback {
        void onDeckClicked(int position);
    }
}
