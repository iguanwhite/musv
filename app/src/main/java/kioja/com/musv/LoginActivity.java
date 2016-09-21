package kioja.com.musv;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import kioja.com.musv.util.AlertaUtil;


public class LoginActivity extends Activity {

    private EditText fieldUsuario;
    private EditText fieldSenha;
    private Button buttonLogin;
    private Button buttonConta;

    private boolean resultado;

    private String usuario, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        fieldSenha = (EditText) findViewById(R.id.fieldSenha);

        fieldUsuario = (EditText) findViewById(R.id.fieldUsuario);
        fieldUsuario.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == 66) {
                   fieldSenha.requestFocus();
                }
                return false;
            }
        });


        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        // add a click listener to the button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (isValidBeforeLogin()) {
                    fazerLogin();
                }
            }
        });

        buttonConta = (Button) findViewById(R.id.buttonConta);
        // add a click listener to the button
        buttonConta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fazerConta();
            }
        });


    }


    private boolean isValidBeforeLogin() {
        usuario = fieldUsuario.getText().toString();
        senha = fieldSenha.getText().toString();


        if ((usuario != null && usuario.length() == 0) || (senha != null && senha.length() == 0)) {

            Toast.makeText(getApplicationContext(), "Informe seu nome de usuário e senha!", Toast.LENGTH_LONG).show();
            return false;

        }else{
            return true;
        }


    }

    private void fazerLogin() {
        Toast.makeText(getApplicationContext(), "Verificando usuário...", Toast.LENGTH_LONG).show();
        findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
        ParseUser.logInInBackground(usuario, senha, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                // sucesso
                if(e == null){
                    findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                    Intent myIntent = new Intent(LoginActivity.this, PrincipalActivity.class);
                    LoginActivity.this.startActivity(myIntent);
                    finish();
                }else{ //erro
                    findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                    AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                    alert.setTitle("Acesso Musv");
                    alert.setMessage("Credenciais Inválidas!");
                    alert.setPositiveButton("OK", null);
                    alert.show();
                    //   Toast.makeText(getApplicationContext(), "Credenciais inválidas!", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(), "Falha ao conectar ao servidor!", Toast.LENGTH_SHORT).show();

                }

            }
        });



    }

    private void fazerConta() {
        Intent myIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        LoginActivity.this.startActivity(myIntent);
        finish();
    }


    @Override
    public void onBackPressed() {
        ParseUser.logOut();
        finish();
        super.onBackPressed();
    }



    /*
    private void verificaVersaoBD() {

        // Cria instancia do DataHelper que controla as versoes do banco e suas
        // respectivas tabelas.
        DataHelper.getInstance(this);

        if (DatabaseUtil.getInstance().isRenew()) {
            DatabaseUtil.getInstance().popularBanco(this);
        }

    }

*/


}
