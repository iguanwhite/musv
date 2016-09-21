package kioja.com.musv;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import kioja.com.musv.adapter.PerfilAdapter;
import kioja.com.musv.adapter.PostagemAdapter;
import kioja.com.musv.adapter.TabsPagerAdapterPerfil;
import kioja.com.musv.util.ParseProxyObject;


public class ResultadoPesquisaActivity extends ListActivity {
    //TODO Fragment para manter barra de pesquisa.


    private List<ParseObject> listPerfis;
    ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        list = (ListView) findViewById(R.id.listView);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {

            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Perfil");
            query.orderByDescending("createdAt");


            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> listObtidos, ParseException e) {
                    if (e == null) {
// sucess.
                        listPerfis = listObtidos;

                        PerfilAdapter adapter = new PerfilAdapter(getListView().getContext(), listObtidos);
                        setListAdapter(adapter);


                    } else {
                        e.toString();

                    }
                }

            });
        }


    }

    @Override
    public void onListItemClick(ListView list, View view, int position, long id) {
        Object o = list.getItemAtPosition(position);
      abrirPerfil(o);


    }

    private void abrirPerfil(Object o){


            Intent myIntent = new Intent(ResultadoPesquisaActivity.this, PerfilActivity.class);
//TODO VER MELHOR FORMA DE PASSAR OBJETO PARA OUTRA TELA

        // --- Sending ---
        ParseProxyObject ppo = new ParseProxyObject((ParseObject) o);
        myIntent.putExtra("parseObject", ppo);

        ParseFile mFile = ((ParseObject) o).getParseFile("Imagem");
        try {
            final byte[] mImageData = mFile.getData();
            myIntent.putExtra("bytes", mImageData);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ResultadoPesquisaActivity.this.startActivity(myIntent);








    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }





}


