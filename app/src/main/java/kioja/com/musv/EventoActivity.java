package kioja.com.musv;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import kioja.com.musv.adapter.TabsPagerAdapterPerfil;


public class EventoActivity extends ActionBarActivity {

    ViewPager tab;
    TabsPagerAdapterPerfil tabAdapter;

    EditText editTextNome;
    EditText editTextDesc;
    EditText editTextLocal;
    EditText editTextCidade;
    EditText editTextEstado;
    Spinner spinnerProdutor;
    ImageView btnCriarEvento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);


        btnCriarEvento = (ImageView) findViewById(R.id.btnCriarEvento);
        // add a click listener to the button
        btnCriarEvento.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (isValidBeforeSave()) {
                    criarEvento();
                }
            }
        });


        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setTitle("Musv");


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_actions, menu);
        return true;

    }

    private boolean isValidBeforeSave() {

        editTextNome = (EditText) findViewById(R.id.editTextNome);
          editTextDesc = (EditText) findViewById(R.id.editTextDesc);
        editTextLocal = (EditText) findViewById(R.id.editTextLocal);
        editTextCidade = (EditText) findViewById(R.id.editTextCidade);
        editTextEstado = (EditText) findViewById(R.id.editTextEstado);
//Evento e


        String nome = editTextNome.getText().toString().trim();
        if(nome != null && nome.length() > 0){
            //e = new Evento
            // e.setNome(nome)
        }else{
            mensagemPendente("Nome do Evento");
            editTextNome.setFocusable(true);
            editTextNome.requestFocus();
            return false;
        }

        String desc = editTextDesc.getText().toString().trim();
        if(desc != null && desc.length() > 0){
             // e.setDescricao(desc)
        }else{
            mensagemPendente("Descrição");
            editTextDesc.setFocusable(true);
            editTextDesc.requestFocus();
            return false;
        }

        String local = editTextLocal.getText().toString().trim();
        if(local != null  && local.length() > 0 ){
            // e.setLocal(local)
        }else{
            mensagemPendente("Local");
            editTextLocal.setFocusable(true);
            editTextLocal.requestFocus();
            return false;
        }

        String cidade = editTextCidade.getText().toString().trim();
        if(cidade != null  && cidade.length() > 0 ){
            // e.setCidade(cidade)
        }else{
            mensagemPendente("Cidade");
            editTextCidade.setFocusable(true);
            editTextCidade.requestFocus();
            return false;
        }

        String estado = editTextEstado.getText().toString().trim();
        if(estado != null  && estado.length() > 0){
            // e.setEstado(estado)
        }else{
            mensagemPendente("Estado");
            editTextEstado.setFocusable(true);
            editTextEstado.requestFocus();
            return false;
        }






        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_search:
                abrirPesquisa();
                return true;
            case R.id.action_settings:
                //     openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private void abrirPesquisa(){
        //  Intent myIntent = new Intent(PrincipalActivity.this, PesquisaActivity.class);
        Intent myIntent = new Intent(EventoActivity.this, PesquisaActivity.class);
        //  myIntent.putExtra("key", value); //Optional parameters
        EventoActivity.this.startActivity(myIntent);

    }


    private void criarEvento() {
        //Criar objeto evento e etc...


        Toast.makeText(this, "Evento " + editTextNome.getText().toString() + " criado com sucesso!", Toast.LENGTH_LONG).show();
        finish();
    }

    private void mensagemPendente(String valor){
        Toast.makeText(this, "Campo: " + valor + " é obrigatório", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
