package com.example.vote_app.UI.VoteList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * author: ITryagain
 * created on: 2020/1/3 21:24
 * description:
 */
public class VoteListViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VoteListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is List fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
