package project.martin.galgelegprojekt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Martin on 17-10-2016.
 */

public class multiPlayer_frag extends Fragment implements View.OnClickListener {
    Button opretLobbyKnap, seLobbysKnap, highscoreKnap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rod = inflater.inflate(R.layout.multiplayer, container, false);
        getActivity().setTitle("Multiplayer");

        opretLobbyKnap = (Button) rod.findViewById(R.id.opretLobby_btn);
        seLobbysKnap = (Button) rod.findViewById(R.id.seLobbys_btn);
        highscoreKnap = (Button) rod.findViewById(R.id.highscore_btn);

        seLobbysKnap.setOnClickListener(this);
        opretLobbyKnap.setOnClickListener(this);
        highscoreKnap.setOnClickListener(this);

        return rod;
    }
    @Override
    public void onClick(View v) {
        if(v == seLobbysKnap){
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .replace(R.id.fragmentindhold, new spil_frag())
                    .addToBackStack(null)
                    .commit();
        }
        else if(v == opretLobbyKnap) {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .replace(R.id.fragmentindhold, new hovedmenu_frag())
                    .addToBackStack(null)
                    .commit();
        }
        else{
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .replace(R.id.fragmentindhold, new hovedmenu_frag())
                    .addToBackStack(null)
                    .commit();     }
    }
}
