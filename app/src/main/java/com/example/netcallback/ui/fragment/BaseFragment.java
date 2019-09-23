package com.example.netcallback.ui.fragment;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.netcallback.struct.FunctionManager;
import com.example.netcallback.ui.activity.Main2Activity;
public class BaseFragment extends Fragment{
    protected FunctionManager mFunctionManager;
    private Main2Activity mBaseActivity;

    public void setmFunctionManager(FunctionManager mFunctionManager) {
        this.mFunctionManager = mFunctionManager;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Main2Activity){
            mBaseActivity= (Main2Activity) context;
            mBaseActivity.setFunctionForFragment(getTag());

        }
    }
}
