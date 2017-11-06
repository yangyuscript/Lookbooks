package com.lin.lookbooks.ui.activity;


import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lin.lookbooks.R;
import com.lin.lookbooks.ui.fragment.Favorites;
import com.lin.lookbooks.ui.fragment.Friends;
import com.lin.lookbooks.ui.fragment.Nearby;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {
    private Favorites favorites_fragment;
    private Friends friends_fragment;
    private Nearby nearby_fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomBar bottomBar=(BottomBar)findViewById(R.id.bottomBar);
        init();
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                Object ob=null;
                switch (tabId){
                    case R.id.tab_favorites:
                        fragmentTransaction.hide(friends_fragment).hide(nearby_fragment);
                        fragmentTransaction.show(favorites_fragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.tab_friends:
                        fragmentTransaction.hide(favorites_fragment).hide(friends_fragment);
                        fragmentTransaction.show(nearby_fragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.tab_nearby:
                        fragmentTransaction.hide(favorites_fragment).hide(nearby_fragment);
                        fragmentTransaction.show(friends_fragment);
                        fragmentTransaction.commit();
                        break;
                }
            }
        });
        /*bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer,new Favorites()).commit();
            }
        });*/
    }
    public void init(){
        favorites_fragment=new Favorites();
        friends_fragment=new Friends();
        nearby_fragment=new Nearby();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.contentContainer,favorites_fragment).add(R.id.contentContainer,friends_fragment).add(R.id.contentContainer,nearby_fragment);
        fragmentTransaction.hide(friends_fragment).hide(nearby_fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
