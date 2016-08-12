package com.irelint.stickyheadandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.irelint.stickyheadandroid.view.StickyScrollView;

public class MainActivity extends AppCompatActivity implements StickyScrollView.OnScrollListener, View.OnClickListener{

    LinearLayout mStickyTab;
    LinearLayout mTopTab;

    TextView tab3;
    TextView tab4;

    Fragment1 fragment1;
    Fragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        RelativeLayout root = (RelativeLayout)findViewById(R.id.activity_main);
        final StickyScrollView scrollview = (StickyScrollView)findViewById(R.id.scrollview);
        scrollview.setOnScrollListener(this);

        mStickyTab = (LinearLayout)findViewById(R.id.sticky_tab);
        mTopTab = (LinearLayout)findViewById(R.id.top_tab);

        tab3 = (TextView)findViewById(R.id.tab3);
        tab4 = (TextView)findViewById(R.id.tab4);

        tab3.setOnClickListener(this);
        tab4.setOnClickListener(this);

        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                onScroll(scrollview.getScrollY());
            }
        });


        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        getSupportFragmentManager().beginTransaction().replace(R.id.fm_conent, fragment1).commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tab3:
                getSupportFragmentManager().beginTransaction().replace(R.id.fm_conent, fragment1).commit();
                tab3.setBackgroundResource(R.drawable.tabselected);
                tab4.setBackgroundResource(R.drawable.tabnormal);
                break;
            case R.id.tab4:
                getSupportFragmentManager().beginTransaction().replace(R.id.fm_conent, fragment2).commit();
                tab3.setBackgroundResource(R.drawable.tabnormal);
                tab4.setBackgroundResource(R.drawable.tabselected);
                break;
        }
    }

    @Override
    public void onScroll(int scrollY) {
        /**
         * 当滚动到顶部的时候，scrollY会继续增大，而mStickyTab.getTop()的值是固定不变的，此时scrollY会大于mStickyTab.getTop()
         * 1、初始状态，scrollY传入0，此时TopTab和StickyTab重合,随后一起滚动到顶部分离
         * 2、layout不是相对于屏幕，而是相对于其父布局，即scrollview顶部，开始时候相对不变，滚动到顶部的时候父布局和mStickyTab仍在滚动，但是mTopTab不变，所以距离逐渐增大（这样就会吸附在屏幕顶部）
         * 3、mStickyTab是滚动头部
         */
        int mBuyLayout2ParentTop = Math.max(scrollY, mStickyTab.getTop());
        mTopTab.layout(0, mBuyLayout2ParentTop, mTopTab.getWidth(), mBuyLayout2ParentTop + mTopTab.getHeight());
    }
}
