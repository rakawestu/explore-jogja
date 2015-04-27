package com.github.rakawestu.explorejogja.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.github.rakawestu.explorejogja.R;

import butterknife.ButterKnife;

/**
 * Base activity for doing the Dependency
 *
 * @author rakawm
 */
public abstract class BaseActivity extends ActionBarActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        injectViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.global, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void injectDependencies() {
        ExploreJogjaApp exploreJogjaApp = (ExploreJogjaApp) getApplication();
        exploreJogjaApp.inject(this);
    }


    private void injectViews() {
        ButterKnife.inject(this);
    }
}
