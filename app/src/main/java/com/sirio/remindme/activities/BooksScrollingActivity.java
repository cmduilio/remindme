package com.sirio.remindme.activities;

import android.os.Bundle;
import com.sirio.remindme.R;
import androidx.appcompat.app.AppCompatActivity;
/*
  Main Activity where all the books will be stored
  it will have a fragment inside which will change on book pressed
 */
public class BooksScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_scrolling);
    }
}
