package com.example.learnsqlite;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
    
	private Context mContext;
    private static LayoutInflater inflater = null;
    private ArrayList<Product> mProducts;
    
    public ListAdapter(Context c, ArrayList<Product> arrayProducts) {
        mContext = c;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mProducts = arrayProducts;
    }

    public int getCount() {
        return mProducts.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        
        vi = inflater.inflate(R.layout.item_list, null);
        TextView text = (TextView) vi.findViewById(R.id.text_list);
        text.setText(mProducts.get(position).getProductName());
        
        return vi;
    }
    
}