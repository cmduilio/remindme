package com.sirio.remindme.fragments;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.sirio.remindme.R;

public class BaseFragment extends Fragment {

    public void changeFragmentWithAnimation(BaseFragment fragmentTo){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.setCustomAnimations(R.animator.enter_from_right,
                R.animator.exit_to_left,
                R.animator.enter_from_left,
                R.animator.exit_to_right);

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.hide(this);
        fragmentTransaction.add(R.id.fragment, fragmentTo);
        fragmentTransaction.commit();
    }
}
