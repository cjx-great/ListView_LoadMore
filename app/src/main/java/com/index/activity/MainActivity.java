package com.index.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;

import com.index.R;
import com.index.view.LoadListView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoadListView.OnLoadListener{

    private List<String> datas = null;

    private LoadListView loadListView = null;
    private ArrayAdapter adapter = null;

    private MHandler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initData(){
        datas = new ArrayList<>();
        datas.add("123");
        datas.add("A FGFHG");
        datas.add("B HJUK");
        datas.add("C TYT");
        datas.add("D LKK");
        datas.add("E BGJH");
        datas.add("F CXV");
        datas.add("J ZXCZC");
        datas.add("H WRE");
        datas.add("I SDS");
        datas.add("J ERT");
        datas.add("K FDD");
        datas.add("M ER");
        datas.add("N ERE");
        datas.add("O DSD");
        datas.add("P SDA");

    }

    private void initView(){
        loadListView = (LoadListView) findViewById(R.id.load_list);

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,datas);
        loadListView.setAdapter(adapter);

        loadListView.setOnLoadListener(this);
    }

    @Override
    public void onLoad() {
        //模拟延时操作

        handler = new MHandler(this);

        //Runnable也可以写成静态内部类

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //获取更多数据
                datas.add("这是加载的更多数据1");
                datas.add("这是加载的更多数据2");
                datas.add("这是加载的更多数据3");
                datas.add("这是加载的更多数据4");

                adapter.notifyDataSetChanged();
                //数据加载完毕
                loadListView.loadCompleted();
            }
        },3000);

    }

    /**
     * 当内部类的生存周期可能超过Activity时，为了解决内存泄漏的问题
     * 使用静态内部类、弱引用
     * */

    private static class MHandler extends Handler{

        private WeakReference<MainActivity> weakReference = null;

        public MHandler(MainActivity mainActivity){
            weakReference = new WeakReference<>(mainActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //switch操作

        }
    }

}
