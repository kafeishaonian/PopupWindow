package com.popup.client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.popup.client.ui.AbsPopupRecyclerView;
import com.popup.client.ui.PopupHorizontalView;
import com.popup.client.ui.PopupVerticalView;
import com.popup.client.util.DisplayUtils;
import com.popup.client.util.MenuTypeItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * VIew
     */
    private Button vertical;
    private Button horizontal;

    private AbsPopupRecyclerView verticalView;
    private ArrayList<MenuTypeItem> verticalItem = new ArrayList<>();

    private AbsPopupRecyclerView horizontalView;
    private ArrayList<MenuTypeItem> horizontalItem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        vertical = (Button) findViewById(R.id.button1);
        horizontal = (Button) findViewById(R.id.button2);
        initData();
        initListener();
    }


    private void initData(){
        verticalItem.clear();
        for (String verticalStr : getResources().getStringArray(R.array.popup_vertical)){
            verticalItem.add(new MenuTypeItem(verticalStr));
        }
        verticalView = new PopupVerticalView(this, verticalItem.size());
        verticalView.addTypeList(verticalItem);

        horizontalItem.clear();
        for (String horizontalStr : getResources().getStringArray(R.array.popup_horizontal)){
            horizontalItem.add(new MenuTypeItem(horizontalStr));
        }
        horizontalView = new PopupHorizontalView(this, horizontalItem.size());
        horizontalView.addTypeList(horizontalItem);
    }

    private void initListener(){
        vertical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verticalView.setOnPopupMenuClickListener(new AbsPopupRecyclerView.ISpinnerWindowClickListener() {
                    @Override
                    public void onSpinnerItemChicked(View view, long position, int typeId) {
                        verticalView.setLastSelected(typeId);
                    }

                    @Override
                    public void onSpinnerOutSideClicked() {

                    }
                });
                verticalView.showBottonPopupWindow(vertical, DisplayUtils.dipToPx(MainActivity.this, 3), DisplayUtils.dipToPx(MainActivity.this, 3));
            }
        });

        horizontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                horizontalView.setOnPopupMenuClickListener(new AbsPopupRecyclerView.ISpinnerWindowClickListener() {
                    @Override
                    public void onSpinnerItemChicked(View view, long position, int typeId) {
                        horizontalView.setLastSelected(typeId);
                    }

                    @Override
                    public void onSpinnerOutSideClicked() {

                    }
                });
                int[] location = new int[2];
                horizontal.getLocationOnScreen(location);
                horizontalView.showTopPopupWindow(horizontal, (location[0] + (horizontal.getWidth() / 2)), location[1] - DisplayUtils.dipToPx(MainActivity.this, 40));
            }
        });
    }

}
