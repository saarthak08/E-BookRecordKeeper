package com.example.e_bookrecordkeeper;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.e_bookrecordkeeper.databinding.ActivityMainBinding;
import com.example.e_bookrecordkeeper.model.Book;
import com.example.e_bookrecordkeeper.model.Category;
import com.example.e_bookrecordkeeper.viewmodel.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel viewModel;
    private ActivityMainBinding activityMainBinding;
    private Category selectedCategory;
    private ArrayList<Category> categoriesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        activityMainBinding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        activityMainBinding.setClickHandlers(new MainActivityClickHandler());

        FloatingActionButton fab = findViewById(R.id.fab);
        viewModel= ViewModelProviders.of(MainActivity.this).get(MainActivityViewModel.class);
        viewModel.getAllCategories().observe(MainActivity.this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoriesList=(ArrayList<Category>)categories;
                for(Category c: categories)
                {
                    Log.i("MyTAG2",c.getCategoryName());
                }
                showOnSpinner();

            }
        });
        viewModel.getAllBooksOfACategory(3).observe(MainActivity.this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                for(Book b: books)
                {
                    Log.i("MyTAG",b.getBookName());
                }
            }
        });

    }

    private void showOnSpinner(){

        ArrayAdapter<Category> categoryArrayAdapter=new ArrayAdapter<Category>(this,R.layout.spinner_item,categoriesList);
        categoryArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        activityMainBinding.setAndroidSpinner(categoryArrayAdapter);

    }

    public class MainActivityClickHandler
    {
        public void onFABButtonClicked(View view)
        {
            Toast.makeText(getApplicationContext(),"FAB Button Clicked",Toast.LENGTH_LONG).show();
        }

        public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {

            selectedCategory = (Category) parent.getItemAtPosition(pos);

            String message = " id is " + selectedCategory.getId() + "\n name is " + selectedCategory.getCategoryName() + "\n email is " + selectedCategory.getCategoryDescription();

            // Showing selected spinner item
            Toast.makeText(parent.getContext(), message, Toast.LENGTH_LONG).show();


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
