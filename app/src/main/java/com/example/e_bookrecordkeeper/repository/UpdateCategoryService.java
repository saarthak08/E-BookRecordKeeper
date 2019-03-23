package com.example.e_bookrecordkeeper.repository;

import android.os.AsyncTask;

import com.example.e_bookrecordkeeper.model.Category;
import com.example.e_bookrecordkeeper.model.CategoryDAO;

public class UpdateCategoryService extends AsyncTask<Category,Void,Void> {
    CategoryDAO categoryDAO;

    public UpdateCategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    protected Void doInBackground(Category... categories) {
        categoryDAO.updateCategory(categories[0]);
        return null;
    }
}
