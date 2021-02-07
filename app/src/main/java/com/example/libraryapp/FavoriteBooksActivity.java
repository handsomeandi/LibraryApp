package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class FavoriteBooksActivity extends AppCompatActivity {
    
    BookRecycleViewAdapter adapter;
    RecyclerView favoriteBooksRecView;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_books);
        favoriteBooksRecView = findViewById(R.id.favoriteBooksRecView);
        adapter = new BookRecycleViewAdapter(FavoriteBooksActivity.this, "favoriteBooks");
        adapter.setBooks(Utils.getInstance(this).getFavoriteBooks());
        favoriteBooksRecView.setAdapter(adapter);
        favoriteBooksRecView.setLayoutManager(new LinearLayoutManager(this));
    
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}