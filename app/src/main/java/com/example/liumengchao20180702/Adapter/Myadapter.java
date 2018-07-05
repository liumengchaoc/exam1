package com.example.liumengchao20180702.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liumengchao20180702.Pojo.MyBean;
import com.example.liumengchao20180702.R;
import com.example.liumengchao20180702.Utills.MyApp;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class Myadapter extends BaseAdapter {
    private List<MyBean.DataBeanX.DataBean> list;
    private String from="http://img.365jia.cn/uploads/";
    private Context context;
    public final int TYPEONE = 0;
    public final int TYPETWO = 1;

    public Myadapter(List<MyBean.DataBeanX.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        List<?> pics = list.get(position).getPics();
        if (pics == null) {
            return TYPEONE;

        } else if (pics != null) {
            return TYPETWO;
        } else {

            return TYPEONE;

        }
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int itemViewType = getItemViewType(i);
        if (itemViewType == TYPEONE) {
            ViewHodler hodler;
            if (view == null) {
                hodler = new ViewHodler();
                view = View.inflate(context, R.layout.child, null);
                hodler.text = view.findViewById(R.id.text);
                view.setTag(hodler);
            } else {
                hodler = (ViewHodler) view.getTag();
            }
            hodler.text.setText(list.get(i).getTitle());
            return view;
        } else if (itemViewType == TYPETWO) {
            ViewHodler1 hodler1;
            if (view == null) {
                hodler1 = new ViewHodler1();
                view = View.inflate(context, R.layout.child1, null);
                hodler1.text1 = view.findViewById(R.id.text1);
                hodler1.img = view.findViewById(R.id.img);
                view.setTag(hodler1);
            } else {
                hodler1 = (ViewHodler1) view.getTag();
            }
            hodler1.text1.setText(list.get(i).getTitle());
//            Object o = list.get(i).getPics().get(0);
//            String paths = from + o;
//            ImageLoader.getInstance().displayImage(paths, hodler1.img, MyApp.shape());

            return view;
        }


        return null;
    }

    class ViewHodler {
        TextView text;
    }

    class ViewHodler1 {
        TextView text1;
        ImageView img;
    }
}
