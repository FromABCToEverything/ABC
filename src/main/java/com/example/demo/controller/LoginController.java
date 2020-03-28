package com.example.demo.controller;


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;

import com.example.demo.DemoApplication;
import com.example.demo.service.impl.UserServiceimpl;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private UserServiceimpl user;


    @GetMapping(path="/login")
    public String showHello(@RequestParam String code0, @RequestParam String name, @RequestParam String avatarUrl) {
        String url="https://api.weixin.qq.com/sns/jscode2session";
        String charset="UTF-8";
        String code=code0;
        String app_id="wx7e277302ee60b0a4";
        String app_secret="0c4b2b5ed08f2500f7d1c22db12dded0";
        String grant_type="authorization_code";
        System.out.println(avatarUrl);

        String session_id = "";
        try {
            String query = String.format("appid=%s&secret=%s&js_code=%s&grant_type=%s",
                    URLEncoder.encode(app_id,charset),
                    URLEncoder.encode(app_secret,charset),
                    URLEncoder.encode(code,charset),
                    URLEncoder.encode(grant_type,charset));
            URLConnection conn = new URL(url+"?"+query).openConnection();
            InputStream resp = conn.getInputStream();
            Scanner scanner = new Scanner(resp);
            String json = scanner.useDelimiter("\\A").next();

            JSONObject jobj  = new JSONObject(json);
            String session_key = jobj.getString("session_key");
            String openid = jobj.getString("openid");

            //用SHA-1加密session_key得到session_id
            MessageDigest dg= MessageDigest.getInstance("SHA-1");
            dg.reset();
            dg.update(session_key.getBytes("utf8"));
            session_id = String.format("%040x",new BigInteger(1,dg.digest()));
            System.out.println("sha1 session_id:"+session_id);


            String pre_session_id = (String) DemoApplication.sessionMap.getKey(openid);
            if(pre_session_id==null) {
                //待实现
                //若无，则将openid=..., name=..., avatarUrl=..., reputation=0 作为一条user记录插入到user表里
                user.insertUser(openid,avatarUrl,name);
                System.out.println("插入新用户");
            }
            DemoApplication.sessionMap.remove(pre_session_id);
            DemoApplication.sessionMap.put(session_id, openid);
            System.out.println(DemoApplication.sessionMap.toString());
        } catch (IOException | NoSuchAlgorithmException | JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return session_id;
    }

}
