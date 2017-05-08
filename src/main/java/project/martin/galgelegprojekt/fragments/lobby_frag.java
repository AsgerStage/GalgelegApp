package project.martin.galgelegprojekt.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import project.martin.galgelegprojekt.R;
import project.martin.galgelegprojekt.utils.HttpUtils;

/**
 * Created by as on 5/3/17.
 */

public class lobby_frag extends Fragment implements AdapterView.OnItemClickListener {
    ListView listView;
    ArrayAdapter adapter;
    String brugernavn;
    Bundle bundle;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bundle = this.getArguments();
        if (bundle != null) {
            brugernavn = bundle.get("brugernavn").toString();
        }

        final List<String> listen = new ArrayList<String>();
        RequestParams rp = new RequestParams();
        HttpUtils.post("/galgeleg/getMultiListNames/", rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Log.d("Galge", "Response from server: " + response);
                try {
                    response.getJSONArray("key");

                    JSONArray jArray = (JSONArray) response.getJSONArray("key");
                    if (jArray != null) {
                        for (int i = 0; i < jArray.length(); i++) {
                            listen.add(jArray.getString(i));
                        }
                    }
                    adapter.notifyDataSetChanged();
                    Log.d("Galge", "onSuccess færdig");

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("Galge", "Response from server: (onFailure)" + responseString + "Status Code: " + statusCode);
            }
        });


        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, listen);

        listView = new ListView(getActivity());
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);
        Log.d("Galge", "Returnerer listView");
        return listView;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {


        String lobbyName = adapterView.getItemAtPosition(position).toString();
        RequestParams rp = new RequestParams();
        rp.add("Hostname", lobbyName);
        rp.add("id", brugernavn);
        HttpUtils.post("/galgeleg/joinMulti/" + brugernavn, rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {


                Log.d("Galge", "Response from server: " + response);
                try {
                    lobbyWait_frag lobbyWait_frag = new lobbyWait_frag();
                    lobbyWait_frag.setArguments(bundle);
                    getFragmentManager().beginTransaction()
                            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                            .replace(R.id.fragmentindhold, lobbyWait_frag)
                            .addToBackStack(null)
                            .commit();
                } catch (Exception e) {
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



