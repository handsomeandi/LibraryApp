package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class AlreadyReadBooksActivity extends AppCompatActivity {

    RecyclerView alreadyReadRecyclerView;
    BookRecycleViewAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_read_books);
        alreadyReadRecyclerView = findViewById(R.id.alreadyReadBooks);
        myAdapter = new BookRecycleViewAdapter(this, "alreadyRead");
        myAdapter.setBooks(Utils.getInstance(this).getAlreadyReadBooks());
        alreadyReadRecyclerView.setAdapter(myAdapter);
        alreadyReadRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}