package com.webutil.badcw.utils;

public class message {
    public String team_name_string, college_string, phone_number_string, leader_string, member1_string, member2_string;
    private String responese = "";

    public String getResponse() {
        responese = GetPostUtil.sendPost("http://verly-badcw.top/space/content/php/register_test.php", String.format("team_name=%s&college=%s&phone_number=%s&leader=%s&member1=%s&member2=%s", team_name_string, college_string, phone_number_string, leader_string, member1_string, member2_string));
        return responese;
    }
}