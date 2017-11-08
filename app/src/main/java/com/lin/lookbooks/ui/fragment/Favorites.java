package com.lin.lookbooks.ui.fragment;



import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lin.lookbooks.R;

/**
 * Created by 18374 on 2017/11/5.
 */
public class Favorites extends Fragment{

    private Context mContext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_favorites,container,false);
        mContext=view.getContext();
        return view;
    }
}
