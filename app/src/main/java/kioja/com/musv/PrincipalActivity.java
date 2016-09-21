package kioja.com.musv;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;


import android.content.Intent;

import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;

import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import com.parse.FindCallback;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import com.parse.ParseUser;


import kioja.com.musv.adapter.PostagemAdapter;



public class PrincipalActivity extends ActionBarActivity implements ActionBar.TabListener {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    private List<ParseObject> listPosts;
    private List<ParseObject> listPostsBombando;

    ListView listInicio;
    ListView listBombando;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);



        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setTitle("Musv");

        //   actionBar.setIcon(Drawable.createFromPath());


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
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
                abrirPesquisa();
                return true;
            case R.id.action_settings:
                //     openSettings();
                return true;
            case R.id.action_logout:
                logout();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private void logout(){
        finish();
        ParseUser.logOut();
        abrirLogin();
    }

    private void abrirLogin() {
        //  Intent myIntent = new Intent(PrincipalActivity.this, PesquisaActivity.class);
        Intent myIntent = new Intent(PrincipalActivity.this, LoginActivity.class);
        //  myIntent.putExtra("key", value); //Optional parameters
        PrincipalActivity.this.startActivity(myIntent);


    }

    private void abrirPesquisa() {
        //  Intent myIntent = new Intent(PrincipalActivity.this, PesquisaActivity.class);
        Intent myIntent = new Intent(PrincipalActivity.this, PesquisaActivity.class);
        //  myIntent.putExtra("key", value); //Optional parameters
        PrincipalActivity.this.startActivity(myIntent);


    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PrincipalFragment (defined as a static inner class below).

            if (position == 0) {
                PrincipalFragment f = new PrincipalFragment();
                return f.newInstance(position + 1);
            } else {
                BombandoFragment f = new BombandoFragment();
                return f.newInstance(position + 1);

            }

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return "Início";
                case 1:
                    return "Bombando";

            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    @SuppressLint("ValidFragment")
    public class PrincipalFragment extends ListFragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PrincipalFragment newInstance(int sectionNumber) {
            PrincipalFragment fragment = new PrincipalFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);


            return fragment;
        }

        public PrincipalFragment() {
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

        }

        @Override
        public void onResume() {
            super.onResume();

            ParseUser currentUser = ParseUser.getCurrentUser();
            if (currentUser != null) {

                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Postagem");
                query.orderByDescending("createdAt");



                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> listPostagens, ParseException e) {
                        if (e == null) {
// sucess.
                            listPosts = listPostagens;

                            PostagemAdapter adapter = new PostagemAdapter(getListView().getContext(), listPosts);
                            setListAdapter(adapter);
                            adapter.notifyDataSetChanged();


                        } else {
//error

                        }


                    }

                });
            }

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_principal, container, false);
            listInicio = (ListView) rootView.findViewById(android.R.id.list);


            fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(getApplicationContext(), "FAB CLICKED", Toast.LENGTH_LONG).show();
                    //abrirPerfil();
                    abrirPublicar();

                }
            });


            ParseUser currentUser = ParseUser.getCurrentUser();
            if (currentUser != null) {

                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Postagem");
                query.orderByDescending("createdAt");


                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> listPostagens, ParseException e) {
                        if (e == null) {
// sucess.
                            listPosts = listPostagens;

                            PostagemAdapter adapter = new PostagemAdapter(getListView().getContext(), listPosts);
                            setListAdapter(adapter);
                            adapter.notifyDataSetChanged();

                        } else {
e.toString();

                        }


                    }

                });
            }
            return rootView;
        }


        void abrirPerfil() {
            Intent myIntent = new Intent(PrincipalActivity.this, EventoActivity.class);
            //  myIntent.putExtra("key", value); //Optional parameters
            PrincipalActivity.this.startActivity(myIntent);

        }

        void abrirPublicar() {
            Intent myIntent = new Intent(PrincipalActivity.this, PostagemActivity.class);
            PrincipalActivity.this.startActivity(myIntent);

        }

        public void onListItemClick(ListView listView, View view, int position, long id) {
            Object o = listInicio.getItemAtPosition(position);
            String pen = o.toString();
            Toast.makeText(getApplicationContext(), "You have chosen the inicio: " + " " + pen, Toast.LENGTH_LONG).show();

        }


    }


    /**
     * A placeholder fragment containing a simple view.
     */
    @SuppressLint("ValidFragment")
    public class BombandoFragment extends ListFragment {


        private final String ARG_SECTION_NUMBER = "section_number";


        public BombandoFragment newInstance(int sectionNumber) {
            BombandoFragment fragment = new BombandoFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public BombandoFragment() {
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_games, container, false);
            //rootView.findViewById(android.R.id.list);
            //listBombando = (ListView) getListView();
            listBombando = (ListView) rootView.findViewById(android.R.id.list);
            fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    abrirPerfil();


                }
            });

            return rootView;
        }

        void abrirPerfil() {
            Intent myIntent = new Intent(PrincipalActivity.this, EventoActivity.class);
            //  myIntent.putExtra("key", value); //Optional parameters
            PrincipalActivity.this.startActivity(myIntent);

        }

        public void onListItemClick(ListView listView, View view, int position, long id) {
            Object o = listBombando.getItemAtPosition(position);
            String pen = o.toString();
            Toast.makeText(getApplicationContext(), "You have chosen the pen: " + " " + pen, Toast.LENGTH_LONG).show();

        }

    }


}
