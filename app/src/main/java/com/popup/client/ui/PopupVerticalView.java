package com.popup.client.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.popup.client.R;
import com.popup.client.adapter.AbsPopupRecyclerAdapter;
import com.popup.client.adapter.PopupVerticalAdapter;

/**
 * Created by 64860 on 2017/9/15.
 */
public class PopupVerticalView extends AbsPopupRecyclerView{

    private PopupVerticalAdapter adapter;

    public PopupVerticalView(Context context, int itemNums) {
        super(context, itemNums);
    }

    @Override
    protected int getItemWidth() {
        return 144;
    }

    @Override
    protected int getItemHeight() {
        return 38;
    }

    @Override
    protected AbsPopupRecyclerAdapter getAdapter() {
        if (adapter == null){
            adapter = new PopupVerticalAdapter(mContext, mItems, lastSelectedId);
            this.typeRecyclerView.setAdapter(adapter);
        } else {
            adapter.updateItems(mItems);
            adapter.setLastSelectedId(lastSelectedId);
        }
        return adapter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.vw_pop_minutes;
    }

    @Override
    protected int getOrientation() {
        return LinearLayoutManager.VERTICAL;
    }
}
