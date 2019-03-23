package com.example.e_bookrecordkeeper.repository;

import android.os.AsyncTask;

import com.example.e_bookrecordkeeper.model.Book;
import com.example.e_bookrecordkeeper.model.BookDAO;

public class UpdateBookService extends AsyncTask<Book,Void,Void> {
    BookDAO bookDAO;

    public UpdateBookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    protected Void doInBackground(Book... books) {
        bookDAO.updateBook(books[0]);
        return null;
    }
}
