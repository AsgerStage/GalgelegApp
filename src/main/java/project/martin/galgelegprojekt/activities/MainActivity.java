package project.martin.galgelegprojekt.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import project.martin.galgelegprojekt.R;
import project.martin.galgelegprojekt.fragments.velkomst_frag;

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
