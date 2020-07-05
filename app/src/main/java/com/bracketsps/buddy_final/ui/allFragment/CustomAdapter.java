package com.bracketsps.buddy_final.ui.allFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bracketsps.buddy_final.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<Food> food = new ArrayList<>();

    public CustomAdapter(Context context, ArrayList<Food> food){
        this.mContext=context;
        this.food = food;
    }


    @Override
    public int getCount() {
        return food.size();
    }

    @Override
    public Object getItem(int i) {
        return food.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view = LayoutInflater.from(mContext).inflate(R.layout.flistview, viewGroup,false);
        }

        Food tmp = (Food) getItem(i);

        TextView tvName = (TextView) view.findViewById(R.id.tvname);
        TextView tvQuan = (TextView) view.findViewById(R.id.tvquan);
        TextView tvUnit = (TextView) view.findViewById(R.id.tvunit);

        tvName.setText(tmp.getName());
        tvQuan.setText(""+tmp.getQuan());
        tvUnit.setText(tmp.getUnit());

        return view;
    }
}
