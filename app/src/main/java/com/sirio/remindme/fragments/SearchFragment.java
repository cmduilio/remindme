package com.sirio.remindme.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.VolleyError;
import com.sirio.remindme.R;
import com.sirio.remindme.adapters.BookListAdapter;
import com.sirio.remindme.entities.AvailableBooks;
import com.sirio.remindme.entities.BookDetail;
import com.sirio.remindme.entities.BookDetailDTO;
import com.sirio.remindme.events.OnBookClick;
import com.sirio.remindme.services.GetAvailableBooks;
import com.sirio.remindme.services.GetBookDetail;
import com.sirio.remindme.services.ServerCallback;

public class SearchFragment extends BaseFragment implements OnBookClick {

    private GetAvailableBooks getAvailableBooks = new GetAvailableBooks();
    private GetBookDetail getBookDetail = new GetBookDetail();
    private SwipeRefreshLayout swipeRefreshLayout;
    private int size = 0;
    private int sizeTotal = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View searchView = inflater.inflate(R.layout.fragment_search, container, false);
        this.swipeRefreshLayout = (SwipeRefreshLayout) searchView.findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                size = 0;
                getAllBooks(searchView, true);

            }
        });

        getAllBooks(searchView, true);

        // Inflate the layout for this fragment
        return searchView;
    }

    private void getAllBooks(final View searchView, boolean onCreateView) {

        getAvailableBooks.run(getContext(),
            new ServerCallback<AvailableBooks>() {
                @Override
                public void onSuccess(final AvailableBooks availableBooks) {
                    RecyclerView list = (RecyclerView) searchView.findViewById(R.id.list);

                    sizeTotal = availableBooks.getPayload().size();
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    list.setLayoutManager(layoutManager);

                    final BookListAdapter adapter = new BookListAdapter(availableBooks.getPayload(),
                            SearchFragment.this);
                    list.setAdapter(adapter);

                    getBookDetail.run(getContext(),
                        availableBooks.getPayload(), new ServerCallback<BookDetailDTO>(){

                            @Override
                            public void onSuccess(BookDetailDTO result) {
                                adapter.notifyDataSetChanged();
                                size++;
                                if(size == sizeTotal)
                                    swipeRefreshLayout.setRefreshing(false);
                            }

                            @Override
                            public void onFail(VolleyError result) {

                            }
                        });

                }

                @Override
                public void onFail(VolleyError result) {

                }
            });
    }

    @Override
    public void onBookClick(BookDetail bookDetail) {
        changeFragmentWithAnimation(new BookDetailFragment(bookDetail));
    }
}
