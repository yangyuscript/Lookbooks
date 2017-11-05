package com.lin.completedemo.service.view;

import com.lin.completedemo.service.entity.Book;

/**
 * Created by 18374 on 2017/8/8.
 */

public interface BookView extends View {
    void onSuccess(Book book);
    void onError(String result);
}
