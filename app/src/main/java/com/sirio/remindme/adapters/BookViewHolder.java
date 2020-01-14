package com.sirio.remindme.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sirio.remindme.R;
import com.sirio.remindme.entities.BookDetail;
import com.sirio.remindme.events.OnBookClick;

public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView name;
    private TextView last;
    private OnBookClick onBookClick;
    private int position;
    private BookDetail bookDetail;

    public BookViewHolder(View itemView, OnBookClick onBookClick) {
        super(itemView);
        this.name = (TextView) itemView.findViewById(R.id.name);
        this.last = (TextView) itemView.findViewById(R.id.last);
        this.onBookClick = onBookClick;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onBookClick.onBookClick(bookDetail);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setNameText(String name) {
        this.name.setText(name);
    }

    public void setLastText(String last) {
        this.last.setText(last);
    }

    public void setBookDetail(BookDetail bookDetail){
        this.bookDetail = bookDetail;
    }
}

