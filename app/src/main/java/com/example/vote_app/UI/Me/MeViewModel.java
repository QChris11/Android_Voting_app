package com.example.vote_app.UI.Me;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * author: ITryagain
 * created on: 2020/1/3 21:18
 * description:
 */
public class MeViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public MeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
