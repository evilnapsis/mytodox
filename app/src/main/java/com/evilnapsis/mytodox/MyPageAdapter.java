package com.evilnapsis.mytodox;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Lenovo on 19/03/2017.
 */

public class MyPageAdapter extends FragmentStatePagerAdapter {
    int ntabs;

    public MyPageAdapter(FragmentManager fm, int nt){
        super(fm);
        this.ntabs=nt;
    }

    public Fragment getItem(int position){
        switch (position){
            case 0:
                Fragment f = new Fragment1();
                return f;
            case 1:
                Fragment f1 = new Fragment2();
                return f1;
            default: return null;
        }
    }

    public int getCount(){
        return ntabs;
    }

}
