package com.lin.completedemo.service;



import com.lin.completedemo.service.entity.Book;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 18374 on 2017/8/8.
 */

public interface RetrofitService {
    @GET("book/search")
    Observable<Book> getSearchBooks(@Query("q")String name,
                                    @Query("tag")String tag,
                                    @Query("start")int start,
                                    @Query("count")int count);
}
