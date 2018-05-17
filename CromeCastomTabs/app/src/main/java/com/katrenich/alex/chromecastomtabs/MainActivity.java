package com.katrenich.alex.chromecastomtabs;

import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnShowInternetPage;
    TextView tvUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
        btnShowInternetPage = findViewById(R.id.btn_view_internet_page);
        btnShowInternetPage.setOnClickListener(this);
        tvUrl = findViewById(R.id.tv_internet_address);

        /**
        // try to serialize object and transfer it by Bundle
        Bundle bundle = new Bundle();
        TestUser user = new TestUser(10, "Vasya");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(user);
        oos.flush();
        byte[] data = bos.toByteArray();
        bundle.putByteArray("pipiska", data);
        */


    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_view_internet_page){
            String url = tvUrl.getText().toString();
            if (url!=null){
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(this, Uri.parse(url));
            }
        }
    }
}
