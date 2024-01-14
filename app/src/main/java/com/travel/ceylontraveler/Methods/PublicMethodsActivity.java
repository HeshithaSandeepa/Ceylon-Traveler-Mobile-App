package com.travel.ceylontraveler.Methods;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.travel.ceylontraveler.R;

public class PublicMethodsActivity {///Go another fragment


    public static void displayFragment(Fragment currentFragment, Fragment targetFragment) {
        FragmentTransaction transaction = currentFragment.getParentFragmentManager().beginTransaction();
        // remove the current fragment
        transaction.remove(currentFragment);
        //display the required fragment
        transaction.replace(R.id.fragment_container, targetFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
