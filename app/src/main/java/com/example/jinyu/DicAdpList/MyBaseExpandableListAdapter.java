package com.example.jinyu.DicAdpList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.jinyu.R;
import com.example.jinyu.Sound.Sound;
import java.util.ArrayList;

/**
 * Created by yyx on 2017/8/20.
 */

public class MyBaseExpandableListAdapter extends BaseExpandableListAdapter {

    private ArrayList<Group> gData;
    private ArrayList<ArrayList<Item>> iData;
    private Context mContext;

    public MyBaseExpandableListAdapter(ArrayList<Group> gData, ArrayList<ArrayList<Item>> iData, Context mContext) {
        this.gData = gData;
        this.iData = iData;
        this.mContext = mContext;
    }

    @Override
    public int getGroupCount() {
        return gData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return iData.get(groupPosition).size();
    }

    @Override
    public Group getGroup(int groupPosition) {
        return gData.get(groupPosition);
    }

    @Override
    public Item getChild(int groupPosition, int childPosition) {
        return iData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //取得用于显示给定分组的视图. 这个方法仅返回分组的视图对象
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ViewHolderGroup groupHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_dic_group, parent, false);
            groupHolder = new ViewHolderGroup();
            groupHolder.tv_group_name = (TextView) convertView.findViewById(R.id.tv_group_name);
            convertView.setTag(groupHolder);
        }else{
            groupHolder = (ViewHolderGroup) convertView.getTag();
        }
        groupHolder.tv_group_name.setText(gData.get(groupPosition).getgName());
        return convertView;
    }

    //取得显示给定分组给定子位置的数据用的视图
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderItem itemHolder;
        itemHolder = new ViewHolderItem();
        Item data = iData.get(groupPosition).get(childPosition);
        ArrayList<String> urlList = data.getUrlList();
        switch (urlList.size()){
            case(1):
                convertView = LayoutInflater.from(mContext).inflate(
                        R.layout.item_dic_item, parent, false);
                itemHolder.imgbt_play1 = new myImgBtn(convertView,R.id.play_btn,urlList.get(0));
                itemHolder.Imgbt_play2 = null;
                break;
            case(2):
                convertView = LayoutInflater.from(mContext).inflate(
                        R.layout.item_dic_item2, parent, false);
                itemHolder.imgbt_play1 = new myImgBtn(convertView,R.id.play_btn1,urlList.get(0));
                itemHolder.Imgbt_play2 = new myImgBtn(convertView, R.id.play_btn2,urlList.get(1));
        }


        itemHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        convertView.setTag(itemHolder);
        itemHolder.tv_name.setText(data.getiName());
        return convertView;
    }

    //设置子列表是否可选中
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    private static class ViewHolderGroup{
        private TextView tv_group_name;
    }

    private static class ViewHolderItem{
        private myImgBtn imgbt_play1,Imgbt_play2;
        private TextView tv_name;
    }
    private class myImgBtn  {
        private ImageButton btn;
        private final String url;

        public myImgBtn(View convertView, int Id, String Url){
            btn =(ImageButton)convertView.findViewById(Id);
            this.url = Url;

            btn.setOnClickListener(new View.OnClickListener()

            {

                public void onClick(View v)

                {
                    //String url = "http://sinacloud.net/jsjmsounds/%E9%B8%A1.mp3?KID=sina,2oe1jprVrqLmlMXBGzrY&Expires=1503287836&ssig=sa3bxUcS%2Fd";
                    Sound.playSound(url);


                }

            });
        }

        public ImageButton getBtn(){
            return btn;
        }
    }
}
