package com.example.tikihometest.Retrofit;

import com.example.tikihometest.Model.Item;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IMyAPI {
    @GET("/")
    Observable<List<Item>> getItems();
}
