package project.martin.galgelegprojekt.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import project.martin.galgelegprojekt.R;
import project.martin.galgelegprojekt.activities.indstillinger_akt;
import project.martin.galgelegprojekt.utils.HttpUtils;

/**
 * Created by Martin on 17-10-2016.
 */

public class hovedmenu_frag extends Fragment implements View.OnClickListener {
    Button singlePlayerKnap, multiPlayerKnap, indstillingerKnap;
    String brugernavn;
    Bundle bundle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rod = inflater.inflate(R.layout.hovedmenu, container, false);
        setHasOptionsMenu(true);
        getActivity().setTitle("Hovedmenu");
        bundle =this.getArguments();
        if (bundle!=null){
            brugernavn=bundle.get("brugernavn").toString();
        }


        multiPlayerKnap = (Button) rod.findViewById(R.id.multiPlayer_btn);
        singlePlayerKnap = (Button) rod.findViewById(R.id.singlePlayer_btn);

        multiPlayerKnap.setOnClickListener(this);
        singlePlayerKnap.setOnClickListener(this);

        return rod;
    }
    @Override
    public void onClick(View v) {
        if(v == multiPlayerKnap){
            RequestParams rp = new RequestParams();
            rp.add("username", brugernavn);

            HttpUtils.post("/galgeleg/startGame/"+brugernavn, rp, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // If the response is JSONObject instead of expected JSONArray
                    Log.d("Galge", "Response from login: " + response);
                    try {
                        JSONObject serverResp = new JSONObject(response.toString());
                        multiPlayer_frag multiPlayer_frag = new multiPlayer_frag();
                        multiPlayer_frag.setArguments(bundle);
                        getFragmentManager().beginTransaction()
                                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                                .replace(R.id.fragmentindhold, multiPlayer_frag)
                                .addToBackStack(null)
                                .commit();

                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                }
            });

        }
        else if(v == singlePlayerKnap) {
            RequestParams rp = new RequestParams();
            rp.add("username", brugernavn);

            HttpUtils.post("/galgeleg/playerCheck/"+brugernavn, rp, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // If the response is JSONObject instead of expected JSONArray
                    Log.d("Galge", "Response from server: " + response);
                    try {
                        singlePlayer_frag singlePlayer_frag = new singlePlayer_frag();
                        singlePlayer_frag.setArguments(bundle);
                        getFragmentManager().beginTransaction()
                                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                                .replace(R.id.fragmentindhold, singlePlayer_frag)
                                .addToBackStack(null)
                                .commit();

                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Log.d("Galge", "Response from server: (onFailure)" + responseString+"Status Code: "+statusCode);
                }
            });

        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.settings){
            Intent i = new Intent(getActivity(), indstillinger_akt.class);
            startActivity(i);
            return true;
        }
        else if(id == R.id.logout){
            Fragment fragment = new logind_frag();
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, fragment)
                    .commit();
        }

        return super.onOptionsItemSelected(item);
    }
}
