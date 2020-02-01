package com.example.shopping;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Shopping> mShoppings;
    private ShopAdapter shopAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.shoppingList);

        mShoppings = new ArrayList<>();

        mShoppings.add(new Shopping(false, "asdf"));
        mShoppings.add(new Shopping(false, "asdf"));
        mShoppings.add(new Shopping(false, "asdf"));
        mShoppings.add(new Shopping(false, "asdf"));
        mShoppings.add(new Shopping(false, "asdf"));
        mShoppings.add(new Shopping(false, "asdf"));
        mShoppings.add(new Shopping(false, "asdf"));
        mShoppings.add(new Shopping(false, "asdf"));

        shopAdapter = new ShopAdapter(this, R.layout.shopping_layout, mShoppings, new ShopAdapter.SetClickListener() {
            @Override
            public void onCheck(int position, boolean isChecked) {
                Shopping shopping = mShoppings.get(position);
                shopping.setDone(isChecked);
                mShoppings.set(position, shopping);
                Log.d("!!!", "onCheck: " + position + ", " + shopping);
                shopAdapter.notifyDataSetChanged();
            }

            @Override
            public void Remove(int position) {
                mShoppings.remove(position);
                shopAdapter.notifyDataSetChanged();

            }

            @Override
            public void Edit(int position) {
                Shopping shopping = mShoppings.get(position);

                Intent intent = new Intent(getApplicationContext(), AddShop.class);
                intent.putExtra("desc", shopping.getDesc());
                intent.putExtra("pos", position);
                intent.putExtra("done", shopping.isDone());
                startActivityForResult(intent, 2);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(shopAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_shop_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemAddList) {
            Intent intent = new Intent(this, AddShop.class);
            startActivityForResult(intent, 1);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String description = data.getStringExtra("shopdesc");
                mShoppings.add(new Shopping(false, description));

                shopAdapter.notifyDataSetChanged();
            }
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                String description = data.getStringExtra("shopdesc");
                int position = data.getIntExtra("position", 0);
                boolean done = data.getBooleanExtra("done", false);

                mShoppings.set(position, new Shopping(done, description));

                shopAdapter.notifyDataSetChanged();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
