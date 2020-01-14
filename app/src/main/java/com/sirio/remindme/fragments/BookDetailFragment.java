package com.sirio.remindme.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sirio.remindme.R;
import com.sirio.remindme.entities.BookDetail;

import java.math.BigDecimal;

public class BookDetailFragment extends BaseFragment {

    private BookDetail bookDetail;

    public BookDetailFragment(BookDetail bookDetail) {
        super();
        this.bookDetail = bookDetail;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View searchView = inflater.inflate(R.layout.fragment_book_detail, container, false);

        ((TextView) searchView.findViewById(R.id.name)).setText(bookDetail.getBook());
        ((TextView) searchView.findViewById(R.id.bid)).setText(bookDetail.getBid());
        ((TextView) searchView.findViewById(R.id.ask)).setText(bookDetail.getAsk());
        ((TextView) searchView.findViewById(R.id.low)).setText(bookDetail.getLow());
        ((TextView) searchView.findViewById(R.id.high)).setText(bookDetail.getHigh());
        ((TextView) searchView.findViewById(R.id.h24)).setText(bookDetail.getVolume());

        BigDecimal bid = new BigDecimal(bookDetail.getBid());
        BigDecimal ask = new BigDecimal(bookDetail.getAsk());
        System.out.println(bid);
        System.out.println(ask);
        System.out.println(bid.subtract(ask));
        String spread = bid.subtract(ask).toString();

        ((TextView) searchView.findViewById(R.id.spread)).setText(spread);
        return searchView;
    }
}
