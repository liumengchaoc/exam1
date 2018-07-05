package com.example.liumengchao20180702;

import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.liumengchao20180702.Adapter.Myadapter;
import com.example.liumengchao20180702.Pojo.MyBean;
import com.example.liumengchao20180702.Utills.HttpUtills;
import com.example.liumengchao20180702.Utills.JsonBack;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;


public class Fragmentone extends Fragment {
    private PullToRefreshListView pullToRefreshListView;
    private String path="http://www.wanandroid.com/tools/mockapi/6523/hotnews_";
    private   Myadapter myadapter;
  private List<MyBean.DataBeanX.DataBean>  list=new ArrayList<>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private int page=1;
    public static Fragment getfragmentone(String s){
      Fragmentone fragmentone=new Fragmentone();
        Bundle bundle=new Bundle();
        bundle.putString("title",s);
        fragmentone.setArguments(bundle);
  return fragmentone;
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment,container,false);
        pullToRefreshListView=view.findViewById(R.id.pulltoreshlistview);
        Bundle arguments = getArguments();
        if(arguments.get("title").equals("热评")){
        initview();
        initdata();

        }
        return view;
    }

    private void initdata() {
  String url=path+page;
  //调取封装类
        HttpUtills.getHttputils(url, getActivity(), new JsonBack() {
            @Override
            public void getData(String s) {
                Gson gson=new Gson();
                //获得数据
                MyBean myBean = gson.fromJson(s, MyBean.class);
                List<MyBean.DataBeanX.DataBean> data = myBean.getData().getData();
                if(page==1){
                    list.clear();
                }
                list.addAll(data);
                myadapter.notifyDataSetChanged();
            }
        });







    }

    private void initview() {
        //写上拉刷新的提示
    pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
    pullToRefreshListView.getLoadingLayoutProxy(true,false);
    pullToRefreshListView.setPullLabel("下拉刷新");
    pullToRefreshListView.setRefreshingLabel("正在刷新");
    pullToRefreshListView.setPullLabel("刷新完成");
   pullToRefreshListView.getLoadingLayoutProxy(false,true);
        pullToRefreshListView.getLoadingLayoutProxy(true,false);
        pullToRefreshListView.setPullLabel("下拉加载");
        pullToRefreshListView.setRefreshingLabel("正在加载");
        pullToRefreshListView.setPullLabel("加载完成");
        //触发事件
         pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
             @Override
             public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
           page=1;
                 handler.postDelayed(new Runnable() {

                     @Override
                     public void run() {
                         pullToRefreshListView.onRefreshComplete();
                     }
                 }, 2000);
                 initdata();

             }

             @Override
             public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
           page++;
                 handler.postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         pullToRefreshListView.onRefreshComplete();
                     }
                 }, 2000);
                 initdata();
             }
         });
         //添加适配器
      myadapter=new Myadapter(list,getActivity());
        pullToRefreshListView.setAdapter(myadapter);

    }


}
