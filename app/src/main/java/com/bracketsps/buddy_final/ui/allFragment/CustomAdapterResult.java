package com.bracketsps.buddy_final.ui.allFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bracketsps.buddy_final.R;

import static com.bracketsps.buddy_final.ui.allFragment.FragmentCalculator.sumcal;
import static com.bracketsps.buddy_final.ui.allFragment.FragmentCalculator.sumpro;


public class CustomAdapterResult extends BaseAdapter {

    Context mcontext;
    int sumProRes;
    int sumCalRes;

    public CustomAdapterResult(Context context, int sumProRes, int sumCalRes){
        this.mcontext = context;
        this.sumProRes = sumProRes;
        this.sumCalRes = sumCalRes;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view = LayoutInflater.from(mcontext).inflate(R.layout.result_listview, viewGroup,false);
        }


        TextView tvcalres = (TextView) view.findViewById(R.id.tvCalRes);
        TextView tvprores = (TextView) view.findViewById(R.id.tvProRes);

        tvcalres.setText(""+sumcal);
        tvprores.setText(""+sumpro);

        return view;
    }
}
