package com.lin.lookbooks.service.presenter;

import android.content.Context;
import android.content.Intent;

import com.lin.lookbooks.service.entity.Book;
import com.lin.lookbooks.service.manager.DataManager;
import com.lin.lookbooks.service.view.BookView;
import com.lin.lookbooks.service.view.View;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by 18374 on 2017/8/8.
 */

public class BookPresenter implements Presenter {
    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private BookView mbookView;
    private Book mbook;
    public BookPresenter(Context mContext) {
        this.mContext=mContext;
    }

    @Override
    public void onCreate() {
        manager=new DataManager(mContext);
        mCompositeSubscription=new CompositeSubscription();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if(mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();//取消订阅防止内存泄漏
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(View view) {
        mbookView=(BookView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getSearchBooks(String name,String tag,int start,int count){
        mCompositeSubscription.add(manager.getSearchBooks(name,tag,start,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Book>() {
                    @Override
                    public void onCompleted() {
                        if(mbook!=null){
                            mbookView.onSuccess(mbook);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mbookView.onError("请求失败!");
                    }

                    @Override
                    public void onNext(Book book) {
                        mbook=book;
                    }
                })
        );
    }
}
