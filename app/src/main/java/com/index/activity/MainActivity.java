package com.index.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.index.R;
import com.index.view.LoadListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoadListView.OnLoadListener{

    private List<String> datas = null;

    private LoadListView loadListView = null;
    private ArrayAdapter adapter = null;

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
        Handler handler = new Handler();
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
}
