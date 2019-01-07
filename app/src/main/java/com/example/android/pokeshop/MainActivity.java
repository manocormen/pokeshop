package com.example.android.pokeshop;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.android.pokeshop.data.ProductsContract;
import com.example.android.pokeshop.data.ProductsContract.ProductsEntry;
import com.example.android.pokeshop.data.ProductsDbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Inflate activity content
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Open editor on FAB click
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            /**
             * Open editor on FAB click.
             *
             * @param clickedView View clicked.
             */
            @Override
            public void onClick(View clickedView) {
                Intent openEditorIntent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(openEditorIntent);
            }
        });

        // Print database row count
        summarizeDb();
    }

    /**
     * Inflate options menu.
     *
     * @param menu Activity's menu.
     * @return true to add menu to app bar.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true; // To add menu to app bar
    }

    /**
     * Print database row count.
     */
    private void summarizeDb() {
        ProductsDbHelper productsDbHelper = new ProductsDbHelper(this);
        SQLiteDatabase productsDb = productsDbHelper.getReadableDatabase();
        Cursor cursor = productsDb.rawQuery("SELECT * FROM " + ProductsEntry.TABLE_NAME, null);

        try {
            TextView productsListTextView = findViewById(R.id.products_list_text_view);
            productsListTextView.setText("Number of rows in products database: " + cursor.getCount());
        } finally {
            cursor.close();
        }
    }
}
