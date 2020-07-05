package com.bracketsps.buddy_final.ui.allFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bracketsps.buddy_final.R;

import java.util.ArrayList;

public class CustomAdapterProgramme extends BaseAdapter {
    Context mContext;

    ArrayList<Programme> pro=new ArrayList<>();


    public CustomAdapterProgramme(Context mContext, ArrayList<Programme> pro) {
        this.mContext = mContext;
        this.pro = pro;
    }

    @Override
    public int getCount() {
        return pro.size();
    }

    @Override
    public Object getItem(int position) {
        return pro.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view = LayoutInflater.from(mContext).inflate(R.layout.custom_listview_pro, viewGroup,false);
        }

        Programme tmp = (Programme) getItem(i);

        ImageView startimg = (ImageView) view.findViewById(R.id.startimg);
        ImageView endimge = (ImageView) view.findViewById(R.id.endimg);
        TextView title = (TextView) view.findViewById(R.id.protitle);

        startimg.setImageResource(tmp.getImg1());
        endimge.setImageResource(tmp.getImg2());
        title.setText(tmp.getTilte());




        return view;



    }
}
