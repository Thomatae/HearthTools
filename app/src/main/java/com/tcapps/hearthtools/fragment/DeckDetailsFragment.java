package com.tcapps.hearthtools.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tcapps.hearthtools.R;
import com.tcapps.hearthtools.adapter.DeckAdapter;

public class DeckDetailsFragment extends Fragment {

    private RecyclerView mCardList;

    private static final String ARG_DECK_ID = "arg.DeckId";

    public static  DeckDetailsFragment newInstance(int deckId) {
        Bundle args = new Bundle();
        args.putInt(ARG_DECK_ID, deckId);

        DeckDetailsFragment fragment = new DeckDetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mCardList = (RecyclerView) inflater.inflate(R.layout.fragment_deck_details, container, false);

        mCardList.setLayoutManager(new LinearLayoutManager(getActivity()));

        mCardList.setAdapter(new DeckAdapter());

        return mCardList;
    }
}
