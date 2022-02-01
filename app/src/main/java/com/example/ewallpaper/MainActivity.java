package com.example.ewallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
     ArrayList<ImageModel>imageModels;
     RecyclerView recyclerView;
     Adapter adapter;
     EditText editText;
     ImageButton search;
     CardView mBus,mTrending,mCar,mNature,mTrain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.rcv);

        editText = findViewById(R.id.editText);
        search = findViewById(R.id.search);
        mBus = findViewById(R.id.Bus);
        mTrending =findViewById(R.id.tranding);
        mCar = findViewById(R.id.car);
        mNature = findViewById(R.id.nature);
        mTrain = findViewById(R.id.train);
        imageModels = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        adapter = new Adapter(getApplicationContext(),imageModels);
        recyclerView.setAdapter(adapter);

        findPhotos();

        mNature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="nature";
                getSearchImage(query);
            }
        });

       mCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="car";
                getSearchImage(query);
            }
        });

        mTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="train";
                getSearchImage(query);
            }
        });

        mBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="bus";
                getSearchImage(query);
            }
        });

        mTrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPhotos();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = editText.getText().toString().toLowerCase();
                if (query.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Enter Something", Toast.LENGTH_SHORT).show();

                }else {
                    getSearchImage(query);

                }
            }
        });
    }
    private void getSearchImage(String query) {
        ApiUtilities.getApiInterFace().getSearchImage(query,1,80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                imageModels.clear();

                if (response.isSuccessful())
                {
                    imageModels.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getApplicationContext(), "Not able to get", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });


    }

    private void findPhotos() {
        imageModels.clear();
        
        ApiUtilities.getApiInterFace().getImage(1,80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                imageModels.clear();

                if (response.isSuccessful())
                {
                    imageModels.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getApplicationContext(), "Not able to get", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });




    }
}