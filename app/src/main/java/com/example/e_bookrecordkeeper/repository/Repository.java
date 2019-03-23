package com.example.e_bookrecordkeeper.repository;

import android.app.Application;

import com.example.e_bookrecordkeeper.model.Book;
import com.example.e_bookrecordkeeper.model.BookDAO;
import com.example.e_bookrecordkeeper.model.Category;
import com.example.e_bookrecordkeeper.model.CategoryDAO;
import com.example.e_bookrecordkeeper.model.Database;

import java.util.List;

import androidx.lifecycle.LiveData;

public class Repository {
    private CategoryDAO categoryDAO;
    private BookDAO bookDAO;
    private LiveData<List<Category>> category;
    private LiveData<List<Book>> books;

    public Repository(Application application) {
        Database database= Database.getInstance(application);
        categoryDAO=database.getcategoryDAO();
        bookDAO=database.getbookDAO();
    }

    public LiveData<List<Category>> getCategories()
    {
        return categoryDAO.getAllCategories();
    }

    public LiveData<List<Book>> getBooks()
    {
        return bookDAO.getAllBooks();
    }

    public void addCategory(Category category)
    {
        new AddCategoryService(categoryDAO).execute(category);
    }

    public void addBook(Book book)
    {
        new AddBookService(bookDAO).execute(book);
    }
}
