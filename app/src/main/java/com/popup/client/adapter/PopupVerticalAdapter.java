package com.popup.client.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.popup.client.R;
import com.popup.client.util.MenuTypeItem;

import java.util.ArrayList;

/**
 * Created by 64860 on 2017/9/15.
 */
public class PopupVerticalAdapter extends AbsPopupRecyclerAdapter {

    private static final String TAG = PopupVerticalAdapter.class.getSimpleName();

    private ArrayList<MenuTypeItem> mItems;
    private LayoutInflater mInflater;
    private int mLastSelectedId;
    private Context mContext;

    public PopupVerticalAdapter(Context context, ArrayList<MenuTypeItem> items, int lastSelectedId){
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
        View view = mInflater.inflate(R.layout.listitem_menu_vertical, parent, false);
        ItemCache viewCache = new ItemCache(view);
        view.setOnClickListener(this);
        return viewCache;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemCache cache = (ItemCache) holder;
        cache.textOpen.setText(mItems.get(position).getText());
        if (position == mLastSelectedId){
            cache.textOpen.setTextColor(mContext.getResources().getColor(R.color.c_f33d3d));
        } else {
            cache.textOpen.setTextColor(mContext.getResources().getColor(R.color.c_07be0f));
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
        TextView textOpen;
        View view;

        public ItemCache(View itemView) {
            super(itemView);
            textOpen = (TextView) itemView.findViewById(R.id.full_open_text);
            view = itemView.findViewById(R.id.full_open_view);
        }
    }

}
