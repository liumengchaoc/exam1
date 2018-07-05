package com.example.liumengchao20180702;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.example.liumengchao20180702.Sqlite.Sqlifehappend;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private HorizontalScrollView horizontalScrollView;
    private LinearLayout line;
    private ViewPager viewPager;
    private List<TextView> list=new ArrayList<>();
    private Button button;
    private List<ChannelBean>  channelBeans=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        horizontalScrollView=findViewById(R.id.horizontalscrollview);
        line=findViewById(R.id.line);
        viewPager=findViewById(R.id.viewpager);
        button=findViewById(R.id.button);
   channelBeans.add(new ChannelBean("头条",true));
        channelBeans.add(new ChannelBean("订阅",true));
        channelBeans.add(new ChannelBean("合肥",true));
        channelBeans.add(new ChannelBean("热评",true));
        channelBeans.add(new ChannelBean("图集",true));
        channelBeans.add(new ChannelBean("视频",true));
        channelBeans.add(new ChannelBean("国内",true));
        channelBeans.add(new ChannelBean("国际",true));
        channelBeans.add(new ChannelBean("社会",true));
        channelBeans.add(new ChannelBean("娱乐",true));
        channelBeans.add(new ChannelBean("家居",true));
        channelBeans.add(new ChannelBean("购物",true));
        channelBeans.add(new ChannelBean("美食",true));
        channelBeans.add(new ChannelBean("休闲",true));
        channelBeans.add(new ChannelBean("科技",true));
        channelBeans.add(new ChannelBean("教育",true));
        channelBeans.add(new ChannelBean("旅游",true));
        channelBeans.add(new ChannelBean("婚庆",true));
        channelBeans.add(new ChannelBean("亲子",true));
        channelBeans.add(new ChannelBean("健康",true));
        channelBeans.add(new ChannelBean("房产",false));
        channelBeans.add(new ChannelBean("汽车",false));
        channelBeans.add(new ChannelBean("军事",false));
        channelBeans.add(new ChannelBean("财经",false));
        channelBeans.add(new ChannelBean("体育",false));
        channelBeans.add(new ChannelBean("安徽",false));
        for (int i = 0; i < channelBeans.size(); i++) {
            //数据库添加
             Sqlifehappend sqlifehappend=new Sqlifehappend(this);
             sqlifehappend.insert(channelBeans.get(i).getName(),channelBeans.get(i).isSelect());
            final TextView textView=new TextView(this);
            //创建textview并添加属性
            textView.setText(channelBeans.get(i).getName().toString());
            textView.setId(i+1000);
            textView.setTextSize(20);
            //是否展示
            if (channelBeans.get(i).isSelect()){
             textView.setVisibility(View.VISIBLE);

            }else {
                textView.setVisibility(View.GONE);

            }
            //第一个为红色
            if(i==0){
                textView.setTextColor(Color.RED);
            }else{
                textView.setTextColor(Color.BLACK);
            }
            //点到那个那个就为页面
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = textView.getId();
                    viewPager.setCurrentItem(id-1000);
                }
            });

//添加间距

LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(50,10,50,10);
            line.addView(textView,layoutParams);
            list.add(textView);
        }
        //添加适配器
  viewPager.setAdapter(new Myadapter(getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            //判断当前的字段是否与view pager一至
            public void onPageScrolled(int i, float v, int i1) {
                for (int j = 0; j < list.size(); j++) {
                    if (j==i){
                  list.get(j).setTextColor(Color.RED);
                    }else {
                        list.get(j).setTextColor(Color.BLACK);
                    }
                    TextView textView = list.get(i);
                    int width = textView.getWidth();
                    horizontalScrollView.scrollTo((width+20)*2,0);


                }
                


                

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        //点击跳转频道管理
   button.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           ChannelActivity.startChannelActivity(MainActivity.this,channelBeans);

       }
   });





    }
//创建fragment
    private class Myadapter extends FragmentPagerAdapter {
        public Myadapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }
        @Override
        public Fragment getItem(int i) {
            return  Fragmentone.getfragmentone(list.get(i).getText().toString());
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
