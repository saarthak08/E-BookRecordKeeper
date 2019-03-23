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

    public LiveData<List<Book>> getCertainBooks(int id)
    {
        return bookDAO.getCertainBooks(id);
    }
    public void addCategory(Category category)
    {
        new AddCategoryService(categoryDAO).execute(category);
    }

    public void addBook(Book book)
    {
        new AddBookService(bookDAO).execute(book);
    }
    public void updateCategory(Category category)
    {
        new UpdateCategoryService(categoryDAO).execute(category);
    }
    public void updateBook(Book book)
    {
        new UpdateBookService(bookDAO).execute(book);
    }
    public void deleteCategory(Category category)
    {
        new DeleteCategoryService(categoryDAO).execute(category);
    }
    public void deleteBook(Book book)
    {
        new DeleteBookService(bookDAO).execute(book);
    }
}
