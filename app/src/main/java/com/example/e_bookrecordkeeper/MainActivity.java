package com.example.e_bookrecordkeeper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.e_bookrecordkeeper.databinding.ActivityMainBinding;
import com.example.e_bookrecordkeeper.model.Book;
import com.example.e_bookrecordkeeper.model.Category;
import com.example.e_bookrecordkeeper.viewmodel.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel viewModel;
    private ActivityMainBinding activityMainBinding;
    private Category selectedCategory;
    private ArrayList<Category> categoriesList;
    private ArrayList<Book> bookArrayList;
    private RecyclerView booksRecyclerView;
    private BooksAdapter booksAdapter;
    private int selectedBookId;
    public static final int ADD_BOOK_REQUEST_CODE=1;
    public static final int EDIT_BOOK_REQUEST_CODE=2;



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
            Intent intent=new Intent(MainActivity.this,AddAndEditActivity.class);
            startActivityForResult(intent,ADD_BOOK_REQUEST_CODE);

        }
        public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {
            selectedCategory = (Category) parent.getItemAtPosition(pos);
            loadBooksArrayList(selectedCategory.getId());
        }
    }

    private void loadBooksArrayList(int categoryId){
        viewModel.getAllBooksOfACategory(categoryId).observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(@Nullable List<Book> books) {
                bookArrayList=(ArrayList<Book>) books;
                loadRecyclerView();
            }
        });

    }

    private void loadRecyclerView() {
        booksRecyclerView = activityMainBinding.secondaryLayout.rvBooks;
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        booksRecyclerView.setHasFixedSize(true);
        booksAdapter = new BooksAdapter();
        booksRecyclerView.setAdapter(booksAdapter);
        booksAdapter.setBooks(bookArrayList);
        booksAdapter.setListener(new BooksAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book book) {
                selectedBookId=book.getBookId();
                Log.i("BookIdTest"," at 1 id is "+selectedBookId);
                Intent intent=new Intent(MainActivity.this, AddAndEditActivity.class);
                intent.putExtra(AddAndEditActivity.BOOK_ID,selectedBookId);
                Log.i("BookIdTest"," at 2 id is "+selectedBookId);
                intent.putExtra(AddAndEditActivity.BOOK_NAME,book.getBookName());
                intent.putExtra(AddAndEditActivity.UNIT_PRICE,book.getBookPrice());
                startActivityForResult(intent,EDIT_BOOK_REQUEST_CODE);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                Book bookToDelete=bookArrayList.get(viewHolder.getAdapterPosition());
                viewModel.deleteBook(bookToDelete);
            }
        }).attachToRecyclerView(booksRecyclerView);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("BookIdTest"," at 4 top id is "+selectedBookId);
        int selectedCategoryId=selectedCategory.getId();
        if(requestCode==ADD_BOOK_REQUEST_CODE && resultCode==RESULT_OK){
            Log.i("BookIdTest"," at 4 wrong 2 id is "+selectedBookId);
            Book book=new Book();
            book.setCategoryId(selectedCategoryId);
            book.setBookName(data.getStringExtra(AddAndEditActivity.BOOK_NAME));
            book.setBookPrice(data.getStringExtra(AddAndEditActivity.UNIT_PRICE));
            viewModel.addBook(book);



        } else if (requestCode == EDIT_BOOK_REQUEST_CODE && resultCode == RESULT_OK) {

            Book book=new Book();
            book.setCategoryId(selectedCategoryId);
            book.setBookName(data.getStringExtra(AddAndEditActivity.BOOK_NAME));
            book.setBookPrice(data.getStringExtra(AddAndEditActivity.UNIT_PRICE));
            Log.i("BookIdTest"," at 4 id is "+selectedBookId);
            book.setBookId(selectedBookId);
            viewModel.updateBook(book);


        }
    }

}
