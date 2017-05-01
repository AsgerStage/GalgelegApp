package project.martin.galgelegprojekt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Martin on 27-10-2016.
 */

public class indstillinger_frag extends Fragment implements AdapterView.OnItemClickListener{
    ListView listView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String[] indstillinger = {"Hjælp", "Skift sprog", "Om appen"};
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, indstillinger);

        listView = new ListView(getActivity());
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

        return listView;
    }


    public void onItemClick(AdapterView<?> liste, View v, int position, long id) {
        if(position == 0){
            Fragment fragment2 = new hjælp_frag();

            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .replace(R.id.framelayout, fragment2)
                    .addToBackStack(null)
                    .commit();
        }
        else {
            Toast.makeText(getActivity(), "Ikke implementeret endnu", Toast.LENGTH_SHORT).show();
        }
    }
}

