package project.martin.galgelegprojekt;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            Fragment fragment = new velkomst_frag();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentindhold, fragment)
                    .commit();
        }

        setTitle("Galgespil");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        if(item.getItemId() == android.R.id.home){
//            onBackPressed();
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
