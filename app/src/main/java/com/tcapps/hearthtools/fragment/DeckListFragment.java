package com.tcapps.hearthtools.fragment;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.tcapps.hearthtools.R;
import com.tcapps.hearthtools.activity.MainActivity;
import com.tcapps.hearthtools.adapter.DeckListAdapter;
import com.tcapps.hearthtools.application.HearthToolsApplication;
import com.tcapps.hearthtools.database.cursor.DeckCursor;
import com.tcapps.hearthtools.database.dao.DeckDao;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.tcapps.hearthtools.util.ViewUtil.dipToPixels;

public class DeckListFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private RecyclerView mDeckList;
    private RecyclerView.LayoutManager mLayoutManager;

    @Inject DeckDao mDeckDao;

    public static DeckListFragment newInstance(int position) {
        DeckListFragment fragment = new DeckListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HearthToolsApplication.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mDeckList = (RecyclerView) inflater.inflate(R.layout.card_view_deck, container, false);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mDeckList.setLayoutManager(mLayoutManager);

        ArrayList<Object> data = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            data.add(new Object());
        }

        mDeckList.setAdapter(new DeckListAdapter(data));

        mDeckList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
            }

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);

                outRect.bottom += dipToPixels(getActivity(), 10);
                outRect.top += dipToPixels(getActivity(), 10);
            }
        });

        return mDeckList;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }

}
