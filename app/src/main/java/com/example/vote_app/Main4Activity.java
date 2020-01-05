package com.example.vote_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vote_app.Vote.VoteItem;
import com.example.vote_app.createVote.CreateVoteItemAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity {

    private FloatingActionButton fb_menu;
    private FloatingActionButton fb_add;
    private FloatingActionButton fb_del;
    private CreateVoteItemAdapter createVoteItemAdapter;
    private Button button;
    LinearLayout linearLayout_add, linearLayout_del;
    private ListView lv;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        lv = findViewById(R.id.lv_vote_item1);
        createVoteItemAdapter = new CreateVoteItemAdapter(Main4Activity.this);
        lv.setAdapter(createVoteItemAdapter);

        fb_menu = findViewById(R.id.menu);
        fb_add = findViewById(R.id.menu_add_button);
        fb_del = findViewById(R.id.menu_delete_button);
        button = findViewById(R.id.btn_submit1);

        linearLayout_add = findViewById(R.id.menu_add);
        linearLayout_del = findViewById(R.id.menu_delete);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VoteItem voteItem = new VoteItem();
                int size = createVoteItemAdapter.getCount();
                // 获取选项
                String ss = "选项：";
                List<String> list = new ArrayList<String>();
                for(int i=0; i<size; i++){
                    list.add((String)createVoteItemAdapter.getItem(i));
                    ss += " " + (String)createVoteItemAdapter.getItem(i);
                }
                // 获取用户名
                ss += "用户名：lmw ";
                // 获取主题
                String theme = ((TextInputLayout)findViewById(R.id.vote_hew_r2)).getEditText().getText().toString();
                ss += "主题：" + theme;
                // 获取多选
                Boolean isMultiChoice = ((CheckBox)findViewById(R.id.checkBox)).isChecked();
                ss += " 是否多选：" + isMultiChoice;
                // 发送请求

                //成功
                Toast.makeText(Main4Activity.this, ss,Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Main4Activity.this, Main2Activity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
//                onDestroy();
            }
        });

        fb_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isOpen){
                    showMenu();
                }else {
                    closeMenu();
                }
            }
        });

        fb_add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
            final EditText et = new EditText(Main4Activity.this);
            new AlertDialog.Builder(Main4Activity.this).setTitle("添加选项")
                    .setIcon(R.drawable.add)
                    .setView(et)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    String input = et.getText().toString();
                                    if (input.equals("")) {
                                        Toast.makeText(getApplicationContext(), "搜索内容不能为空！" + input, Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        createVoteItemAdapter.addItem(input);
                                    }
                                }
                            }

                    )
                    .setNegativeButton("取消", null)
                    .show();
            }
        });

        fb_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createVoteItemAdapter.delItem();
                lv.setAdapter(createVoteItemAdapter);
                createVoteItemAdapter.notifyDataSetChanged();
            }
        });
    }

    private void showMenu(){
        isOpen = true;
        linearLayout_del.setVisibility(View.VISIBLE);
        linearLayout_add.setVisibility(View.VISIBLE);
    }

    private void closeMenu(){
        isOpen = false;
        linearLayout_add.setVisibility(View.GONE);
        linearLayout_del.setVisibility(View.GONE);
    }

    public void onBackPressed(View v){
        View view=getCurrentFocus();
        if(view != null){
            InputMethodManager manager= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(),0);
            if(isOpen){
                closeMenu();
            }
        }
    }
}
