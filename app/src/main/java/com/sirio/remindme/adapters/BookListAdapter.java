package com.sirio.remindme.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sirio.remindme.R;
import com.sirio.remindme.entities.Book;
import com.sirio.remindme.events.OnBookClick;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookViewHolder> {

    private List<Book> books;
    private OnBookClick onBookClick;

    public BookListAdapter(List<Book> books, OnBookClick onBookClick) {
        this.books = books;
        this.onBookClick = onBookClick;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LinearLayout mLayout = (LinearLayout)
                LayoutInflater.from(parent.getContext()).inflate(R.layout.book_layout, parent, false);

        BookViewHolder bookViewHolder = new BookViewHolder(mLayout, onBookClick);

        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = this.books.get(position);
        String lastPrice = book.getBookDetail() != null ? book.getBookDetail().getLast() : "";
        holder.setNameText(book.getBook());
        holder.setLastText(lastPrice);
        holder.setPosition(position);
        holder.setBookDetail(books.get(position).getBookDetail());
    }

    @Override
    public int getItemCount() {
        return this.books.size();
    }
}