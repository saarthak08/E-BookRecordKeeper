package com.example.e_bookrecordkeeper.model;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import io.reactivex.Flowable;

@Dao
public interface CategoryDAO {

    @Insert
    void addCategory(Category category);

    @Update
    void updateCategory(Category category);

    @Delete
    void deleteCategory(Category category);

    @Query("SELECT * from categories_table")
    Flowable<List<Category>> getAllCategories();
}
