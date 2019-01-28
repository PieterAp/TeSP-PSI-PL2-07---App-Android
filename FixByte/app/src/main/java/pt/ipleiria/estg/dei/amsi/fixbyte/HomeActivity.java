package pt.ipleiria.estg.dei.amsi.fixbyte;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v4.view.MenuItemCompat;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout nMainFrame;

    public static final String TOKEN = "amsi.dei.estg.ipleiria.pt.TOKEN";

    private HomeFragment homeFragment;
    private CategoriesFragment categoriesFragment;
    private SalesFragment salesFragment;
    private CartFragment cartFragment;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        switch (item.getItemId())
        {
            case R.id.itemSearch:
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.itemRepair:
                if (networkInfo != null && networkInfo.isConnected()){
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    String Token = preferences.getString("token", "");
                    if (Token == null || Token.isEmpty()){
                        Toast.makeText(this, "You must log on to access your cart.", Toast.LENGTH_SHORT).show();
                    }else{
                        intent = new Intent(getApplication(), ReparacaoListActivity.class);
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(this, "No internet connection.", Toast.LENGTH_SHORT).show();

                }

                return true;

            case R.id.itemCart:
                if (networkInfo != null && networkInfo.isConnected()){
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    String Token = preferences.getString("token", "");
                    if (Token == null || Token.isEmpty()){
                        Toast.makeText(this, "You must log on to access your cart.", Toast.LENGTH_SHORT).show();
                    }else{
                        intent = new Intent(getApplication(), ComprasListActivity.class);
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(this, "No internet connection.", Toast.LENGTH_SHORT).show();

                }

                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_multi_item,menu);

        nMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);

        homeFragment = new HomeFragment();
        categoriesFragment = new CategoriesFragment();
        salesFragment = new SalesFragment();
        cartFragment = new CartFragment();

        setFragment(homeFragment);

        MenuItem itemPesquisa = menu.findItem(R.id.itemSearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(itemPesquisa);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.itemHome:
                        setFragment(homeFragment);
                        return true;

                    case R.id.itemCategories:
                        setFragment(categoriesFragment);
                        return true;

                    case R.id.itemSales:
                        //setFragment(salesFragment);
                        intent = new Intent(getApplication(), ListaCampanhasActivity.class);
                        startActivity(intent);
                        return true;

                    case R.id.itemCart:
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        String Token = preferences.getString("token", "");
                        if (Token == null || Token.isEmpty()){
                            Toast.makeText(getApplicationContext(), "You must log in that least one time to access your data.", Toast.LENGTH_SHORT).show();
                        }else{
                            intent = new Intent(getApplication(), AccountActivity.class);
                            String text = getIntent().getStringExtra(TOKEN);
                            startActivity(intent);
                            setFragment(cartFragment);
                        }
                        return true;

                        default:
                            return false;
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                /*
                ArrayList <Produto> listalivroSearch = new ArrayList<>();

                for(Livro temp: FixByteSingleton.getInstance(getApplicationContext()).getLivros()){
                    if (temp.getTitulo().toLowerCase().contains(newText.toLowerCase())){
                        listalivroSearch.add(temp);
                    }
                }

                lvlistView.setAdapter(new ListaLivroAdaptador(ListaLivrosActivity.this,listalivroSearch));
                */
                return true;

            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}
