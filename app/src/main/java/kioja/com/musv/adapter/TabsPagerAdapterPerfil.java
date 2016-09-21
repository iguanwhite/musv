package kioja.com.musv.adapter;

/**
 * Created by Henrique on 19/02/2015.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import kioja.com.musv.BiografiaFragment;
import kioja.com.musv.ContatoFragment;
import kioja.com.musv.PublicacoesFragment;
import kioja.com.musv.AgendaFragment;

public class TabsPagerAdapterPerfil extends FragmentPagerAdapter {

    public TabsPagerAdapterPerfil(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:

                return new AgendaFragment();

            case 1:

                return new PublicacoesFragment();

            case 2:
                return new BiografiaFragment();

            case 3:
                return new ContatoFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 4;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:

                return "Agenda";
            case 1:
                return "Publicações";

            case 2:

                return "Biografia";
            case 3:
                return "Contato";

        }
        return null;
    }
}