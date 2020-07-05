package com.bracketsps.buddy_final.ui.allFragment;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.bracketsps.buddy_final.metadata.SavingProgram;

import static com.bracketsps.buddy_final.BackgroundWorker.loginStatus;

public class LoginSectionsPagerAdapter extends FragmentStatePagerAdapter {

    private final Context mContext;
    SavingProgram saved_pro;

    public LoginSectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f=null;
        switch (position)
        {
            case 0:
                return FragmentCalculator.newInstance();
            case 1:
                return  FragmentCalculator.newInstance();
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
                return  FragmentMap.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
