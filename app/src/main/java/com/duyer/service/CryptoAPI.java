package com.duyer.service;

import com.duyer.Model.CryptoModeli;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {
    //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/refs/heads/master/crypto.json
    @GET("atilsamancioglu/K21-JSONDataSet/refs/heads/master/crypto.json")
    Observable<List<CryptoModeli>> getData();
}
