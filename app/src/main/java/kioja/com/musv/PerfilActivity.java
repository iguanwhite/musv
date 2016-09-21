package kioja.com.musv;




import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.parse.ParseException;
import com.parse.ParseFile;

import java.util.HashMap;

import kioja.com.musv.adapter.TabsPagerAdapter;
import kioja.com.musv.adapter.TabsPagerAdapterPerfil;
import kioja.com.musv.util.ParseProxyObject;


public class PerfilActivity extends ActionBarActivity {

    ViewPager tab;
    TabsPagerAdapterPerfil tabAdapter;

    Button seguirBtn;
    TextView textLocal, textEstilo, textPreferencia, textPerfil, textNome;
    ImageView fotoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil2);


        textLocal = (TextView) findViewById(R.id.textLocal);
        textEstilo = (TextView) findViewById(R.id.textEstilo);
        textPreferencia = (TextView) findViewById(R.id.textPreferencia);
        textPerfil = (TextView) findViewById(R.id.textPerfil);
        textNome = (TextView) findViewById(R.id.textNome);
        fotoImageView = (ImageView) findViewById(R.id.fotoImageView);
        seguirBtn = (Button) findViewById(R.id.seguirBtn);

        tabAdapter = new TabsPagerAdapterPerfil(getSupportFragmentManager());

        tab = (ViewPager)findViewById(R.id.pager);
        tab.setAdapter(tabAdapter);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setTitle("Musv");
/*
        PagerTabStrip strip = PagerTabStrip.class.cast(findViewById(R.id.pts_main));
        strip.setTextSpacing(View.ACCESSIBILITY_LIVE_REGION_NONE);
        strip.setDrawFullUnderline(false);
        */
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(tab);

   popularPerfil();

    }

    private void popularPerfil(){
        Intent intent = getIntent();
        ParseProxyObject ppo = (ParseProxyObject) intent.getSerializableExtra("parseObject");
        textNome.setText(ppo.getString("Nome"));

        byte[] bytes = (byte[]) intent.getSerializableExtra("bytes");

            if(bytes != null) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                fotoImageView.setImageBitmap(bmp);
            }




        fotoImageView = (ImageView) findViewById(R.id.fotoImageView);
        seguirBtn = (Button) findViewById(R.id.seguirBtn);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_actions, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_search:
                //         openSearch();
                return true;
            case R.id.action_settings:
                //     openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }






}
