package com.popup.client.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Hongmingwei on 2017/9/19.
 * Email: 648600445@qq.com
 */

public abstract class AbsPopupRecyclerAdapter extends RecyclerView.Adapter implements View.OnClickListener{

    public OnItemClickListener onItemClickListener = null;

    public AbsPopupRecyclerAdapter(){

    }

    public static interface OnItemClickListener{
        void onItemClick(View view, int position, int id);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }

}