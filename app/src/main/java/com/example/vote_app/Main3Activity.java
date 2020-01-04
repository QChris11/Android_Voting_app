package com.example.vote_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vote_app.Vote.VoteAdapter;
import com.example.vote_app.Vote.VoteItem;
import com.example.vote_app.Vote.VoteViewHolder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    private ListView lvVoteView;
    private VoteAdapter voteAdapter;
    private VoteItem vote_item;
    private Button btnSubmit;
    private String radioButtonSelected;
//    private Boolean isMultiChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        final TextView textView = (TextView)findViewById(R.id.vote_theme);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String s;
        if(bundle != null){
            s = bundle.getString("Title");
            textView.setText(s);
        }


        lvVoteView = (ListView) findViewById(R.id.lv_vote_item);
        btnSubmit = findViewById(R.id.btn_submit);
        radioButtonSelected = "";
        ArrayList<String> a = new ArrayList<String>();
        a.add("123");
        a.add("111");
        a.add("132");
        vote_item = new VoteItem("1", "abc", a, true, new Date());
        voteAdapter = new VoteAdapter(vote_item.getVote_item(), this, vote_item.getMultiChoice());
        lvVoteView.setAdapter(voteAdapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String selectValues="选中信息:";
                //遍历用户选中项目，可以根据实际需求获取选中项目的任何信息
                for (int i = 0; i < vote_item.getVote_item().size(); i++) {
                    if(voteAdapter.getSubjectItemMap().get(vote_item.getVote_item().get(i)))
                    {
                        selectValues += "选项:"+vote_item.getVote_item().get(i);
                    }

                }
                Toast.makeText(Main3Activity.this, selectValues.equals("选中信息:")?"未选中任何信息":selectValues, Toast.LENGTH_LONG).show();
            }
        });

        // ListView控件每一行点击事件处理
        lvVoteView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                VoteViewHolder viewHolder = (VoteViewHolder) view.getTag();
                // 如果当前行是多选项目
                if (vote_item.getMultiChoice()) {
                    viewHolder.cbSubjectItem.toggle();
                    voteAdapter.getSubjectItemMap().put(viewHolder.tvVoteItemName.getText().toString(), viewHolder.cbSubjectItem.isChecked());
                }

                //如果当前行为单选项目，注意单选项目选中后需要将同一主题下已经选中的项目设置为未选中状态
                else {
//                    String currentSubjectIdSelected = viewHolder.tvSubjectId.getText().toString();
                    String currentSubjectItemId = viewHolder.tvVoteItemName.getText().toString();
                    //判断该单选主题是否有已经选中项目，如果有需要将它的选中状态设置为未选中
                    if (vote_item.getVote_item().contains(radioButtonSelected)) {
                        voteAdapter.getSubjectItemMap().put(radioButtonSelected,false);
                    }
                    //将当前选中的项目设置为该单选主题的选中项目
                    radioButtonSelected = currentSubjectItemId;
                    viewHolder.rbSubjectItem.toggle();
                    voteAdapter.getSubjectItemMap().put(currentSubjectItemId,viewHolder.rbSubjectItem.isChecked());
                    //更新ListView
                    updateListView();

                }}});

    }

    private void updateListView()
    {
        voteAdapter.notifyDataSetChanged();
    }

}
