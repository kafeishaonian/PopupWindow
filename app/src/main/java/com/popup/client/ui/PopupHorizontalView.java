package com.popup.client.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.popup.client.R;
import com.popup.client.adapter.AbsPopupRecyclerAdapter;
import com.popup.client.adapter.PopupHorizontalAdapter;

/**
 * Created by 64860 on 2017/9/18.
 */
public class PopupHorizontalView extends AbsPopupRecyclerView {

    private static final String TAG = PopupHorizontalView.class.getSimpleName();

    private PopupHorizontalAdapter adapter;

    public PopupHorizontalView(Context context, int itemNums) {
        super(context, itemNums);
    }

    @Override
    protected int getItemWidth() {
        return 100;
    }

    @Override
    protected int getItemHeight() {
        return 32;
    }

    @Override
    protected AbsPopupRecyclerAdapter getAdapter() {
        if (adapter == null){
            Log.e(TAG, "getAdapter: ========" + lastSelectedId );
            adapter = new PopupHorizontalAdapter(mContext, mItems, lastSelectedId);
            this.typeRecyclerView.setAdapter(adapter);
        } else {
            adapter.updateItems(mItems);
            adapter.setLastSelectedId(lastSelectedId);
        }
        return adapter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.vw_pop_crosswise;
    }

    @Override
    protected int getOrientation() {
        return LinearLayoutManager.HORIZONTAL;
    }
}
