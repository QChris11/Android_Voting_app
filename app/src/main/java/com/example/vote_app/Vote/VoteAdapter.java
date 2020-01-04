package com.example.vote_app.Vote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.vote_app.R;

import java.util.HashMap;
import java.util.List;

/**
 * author: ITryagain
 * created on: 2020/1/4 15:32
 * description:
 */
public class VoteAdapter extends BaseAdapter {

    private List<String> vote_item;
    private Context context;
    private LayoutInflater inflater;
    private HashMap<String,Boolean> subjectItemMap;
    private Boolean isMultiChoice;

    public VoteAdapter(List<String> vote_item, Context context, Boolean isMultiChoice){
        this.vote_item = vote_item;
        this.context = context;
        this.isMultiChoice = isMultiChoice;
        this.inflater = LayoutInflater.from(context);
        this.subjectItemMap=new HashMap<String, Boolean>();
        for (int i = 0; i < vote_item.size(); i++) {
            this.subjectItemMap.put(vote_item.get(i), false);
        }
    }

    @Override
    public int getCount() {
        return vote_item.size();
    }

    @Override
    public Object getItem(int i) {
        return vote_item.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View converView, ViewGroup parent) {

        VoteViewHolder voteViewHolder = null;
        String s = vote_item.get(i);
        if(converView != null && converView.getId() == R.id.lv_vote_item){
            voteViewHolder = (VoteViewHolder)converView.getTag();
        }else{
            voteViewHolder = new VoteViewHolder();
            converView = inflater.inflate(R.layout.vote_item, null);
            voteViewHolder.cbSubjectItem = converView.findViewById(R.id.cb_subject_item);
            voteViewHolder.rbSubjectItem = converView.findViewById(R.id.rb_subject_item);
            voteViewHolder.isMultiChoice = this.isMultiChoice;
            voteViewHolder.tvVoteItemName = converView.findViewById(R.id.tv_vote_item_name);
        }
        voteViewHolder.tvVoteItemName.setText(s);
        // 多选
        if(this.isMultiChoice){
            voteViewHolder.cbSubjectItem.setVisibility(View.VISIBLE);
            voteViewHolder.rbSubjectItem.setVisibility(View.GONE);
            voteViewHolder.cbSubjectItem.setChecked(this.subjectItemMap.get(s));
        }else{
            voteViewHolder.cbSubjectItem.setVisibility(View.GONE);
            voteViewHolder.rbSubjectItem.setVisibility(View.VISIBLE);
            voteViewHolder.rbSubjectItem.setChecked(this.subjectItemMap.get(s));
        }
        converView.setTag(voteViewHolder);
        return converView;
    }

    public HashMap<String,Boolean> getSubjectItemMap() {
        return this.subjectItemMap;
    }
}
