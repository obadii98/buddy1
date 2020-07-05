package com.bracketsps.buddy_final.ui.allFragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.bracketsps.buddy_final.metadata.SavingProgram;

import java.io.Serializable;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;
import static com.bracketsps.buddy_final.BackgroundWorker.loginStatus;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentStatePagerAdapter  implements Serializable {

    private final Context mContext;
    SavingProgram saved_pro;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        if(object instanceof FragmentProgram)
        return POSITION_NONE;
        else
        return POSITION_UNCHANGED;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f=null;
        switch (position)
        {
            case 0:{
                return  FragmentMain.newInstance();

            }

            case 1:
            {

              return  FragmentCalculator.newInstance();


            }

            case 2:
            {




                saved_pro=new SavingProgram("pro.bin");
                if(saved_pro.isIsthere()==false) {
                 return  FragmentProgram.newInstance();


                }else {
                    return   FragmentExercise.newInstance();



                }

            }

            case 3:
            {


               return  FragmentMap.newInstance();


            }

        }

        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
return null;

    }

    @Override
    public void startUpdate(@NonNull ViewGroup container) {
        super.startUpdate(container);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 4;
    }


}