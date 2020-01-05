package com.example.vote_app.createVote;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import com.example.vote_app.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * author: ITryagain
 * created on: 2020/1/4 22:38
 * description:
 */
public class CreateVoteItemAdapter extends BaseAdapter {

    private Context context;
    private List<String> items = new ArrayList<String>();

    public CreateVoteItemAdapter(Context context){
        this.context = context;
    }

    public void addItem(String item){
        if(items != null){
            items.add(item);
        }
    }

    public void delItem(){
        if(items != null && items.size() > 0){
            items.remove(items.size() - 1);
        }
    }

    @Override
    public int getCount() {
        if (items == null){
            return 0;
        }
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.createvote, null);

        ((EditText)convertView.findViewById(R.id.item_et)).setText(items.get(i));
        convertView.invalidate();
        return convertView;
    }
}
