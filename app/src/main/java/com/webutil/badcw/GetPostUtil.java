package com.webutil.badcw;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class GetPostUtil {

    private static final String TAG = "data";
    /*
    * 向指定的url发送post请求
    * @param url
    * @param params 请求参数
    * @return 远程响应
    * */
    public static String sendPost(String url, String params) {
        PrintWriter out = null;
        BufferedReader in = null;
        String res = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            //获取输出流
            out = new PrintWriter(conn.getOutputStream());
            out.print(params);
            out.flush();
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );
            String line;
            while ((line = in.readLine()) != null) {
                res += "\n" + line;
            }
        } catch (Exception e) {
            Log.i(TAG, "sendPost: error");
            e.printStackTrace();
        }

        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

}
