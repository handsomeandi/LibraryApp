package com.example.libraryapp;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.libraryapp.BooksyActivity.BOOK_ID;

public class BookRecycleViewAdapter extends RecyclerView.Adapter<BookRecycleViewAdapter.ViewHolder> {
    private static final String TAG = "BookRecycleViewAdapter";

    // Initializing books array(that will be used as reference to book data) and context of the RecyclerView
    private ArrayList<Book> books = new ArrayList<>();
    private Context mContext;
    private String activityName;

    //Creating constructor that gets value of RecyclerView context and name of Activity and assigns it to mContext and activityName variables
    public BookRecycleViewAdapter(Context mContext, String activityName) {
        this.mContext = mContext;
        this.activityName = activityName;
    }

    //Creating view from layout file and passing it to constructor of ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book,parent,false);
        return new ViewHolder(view);

    }

    //Called to set info for every element of RecyclerView
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called");
        holder.bookName.setText(books.get(position).getName());
        holder.txtAuthor.setText(books.get(position).getAuthor());
        holder.txtDescription.setText(books.get(position).getShortDesc());
        Picasso.get().load(books.get(position).getImageUrl()).into(holder.bookImage);

        //Redirecting to BookActivity(Book page) by clicking on book
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,BooksyActivity.class);
                intent.putExtra(BOOK_ID, books.get(position).getId());
                mContext.startActivity(intent);
            }
        });

        //Setting rules for expanded and shrinked versions of books
        if(books.get(position).isExpanded()){
            //Simple animation
            TransitionManager.beginDelayedTransition(holder.parent);
            //
            holder.expandedRelLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);
            if(activityName == "allBooks"){
                holder.deleteBtn.setVisibility(View.GONE);
            }else if(activityName.equals("currentlyReading")){
                holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Creating alert that will pop up when user clicks on Delete Button
                        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                        //If yes clicked - delete item
                        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String book_name = books.get(position).getName();
                                if(Utils.getInstance(mContext).removeCurrentlyReadingBooks(books.get(position))){
                                    Toast.makeText(mContext, book_name + " book removed", Toast.LENGTH_SHORT).show();
                                    //notify that content of instance(Utils) was changed
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        //If no clicked - dismiss
                        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alert.setMessage("Do you want to delete this book?");
                        alert.create().show();
                    }
                });
            }else if(activityName.equals("alreadyRead")){
                holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String book_name = books.get(position).getName();
                                if(Utils.getInstance(mContext).removeAlreadyReadBooks(books.get(position))){
                                    Toast.makeText(mContext, book_name + " book removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alert.setMessage("Do you want to delete this book?");
                        alert.create().show();
                    }
                });
            }else if(activityName.equals("wantToRead")){
                holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String book_name = books.get(position).getName();
                                if(Utils.getInstance(mContext).removeWishListBooks(books.get(position))){
                                    Toast.makeText(mContext, book_name + " book removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alert.setMessage("Do you want to delete this book?");
                        alert.create().show();
                    }
                });
            }else if(activityName.equals("favoriteBooks")){
                holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String book_name = books.get(position).getName();
                                if(Utils.getInstance(mContext).removeFavoriteBooks(books.get(position))){
                                    Toast.makeText(mContext, book_name + " book removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alert.setMessage("Do you want to delete this book?");
                        alert.create().show();
                    }
                });
            }
        }else{
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    //Realization of abstract class RecyclerView.ViewHolder that holds data of single element of RecycleView
    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView parent;
        private ImageView bookImage;
        private TextView bookName;
        private ImageView downArrow, upArrow;
        private RelativeLayout expandedRelLayout;
        private TextView txtAuthor, txtDescription, deleteBtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            bookImage = itemView.findViewById(R.id.imgBook);
            bookName = itemView.findViewById(R.id.bookName);
            downArrow = itemView.findViewById(R.id.btnDownArrow);
            upArrow = itemView.findViewById(R.id.btnUpArrow);
            expandedRelLayout = itemView.findViewById(R.id.expandedRelLayout);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtDescription = itemView.findViewById(R.id.txtShortDesc);
            deleteBtn = itemView.findViewById(R.id.deleteItem);

            //Expand and shrink the book element info
            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }
}
