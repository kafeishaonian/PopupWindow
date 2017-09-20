package com.popup.client.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.popup.client.R;
import com.popup.client.util.MenuTypeItem;

import java.util.ArrayList;

/**
 * Created by 64860 on 2017/9/18.
 */
public class PopupHorizontalAdapter extends AbsPopupRecyclerAdapter {

    private static final String TAG = PopupHorizontalAdapter.class.getSimpleName();

    private ArrayList<MenuTypeItem> mItems;
    private LayoutInflater mInflater;
    private int mLastSelectedId;
    private Context mContext;

    public PopupHorizontalAdapter(Context context, ArrayList<MenuTypeItem> items, int lastSelectedId){
        mContext = context;
        mItems = items;
        mLastSelectedId = lastSelectedId;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void updateItems(ArrayList<MenuTypeItem> items){
        if (items != null && items.size() > 0){
            mItems = items;
        }
    }

    public void setLastSelectedId(int lastSelectedId){
        mLastSelectedId = lastSelectedId;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.listitem_menu_horizontal, parent, false);
        ItemCache viewCache = new ItemCache(view);
        view.setOnClickListener(this);
        return viewCache;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemCache cache = (ItemCache) holder;
        Log.e(TAG, "onBindViewHolder: =========" + position + "=======" + mLastSelectedId);
        cache.textUnitcost.setText(mItems.get(position).getText());
        if (position == mLastSelectedId){
            cache.textUnitcost.setTextColor(mContext.getResources().getColor(R.color.c_2582f5));
        } else {
            cache.textUnitcost.setTextColor(mContext.getResources().getColor(R.color.c_f33d3d));
        }
        if (position == (mItems.size()-1)){
            cache.view.setVisibility(View.GONE);
        } else{
            cache.view.setVisibility(View.VISIBLE);
        }
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null){
            onItemClickListener.onItemClick(v, (Integer) v.getTag(), (Integer) v.getTag());
        }
    }

    public static class ItemCache extends RecyclerView.ViewHolder {
        TextView textUnitcost;
        View view;

        public ItemCache(View itemView) {
            super(itemView);
            textUnitcost = (TextView) itemView.findViewById(R.id.full_unitcost_text);
            view = itemView.findViewById(R.id.full_unitcost_view);
        }
    }

}
