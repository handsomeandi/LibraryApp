package com.example.libraryapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button allBooks, favoriteBooks, currentlyReadingBooks, alreadyReadBooks, aboutPage, wantToReadBooks;
    TextView appName, licenceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init_views();

        //Setting onClickListeners on every Button of the Main page

        allBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectPage(AllBooksyActivity.class);
            }
        });
        alreadyReadBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectPage(AlreadyReadBooksActivity.class);
            }
        });
        currentlyReadingBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectPage(CurrentlyReadingActivity.class);
            }
        });
        favoriteBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectPage(FavoriteBooksActivity.class);
            }
        });
        wantToReadBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectPage(WishListActivity.class);
            }
        });
        aboutPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Creating intent to navigate to WebView and pasting the url element
                        Intent intent = new Intent(MainActivity.this, WebActivity.class);
                        intent.putExtra("url", "https://vk.com/tanya_luk");
                        startActivity(intent);
                    }
                });
                builder.setTitle("About");
                builder.setMessage("I am very cool software developer and I want to develop different cool stuff for myself and to earn money. Do you want to visit my page in vkontakte.ru");
                builder.setCancelable(false);
                builder.create().show();
            }
        });

        Utils.getInstance(this);
    }

    //Method to redirect to another activity from MainActivity
    private void redirectPage(Class red_page){
        Intent intent = new Intent(MainActivity.this, red_page);
        startActivity(intent);
    }

    private void init_views() {
        allBooks = findViewById(R.id.allBooks);
        favoriteBooks = findViewById(R.id.favoriteBooks);
        currentlyReadingBooks = findViewById(R.id.currentlyReadingBooks);
        alreadyReadBooks = findViewById(R.id.alreadyReadBooks);
        aboutPage = findViewById(R.id.aboutPage);
        wantToReadBooks = findViewById(R.id.toReadBooks);
        appName = findViewById(R.id.appName);
        licenceText = findViewById(R.id.licenceText);
    }
}