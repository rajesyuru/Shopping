package com.example.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddShop extends AppCompatActivity {
    EditText editText;
    int position = 0;
    boolean done = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop);

        editText = findViewById(R.id.editShopping);

        Intent intent = getIntent();
        String description = intent.getStringExtra("desc");
        position = intent.getIntExtra("pos", 0);
        done = intent.getBooleanExtra("done", false);

        editText.setText(description);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_save_shop, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemSave) {
            if (editText.getText().length() == 0) {
                Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent();
                intent.putExtra("shopdesc", editText.getText().toString());
                intent.putExtra("position", position);
                intent.putExtra("done", done);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
