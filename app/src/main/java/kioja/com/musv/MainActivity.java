package kioja.com.musv;








import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.Parse;
import com.parse.ParseUser;


public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser == null) {
            abrirLogin();
        }else{
            abrirPrincipal();
        }

    }


    @Override
    public void onBackPressed() {
        ParseUser.logOut();
        finish();
        super.onBackPressed();
    }


    private void abrirLogin() {
        //  Intent myIntent = new Intent(PrincipalActivity.this, PesquisaActivity.class);
        Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
        //  myIntent.putExtra("key", value); //Optional parameters
        MainActivity.this.startActivity(myIntent);
        finish();


    }


    private void abrirPrincipal() {
        //  Intent myIntent = new Intent(PrincipalActivity.this, PesquisaActivity.class);
        Intent myIntent = new Intent(MainActivity.this, PrincipalActivity.class);
        //  myIntent.putExtra("key", value); //Optional parameters
        MainActivity.this.startActivity(myIntent);
        finish();

    }






}