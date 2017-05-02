package project.martin.galgelegprojekt.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import project.martin.galgelegprojekt.R;

/**
 * Created by Martin on 17-10-2016.
 */

public class velkomst_frag extends Fragment implements Runnable {

    Handler handler = new Handler();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View rod = inflater.inflate(R.layout.velkomst, container, false);

        handler.postDelayed(this, 3000);

        return rod;
    }

    @Override
    public void run() {
        Fragment fragment = new logind_frag();
        getFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.fragmentindhold, fragment)
                .commit();
    }
}
