package com.example.e_bookrecordkeeper.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.e_bookrecordkeeper.model.Book;
import com.example.e_bookrecordkeeper.model.Category;
import com.example.e_bookrecordkeeper.repository.Repository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private Repository repository;
    private LiveData<List<Category>> category;
    private LiveData<List<Book>> book;
    public MainActivityViewModel(Repository repository) {
        this.repository=repository;
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
