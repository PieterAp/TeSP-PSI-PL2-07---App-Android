package pt.ipleiria.estg.dei.amsi.fixbyte;

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

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout nMainFrame;

    private HomeFragment homeFragment;
    private CategoriesFragment categoriesFragment;
    private SalesFragment salesFragment;
    private CartFragment cartFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
                        setFragment(salesFragment);
                        return true;

                    case R.id.itemCart:
                        setFragment(cartFragment);
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
                ArrayList <Livro> listalivroSearch = new ArrayList<>();

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
