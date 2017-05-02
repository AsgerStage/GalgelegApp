package project.martin.galgelegprojekt.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Martin on 07-11-2016.
 */

public class hjælp_frag extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        getActivity().setTitle("Hjælp");
        TextView tw = new TextView(getActivity());
        tw.setText("Her skal stå noget hjælp på et tidspunkt :D");
        return tw;
    }
}

