package com.duyer.View;

import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.duyer.Adapter.CryptoAdapter;
import com.duyer.Model.CryptoModeli;
import com.duyer.retrofitjava.R;
import com.duyer.retrofitjava.databinding.ActivityMainBinding;
import com.duyer.service.CryptoAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding tasarim;
    ArrayList<CryptoModeli> cryptoModels;
    private String baseURL = "https://raw.githubusercontent.com/";
    public Retrofit retrofit;
    private CryptoAdapter cryptoAdapter;
    private CompositeDisposable compositeDisposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tasarim = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(tasarim.getRoot());

        //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/refs/heads/master/crypto.json

        Gson gson = new GsonBuilder().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        loadData();
    }
    private void loadData() {
        final CryptoAPI cryptoAPI = retrofit.create(CryptoAPI.class);
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(cryptoAPI.getData()
                .subscribeOn(Schedulers.io())
                .observeOn((AndroidSchedulers.mainThread()))
                .subscribe(this::handleResponse));

        /*
        Call<List<CryptoModeli>> call = cryptoAPI.getData();
        call.enqueue(new Callback<List<CryptoModeli>>() {
            @Override
            public void onResponse(Call<List<CryptoModeli>> call, Response<List<CryptoModeli>> response) {
                if (response.body() != null) {
                    List<CryptoModeli> responseList = response.body();
                    cryptoModels = new ArrayList<>(responseList);

                    tasarim.rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    cryptoAdapter = new CryptoAdapter(cryptoModels);
                    tasarim.rv.setAdapter(cryptoAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<CryptoModeli>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
 }
        }); */
    }
    private void handleResponse(List<CryptoModeli> cryptoModeliList) {
        cryptoModels = new ArrayList<>(cryptoModeliList);
        tasarim.rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        cryptoAdapter = new CryptoAdapter(cryptoModels);
        tasarim.rv.setAdapter(cryptoAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}










