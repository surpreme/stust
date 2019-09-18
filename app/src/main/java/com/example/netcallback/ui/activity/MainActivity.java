package com.example.netcallback.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.netcallback.R;
import com.example.netcallback.bean.GetJson;
import com.example.netcallback.callback.HttpCallback;
import com.example.netcallback.utils.HttpHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private ImageView imageView;
    private String url = "http://c.3g.163.com/photo/api/set/0001%2F2250173.json";
    private Map<String, Object> params = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        textView = findViewById(R.id.text);
        imageView = findViewById(R.id.imageView);
        button2.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                HttpHelper.obtain().post(url, params, new HttpCallback<GetJson>() {
                    @Override
                    public void onSuccess(GetJson result) {
                        if (result == null) return;
                        /**
                         * list无法判断null 也没办法用TextUtils.isEmpty
                         * 返回"[]"  空不报错
                         */
                        Log.i("MainActivity", String.valueOf(result.getRelatedids()));
                        /**
                         * string不返回null*result.getSeries() == null
                         * 但可以用TextUtils.isEmpty 空不报错
                         */
                        if (TextUtils.isEmpty(result.getSeries()))
                            Log.e("ERROR", "null");
                        else
                            Log.e("MainActivity", result.getSeries());


                        StringBuffer stringBuffer = new StringBuffer();
                        List<GetJson.PhotosBeans> photosBeans = result.getPhotos();
                        String mtimgurl = null, mPhotohtml = null;
                        for (GetJson.PhotosBeans phots : photosBeans) {
                            mPhotohtml = phots.getPhotohtml();
                            mtimgurl = phots.getTimgurl();
                        }
                        if (mtimgurl == null || mPhotohtml == null) return;
                        stringBuffer.append("来源").append(result.getTxt())
                                .append("Postid").append(result.getPostid())
                                .append("message").append(result.getDesc()).append(mPhotohtml).append(mtimgurl).append(result.getSeries());
                        if (result.getTxt() != null)
                            Toast.makeText(MainActivity.this, "" + result.getTxt(), Toast.LENGTH_LONG).show();
                        textView.setText(stringBuffer);
                        /**
                         * RequestOptions裁剪圆
                         * placeholder未加载时候的图片
                         * error错位图
                         */
                        RequestOptions requestOptions = RequestOptions.circleCropTransform();
                        Glide.with(MainActivity.this).load(mtimgurl).placeholder(R.mipmap.ic_launcher_round).error(R.drawable.ic_launcher_background).apply(requestOptions).into(imageView);

                    }

                    @Override
                    public void onFailure(String e) {

                    }
                });
                break;
            case R.id.button2:
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,Main2Activity.class);
                startActivity(intent);
                break;
        }
    }
}
