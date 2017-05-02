package project.martin.galgelegprojekt.fragments;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import project.martin.galgelegprojekt.utils.HttpUtils;


/**
 * Created by Martin on 17-10-2016.
 */

public class mp_spil_frag extends Fragment implements View.OnClickListener {
    private ImageView iw;
    private TextView info, scoreWin, scoreLoose;
    private Button gætKnap, spilIgen, resetScore;
    private EditText edit;
    Bundle bundle;
    String brugernavn;

    SharedPreferences prefs;

    public mp_spil_frag(){
        System.out.println("spilfrag oprettet");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        System.out.println("spilfragment oncreateview");
        ScrollView sw = new ScrollView(getActivity());
        TableLayout tl = new TableLayout(getActivity());
        LinearLayout ll = new LinearLayout(getActivity());
        LinearLayout ll2 = new LinearLayout(getActivity());
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        bundle =this.getArguments();
        if (bundle!=null){
            brugernavn=bundle.get("brugernavn").toString();
        }
        System.out.println("winscore = " + prefs.getInt("Score", prefs.getInt("Score", 0)));
        System.out.println("loosescore = " + prefs.getInt("ScoreLoose", prefs.getInt("ScoreLoose", 0)));



        info = new TextView(getActivity());
        info.setText("Velkommen til Galgelegen!" +
                "\nHeld og lykke :)\n");
        tl.addView(info);
        RequestParams rp = new RequestParams();
        rp.add("username", brugernavn); rp.add("ord","");

        HttpUtils.post("/galgeleg/gaetBogstavMultiOgLog/"+brugernavn, rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray

                    Log.d("Galge", "Response from server: " + response);
                try {
                    info.setText(response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("Galge", "Response from server: (onFailure)" + responseString+"Status Code: "+statusCode);
            }
        });

        edit = new EditText(getActivity());
        edit.setHint("Skriv ét bogstav...");
        edit.setLayoutParams(new TableLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
        ll.addView(edit);

        gætKnap = new Button(getActivity());
        gætKnap.setText("Gæt");
        gætKnap.setBackgroundColor(Color.parseColor("#03A9F4"));
        gætKnap.setLayoutParams(new TableLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 5f));
        ll.addView(gætKnap);

        tl.addView(ll);

        spilIgen = new Button(getActivity());
        spilIgen.setText("Start forfra");
        spilIgen.setVisibility(View.VISIBLE);
        tl.addView(spilIgen);



        gætKnap.setOnClickListener(this);
        spilIgen.setOnClickListener(this);

        sw.addView(tl);
        return sw;
    }

    @Override
    public void onClick(View v) {
        if(v == gætKnap) {
            RequestParams rp = new RequestParams();
            rp.add("ord", edit.getText().toString());

            HttpUtils.post("/galgeleg/gaetBogstav/"+brugernavn, rp, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Log.d("Galge", "Response from server: " + response);
                    try {
                        RequestParams rp = new RequestParams();
                        rp.add("username", brugernavn);

                        HttpUtils.get("/galgeleg/log/"+brugernavn, rp, new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                // If the response is JSONObject instead of expected JSONArray

                                Log.d("Galge", "Response from server: " + response);
                                try {
                                    info.setText(response.get("key").toString());


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                Log.d("Galge", "Response from server: (onFailure)" + responseString+"Status Code: "+statusCode);
                            }
                        });


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Log.d("Galge", "Response from server: (onFailure)" + responseString+"Status Code: "+statusCode);
                }
            });

        }
        else if(v == spilIgen){
            RequestParams rp = new RequestParams();
            rp.add("username", brugernavn);
            HttpUtils.post("/galgeleg/nulstil/"+brugernavn, rp, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // If the response is JSONObject instead of expected JSONArray
                    Log.d("Galge", "Response from server: " + response);
                    try {
                        RequestParams rp = new RequestParams();
                        rp.add("username", brugernavn);

                        HttpUtils.get("/galgeleg/log/"+brugernavn, rp, new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                // If the response is JSONObject instead of expected JSONArray

                                Log.d("Galge", "Response from server: " + response);
                                try {
                                    info.setText(response.get("key").toString());


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                Log.d("Galge", "Response from server: (onFailure)" + responseString+"Status Code: "+statusCode);
                            }
                        });


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




}
