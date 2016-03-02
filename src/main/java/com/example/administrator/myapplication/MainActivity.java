package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText LoginName;
    private EditText LoginPassword;
    private String Name;
    private String Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       initView();
    }
    public void initView(){
        LoginName=(EditText)findViewById(R.id.et_name);
        LoginPassword=(EditText)findViewById(R.id.et_pwd);
    }

//    String path = "www.************.con"；
//    URL	url = new URL(path）；
//            HttpURLConnection con = (HttpURLConnection) url.openConnection;
//    con.setRequestMethod("GET");
//    con.setConnectionTimeout(50000);
//    con.setTimeout(50000);
//    //发送http的get请求，返回相应状态码
//    if(con.RequestCode()==200){
//        InputStream is = con.getInputStream();
//    }
    public void SignUp(View view){
        System.out.print("button has been clicked");
     //   Toast.makeText(MainActivity.this, "Button has been clicked", Toast.LENGTH_SHORT).show();
        Name=LoginName.getText().toString();
        Password=LoginPassword.getText().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String path = "http://192.168.1.5:8080/android-client-demo/LoginServlet4Android"+"?LoginName="+Name+"&LoginPassword="+Password;
                    System.out.print(path);
                    URL url = new URL(path);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(50000);
                    con.connect();

                    if(con.getResponseCode()==311){
          //              Toast.makeText(MainActivity.this,"success",Toast.LENGTH_SHORT).show();
                        System.out.print("success");
                       Intent mintent = new Intent(MainActivity.this,HomeActivity.class);
                       startActivity(mintent);
                    }else{
                        System.out.print("failed");
           //             Toast.makeText(MainActivity.this, "password or name id error!!!", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
            }
            }
        }).start();
    }
}
