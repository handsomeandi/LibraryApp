package com.example.libraryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class BooksyActivity extends AppCompatActivity {
    ImageView bigImg;
    Button btnWantToRead, btnCurrentlyReading, btnAddToFavorite, btnAlreadyRed;
    TextView txtName, txtAuthor, txtPages, txtDescription;
    public static final String BOOK_ID = "bookId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        init_views();
        //Handling the look of book Activity(how buttons look depending on in which lists book is added)
        Intent intent = getIntent();
        if(intent!=null) {
            Book book = Utils.getInstance(this).findBookById(intent.getIntExtra(BOOK_ID, -1));
            if (book != null) {
                setData(book);
                handleAlreadyRead(book);
                handleCurrentlyReading(book);
                handleFavoriteBooks(book);
                handleWishListBooks(book);
            }
        }
    }


    //Defining looks of buttons and creating onClickListeners that append books to corresponding Lists
    private void handleWishListBooks(Book book){
        boolean existsInWishListBooks = false;
        for(Book b: Utils.getInstance(this).getWishListBooks()){
            if(b==book){
                existsInWishListBooks = true;
            }
        }
        if(!existsInWishListBooks){
            btnWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(BooksyActivity.this, "Book successfully added to WishList", Toast.LENGTH_SHORT).show();
                    Utils.getInstance(BooksyActivity.this).setWishListBooks(book);
                    btnWantToRead.setEnabled(false);
                    Intent intent = new Intent(BooksyActivity.this, WishListActivity.class);
                    startActivity(intent);
                }
            });
        }else{
            btnWantToRead.setEnabled(false);
        }
    }

    public void handleFavoriteBooks(Book book){
        boolean existsInFavoriteBooks = false;
        for(Book b: Utils.getInstance(this).getFavoriteBooks()){
            if(b==book){
                existsInFavoriteBooks = true;
            }
        }
        if(!existsInFavoriteBooks){
            btnAddToFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(BooksyActivity.this, "Book successfully added to Favorite Books", Toast.LENGTH_SHORT).show();
                    Utils.getInstance(BooksyActivity.this).setFavoriteBooks(book);
                    btnAddToFavorite.setEnabled(false);
                    Intent intent = new Intent(BooksyActivity.this, FavoriteBooksActivity.class);
                    startActivity(intent);
                }
            });
        }else{
            btnAddToFavorite.setEnabled(false);
        }
    }

    public void handleCurrentlyReading(Book book){
        boolean existsInCurrentlyReading = false;
        for(Book b: Utils.getInstance(this).getCurrentlyReadingBooks()){
            if(b==book){
                existsInCurrentlyReading = true;
            }
        }
        if(!existsInCurrentlyReading){
            btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(BooksyActivity.this, "Book successfully added to Currently Reading", Toast.LENGTH_SHORT).show();
                    Utils.getInstance(BooksyActivity.this).setCurrentlyReadingBooks(book);
                    btnCurrentlyReading.setEnabled(false);
                    Intent intent = new Intent(BooksyActivity.this, CurrentlyReadingActivity.class);
                    startActivity(intent);
                }
            });
        }else{
            btnCurrentlyReading.setEnabled(false);
        }
    }

    public void handleAlreadyRead(Book book){
        boolean existsInAlreadyRead = false;
        for(Book b: Utils.getInstance(this).getAlreadyReadBooks()){
            if(b==book){
                existsInAlreadyRead = true;
            }
        }
        if(!existsInAlreadyRead){
            btnAlreadyRed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(BooksyActivity.this, "Book successfully added to Already Read", Toast.LENGTH_SHORT).show();
                    Utils.getInstance(BooksyActivity.this).setAlreadyReadBook(book);
                    btnAlreadyRed.setEnabled(false);
                    Intent intent = new Intent(BooksyActivity.this, AlreadyReadBooksActivity.class);
                    startActivity(intent);
                }
            });
        }else{
            btnAlreadyRed.setEnabled(false);
        }
    }

    //Setting data of Book page
    private void setData(Book book){
        txtName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());
        Picasso.get().load(book.getImageUrl()).into(bigImg);
    }
    private void init_views() {
        bigImg = findViewById(R.id.bookImageBig);
        btnWantToRead = findViewById(R.id.btnWantToRead);
        btnCurrentlyReading = findViewById(R.id.btnCurrentlyReading);
        btnAddToFavorite = findViewById(R.id.btnAddFavorite);
        btnAlreadyRed = findViewById(R.id.btnAlreadyRead);
        txtName = findViewById(R.id.nameOfBook);
        txtAuthor = findViewById(R.id.authorOfBook);
        txtPages = findViewById(R.id.numberOfPages);
        txtDescription = findViewById(R.id.longDesc);
    }
}