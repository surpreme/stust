package com.example.netcallback.ui.activity;

import androidx.annotation.NonNull;
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

public class Main2Activity extends BaseActivity implements View.OnClickListener {
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
    private int mFragmentTag_INDEX;
    protected String CODE_FRAGMENT_KEY;//key
    private static final String[] FRAGMENT_TAG = {"messageFragment", "newsFragment", "settingFragment"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initViews();
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            if (savedInstanceState.getInt(CODE_FRAGMENT_KEY) == 0 && messageFragment == null)
                messageFragment = (Fragment1) fragmentManager.findFragmentByTag(FRAGMENT_TAG[0]);
            if (savedInstanceState.getInt(CODE_FRAGMENT_KEY) == 1 && newsFragment == null)
                newsFragment = (Fragment2) fragmentManager.findFragmentByTag(FRAGMENT_TAG[1]);
            if (savedInstanceState.getInt(CODE_FRAGMENT_KEY) == 2 && settingFragment == null)
                settingFragment = (Fragment3) fragmentManager.findFragmentByTag(FRAGMENT_TAG[2]);
            setTabSelection(savedInstanceState.getInt(CODE_FRAGMENT_KEY));
        } else
            setTabSelection(0);
    }

    /**
     * super.onSaveInstanceState(outState);
     * 这个需要添加在方法下面 否则不能执行
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CODE_FRAGMENT_KEY, mFragmentTag_INDEX);
        super.onSaveInstanceState(outState);
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
        clearSelection();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        mFragmentTag_INDEX = index;
        switch (index) {
            case 0:
                message_image.setImageResource(R.mipmap.ic_launcher);
                message_text.setTextColor(Color.parseColor("#82858b"));
                if (messageFragment == null) {
                    messageFragment = new Fragment1();
                    transaction.add(R.id.content, messageFragment, FRAGMENT_TAG[index]);
                } else
                    transaction.show(messageFragment);
                break;
            case 1:
                news_image.setImageResource(R.mipmap.ic_launcher);
                news_text.setTextColor(Color.parseColor("#82858b"));
                if (newsFragment == null) {
                    newsFragment = new Fragment2();
                    transaction.add(R.id.content, newsFragment, FRAGMENT_TAG[index]);
                } else
                    transaction.show(newsFragment);
                break;
            case 2:
                setting_image.setImageResource(R.mipmap.ic_launcher);
                setting_text.setTextColor(Color.parseColor("#82858b"));
                if (settingFragment == null) {
                    settingFragment = new Fragment3();
                    transaction.add(R.id.content, settingFragment, FRAGMENT_TAG[index]);
                } else
                    transaction.show(settingFragment);
                break;
        }
        transaction.commit();
    }

    private void clearSelection() {
        message_image.setImageResource(R.drawable.ic_launcher_background);
        message_text.setTextColor(Color.parseColor("#82858b"));
        news_image.setImageResource(R.drawable.ic_launcher_background);
        news_text.setTextColor(Color.parseColor("#82858b"));
        setting_image.setImageResource(R.drawable.ic_launcher_background);
        setting_text.setTextColor(Color.parseColor("#82858b"));

    }

    private void hideFragment(FragmentTransaction transaction) {
        if (messageFragment != null)
            transaction.hide(messageFragment);
        if (newsFragment != null)
            transaction.hide(newsFragment);
        if (settingFragment != null)
            transaction.hide(settingFragment);
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
