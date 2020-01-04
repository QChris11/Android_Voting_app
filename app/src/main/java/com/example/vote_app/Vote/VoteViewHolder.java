package com.example.vote_app.Vote;

import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * author: ITryagain
 * created on: 2020/1/4 15:34
 * description:
 */
public class VoteViewHolder {

    // 选中CheckBox控件（主题类型为多选时显示）
    public CheckBox cbSubjectItem;
    // 选中RadioButton控件（主题类型为单选时显示）
    public RadioButton rbSubjectItem;
    // 选项内容
    public TextView tvVoteItemName;
    //是否多选
    public Boolean isMultiChoice;

}
