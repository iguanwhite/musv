package kioja.com.musv;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;

import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class RegisterActivity extends ActionBarActivity {

    private EditText fieldUsuario;
    private EditText fieldSenha;
    private Button buttonCreate;

    private String usuario, senha;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViewById(R.id.loadingPanel).setVisibility(View.GONE);

        buttonCreate = (Button) findViewById(R.id.buttonLogin);
        // add a click listener to the button
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (isValidBeforeCreate()) {
                    criarConta();
                }
            }
        });

        fieldSenha = (EditText) findViewById(R.id.fieldSenha);
        fieldUsuario = (EditText) findViewById(R.id.fieldUsuario);
        fieldUsuario.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == 66) {
                    fieldSenha.requestFocus();
                }
                return false;
            }
        });

    }


    private boolean isValidBeforeCreate() {
        usuario = fieldUsuario.getText().toString();
        senha = fieldSenha.getText().toString();

        EmailValidator ev = new EmailValidator();
        if ((usuario != null && usuario.length() == 0) || (senha != null && senha.length() == 0)) {
            Toast.makeText(getApplicationContext(), "Informe seu nome de usuário e senha para cadastrar!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!ev.validate(usuario)) {
            Toast.makeText(getApplicationContext(), "Formato inválido de e-mail!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }

    }


    private void criarConta() {
        ParseUser user = new ParseUser();
        user.setUsername(usuario);
        user.setPassword(senha);
        user.setEmail(usuario);
        findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
        Toast.makeText(getApplicationContext(), "Registrando usuário...", Toast.LENGTH_LONG).show();
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    findViewById(R.id.loadingPanel).setVisibility(View.GONE);

                    Intent myIntent = new Intent(RegisterActivity.this, CadastroActivity.class);
                    RegisterActivity.this.startActivity(myIntent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Usuário já existente!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        RegisterActivity.this.startActivity(myIntent);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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


class EmailValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    /**
     * Validate hex with regular expression
     *
     * @param hex hex for validation
     * @return true valid hex, false invalid hex
     */
    public boolean validate(final String hex) {

        matcher = pattern.matcher(hex);
        return matcher.matches();

    }
}
