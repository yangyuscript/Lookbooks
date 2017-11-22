package com.lin.lookbooks.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.lin.lookbooks.R;
import com.lin.lookbooks.utils.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 18374 on 2017/11/5.
 */
public class Favorites extends Fragment{
    private View view;
    private Context mContext;
    private List imgUrls;
    private Banner banner;
    private GridView gridView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_favorites,container,false);
        mContext=view.getContext();
        init();
        return view;
    }
    public void init(){
        //轮播图初始化
        imgUrls=new ArrayList();
        imgUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1511282898132&di=64ca56178f4fbf75a01915bccd80bd5d&imgtype=0&src=http%3A%2F%2Fpic.qiantucdn.com%2F58pic%2F17%2F70%2F72%2F02U58PICKVg_1024.jpg");
        imgUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1511361892616&di=72efaf3032ba36af5f550d59237a04ae&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F56%2F99%2F88f58PICuBh_1024.jpg");
        imgUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1511282898132&di=c76b9c668b331a015dd280700a4f68e8&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2Fqk%2Fback_origin_pic%2F00%2F04%2F18%2F362cf1744b491c674a246f6e14f0594e.jpg");
        banner=(Banner)view.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(imgUrls);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        //中部图标初始化
        gridView=(GridView) view.findViewById(R.id.gridView);
        List listItems=new ArrayList();
        int[] imageId=new int[]{R.mipmap.head,R.mipmap.head,R.mipmap.head,R.mipmap.head,R.mipmap.head,R.mipmap.head,R.mipmap.head,R.mipmap.head};
        String[] tittle={"测试1","测试2","测试3","测试4","测试5","测试6","测试7","测试8"};
        for(int i=0;i<8;i++){
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("image",imageId[i]);
            map.put("title",tittle[i]);
            listItems.add(map);
        }
        SimpleAdapter adapter=new SimpleAdapter(mContext,listItems,R.layout.index_icon_layout,new String[]{"title","image"},new int[]{R.id.title,R.id.image});
        gridView.setAdapter(adapter);
    }
}
