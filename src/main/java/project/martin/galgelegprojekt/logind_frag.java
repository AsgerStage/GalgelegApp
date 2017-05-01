package project.martin.galgelegprojekt;

import android.content.Intent;
import android.os.Bundle;
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
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Martin on 17-10-2016.
 */

public class logind_frag extends Fragment implements View.OnClickListener {
    TextView brugerNavnText, passwordText;
    EditText brugerNavnEdit, passwordEdit;
    Button logindKnap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rod = inflater.inflate(R.layout.logind, container, false);
        getActivity().setTitle("Log Ind");

        brugerNavnText = (TextView) rod.findViewById(R.id.brugernavn_text);
        brugerNavnEdit = (EditText) rod.findViewById(R.id.brugernavn_edit);

        passwordText = (TextView) rod.findViewById(R.id.password_text);
        passwordEdit = (EditText) rod.findViewById(R.id.password_edit);
        logindKnap = (Button) rod.findViewById(R.id.logind_btn);

        logindKnap.setOnClickListener(this);

        return rod;
    }
    @Override
    public void onClick(View v) {
        if(v == logindKnap){
            RequestParams rp = new RequestParams();
            rp.add("username", brugerNavnEdit.getText().toString()); rp.add("password", passwordEdit.getText().toString());

            HttpUtils.post("/galgeleg/login/"+brugerNavnEdit.getText().toString(), rp, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // If the response is JSONObject instead of expected JSONArray
                    Log.d("Galge", "Response from login: " + response);
                    try {
                        JSONObject serverResp = new JSONObject(response.toString());
                        Bundle bundle = new Bundle();
                        bundle.putString("brugernavn",serverResp.getString("brugernavn"));

                        hovedmenu_frag hovedmenu_frag = new hovedmenu_frag();
                        hovedmenu_frag.setArguments(bundle);
                            getFragmentManager().beginTransaction()
                                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                                    .replace(R.id.fragmentindhold, hovedmenu_frag)
                                    .addToBackStack(null)
                                    .commit();

                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    brugerNavnText.setText("Forkert brugernavn eller password!");
                }
            });
        }

        }
    }

