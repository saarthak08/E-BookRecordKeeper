package com.example.e_bookrecordkeeper.model;


import android.os.Build;

import com.example.e_bookrecordkeeper.BR;

import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "book_table",foreignKeys = @ForeignKey(entity = Category.class,parentColumns = "id",childColumns = "category_id",onDelete = CASCADE))
public class Book extends BaseObservable {
    @ColumnInfo(name = "book_id")
    @PrimaryKey(autoGenerate = true)
    private int bookId;

    @ColumnInfo(name = "book_name")
    private String bookName;
    @ColumnInfo(name = "book_price")
    private String bookPrice;
    @ColumnInfo(name = "category_id")
    private int categoryId;

    @Ignore
    public Book() {
    }

    public Book(int bookId, String bookName, String bookPrice, int categoryId) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.categoryId = categoryId;
    }

    @Bindable
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
        notifyPropertyChanged(BR.bookId);
    }

    @Bindable
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
        notifyPropertyChanged(BR.bookName);
    }

    @Bindable
    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
        notifyPropertyChanged(BR.bookPrice);
    }

    @Bindable
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        notifyPropertyChanged(BR.categoryId);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(this==obj)
        {
            return true;
        }
        if(!(obj instanceof Book)){
            return false;
        }
        Book book=(Book)obj;
        return getBookId()==book.getBookId()&&getBookName()==book.getBookName()&&getBookPrice()==book.getBookPrice()&&getCategoryId()==book.getCategoryId();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(getBookId(),getBookName(),getBookPrice(),getCategoryId());
    }
}
