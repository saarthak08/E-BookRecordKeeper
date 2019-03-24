package com.example.e_bookrecordkeeper.viewmodel;

import android.app.Application;

import com.example.e_bookrecordkeeper.model.Book;
import com.example.e_bookrecordkeeper.model.Category;
import com.example.e_bookrecordkeeper.repository.Repository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MainActivityViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Category>> category;
    private LiveData<List<Book>> book;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
    }
    public LiveData<List<Category>> getAllCategories()
    {
        return repository.getCategories();
    }

    public LiveData<List<Book>> getAllBooksOfACategory(int categoryId)
    {
        return repository.getCertainBooks(categoryId);
    }

    public void addBook(Book book)
    {
        repository.addBook(book);
    }
    public void deleteBook(Book book)
    {
        repository.deleteBook(book);
    }
    public void updateBook(Book book)
    {
        repository.deleteBook(book);
    }
}
