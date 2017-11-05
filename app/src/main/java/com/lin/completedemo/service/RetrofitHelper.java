package com.lin.completedemo.service;

import android.content.Context;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 18374 on 2017/8/8.
 * resetApp方法，就是前面介绍的Retrofit的创建，getServer方法就是为了获取RetrofitService接口类的实例化。
 * 然后定义了一个静态方法getInstance用于获取自身RetrofitHelper的实例化，并且只会实例化一次
 */

public class RetrofitHelper {
    private Context mcontext;

    OkHttpClient client=new OkHttpClient();
    GsonConverterFactory factory=GsonConverterFactory.create(new GsonBuilder().create());
    private static RetrofitHelper instance=null;
    private Retrofit mRetrofit=null;
    public static RetrofitHelper getInstance(Context context){
        if(instance==null){
            instance=new RetrofitHelper(context);
        }
        return instance;
    }

    private RetrofitHelper(Context mcontext){
        this.mcontext=mcontext;
        init();
    }
    private void init(){
        resetApp();
    }
    private void resetApp(){
        mRetrofit=new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
    public RetrofitService getServer(){
        return mRetrofit.create(RetrofitService.class);
    }

}
