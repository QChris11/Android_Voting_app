package com.example.vote_app.UI.VoteList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.vote_app.Main2Activity;
import com.example.vote_app.Main3Activity;
import com.example.vote_app.R;

/**
 * author: ITryagain
 * created on: 2020/1/3 22:48
 * description:
 */
public class VoteListCardViewHolder extends RecyclerView.ViewHolder {
    public NetworkImageView voteImage;
    public TextView voteTitle;
    public TextView votePrice;
    private CardView cardView;

    public VoteListCardViewHolder(@NonNull final View itemView, final Context mContext) {
        super(itemView);
        voteImage = itemView.findViewById(R.id.product_image);
        voteTitle = itemView.findViewById(R.id.product_title);
        votePrice = itemView.findViewById(R.id.product_price);

        cardView = itemView.findViewById(R.id.vote_card);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //这里判断是否已经投过票
                Bundle bundle = new Bundle();
                bundle.putString("Title",voteTitle.getText().toString());
                Intent voteinfo = new Intent(mContext, Main3Activity.class);

                voteinfo.putExtras(bundle);
                mContext.startActivity(voteinfo);
            }
        });
    }
}
