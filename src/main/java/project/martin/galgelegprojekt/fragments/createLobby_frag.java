package project.martin.galgelegprojekt.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import project.martin.galgelegprojekt.R;
import project.martin.galgelegprojekt.utils.HttpUtils;

/**
 * Created by as on 5/3/17.
 */

public class createLobby_frag extends Fragment implements View.OnClickListener{
    Button leaveLobby,startGame;
    Bundle bundle;
    String brugernavn;
    TextView text;
    boolean run;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rod = inflater.inflate(R.layout.createlobby_frag, container, false);
        run=true;
        bundle = this.getArguments();
        if (bundle != null) {
            brugernavn = bundle.get("brugernavn").toString();
        }
        leaveLobby = (Button) rod.findViewById(R.id.forlad_btn_c);
        startGame = (Button) rod.findViewById(R.id.startLobby);

        leaveLobby.setOnClickListener(this);
        startGame.setOnClickListener(this);

        text = (TextView) rod.findViewById(R.id.createlobbytext);


                RequestParams rp = new RequestParams();
        rp.add("brugernavn",brugernavn);
                HttpUtils.post("/galgeleg/newMulti/"+brugernavn, rp, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {


                        Log.d("Galge", "Response from server: " + response);

                        text.setText("Lobbyen er startet. Start spillet får alle er klar");

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.d("Galge", "Response from server: (onFailure)" + responseString + "Status Code: " + statusCode);
                    }
                });
        return rod;
    }

    @Override
    public void onClick(View view) {
        if(view == leaveLobby){
            RequestParams rp = new RequestParams();
            rp.add("brugernavn",brugernavn);
            HttpUtils.post("/galgeleg/leaveLobby/"+brugernavn, rp, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {


                    Log.d("Galge", "Response from server: " + response);
                    getFragmentManager().popBackStackImmediate();


                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Log.d("Galge", "Response from server: (onFailure)" + responseString + "Status Code: " + statusCode);
                }
            });

        }
        else if(view == startGame) {
            RequestParams rp = new RequestParams();
            rp.add("brugernavn",brugernavn);
            HttpUtils.post("/galgeleg/startGame/"+brugernavn, rp, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Log.d("Galge", "Response from server: " + response);
                    mp_spil_frag mp_spil_frag = new mp_spil_frag();
                    mp_spil_frag.setArguments(bundle);
                    getFragmentManager().beginTransaction()
                            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                            .replace(R.id.fragmentindhold, mp_spil_frag)
                            .addToBackStack(null)
                            .commit();


                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Log.d("Galge", "Response from server: (onFailure)" + responseString + "Status Code: " + statusCode);
                }
            });

        }

    }
}
