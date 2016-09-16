package com.example.caffeinelabs.searchlistview;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class toolbarSearch extends AppCompatActivity {

    private TextView textView;

    private RecyclerView recyclerView;
    private Adapter adapter;
    private RecyclerView.LayoutManager llm;

    private List<Item> itemList;
    private Iterator<Item> iterator;

    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_search);

        //referenciacion
        textView = (TextView) findViewById(R.id.textView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText = (EditText) findViewById(R.id.txtsearch);


        //configurar la lista y la recyclerview
        initList();

    }//onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflar el menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        
        //searchView.setQueryHint(ge tText(R.string.search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                /*Toast.makeText(toolbarSearch.this, R.string.submitted, Toast.LENGTH_SHORT).show();
                searchView.setQuery("", false);
                searchView.setIconified(true);*/

                int Query = query.length();
                String string = Integer.toString(Query);
                Log.i("onQueryTextSubmit", string);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                textView.setText(newText);

                //filtrar la lista
                if (newText.equals("")) {
                    //reset listview
                    initList();
                } else{
                    //perform search
                    filterRecyclerView(newText);
                }

                //adapter.getFilter().filter(newText);

                return false;
            }
        });
        //check http://stackoverflow.com/questions/11085308/changing-the-background-drawable-of-the-searchview-widget
        //View searchPlate = (View) searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
        //searchPlate.setBackgroundResource(R.mipmap.textfield_custom);
        return super.onCreateOptionsMenu(menu);
    }

    //metodo que se ejecuta al seleccionar una de las opciones
    //del menu del toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                action(R.string.action_settings);
                return true;
            case R.id.action_help:
                action(R.string.action_help);
                return true;
            case R.id.action_about:
                action(R.string.action_about);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void action(int resid) {
        Toast.makeText(this, getText(resid), Toast.LENGTH_SHORT).show();
    }

    //metodo que configura la lista y la recyclerView
    public void initList(){
        //llenar la lista
        itemList = new ArrayList<Item>();
        itemList.add(new Item("Abril"));
        itemList.add(new Item("Jose"));
        itemList.add(new Item("Ana"));
        itemList.add(new Item("Raul"));
        itemList.add(new Item("Antonio"));
        itemList.add(new Item("Fabiola"));
        itemList.add(new Item("Mariana"));
        itemList.add(new Item("Sonia"));

        //configurar la recyclerView
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);

        //pasar la lista al adaptador
        adapter = new Adapter(itemList);
        //Asignar el adaptador a la recyclerView
        recyclerView.setAdapter(adapter);
    }

    //metodo que busca el nombre del item que se insertó en el TextView
    //para filtrar la lista
    private void filterRecyclerView(String charText) {
        //charText = charText.toLowerCase();
        iterator = itemList.iterator();

        while(iterator.hasNext()){
            Item item = iterator.next();

            if(!item.getText_item().toLowerCase().contains(charText.toLowerCase())){
                iterator.remove();
            }
        }
        adapter.notifyDataSetChanged();
    }
}//class
