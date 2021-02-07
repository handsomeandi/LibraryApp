package com.example.libraryapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AllBooksyActivity extends AppCompatActivity {

    //Recycler View element and RecycleViewAdapter
    RecyclerView booksRecycleView;
    BookRecycleViewAdapter myBookAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        //Display back button at the top of the screen
//        AllBooksyActivity.this.setSupportActionBar(new Toolbar(this));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        booksRecycleView = findViewById(R.id.booksRecyclerView);
        //Creating an instance of RecycleViewAdapter
        myBookAdapter = new BookRecycleViewAdapter(this, "allBooks");
        //Setting array of books to instance of Utils.getInstance()
        myBookAdapter.setBooks(Utils.getInstance(this).getAllBooks());
        //Setting adapter for RecyclerView
        booksRecycleView.setAdapter(myBookAdapter);
        //Setting LayoutManager for RecycleView(how elements should be displayed)
        booksRecycleView.setLayoutManager(new LinearLayoutManager(this));
    }

    //Setting action to the button at the top of the screen
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}