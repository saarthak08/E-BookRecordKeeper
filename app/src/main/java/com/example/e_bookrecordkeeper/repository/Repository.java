package com.example.e_bookrecordkeeper.repository;

import android.app.Application;
import android.widget.Toast;

import com.example.e_bookrecordkeeper.di.App;
import com.example.e_bookrecordkeeper.model.Book;
import com.example.e_bookrecordkeeper.model.BookDAO;
import com.example.e_bookrecordkeeper.model.Category;
import com.example.e_bookrecordkeeper.model.CategoryDAO;
import com.example.e_bookrecordkeeper.model.Database;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Repository {
    private CategoryDAO categoryDAO;
    private BookDAO bookDAO;
    private MutableLiveData<List<Category>> category=new MutableLiveData<>();
    private MutableLiveData<List<Book>> books=new MutableLiveData<>();
    private MutableLiveData<List<Book>> certainbook=new MutableLiveData<>();
    private CompositeDisposable compositeDisposable=new CompositeDisposable();

    public Repository(Application application) {
        Database database= Database.getInstance(application);
        categoryDAO=database.getcategoryDAO();
        bookDAO=database.getbookDAO();
        compositeDisposable.add(
                categoryDAO.getAllCategories().subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Category>>() {
                    @Override
                    public void accept(List<Category> categories) throws Exception {
                        category.postValue(categories);
                    }
                }));

        compositeDisposable.add(
                bookDAO.getAllBooks().subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<Book>>() {
                            @Override
                            public void accept(List<Book> listLiveData) throws Exception {
                                books.postValue(listLiveData);
                            }
                        })
        );
    }

    public LiveData<List<Category>> getCategories()
    {

        return category;
    }


    public LiveData<List<Book>> getBooks()
    {

        return books;
    }

    public LiveData<List<Book>> getCertainBooks(final int id)
    {
        compositeDisposable.add(
                bookDAO.getCertainBooks(id).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Book>>() {
                    @Override
                    public void accept(List<Book> listLiveData) throws Exception {
                        certainbook.postValue(listLiveData);
                    }
                })
        );
        return certainbook;
    }
    public void addCategory(final Category category)
    {
        compositeDisposable.add(
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                categoryDAO.addCategory(category);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
            }

            @Override
            public void onError(Throwable e) {

            }
        }));
    }

    public void addBook(final Book book)
    {
        compositeDisposable.add(
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                bookDAO.addBook(book);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableCompletableObserver() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        }));
    }
    public void updateCategory(final Category category)
    {
        compositeDisposable.add(Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                categoryDAO.updateCategory(category);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableCompletableObserver() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        }));
    }


    public void updateBook(final Book book)
    {
        compositeDisposable.add(Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                bookDAO.updateBook(book);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableCompletableObserver() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        }));
    }

    public void deleteCategory(final Category category)
    {
        compositeDisposable.add(Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                categoryDAO.deleteCategory(category);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }
    public void deleteBook(final Book book)
    {
        compositeDisposable.add(Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                bookDAO.deleteBook(book);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableCompletableObserver() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        }));
    }
    public void clearDisposable()
    {
        compositeDisposable.clear();
    }
}
