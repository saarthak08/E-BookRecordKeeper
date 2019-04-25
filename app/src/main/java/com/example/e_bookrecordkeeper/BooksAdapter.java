package com.example.e_bookrecordkeeper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_bookrecordkeeper.databinding.BookListItemBinding;
import com.example.e_bookrecordkeeper.model.Book;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder>{
    private OnItemClickListener listener;
    private ArrayList<Book> books=new ArrayList<>();



    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        BookListItemBinding bookListItemBinding= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.book_list_item,viewGroup,false);
        return new BookViewHolder(bookListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder bookViewHolder, int i) {
        Book book=books.get(i);
        bookViewHolder.bookListItemBinding.setBook(book);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> newbooks) {
       final DiffUtil.DiffResult result=DiffUtil.calculateDiff(new BookDiffUtil(books,newbooks),false);
       books=newbooks;
       result.dispatchUpdatesTo(BooksAdapter.this);
        //this.books = books;
        //notifyDataSetChanged();
    }

    class BookViewHolder extends RecyclerView.ViewHolder{
        private BookListItemBinding bookListItemBinding;

        public BookViewHolder(@NonNull BookListItemBinding bookListItemBinding) {
            super(bookListItemBinding.getRoot());
            this.bookListItemBinding=bookListItemBinding;
            bookListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPosition=getAdapterPosition();
                    if(listener!=null && clickedPosition!=RecyclerView.NO_POSITION) {
                        listener.onItemClick(books.get(clickedPosition));
                    }
                }
            });

        }

    }

    public interface OnItemClickListener{
        void onItemClick(Book book);
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
