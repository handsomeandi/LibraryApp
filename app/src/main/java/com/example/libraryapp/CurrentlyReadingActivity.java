package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class CurrentlyReadingActivity extends AppCompatActivity {
    BookRecycleViewAdapter adapter;
    RecyclerView currentlyReadingRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_reading);
        currentlyReadingRecView = findViewById(R.id.currentlyReadingBooksView);
        adapter = new BookRecycleViewAdapter(CurrentlyReadingActivity.this, "currentlyReading");
        adapter.setBooks(Utils.getInstance(this).getCurrentlyReadingBooks());
        currentlyReadingRecView.setAdapter(adapter);
        currentlyReadingRecView.setLayoutManager(new LinearLayoutManager(this));
    }
    //Overriding Back button action to update state of Layout(button changed it's look)
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}