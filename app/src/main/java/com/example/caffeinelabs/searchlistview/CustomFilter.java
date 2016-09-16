package com.example.caffeinelabs.searchlistview;

import android.support.v7.widget.RecyclerView;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caffeineLabs on 05/09/16.
 */
public class CustomFilter extends Filter{

    RecyclerView.Adapter adapter;
    List<Item> itemList;


    //constructor
    public CustomFilter(List<Item> itemList, RecyclerView.Adapter adapter) {
        this.itemList = itemList;
        this.adapter = adapter;
    }


    //metodo que cacha el valor que el usuario escribe en el SearchView
    //y lo utiliza para filtrar la recyclerView
    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {

        FilterResults results = new FilterResults();

        //si el usuario teclea texto en el SearchView y la variable que contiene ese texto
        //no esta vacia
        if(charSequence != null && charSequence.length() > 0)
        {
            //cambiar a minusculas el texto que el usuario tecleo
            charSequence = charSequence.toString().toLowerCase();

            //almacenar en esta lista los item filtrados
            List<Item> filterList = new ArrayList<>();

            //recorrer la lista para filtrarla de los elementos que no contengan el CharSequence
            for(int i=0; i<itemList.size(); i++){

                //si el elemento de la posision (i) es igual al CharSequense que proporciono el usuario
                if(itemList.get(i).getText_item().toLowerCase().contains(charSequence)){

                    //agregar el elemento de la lista original a la lista nueva lista
                    filterList.add(itemList.get(i));
                }
            }

            //Dar un tamaño a la instancia del FilterResults
            //ese tamaño va a ser igual al tamaño de la lista filtrada
            results.count = filterList.size();
            //agregar los valores de la lista filtrada a la instancia de FilterResults
            results.values = filterList;

        }else{

            //Dar un tamaño a la instancia del FilterResults
            //ese tamaño va a ser igual al tamaño de la lista original ya que el CharSequense
            //no tiene ningun valor
            results.count = itemList.size();
            //agregar los valores de la lista original a la instancia de FilterResults
            results.values = itemList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

       /* itemList = (ArrayList<Item>)filterResults.values;
        adapter = new Adapter(itemList);*/
        //notificar a la RecyclerView los cambios que se hicieron
        //para que se muestre la lista que ya se filtró
        //adapter.notifyDataSetChanged();
    }
}//class
