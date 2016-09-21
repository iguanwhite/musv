package kioja.com.musv;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Henrique on 09/07/2015.
 */
public class MusvAplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "ElVetu4170rE2tCl4047tr6HzdtNAXc5bruCa5JL", "1e87eNoYKgA8aDwMUHflzWDkrW76c08wEtMHujZm");
    }
}


