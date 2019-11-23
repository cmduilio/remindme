package com.sirio.remindme.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sirio.remindme.R;

public class TextPairRow extends LinearLayout {

    private TextView label, data;
    private boolean showLabel, showData;

    private static int counter = 1;

    public TextPairRow(Context context) {
        super(context);

        final LinearLayout mLayout = (LinearLayout)
                LayoutInflater.from(getContext()).inflate(R.layout.reminder_layout, this);

        label = (TextView) mLayout.findViewById(R.id.label);
        data = (TextView) mLayout.findViewById(R.id.data);
        ImageView delete = (ImageView) mLayout.findViewById(R.id.delete);

        delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewManager) mLayout.getParent()).removeView(mLayout);
            }
        });

        data.setText("data: " + ++counter);
        initViews(null);
    }

    public TextPairRow(Context context, AttributeSet attrs) {
        super(context, attrs);

        LinearLayout mLayout = (LinearLayout)
                LayoutInflater.from(getContext()).inflate(R.layout.reminder_layout, this);

        label = (TextView) mLayout.findViewById(R.id.label);
        data = (TextView) mLayout.findViewById(R.id.data);

        initViews(attrs);
    }

    private void initViews(AttributeSet attrs) {

        if(attrs == null){
            return;
        }

        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TextPairRow, 0, 0);

        try {
            showLabel = a.getBoolean(R.styleable.TextPairRow_showLabel, false);
            showData = a.getBoolean(R.styleable.TextPairRow_showData, false);
        } finally {
            a.recycle();
        }

        // Here you can inflate whatever you want to be in your
        // view or add views programatically.
        // In this example, we'll just assume you have a basic XML
        // layout which defines a LinearLayout with two TextViews.

        if (showLabel)
            label.setVisibility(View.VISIBLE);
        else
            label.setVisibility(View.GONE); // can also use View.INVISIBLE
        // depending on your needs

        if (showData)
            data.setVisibility(View.VISIBLE);
        else
            data.setVisibility(View.GONE); // can also use View.INVISIBLE
            // depending on your needs
    }
}

