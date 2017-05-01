package project.martin.galgelegprojekt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
