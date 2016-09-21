package kioja.com.musv;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import kioja.com.musv.adapter.TabsPagerAdapterPerfil;


public class PesquisaActivity extends ActionBarActivity {

    ViewPager tab;
    TabsPagerAdapterPerfil tabAdapter;
    ImageView btnPesquisar;
    CheckBox checkBoxCover, checkBoxAutoral, checkBoxProdutor, checkBoxArtista;
    EditText editTextMunicipio, editTextEstado, editTextEstilos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

        btnPesquisar = (ImageView) findViewById(R.id.btnPesquisar);
        // add a click listener to the button
        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (isValidBeforeSearch()) {
                    abrirResultadoPesquisa();
                }
            }
        });


        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setTitle("Musv");


    }

    private boolean isValidBeforeSearch() {

        int n = 0;

        checkBoxCover = (CheckBox) findViewById(R.id.checkBoxCover);
        if (checkBoxCover.isChecked()) {
            n++;
        }

        checkBoxAutoral = (CheckBox) findViewById(R.id.checkBoxAutoral);
        if (checkBoxAutoral.isChecked()) {
            n++;
        }

        checkBoxProdutor = (CheckBox) findViewById(R.id.checkBoxProdutor);
        if (checkBoxProdutor.isChecked()) {
            n++;
        }

        checkBoxArtista = (CheckBox) findViewById(R.id.checkBoxArtista);
        if (checkBoxArtista.isChecked()) {
            n++;
        }

        editTextMunicipio = (EditText) findViewById(R.id.editTextMunicipio);
        editTextEstado = (EditText) findViewById(R.id.editTextEstado);
        editTextEstilos = (EditText) findViewById(R.id.editTextEstilos);

//Evento e


        String municipio = editTextMunicipio.getText().toString().trim();
        if (municipio != null && municipio.length() > 0) {
            n++;
            //e = new Evento
            // e.setNome(nome)
        }

        String estado = editTextEstado.getText().toString().trim();
        if (estado != null && estado.length() > 0) {
            n++;
            // e.setDescricao(desc)
        }

        String estilos = editTextEstilos.getText().toString().trim();
        if (estilos != null && estilos.length() > 0) {
            n++;
            // e.setLocal(local)
        }


        if (n == 0) {
            Toast.makeText(this, "Utilize ao menos um filtro para a pesquisa!", Toast.LENGTH_LONG).show();
            return false;
        }else{
//Pesquisa com filtros
           // abrirResultadoPesquisa();


        }


        return true;
    }




    private void abrirResultadoPesquisa() {

        Intent myIntent = new Intent(PesquisaActivity.this, ResultadoPesquisaActivity.class);

        PesquisaActivity.this.startActivity(myIntent);
        finish();

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
