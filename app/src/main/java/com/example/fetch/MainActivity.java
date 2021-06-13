package com.example.fetch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.fetch.adapter.ItemRecyclerAdapter;
import com.example.fetch.model.Item;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String fetch_path = "https://fetch-hiring.s3.amazonaws.com/hiring.json";

    private static final String TAG = "MainActivity.class";
    private List<Item> items;

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private ItemRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, fetch_path, null, response -> {
            for (int x = 0; x < response.length(); x++) {
                try {
                    JSONObject obj = response.getJSONObject(x);

                    String name = obj.getString("name");

                    System.out.println("Name:  " + name);

                    if (name.equals("null") || name.isEmpty()) continue;

                    Item item = new Item(obj.getInt("id"), obj.getInt("listId"), name);
                    items.add(item);
                } catch (JSONException e) {
                    Log.e(TAG, "The json caused an error");
                }
            }
            items.sort(Item::compareTo);
            adapter = new ItemRecyclerAdapter(items, this);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }, error -> Log.d(TAG, "An error occurred when retrieving json array"));

        requestQueue.add(jsonArrayRequest);
    }
}