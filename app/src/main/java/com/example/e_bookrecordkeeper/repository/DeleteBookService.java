package com.example.e_bookrecordkeeper.repository;

import android.os.AsyncTask;

import com.example.e_bookrecordkeeper.model.Book;
import com.example.e_bookrecordkeeper.model.BookDAO;

public class DeleteBookService extends AsyncTask<Book,Void,Void> {
    BookDAO bookDAO;

    public DeleteBookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    protected Void doInBackground(Book... books) {
        bookDAO.deleteBook(books[0]);
        return null;
    }
}
