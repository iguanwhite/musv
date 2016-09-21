package kioja.com.musv.util;

import android.content.Context;

import kioja.com.musv.entity.perfil.contato.Contato;
import kioja.com.musv.exception.RegraNegocioException;


/**
 * Created by Henrique on 01/07/2015.
 */
public class DatabaseUtil {


    private static DatabaseUtil instance;
    private static boolean renew = true;


    public boolean isRenew() {
        return renew;
    }


    public void setRenew(boolean renew) {
        DatabaseUtil.renew = renew;
    }


    public static DatabaseUtil getInstance() {
        if (instance == null) {
            instance = new DatabaseUtil();
        }

        return instance;
    }


    //       PessoaRN prn = new PessoaRN(ctx);
    ///     prn.popularBanco();


//		MunicipioRN mrn = new MunicipioRN(ctx);
//		mrn.popularBanco();
//
//		AgenteRN arn = new AgenteRN(ctx);
//		arn.popularBanco();
//
//		MensagemRN mern = new MensagemRN(ctx);
//		mern.popularBanco();

//		ConfiguracaoRN confrn = new ConfiguracaoRN(ctx);
//		confrn.popularBanco();


    public void popularBanco(Context ctx) {

        Contato c1 = new Contato();
        c1.setAbreviacao("asdas");
        c1.setIdContato(1l);
        c1.setIdPerfil(1);
        c1.setLink("asdas");
        c1.setNumContato("232");
        c1.setTipo("0");




    }




    }






