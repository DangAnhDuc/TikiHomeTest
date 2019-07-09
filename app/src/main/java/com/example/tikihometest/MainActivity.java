package com.example.tikihometest;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tikihometest.Adapter.ItemAdapter;
import com.example.tikihometest.Common.Common;
import com.example.tikihometest.Model.Item;
import com.example.tikihometest.Retrofit.IMyAPI;
import com.example.tikihometest.Retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    IMyAPI myAPI;
    RecyclerView recycler_items;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserPrefs.initUserPrefs(this);

        RecyclerView rv = findViewById(R.id.recycler_view);
        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mAdapter = new ItemAdapter(this);
        rv.setLayoutManager(lm);
        rv.setAdapter(mAdapter);

        loadDataFromNetwork();

    }



    private void loadDataFromNetwork() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(Common.KEYWORK_LINK)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //failed load cache
                handleWhenRequestFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                int code = response.code();
                if (code == 200){
                    String raw = response.body().string();
                    UserPrefs.set(UserPrefs.PREF_CACHE, raw);
                    String[] data = new Gson().fromJson(raw, String[].class);
                    showDataOnList(Arrays.asList(data));
                } else {
                    handleWhenRequestFailed();
                }
            }
        });
    }

    private void handleWhenRequestFailed(){
        //failed load cache
        String raw = UserPrefs.get(UserPrefs.PREF_CACHE, "");
        if (!raw.isEmpty()){
            String[] data = new Gson().fromJson(raw, String[].class);
            showDataOnList(Arrays.asList(data));
        }
    }

    private void showDataOnList(final List<String> data){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.setData(data);
            }
        });
    }


    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}
