package com.example.netcallback.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.netcallback.R;
import com.example.netcallback.struct.FuctionNoParamNoResult;
import com.example.netcallback.struct.FuctionWithParamAndResult;
import com.example.netcallback.struct.FuctionWithParamOnly;
import com.example.netcallback.struct.FuctionWithResultOnly;
import com.example.netcallback.struct.FunctionManager;
import com.example.netcallback.ui.fragment.BaseFragment;
import com.example.netcallback.ui.fragment.Fragment1;
import com.example.netcallback.ui.fragment.Fragment2;
import com.example.netcallback.ui.fragment.Fragment3;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private Fragment1 messageFragment;
    private Fragment2 newsFragment;
    private Fragment3 settingFragment;
    private View messageLayout;
    private View newsLayout;
    private View settingLayout;
    private ImageView message_image;
    private ImageView news_image;
    private ImageView setting_image;
    private TextView message_text;
    private TextView news_text;
    private TextView setting_text;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initViews();
        fragmentManager = getSupportFragmentManager();
        setTabSelection(0);
    }


    private void initViews() {
        messageLayout = findViewById(R.id.message_layout);
        newsLayout = findViewById(R.id.news_layout);
        settingLayout = findViewById(R.id.setting_layout);
        message_image = findViewById(R.id.image_message);
        news_image = findViewById(R.id.image_news);
        setting_image = findViewById(R.id.image_setting);
        message_text = findViewById(R.id.message_text);
        news_text = findViewById(R.id.news_text);
        setting_text = findViewById(R.id.setting_text);
        messageLayout.setOnClickListener(this);
        newsLayout.setOnClickListener(this);
        settingLayout.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.message_layout:
                setTabSelection(0);
                break;
            case R.id.news_layout:
                setTabSelection(1);
                break;
            case R.id.setting_layout:
                setTabSelection(2);
                break;
            default:
                break;

        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void setTabSelection(int index) {
        //清除选中
        clearSelection();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //隐藏碎片
        hideFragment(transaction);
        switch (index) {
            case 0:
                message_image.setImageResource(R.mipmap.ic_launcher);
                message_text.setTextColor(Color.parseColor("#82858b"));
                //判断碎片是否为空 以免重复建立 影响性能
                if (messageFragment == null) {
                    messageFragment = new Fragment1();
                    transaction.add(R.id.content, messageFragment);
                } else {
                    transaction.show(messageFragment);
                }
                break;
            case 1:
                news_image.setImageResource(R.mipmap.ic_launcher);
                news_text.setTextColor(Color.parseColor("#82858b"));


                //判断碎片是否为空 以免重复建立 影响性能
                if (newsFragment == null) {
                    newsFragment = new Fragment2();
                    transaction.add(R.id.content, newsFragment);
                } else {
                    transaction.show(newsFragment);
                }
                break;
            case 2:
                setting_image.setImageResource(R.mipmap.ic_launcher);
                setting_text.setTextColor(Color.parseColor("#82858b"));
                //判断碎片是否为空 以免重复建立 影响性能
                if (settingFragment == null) {
                    settingFragment = new Fragment3();
                    transaction.add(R.id.content, settingFragment);
                } else {
                    transaction.show(settingFragment);
                }
                break;
        }
        transaction.commit();

    }

    private void clearSelection() {
        //设置清除后的图片文字修改
        message_image.setImageResource(R.drawable.ic_launcher_background);
        message_text.setTextColor(Color.parseColor("#82858b"));
        news_image.setImageResource(R.drawable.ic_launcher_background);
        news_text.setTextColor(Color.parseColor("#82858b"));
        setting_image.setImageResource(R.drawable.ic_launcher_background);
        setting_text.setTextColor(Color.parseColor("#82858b"));

    }

    private void hideFragment(FragmentTransaction transaction) {
        //隐藏碎片 避免重叠
        if (messageFragment != null) {
            transaction.hide(messageFragment);
        }
        if (newsFragment != null) {
            transaction.hide(newsFragment);
        }
        if (settingFragment != null) {
            transaction.hide(settingFragment);
        }

    }

    public void setFunctionForFragment(String tag) {
        FragmentManager f = getSupportFragmentManager();
        BaseFragment fragment = (BaseFragment) f.findFragmentByTag(tag);
        FunctionManager functionManager = FunctionManager.getInstance();
        if (fragment == null)
            fragment = new BaseFragment();
        fragment.setmFunctionManager(functionManager
                /**
                 * 无参数无返回值
                 */
                .addFunction(new FuctionNoParamNoResult(Fragment1.INSTERFACE) {
                    @Override
                    public void funtion() {
                        Toast.makeText(Main2Activity.this, "收到了信息", Toast.LENGTH_SHORT).show();
                    }
                    /**
                     * 有参数无返回值
                     */
                }).addFunction(new FuctionWithResultOnly<String>(Fragment2.INSTERFACE_RESULT) {
                    @Override
                    public String function() {
                        return "返回值";
                    }
                    /**
                     * 有参数有返回值
                     * 如果需要传多个参数 可以像intent一样封装
                     * 例如hashmap或bundle
                     */
                }).addFunction(new FuctionWithParamAndResult<String, Integer>(Fragment3.INSTEFACE_RESULTANDPARAMS) {
                    @Override
                    public String function(Integer data) {
                        return "activity" + data;
                    }
                    /**
                     * 无参数有返回值
                     */
                }).addFunction(new FuctionWithParamOnly(Fragment1.INSTERFACE_PARAMS) {
                    @Override
                    public void function(Object o) {
                        Log.d("MAIN", "收到了fragment的数据" + o);

                    }
                }));
    }
}
