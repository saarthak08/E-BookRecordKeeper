package com.example.e_bookrecordkeeper.repository;

import android.os.AsyncTask;

import com.example.e_bookrecordkeeper.model.Category;
import com.example.e_bookrecordkeeper.model.CategoryDAO;

public class DeleteCategoryService extends AsyncTask<Category,Void,Void> {
    CategoryDAO categoryDAO;

    public DeleteCategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    protected Void doInBackground(Category... categories) {
        categoryDAO.deleteCategory(categories[0]);
        return null;
    }
}
