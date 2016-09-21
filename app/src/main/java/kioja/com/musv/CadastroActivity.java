package kioja.com.musv;


import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.io.ByteArrayOutputStream;

import kioja.com.musv.adapter.TabsPagerAdapterPerfil;


public class CadastroActivity extends ActionBarActivity {


    RadioGroup radioGroupPerfil, radioGroupAutoral;
    EditText editTextNome, editTextEstilos, editTextEstado, editTextCidade, editTextBiografia, editTextContato, editTextMusica;
    ImageView buttonConfirmar, imagemPerfil;
    boolean inseriuImagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        radioGroupPerfil = (RadioGroup) findViewById(R.id.radioGroupPerfil);
        radioGroupAutoral = (RadioGroup) findViewById(R.id.radioGroupAutoral);

        // TODO Colocar lista de municpios e estados.
        //         Colcoar foto
        //       apos terminar cadastro de perfil cadastrar publicacoes/postagens
        //      VAM q VAMO

        editTextNome = (EditText) findViewById(R.id.editTextNome);
        editTextEstilos = (EditText) findViewById(R.id.editTextEstilos);
        editTextEstado = (EditText) findViewById(R.id.editTextEstado);
        editTextCidade = (EditText) findViewById(R.id.editTextCidade);
        editTextBiografia = (EditText) findViewById(R.id.editTextBiografia);
        editTextContato = (EditText) findViewById(R.id.editTextContato);
        editTextMusica = (EditText) findViewById(R.id.editTextMusica);

        buttonConfirmar = (ImageView) findViewById(R.id.buttonConfirmar);
        buttonConfirmar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (isValidBeforeCreatePerfil()) {
                    criarPerfil();
                }
            }
        });

        imagemPerfil = (ImageView) findViewById(R.id.imagemPerfil);
        imagemPerfil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                colocarFotoPerfil();


            }
        });


        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setTitle("Musv");


    }


    private void colocarFotoPerfil() {


        if (Build.VERSION.SDK_INT < 19) {
            Intent intent = new Intent();
            intent.setType("image/jpeg");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Selecione Imagem"), 1);
        } else {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/jpeg");
            startActivityForResult(intent, 2);
        }


    }


    private boolean isValidBeforeCreatePerfil() {

        String nome, estilos, estado, cidade, biografia, contato, musica;

        nome = editTextNome.getText().toString();
        estilos = editTextEstilos.getText().toString();
        estado = editTextEstado.getText().toString();
        cidade = editTextCidade.getText().toString();
        biografia = editTextBiografia.getText().toString();
        contato = editTextContato.getText().toString();
        musica = editTextMusica.getText().toString();


        if ((nome != null && nome.length() == 0)) {
            Toast.makeText(getApplicationContext(), "Informe seu nome de trabalho!.", Toast.LENGTH_SHORT).show();
            return false;
        }

        int selectedId = radioGroupPerfil.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        RadioButton radioEscolhido = (RadioButton) findViewById(selectedId);


        int selectedIdAutoral = radioGroupAutoral.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        RadioButton radioEscolhidoAutoral = (RadioButton) findViewById(selectedIdAutoral);


        if ((radioEscolhido == null)) {
            Toast.makeText(getApplicationContext(), "Informe se você é um Artista ou Produtor de Eventos!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if ((radioEscolhidoAutoral == null)) {
            Toast.makeText(getApplicationContext(), "Informe se seu trabalho é Autoral ou Cover!", Toast.LENGTH_SHORT).show();
            return false;
        }


        if ((estilos != null && estilos.length() == 0)) {
            Toast.makeText(getApplicationContext(), "Informe 1 à 3 estilos que você atua.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if ((estado != null && estado.length() == 0)) {
            Toast.makeText(getApplicationContext(), "Informe seu estado!!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if ((cidade != null && cidade.length() == 0)) {
            Toast.makeText(getApplicationContext(), "Informe sua cidade!!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if ((contato != null && contato.length() == 0)) {
            Toast.makeText(getApplicationContext(), "Informe alguma informação de contato!!", Toast.LENGTH_SHORT).show();
            return false;

        } else {
            return true;
        }
    }

    private void criarPerfil() {

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {

            int selectedId = radioGroupPerfil.getCheckedRadioButtonId();
            RadioButton radioEscolhido = (RadioButton) findViewById(selectedId);

            int selectedIdAutoral = radioGroupAutoral.getCheckedRadioButtonId();
            RadioButton radioEscolhidoAutoral = (RadioButton) findViewById(selectedIdAutoral);


            String nome, estilos, estado, cidade, biografia, contato, trabalho;

            nome = editTextNome.getText().toString();
            estilos = editTextEstilos.getText().toString();
            estado = editTextEstado.getText().toString();
            cidade = editTextCidade.getText().toString();
            biografia = editTextBiografia.getText().toString();
            contato = editTextContato.getText().toString();
            trabalho = editTextMusica.getText().toString();


            ParseObject postObject = new ParseObject("Perfil");
            postObject.put("Nome", nome);

            ParseRelation<ParseObject> relation = postObject.getRelation("User");
            relation.add(ParseUser.getCurrentUser());

            postObject.put("Ativo", true);
            if (radioEscolhido.getText().toString().contains("Artista")) {
                postObject.put("Artista", true);
            } else {
                postObject.put("Artista", false);
            }
            if (radioEscolhido.getText().toString().contains("Autoral")) {
                postObject.put("Autoral", true);
            } else {
                postObject.put("Autoral", false);
            }
            postObject.put("Estilo", estilos);
            postObject.put("Biografia", biografia);
            postObject.put("Celular", contato);
            postObject.put("Estado", estado);
            postObject.put("Cidade", cidade);
            postObject.put("Contato", contato);
            postObject.put("Trabalho", trabalho);

            if(inseriuImagem) {
                BitmapDrawable bitmapDrawable = ((BitmapDrawable) imagemPerfil.getDrawable());
                Bitmap bitmap = bitmapDrawable.getBitmap();

                ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                Bitmap redux = Bitmap.createScaledBitmap(bitmap, 120, 120, false);
                redux.compress(Bitmap.CompressFormat.JPEG, 100, stream2);
                // get byte array here
                byte[] bytearray = stream2.toByteArray();

                if (bytearray != null) {
                    ParseFile photoFile = new ParseFile("profile.jpg", bytearray);
                    photoFile.saveInBackground(new SaveCallback() {

                        public void done(ParseException e) {
                            if (e != null) {

                            } else {

                            }
                        }
                    });
                    postObject.put("Imagem", photoFile);
                }
            }

            postObject.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Toast.makeText(getApplicationContext(), "Perfil publicado com sucesso!!", Toast.LENGTH_SHORT).show();
                        abrirPrincipal();

                    } else {
                        // erro. Melhor colocar um dialog?
                        Toast.makeText(getApplicationContext(), "Falha ao conectar ao servidor!", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        } else {


            Toast.makeText(getApplicationContext(), "Falha ao conectar ao servidor!, Faça login novamente!", Toast.LENGTH_SHORT).show();
            return;

        }

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
            case R.id.action_logout:
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


    private void logout() {

        finish();
        ParseUser.logOut();
        abrirLogin();


    }

    private void abrirLogin() {
        Intent myIntent = new Intent(CadastroActivity.this, LoginActivity.class);
        CadastroActivity.this.startActivity(myIntent);
    }

    private void abrirPrincipal() {
        finish();
        Intent myIntent = new Intent(CadastroActivity.this, PrincipalActivity.class);
        CadastroActivity.this.startActivity(myIntent);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;
        if (null == data) return;
        Uri originalUri = null;
        if (requestCode == 1) {
            originalUri = data.getData();

            imagemPerfil.setImageURI(originalUri);
            inseriuImagem = true;

        } else if (requestCode == 2) {
            originalUri = data.getData();
            final int takeFlags = data.getFlags()
                    & (Intent.FLAG_GRANT_READ_URI_PERMISSION
                    | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            imagemPerfil.setImageURI(originalUri);
            inseriuImagem = true;
        }


    }


}
