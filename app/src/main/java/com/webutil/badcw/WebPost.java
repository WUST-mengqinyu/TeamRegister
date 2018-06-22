package com.webutil.badcw;

import com.webutil.badcw.utils.GetCorrect;
import com.webutil.badcw.utils.giveBack;
import com.webutil.badcw.utils.message;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WebPost extends AppCompatActivity {

    public static final int MAX_LENGTH = 10;
    EditText team_name, college, phone_number, leader, member1, member2;
    giveBack response;
    Handler handler = new Handler() {
        @Override
        public void handleMessage (Message msg) {
            if (msg.what == 0x123) {
//                Toast.makeText(WebPost.this, "success", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(WebPost.this);
                builder.setTitle("确认");
                builder.setMessage(response.getInfo());
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
        Button submit = findViewById(R.id.submit_button);
        team_name = findViewById(R.id.team_name);
        college = findViewById(R.id.college);
        phone_number = findViewById(R.id.phone_number);
        leader = findViewById(R.id.leader);
        member1 = findViewById(R.id.member1);
        member2 = findViewById(R.id.member2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get string
                final message x = new message();
                x.team_name_string = team_name.getText().toString();
                x.college_string = college.getText().toString();
                x.phone_number_string = phone_number.getText().toString();
                x.leader_string = leader.getText().toString();
                x.member1_string = member1.getText().toString();
                x.member2_string = member2.getText().toString();
                response = GetCorrect.getCorrect(x);
                if (response.getres()) {
                    new Thread() {
                        @Override
                        public void run() {
                            x.getResponse();
                        }
                    }.start();
                }
                handler.sendEmptyMessage(0x123);
            }
        });
        Button clearButton = findViewById(R.id.clear_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                team_name.setText("");
                college.setText("");
                phone_number.setText("");
                leader.setText("");
                member1.setText("");
                member2.setText("");
            }
        });
    }
}
