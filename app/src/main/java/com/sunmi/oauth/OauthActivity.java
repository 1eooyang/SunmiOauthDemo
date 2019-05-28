package com.sunmi.oauth;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：杨柳 on 2019/5/17 0017 16:54
 * <p>
 * 邮箱：yangliu@sunmi.com
 */
public class OauthActivity extends Activity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private boolean showOpen = true;
    private List<OauthBean> mOauthBeans = new ArrayList<>();

    private OauthBean mOauthBean;
    private OauthBean chooseBean;

    private CommonAdapter<OauthBean> mCommonAdapter;


    //http://test.api.sunmi.com/api/merchant/app/oauth/1.0/?service=

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth_login);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        chooseBean = mOauthBean = new OauthBean("杨柳", "15921449923");

        mCommonAdapter = new CommonAdapter<OauthBean>(this, R.layout.item_oauth, mOauthBeans) {
            @Override
            protected void convert(ViewHolder holder, final OauthBean oauthBean, final int position) {
                ImageView iv_del = holder.getView(R.id.iv_del);
                TextView tv_name = holder.getView(R.id.tv_name);
                TextView tv_phone = holder.getView(R.id.tv_phone);

                int res = showOpen ? R.drawable.down : R.drawable.up;
                iv_del.setImageResource(position == 0 ? res : R.drawable.delete);
                tv_name.setText(oauthBean.getName());
                tv_phone.setText(oauthBean.getPhone());



                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(position != 0){
                                chooseBean = oauthBean;
                            }
                            switchItem();

                        }
                    });

                    iv_del.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(position != 0){
                                Toast.makeText(mContext, "是否删除 ", Toast.LENGTH_SHORT).show();
                            }else{
                                switchItem();
                            }
                        }
                    });



            }
        };

        mRecyclerView.setAdapter(mCommonAdapter);
        getOneData();
        findViewById(R.id.btn_login).setOnClickListener(this);


    }

    private void switchItem() {
        if (showOpen) {
            getSumData(5);
        } else {
            getOneData();
        }

        showOpen = !showOpen;
    }


    private void getOneData() {
        mOauthBeans.clear();
        mOauthBeans.add(chooseBean);
        mCommonAdapter.notifyDataSetChanged();
    }


    private void getSumData(int sum) {
        mOauthBeans.clear();
        mOauthBeans.add(chooseBean);
        mOauthBeans.add(mOauthBean);
        for (int i = 0; i < sum; i++) {
            mOauthBeans.add(new OauthBean("用户_abc" + i, "159****000" + i));
        }
        mCommonAdapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {

    }
}
