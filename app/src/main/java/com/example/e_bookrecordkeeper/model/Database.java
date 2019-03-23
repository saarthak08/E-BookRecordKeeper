package com.example.e_bookrecordkeeper.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@androidx.room.Database(entities = {Category.class,Book.class},version = 1)
public abstract class Database extends RoomDatabase {

    public abstract BookDAO getbookDAO();

    public abstract CategoryDAO getcategoryDAO();

    private static Database instance;

    public static synchronized Database getInstance(Context context)
    {
        if(instance==null)
        {
            instance= Room.databaseBuilder(context.getApplicationContext(),Database.class,"books_database").addCallback(callback).fallbackToDestructiveMigration().build();
        }
        return instance;
    }
    private static RoomDatabase.Callback callback=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InitialDataAsyncTask(instance).execute();
        }
    };

    private static class InitialDataAsyncTask extends AsyncTask<Void,Void,Void>
    {
        private static BookDAO bookDAO;
        private static CategoryDAO categoryDAO;

        public InitialDataAsyncTask(Database database) {
            bookDAO=database.getbookDAO();
            categoryDAO=database.getcategoryDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Category category1=new Category();
            category1.setCategoryName("Text Books");
            category1.setCategoryDescription("Text Books Description");

            Category category2=new Category();
            category2.setCategoryName("Novels");
            category2.setCategoryDescription("Novels Description");

            Category category3=new Category();
            category3.setCategoryName("Other Books");
            category3.setCategoryDescription("Text Books Description");

            categoryDAO.addCategory(category1);
            categoryDAO.addCategory(category2);
            categoryDAO.addCategory(category3);

            Book book1=new Book();
            book1.setBookName("High school Java ");
            book1.setBookPrice("$150");
            book1.setCategoryId(1);

            Book book2=new Book();
            book2.setBookName("Mathematics for beginners");
            book2.setBookPrice("$200");
            book2.setCategoryId(1);

            Book book3=new Book();
            book3.setBookName("Object Oriented Androd App Design");
            book3.setBookPrice("$150");
            book3.setCategoryId(1);

            Book book4=new Book();
            book4.setBookName("Astrology for beginners");
            book4.setBookPrice("$190");
            book4.setCategoryId(1);

            Book book5=new Book();
            book5.setBookName("High school Magic Tricks ");
            book5.setBookPrice("$150");
            book5.setCategoryId(1);

            Book book6=new Book();
            book6.setBookName("Chemistry  for secondary school students");
            book6.setBookPrice("$250");
            book6.setCategoryId(1);

            Book book7=new Book();
            book7.setBookName("A Game of Cats");
            book7.setBookPrice("$19.99");
            book7.setCategoryId(2);

            Book book8=new Book();
            book8.setBookName("The Hound of the New York");
            book8.setBookPrice("$16.99");
            book8.setCategoryId(2);

            Book book9=new Book();
            book9.setBookName("Adventures of Joe Finn");
            book9.setBookPrice("$13");
            book9.setCategoryId(2);

            Book book10=new Book();
            book10.setBookName("Arc of witches");
            book10.setBookPrice("$19.99");
            book10.setCategoryId(2);

            Book book11=new Book();
            book11.setBookName("Can I run");
            book11.setBookPrice("$16.99");
            book11.setCategoryId(2);

            Book book12=new Book();
            book12.setBookName("Story of a joker");
            book12.setBookPrice("$13");
            book12.setCategoryId(2);

            Book book13=new Book();
            book13.setBookName("Notes of a alien life cycle researcher");
            book13.setBookPrice("$1250");
            book13.setCategoryId(3);

            Book book14=new Book();
            book14.setBookName("Top 9 myths abut UFOs");
            book14.setBookPrice("$789");
            book14.setCategoryId(3);

            Book book15=new Book();
            book15.setBookName("How to become a millionaire in 24 hours");
            book15.setBookPrice("$1250");
            book15.setCategoryId(3);

            Book book16=new Book();
            book16.setBookName("1 hour work month");
            book16.setBookPrice("$199");
            book16.setCategoryId(3);

            bookDAO.addBook(book1);
            bookDAO.addBook(book2);
            bookDAO.addBook(book3);
            bookDAO.addBook(book4);
            bookDAO.addBook(book5);
            bookDAO.addBook(book6);
            bookDAO.addBook(book7);
            bookDAO.addBook(book8);
            bookDAO.addBook(book9);
            bookDAO.addBook(book10);
            bookDAO.addBook(book11);
            bookDAO.addBook(book12);
            bookDAO.addBook(book13);
            bookDAO.addBook(book14);
            bookDAO.addBook(book15);
            bookDAO.addBook(book16);
            return null;
        }
    }
}

