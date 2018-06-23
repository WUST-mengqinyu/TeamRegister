package com.webutil.badcw;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.webutil.badcw.utils.giveBack;
import com.webutil.badcw.utils.message;

public class ConfirmActivity extends AppCompatActivity {

    giveBack response;
    Handler handler = new Handler() {
        @Override
        public void handleMessage (Message msg) {
            if (msg.what == 0x123) {
//                Toast.makeText(WebPost.this, "success", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(ConfirmActivity.this);
                builder.setTitle("确认");
                builder.setMessage(response.getInfo());
                builder.setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        Intent intent = new Intent(ConfirmActivity.this, WebPost.class);
                        startActivity(intent);
                    }
                });
                builder.show();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(ConfirmActivity.this);
                builder.setTitle("确认");
                builder.setMessage("注册失败，联系比赛负责人");
                builder.setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        Intent intent = new Intent(ConfirmActivity.this, WebPost.class);
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        }
    };

    TextView team_name_check, college_check, phone_number_check, leader_check, member1_check, member2_check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        team_name_check = findViewById(R.id.team_name_check);
        phone_number_check = findViewById(R.id.phone_number_check);
        leader_check = findViewById(R.id.leader_check);
        college_check = findViewById(R.id.college_check);
        member1_check = findViewById(R.id.member1_check);
        member2_check = findViewById(R.id.member2_check);
        Intent intent = getIntent();
        final message x =  (message) intent.getSerializableExtra("team");
        team_name_check.setText("队伍名: " + x.team_name_string);
        phone_number_check.setText("电话号码: " + x.phone_number_string);
        leader_check.setText("队长名字: " + x.leader_string);
        college_check.setText("学校名: " + x.college_string);
        member1_check.setText("队员1: " + x.member1_string);
        member2_check.setText("队员2: " + x.member2_string);
        Button confirm, redo;
        confirm = findViewById(R.id.check_confirm);
        redo = findViewById(R.id.check_redo);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                response = WebPost.response;
                new Thread() {
                    @Override
                    public void run() {
                        x.getResponse();
                    }
                }.start();
                handler.sendEmptyMessage(0x123);
            }
        });
        redo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmActivity.this, WebPost.class);
                startActivity(intent);
            }
        });
    }
}
