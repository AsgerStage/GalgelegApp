package project.martin.galgelegprojekt.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import project.martin.galgelegprojekt.R;
import project.martin.galgelegprojekt.utils.HttpUtils;

/**
 * Created by as on 5/3/17.
 */

public class lobbyWait_frag extends Fragment implements View.OnClickListener {
    Bundle bundle;
    String brugernavn;
    Handler handler;
    TextView text;
    boolean run;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rod = inflater.inflate(R.layout.lobbywait_frag, container, false);
        run = true;
        bundle = this.getArguments();
        if (bundle != null) {
            brugernavn = bundle.get("brugernavn").toString();
        }
        handler = new Handler();
        text = (TextView) rod.findViewById(R.id.lobbywaittext);
        text.setText("Venter på at spillet starter.");
        final Runnable r = new Runnable() {
            public void run() {
                RequestParams rp = new RequestParams();
                HttpUtils.get("/galgeleg/isGameStarted/" + brugernavn, rp, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {


                        Log.d("Galge", "Response from server: " + response);
                        try {
                            if (response.get("key").toString().equals("true")) {
                                run = false;
                                mp_spil_frag mp_spil_frag = new mp_spil_frag();
                                mp_spil_frag.setArguments(bundle);
                                getFragmentManager().beginTransaction()
                                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                                        .replace(R.id.fragmentindhold, mp_spil_frag)
                                        //     .addToBackStack(null)
                                        .commit();
                            } else {
                                Log.d("Galge", "isGameStarted=false");
                                if (text.length() < 31)
                                    text.append(".");
                                else {
                                    text.setText("Venter på at spillet starter.");
                                }
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.d("Galge", "Response from server: (onFailure)" + responseString + "Status Code: " + statusCode);
                    }
                });

                if (run) handler.postDelayed(this, 2000);
            }
        };

        handler.postDelayed(r, 2000);


        return rod;
    }

    @Override
    public void onClick(View view) {

    }
}
