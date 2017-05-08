package project.martin.galgelegprojekt.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import project.martin.galgelegprojekt.R;
import project.martin.galgelegprojekt.utils.HttpUtils;

/**
 * Created by Martin on 17-10-2016.
 */

public class singlePlayer_frag extends Fragment implements View.OnClickListener {
    Button gammeltSpilKnap, nytSpilKnap;
    Bundle bundle;
    String brugernavn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rod = inflater.inflate(R.layout.singleplayer, container, false);
        getActivity().setTitle("Singleplayer");
        bundle = this.getArguments();
        if (bundle != null) {
            brugernavn = bundle.get("brugernavn").toString();
        }

        gammeltSpilKnap = (Button) rod.findViewById(R.id.gammeltSpil_btn);
        nytSpilKnap = (Button) rod.findViewById(R.id.nytSpil_btn);

        gammeltSpilKnap.setOnClickListener(this);
        nytSpilKnap.setOnClickListener(this);

        return rod;
    }

    @Override
    public void onClick(View v) {
        if (v == nytSpilKnap) {
            RequestParams rp = new RequestParams();
            rp.add("username", brugernavn);

            HttpUtils.post("/galgeleg/nulstil/" + brugernavn, rp, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Log.d("Galge", "Response from server: " + response);
                    try {
                        spil_frag spil_frag = new spil_frag();
                        spil_frag.setArguments(bundle);
                        getFragmentManager().beginTransaction()
                                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                                .replace(R.id.fragmentindhold, spil_frag)
                                .addToBackStack(null)
                                .commit();

                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Log.d("Galge", "Response from server: (onFailure)" + responseString + "Status Code: " + statusCode);
                }
            });


        } else if (v == gammeltSpilKnap) {
            RequestParams rp = new RequestParams();
            rp.add("username", brugernavn);

            HttpUtils.post("/galgeleg/isContinueAvailable/" + brugernavn, rp, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        Log.d("Galge", "Response from server: " + response.get("key"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (response.get("key").toString().equals("true")) {
                            spil_frag spil_frag = new spil_frag();
                            spil_frag.setArguments(bundle);
                            getFragmentManager().beginTransaction()
                                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                                    .replace(R.id.fragmentindhold, spil_frag)
                                    .addToBackStack(null)
                                    .commit();
                        } else {
                            Context context = getActivity().getApplicationContext();
                            CharSequence text = "Du har ikke noget spil du kan fortsætte";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Log.d("Galge", "Response from server: (onFailure)" + responseString + "Status Code: " + statusCode);
                }
            });

        }
    }
}
