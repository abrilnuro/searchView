package com.example.caffeinelabs.searchlistview;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity{

    String[] items;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText editText;

    int length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //referenciacion
        listView = (ListView) findViewById(R.id.listview);
        editText = (EditText) findViewById(R.id.txtsearch);
        //hacer invisible el editText
        editText.setVisibility(View.GONE);

        //mostrar los elementos de la ListView
        initList();

        //evento del TextView de busqueda
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int
                    after) {
                length=s.toString().length();
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int
                    count) {

                if (s.toString().equals("")) {
                    // reset listview
                    initList();
                } else {
                    // perform search
                    searchItem(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() < length) {
                    initList();
                    for (String item : items) {
                        if (!item.toLowerCase().contains(s.toString().toLowerCase())) {
                            listItems.remove(item);
                        }//if
                    }//for
                }//if
            }
        });

    }//onCreate

    //metodo que busca el nombre del item que se insertó en el TextView
    public void searchItem(String textToSearch) {

        for (String item : items) {

            if (!item.toLowerCase().contains(textToSearch.toString().toLowerCase())) {

                listItems.remove(item);
            }
        }
        adapter.notifyDataSetChanged();
    }

    //metodo que llena la lista y configura la ListView para mostrar los elementos de la lista
    public void initList() {

        //crear el Array de strings y agregarle datos
        items = new String[]{"Canada", "China", "Japan", "USA"};

        //Convierte el array de Strings en lista
        listItems = new ArrayList<>(Arrays.asList(items));

        //Crear un adaptador para conectar la listItems
        //con el layout(personalizado) de los items que tiene la listView
        adapter = new ArrayAdapter<String>(this,
                R.layout.list_item, R.id.txtitem, listItems);

        //Asignar el adaptador a la lista para que se acomoden los items
        listView.setAdapter(adapter);
    }


    //metodo para inflar el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);

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
            case R.id.action_search:
                action(R.string.action_search);
               // editText.setVisibility(View.VISIBLE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //metodo que muestra un mensaje para avisar que opcion del menu fue seleccionada
    private void action(int resid) {
        Toast.makeText(this, getText(resid), Toast.LENGTH_SHORT).show();
    }

}//class
