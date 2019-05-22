package com.contentful.hello.android.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.contentful.hello.android.SignInFragment;
import com.contentful.hello.android.SignUpFragment;

public class Pager extends FragmentStatePagerAdapter {

    private String[] tabTitles = {"Sign In","Account Creation"};

    public CharSequence getPageTitle(int position)
    {
        return tabTitles[position];
    }

    public Pager(FragmentManager fm) {
        super(fm);
    }


    @Override

    public Fragment getItem(int position) {
        int i = position;

        switch (i)
        {
            case 0:
                SignInFragment signInFragment = new SignInFragment();
                return signInFragment;

            case 1:
                SignUpFragment signUpFragment = new SignUpFragment();
                return signUpFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }
}
