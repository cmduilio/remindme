package com.sirio.remindme.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.sirio.remindme.R;
import com.sirio.remindme.views.TextPairRow;

public class SearchFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_search, container, false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragmentWithAnimation(new AddReminderFragment());
            }
        });

        Button button = (Button) view.findViewById(R.id.advanced_search_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragmentWithAnimation(new AdvancedSearchFragment());
            }
        });

        button = (Button) view.findViewById(R.id.prueba);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view2 = new TextPairRow(getContext());
                LinearLayout myLayout = (LinearLayout) view.findViewById(R.id.reminder_layout);
                myLayout.addView(view2);
            }
        });

        button = (Button) view.findViewById(R.id.restart);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                startActivity(getActivity().getIntent());
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}
