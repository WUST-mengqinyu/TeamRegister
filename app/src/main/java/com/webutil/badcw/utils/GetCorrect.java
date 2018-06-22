package com.webutil.badcw.utils;

import static com.webutil.badcw.WebPost.MAX_LENGTH;

public class GetCorrect {
    public static giveBack getCorrect(message x) {
        giveBack res = new giveBack();
        if (x.team_name_string.isEmpty()) {
            res.makeText("invalid team name!");
            return res;
        }
        if (x.team_name_string.length() > MAX_LENGTH) {
            res.makeText("team name too long!");
            return res;
        }
        if (x.college_string.isEmpty()) {
            res.makeText("college must be assigned!");
            return res;
        }
        if (x.leader_string.isEmpty()) {
            res.makeText("leader must be assigned!");
            return res;
        }
        if (x.phone_number_string.length() != 11) {
            res.makeText("phone number incorrect");
            return res;
        }
        if (x.member1_string.isEmpty() && !x.member2_string.isEmpty()) {
            res.makeText("you must first fill in the member1 first");
            return res;
        }
        res.makeText("注册成功请等待短信消息", true);
        return res;
    }
}
