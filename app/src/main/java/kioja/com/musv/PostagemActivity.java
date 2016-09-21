package kioja.com.musv;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

import kioja.com.musv.util.EditTextPostagem;


public class PostagemActivity extends ActionBarActivity {

    private EditText fieldPublicacao;
    private Button buttonPublicar;
    private String publicacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postagem);
        fieldPublicacao = (EditText) findViewById(R.id.fieldPublicacao);

        buttonPublicar = (Button) findViewById(R.id.buttonPublicar);
        // add a click listener to the button
        buttonPublicar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (isValidBeforePublish()) {
                    publicar();
                }
            }
        });

    }


    private boolean isValidBeforePublish() {
        publicacao = fieldPublicacao.getText().toString();
        if ((publicacao != null && publicacao.length() == 0)) {
            Toast.makeText(getApplicationContext(), "Informe sua postagem!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }


    private void publicar() {
        String postagem = fieldPublicacao.getText().toString();

        ParseObject postObject = new ParseObject("Postagem");
        postObject.put("Texto", postagem);

        ParseRelation<ParseObject> relation = postObject.getRelation("Perfil");
        relation.add(buscarPerfilViaCurrentUser());

        postObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(getApplicationContext(), "Publicado!!", Toast.LENGTH_SHORT).show();
                    voltarTelaPrincipal();
                } else {
                    Toast.makeText(getApplicationContext(), "Falha ao conectar ao servidor!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void voltarTelaPrincipal(){
        finish();
    }


    private ParseObject buscarPerfilViaCurrentUser() {
        final ParseObject[] currentPerfilObject = {null};
        ParseUser currentUser = ParseUser.getCurrentUser();

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Perfil");
        query.whereEqualTo("User", currentUser);
        try {
            List<ParseObject> list = query.find();
            currentPerfilObject[0] = list.get(0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return currentPerfilObject[0];
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_postagem, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
