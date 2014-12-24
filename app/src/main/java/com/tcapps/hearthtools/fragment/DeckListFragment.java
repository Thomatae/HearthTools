package com.tcapps.hearthtools.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.tcapps.hearthtools.R;
import com.tcapps.hearthtools.activity.MainActivity;
import com.tcapps.hearthtools.application.HearthToolsApplication;
import com.tcapps.hearthtools.database.cursor.DeckCursor;
import com.tcapps.hearthtools.database.dao.DeckDao;

import javax.inject.Inject;

public class DeckListFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private TextView mDeckName;
    private TextView mDeckClass;
    private EditText mDeckNameInput;
    private EditText mDeckClassInput;
    private Button mAdd;
    private Button mRefresh;


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
        View rootView = inflater.inflate(R.layout.card_view_deck, container, false);

        mDeckName = (TextView) rootView.findViewById(R.id.deck_name);
        mDeckClass = (TextView) rootView.findViewById(R.id.deck_class);
        mDeckNameInput = (EditText) rootView.findViewById(R.id.deck_name_input);
        mDeckClassInput = (EditText) rootView.findViewById(R.id.deck_class_input);
        mAdd = (Button) rootView.findViewById(R.id.add_deck);
        mRefresh = (Button) rootView.findViewById(R.id.refresh);

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDeckToDatabase();
            }
        });

        mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFromDatabase();
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }

    private void addDeckToDatabase() {
        mDeckDao.insertDeck(mDeckNameInput.getText().toString(), mDeckClassInput.getText().toString());
    }

    private void readFromDatabase() {
        DeckCursor cursor = mDeckDao.readDecks();
        int count = cursor.getCount();

        if (cursor.moveToPosition(count - 1)) {
            mDeckName.setText(cursor.readName());
            mDeckClass.setText(cursor.readDeckType());
        }

        cursor.close();
    }
}
