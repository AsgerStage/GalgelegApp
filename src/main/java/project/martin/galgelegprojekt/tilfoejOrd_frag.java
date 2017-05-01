package project.martin.galgelegprojekt;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

/**
 * Created by Martin on 17-10-2016.
 */

public class tilfoejOrd_frag extends Fragment implements View.OnClickListener {

    Galgelogik galge = new Galgelogik();
    private EditText tilfoej;
    private TextView tv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ScrollView sv = new ScrollView(getActivity());

        TableLayout tl = new TableLayout(getActivity());

        TextView overskrift = new TextView(getActivity());
        overskrift.setText("Ord som er tilføjet");
        overskrift.setTextSize(36);
        overskrift.setTextColor(Color.BLACK);
        tl.addView(overskrift);

        tv = new TextView(getActivity());
        tv.setText(galge.toString());
        tl.addView(tv);

        TextView til = new TextView(getActivity());
        til.setText("\nTilføj et ord herunder:");
        til.setTextSize(16);
        til.setTextColor(Color.BLACK);
        tl.addView(til);

        LinearLayout ll = new LinearLayout(getActivity());

        tilfoej = new EditText(getActivity());
        tilfoej.setHint("Indtast et ord her...");
        tilfoej.setWidth(750);
        ll.addView(tilfoej);

        Button OK = new Button(getActivity());
        OK.setText("OK");
        OK.setOnClickListener(this);
        ll.addView(OK);

        tl.addView(ll);
        sv.addView(tl);

        return sv;
    }

    @Override
    public void onClick(View v) {
        String tilf = tilfoej.getText().toString().toLowerCase();
        if(tilf.length() < 2){
            tilfoej.setError("Ordet skal bestå af mindst 2 bogstaver");
        }
        else {
            galge.muligeOrd.add(tilf);
            tv.setText(galge.toString());
            tilfoej.setText("");
            tilfoej.setHint("Indtast et ord her...");
        }
    }
}
