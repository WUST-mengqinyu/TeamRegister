package com.webutil.badcw;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WebPost extends AppCompatActivity {

    public final int MAX_LENGTH = 10;
    String response;
    EditText team_name, college, phone_number, leader, member1, member2;
    String team_name_string, college_string, phone_number_string, leader_string, member1_string, member2_string;
    Handler handler = new Handler() {
        @Override
        public void handleMessage (Message msg) {
            if (msg.what == 0x123) {
//                Toast.makeText(WebPost.this, "success", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(WebPost.this);
                builder.setTitle("确认");
                builder.setMessage("注册成功请等待短信消息");
                builder.setPositiveButton("我知道了", null);
                builder.show();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(WebPost.this);
                builder.setTitle("确认");
                builder.setMessage("注册失败，联系比赛负责人");
                builder.setPositiveButton("我知道了", null);
                builder.show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_post);
        Button test = findViewById(R.id.test_button);
        team_name = findViewById(R.id.team_name);
        college = findViewById(R.id.college);
        phone_number = findViewById(R.id.phone_number);
        leader = findViewById(R.id.leader);
        member1 = findViewById(R.id.member1);
        member2 = findViewById(R.id.member2);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get string
                team_name_string = team_name.getText().toString();
                college_string = college.getText().toString();
                phone_number_string = phone_number.getText().toString();
                leader_string = leader.getText().toString();
                member1_string = member1.getText().toString();
                member2_string = member2.getText().toString();
                new Thread() {
                    @Override
                    public void run() {
                        response = GetPostUtil.sendPost("http://verly-badcw.top/space/content/php/register_test.php", String.format("team_name=%s&college=%s&phone_number=%s&leader=%s&member1=%s&member2=%s", team_name_string, college_string, phone_number_string, leader_string, member1_string, member2_string));
                    }
                }.start();
                handler.sendEmptyMessage(0x123);
//                GetPostUtil.sendPost("http://verly-badcw.top/space/content/php/chat_send_ajax.php", "msg=hello app");
            }
        });
    }
}
