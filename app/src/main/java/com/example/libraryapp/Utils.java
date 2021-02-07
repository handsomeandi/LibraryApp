package com.example.libraryapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.ScaleGestureDetector;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_BOOK = "already_read_books";
    private static final String WANT_TO_READ_BOOKS = "want_to_read_books";
    private static final String FAVORITE_BOOKS = "favorite_books";
    private static final String CURRENTLY_READING_BOOKS = "currently_reading_books";
    private SharedPreferences sharedPreferences;

    private static Utils instance;
//    private static ArrayList<Book> allBooks;
//    private static ArrayList<Book> favoriteBooks;
//    private static ArrayList<Book> wishListBooks;
//    private static ArrayList<Book> currentlyReadingBooks;
//    private static ArrayList<Book> alreadyReadBooks;

    private Utils(Context context){

        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);

        if(null == getAllBooks()){
            initData();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();


        if(null == getAlreadyReadBooks()){
            editor.putString(ALREADY_READ_BOOK, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if(null == getWishListBooks()){
            editor.putString(WANT_TO_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if(null == getCurrentlyReadingBooks()){
            editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if(null == getFavoriteBooks()){
            editor.putString(FAVORITE_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

    public  ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
        return books;
    }

    public  ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVORITE_BOOKS, null), type);
        return books;
    }

    public  ArrayList<Book> getWishListBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS, null), type);
        return books;
    }

    public  ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS, null), type);
        return books;
    }

    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOK, null), type);
        return books;
    }

    private void initData(){
        //TODO: initialize data
        ArrayList<Book> allBooks = new ArrayList<>();
        allBooks.add(new Book(1,"Master and Margarita", "Bulghakov",
                384,"https://almabooks.com/wp-content/uploads/2016/10/9781847492425.jpg",
                "The novel about Satan coming to Earth",
                "The novel has two settings. The first is Moscow during the 1930s, where Satan appears at Patriarch's Ponds as Professor Woland."));
        allBooks.add(new Book(2,"Crime and punishment", "Dostoevsky",
                432,"https://m.media-amazon.com/images/I/41AM6pjaZhL.jpg",
                "Guy kills granny(maybe)",
                "Through the story of the brilliant but conflicted young Raskolnikov and the murder he commits." ));
        allBooks.add(new Book(3,"Don Quixote de la Mancha", "Servantes",
                949,"https://m.media-amazon.com/images/I/51PWA6HDUDL.jpg",
                "Guy travells around the world",
                "Cervantes wrote that the first chapters were taken from \"the archives of La Mancha\", and the rest were translated from an Arabic text by the Moorish author Cide Hamete Benengeli. This metafictional trick appears to give a greater credibility to the text, implying that Don Quixote is a real character and that the events related truly occurred several decades prior to the recording of this account. However, it was also common practice in that era for fictional works to make some pretense of being factual, such as the common opening line of fairy tales \"Once upon a time in a land far away...\".\n" +
                        "\n" +
                        "In the course of their travels, the protagonists meet innkeepers, prostitutes, goat-herders, soldiers, priests, escaped convicts and scorned lovers. The aforementioned characters sometimes tell tales that incorporate events from the real world, like the conquest of the Kingdom of Maynila or battles in the Eighty Years' War. Their encounters are magnified by Don Quixote's imagination into chivalrous quests. Don Quixote's tendency to intervene violently in matters irrelevant to himself, and his habit of not paying debts, result in privations, injuries, and humiliations (with Sancho often the victim). Finally, Don Quixote is persuaded to return to his home village. The narrator hints that there was a third quest, but says that records of it have been lost."));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(allBooks));
        editor.commit();
    }

    public void setAlreadyReadBook(Book alreadyReadBook) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if(books != null){
            if(books.add(alreadyReadBook)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOK);
                editor.putString(ALREADY_READ_BOOK, gson.toJson((books)));
                editor.commit();
            }
        }
    }

    public void setFavoriteBooks(Book favoriteBook) {
        ArrayList<Book> books = getFavoriteBooks();
        if(books != null){
            if(books.add(favoriteBook)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVORITE_BOOKS);
                editor.putString(FAVORITE_BOOKS, gson.toJson((books)));
                editor.commit();
            }
        }
    }

    public  void setWishListBooks(Book book) {
        ArrayList<Book> books = getWishListBooks();
        if(books != null){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS);
                editor.putString(WANT_TO_READ_BOOKS, gson.toJson((books)));
                editor.commit();
            }
        }
    }

    public void setCurrentlyReadingBooks(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if(books != null){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS, gson.toJson((books)));
                editor.commit();
            }
        }
    }

    public Book findBookById(int id){
        ArrayList<Book> books = getAllBooks();
        if(null != books) {
            for (Book i : books) {
                if (i.getId() == id) {
                    return i;
                }
            }
        }
        return null;
    }

    public static synchronized Utils getInstance(Context context){
        if(null==instance){
            instance = new Utils(context);
        }
        return instance;
    }

    public boolean removeAlreadyReadBooks(Book alreadyReadBook) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if(null != books){
            for(Book b: books){
                if(b.getId() == alreadyReadBook.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOK);
                        editor.putString(ALREADY_READ_BOOK, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFavoriteBooks(Book favoriteBook) {
        ArrayList<Book> books = getFavoriteBooks();
        if(null != books){
            for(Book b: books){
                if(b.getId() == favoriteBook.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVORITE_BOOKS);
                        editor.putString(FAVORITE_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeWishListBooks(Book book) {
        ArrayList<Book> books = getWishListBooks();
        if(null != books){
            for(Book b: books){
                if(b.getId() == book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOKS);
                        editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeCurrentlyReadingBooks(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if(null != books){
            for(Book b: books){
                if(b.getId() == book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
