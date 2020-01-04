package com.example.vote_app.UI.VoteList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vote_app.R;
import com.example.vote_app.network.ImageRequester;
import com.example.vote_app.network.VoteEntry;

import java.util.List;

/**
 * author: ITryagain
 * created on: 2020/1/3 22:47
 * description:
 */
public class VoteListCardRecyclerViewAdapter extends RecyclerView.Adapter<VoteListCardViewHolder> {

    private List<VoteEntry> voteList;
    private ImageRequester imageRequester;
    Context mContext;

    VoteListCardRecyclerViewAdapter(Context mContext, List<VoteEntry> voteList){
        this.mContext = mContext;
        this.voteList = voteList;
        imageRequester = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public VoteListCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.votelistcard, parent,false);
        return new VoteListCardViewHolder(layoutView, mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull VoteListCardViewHolder holder, int position) {
        if (voteList != null && position < voteList.size()) {
            VoteEntry product = voteList.get(position);
            holder.voteTitle.setText(product.title);
            holder.votePrice.setText(product.price);
            imageRequester.setImageFromUrl(holder.voteImage, product.url);
        }
    }

    @Override
    public int getItemCount() {
        return voteList.size();
    }
}
