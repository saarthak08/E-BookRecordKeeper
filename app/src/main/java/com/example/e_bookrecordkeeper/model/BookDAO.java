package com.example.e_bookrecordkeeper.model;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface BookDAO {

    @Insert
    void addBook(Book book);

    @Update
    void updateBook(Book book);

    @Delete
    void deleteBook(Book book);

    @Query("select * from book_table")
    LiveData<List<Book>> getAllBooks();

    @Query("select * from book_table where category_id==:categoryId")
    LiveData<List<Book>> getCertainBooks(int categoryId);
}
