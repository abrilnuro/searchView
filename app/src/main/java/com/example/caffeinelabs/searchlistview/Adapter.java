package com.example.caffeinelabs.searchlistview;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.List;

/**
 * Created by caffeineLabs on 31/08/16.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.RecyclerAdapterViewHolder> implements Filterable{

    //lista que va a contener los valores originales
    private List<Item> list_items;
    //lista que va a contener los valores filtrados
    private List<Item> list_Filter_items;
    //instancia de la clase CustomFilter
    private CustomFilter filter;

    //constructor
    public Adapter() {
    }

    //constructor
    public Adapter(List<Item> list_items) {
        this.list_items = list_items;
        this.list_Filter_items = list_items;
    }

    //clase interna
    public static class RecyclerAdapterViewHolder extends RecyclerView.ViewHolder
    {
        CardView image_card;
        TextView text_view;

        //constructor
        public RecyclerAdapterViewHolder(View itemView) {
            super(itemView);

            text_view =(TextView) itemView.findViewById(R.id.textView_item);
            image_card =(CardView)itemView.findViewById(R.id.cardView_item);
        }
    }//class

    @Override
    public RecyclerAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item, null);
        RecyclerAdapterViewHolder ravh = new RecyclerAdapterViewHolder(v);
        return ravh;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterViewHolder holder, final int position) {
        holder.text_view.setText(list_items.get(position).getText_item().toString());
    }

    @Override
    public int getItemCount() {
        return list_items.size();
    }


    //metodo que me lleva a la clase CustomFilter para filtrar la lista
    @Override
    public Filter getFilter() {
        if(filter == null){
            filter = new CustomFilter(list_Filter_items, this);
        }
        return filter;
    }

}//class
