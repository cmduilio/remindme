package com.sirio.remindme.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.VolleyError;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sirio.remindme.R;
import com.sirio.remindme.adapters.ReminderListAdapter;
import com.sirio.remindme.adapters.ReminderItemDecorator;
import com.sirio.remindme.entities.AvailableBooks;
import com.sirio.remindme.entities.Reminder;
import com.sirio.remindme.events.OnReminderClick;
import com.sirio.remindme.services.GetAvailableBooks;
import com.sirio.remindme.services.ServerCallback;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends BaseFragment implements OnReminderClick {

    private GetAvailableBooks getAvailableBooks = new GetAvailableBooks();
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton fab;
    private int size = 0;
    private int sizeTotal = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View searchView = inflater.inflate(R.layout.fragment_search, container, false);

        this.fab = (FloatingActionButton) searchView.findViewById(R.id.fab);

        this.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("ASDDDDDDD");
            }
        });

        this.swipeRefreshLayout = (SwipeRefreshLayout) searchView.findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                size = 0;
                getAllBooks(searchView, true);

            }
        });

        RecyclerView list = (RecyclerView) searchView.findViewById(R.id.list);

        ReminderItemDecorator reminderItemDecorator = new ReminderItemDecorator(getContext(),
                DividerItemDecoration.VERTICAL, false);
        list.addItemDecoration(reminderItemDecorator);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        list.setLayoutManager(layoutManager);

        List<Reminder> asd = new ArrayList<>();
        Reminder reminder1 = new Reminder();
        reminder1.setLabel("asdasd");
        reminder1.setDays("JUST ONE TIME");
        reminder1.setTime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        asd.add(reminder1);
        asd.add(reminder1);
        asd.add(reminder1);
        asd.add(reminder1);
        asd.add(reminder1);
        asd.add(reminder1);
        asd.add(reminder1);

        final ReminderListAdapter adapter = new ReminderListAdapter(asd,
                SearchFragment.this);
        list.setAdapter(adapter);

        //getAllBooks(searchView, true);

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

                    final ReminderListAdapter adapter = new ReminderListAdapter(availableBooks.getPayload(),
                            SearchFragment.this);
                    list.setAdapter(adapter);

                    swipeRefreshLayout.setRefreshing(false);
                }

                @Override
                public void onFail(VolleyError result) {

                }
            });
    }

    @Override
    public void onReminderClick(Reminder reminder) {
        changeFragmentWithAnimation(new ReminderDetailFragment(reminder));
    }
}
