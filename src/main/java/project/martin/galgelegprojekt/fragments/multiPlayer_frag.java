package project.martin.galgelegprojekt.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import project.martin.galgelegprojekt.R;

/**
 * Created by Martin on 17-10-2016.
 */

public class multiPlayer_frag extends Fragment implements View.OnClickListener {
    Button opretLobbyKnap, seLobbysKnap, highscoreKnap;
    String brugernavn;
    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rod = inflater.inflate(R.layout.multiplayer, container, false);
        getActivity().setTitle("Multiplayer");
        bundle = this.getArguments();
        if (bundle != null) {
            brugernavn = bundle.get("brugernavn").toString();
        }

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
        if (v == seLobbysKnap) {
            lobby_frag lobby_frag = new lobby_frag();
            lobby_frag.setArguments(bundle);
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .replace(R.id.fragmentindhold, lobby_frag)
                    .addToBackStack(null)
                    .commit();
        } else if (v == opretLobbyKnap) {
            createLobby_frag createLobby_frag = new createLobby_frag();
            createLobby_frag.setArguments(bundle);
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .replace(R.id.fragmentindhold, createLobby_frag)
                    .addToBackStack(null)
                    .commit();
        } else {
            highscore_frag highscore_frag = new highscore_frag();
            highscore_frag.setArguments(bundle);
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .replace(R.id.fragmentindhold, highscore_frag)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
