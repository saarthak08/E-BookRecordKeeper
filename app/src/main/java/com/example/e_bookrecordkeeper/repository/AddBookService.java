package com.example.e_bookrecordkeeper.repository;

import android.os.AsyncTask;

import com.example.e_bookrecordkeeper.model.Book;
import com.example.e_bookrecordkeeper.model.BookDAO;

public class AddBookService extends AsyncTask<Book,Void,Void> {
    BookDAO bookDAO;

    public AddBookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    protected Void doInBackground(Book... books) {
        bookDAO.addBook(books[0]);
        return null;
    }
}
