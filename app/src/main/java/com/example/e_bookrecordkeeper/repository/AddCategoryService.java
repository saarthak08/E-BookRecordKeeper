package com.example.e_bookrecordkeeper.repository;

import android.os.AsyncTask;

import com.example.e_bookrecordkeeper.model.Category;
import com.example.e_bookrecordkeeper.model.CategoryDAO;

public class AddCategoryService extends AsyncTask<Category,Void,Void> {
    CategoryDAO categoryDAO;

    public AddCategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    protected Void doInBackground(Category... categories) {
        categoryDAO.addCategory(categories[0]);
        return null;
    }
}
