package com.bracketsps.buddy_final.ui.allFragment;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bracketsps.buddy_final.R;

public class AllFoodListview extends ArrayAdapter<String> {

    String[] foodname;
    int[] foodcal;
    int[] foodprot;
    String[] foodunit;
    Activity context;

    public AllFoodListview(Activity context, String[] fname, int[] fcal, int[] fprot, String[] funit) {
        super(context, R.layout.all_food,fname);

        this.context = context;
        this.foodname = fname;
        this.foodcal = fcal;
        this.foodprot = fprot;
        this.foodunit = funit;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        ViewHolder viewHolder = null;
        if (r == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.all_food,null,true);
            viewHolder= new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) r.getTag();
        }

        viewHolder.tv1.setText(foodname[position]);
        viewHolder.tv2.setText(Integer.toString(foodcal[position]));
        viewHolder.tv3.setText(Integer.toString(foodprot[position]));
        viewHolder.tv4.setText(foodunit[position]);

        return r;
    }

    class ViewHolder{
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;

        ViewHolder(View v){
            tv1 = (TextView) v.findViewById(R.id.textView6);
            tv2 = (TextView) v.findViewById(R.id.textView7);
            tv3 = (TextView) v.findViewById(R.id.textView8);
            tv4 = (TextView) v.findViewById(R.id.textView9);
        }
    }
}
