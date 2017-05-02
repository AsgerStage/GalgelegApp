package project.martin.galgelegprojekt.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;

import project.martin.galgelegprojekt.R;
import project.martin.galgelegprojekt.fragments.indstillinger_frag;

public class indstillinger_akt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_indstillinger_akt);

        setTitle("Indstillinger");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportFragmentManager().beginTransaction()
                //.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .add(R.id.framelayout, new indstillinger_frag())
                .commit();

    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            setTitle("Indstillinger");
        }
        return super.onOptionsItemSelected(item);
    }
}
