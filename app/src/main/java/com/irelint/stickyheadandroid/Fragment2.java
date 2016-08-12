package com.irelint.stickyheadandroid;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import java.util.ArrayList;

/**
 * 作者：当我遇上你 on 2016-8-12 17:13
 * 邮箱：cuishiying163@163.com
 */

public class Fragment2 extends Fragment {
    private View view;
    private Activity context;
    QuickAdapter<String> adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        initView();
        initData();
    }

    private void initView() {
        ListView lv2 = (ListView)view.findViewById(R.id.lv2);
        lv2.setFocusable(false);
        adapter = new QuickAdapter<String>(context, R.layout.layout_item){

            @Override
            protected void convert(BaseAdapterHelper helper, String item) {
                helper.setText(R.id.tv_item,item);
            }
        };
        lv2.setAdapter(adapter);
    }
    private void initData() {
        ArrayList<String> data = new ArrayList<>();
        for (int i=0;i<20;i++){
            data.add("我是Tab2的数据="+i);
        }
        adapter.addAll(data);
    }
}
