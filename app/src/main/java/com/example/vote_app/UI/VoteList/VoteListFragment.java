package com.example.vote_app.UI.VoteList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vote_app.R;
import com.example.vote_app.network.VoteEntry;

/**
 * author: ITryagain
 * created on: 2020/1/3 20:00
 * description:
 */
public class VoteListFragment extends Fragment {

    private VoteListViewModel listViewModel;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listViewModel =
                ViewModelProviders.of(this).get(VoteListViewModel.class);
        View view = inflater.inflate(R.layout.fragment_vote, container, false);
//        final TextView textView = root.findViewById(R.id.text_vote);
//        listViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        mRecyclerView = view.findViewById(R.id.vote_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);


        mAdapter = new VoteListCardRecyclerViewAdapter(getActivity(),VoteEntry.initVoteEntry(getResources()));
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

}
