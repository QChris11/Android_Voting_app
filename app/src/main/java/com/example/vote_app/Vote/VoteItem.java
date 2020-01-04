package com.example.vote_app.Vote;

import java.util.Date;
import java.util.List;

/**
 * author: ITryagain
 * created on: 2020/1/4 14:06
 * description:
 */
public class VoteItem {

    // 投票id
    private String vote_id;
    // 投票 theme
    private String vote_theme;
    // 投票 theme + item
    private List<String> vote_item;
    // 是否多选
    private Boolean isMultiChoice;
    // 结束时间
    private Date endDate;

    public VoteItem(String vote_id, String vote_theme , List<String> vote_item, Boolean isMultiChoice, Date endDate){
        this.vote_id = vote_id;
        this.vote_theme = vote_theme;
        this.vote_item = vote_item;
        this.isMultiChoice = isMultiChoice;
        this.endDate = endDate;
    }

    public Boolean getMultiChoice() {
        return isMultiChoice;
    }

    public String getVote_id() {
        return vote_id;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getVote_theme() {
        return vote_theme;
    }

    public List<String> getVote_item() {
        return vote_item;
    }
}
