package com.example.netcallback.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.example.netcallback.R;
import com.example.netcallback.struct.FunctionException;
import com.example.netcallback.struct.FunctionManager;
import com.example.netcallback.ui.activity.MainActivity;

public class Fragment1 extends BaseFragment {
    public static final String INSTERFACE = Fragment1.class.getName() + "NpNr";
    public static final String INSTERFACE_PARAMS = Fragment1.class.getName() + "WpNr";
    private static Fragment1 mInstance;


    public static Fragment1 getInstance() {
        Bundle args = new Bundle();
        synchronized (Fragment1.class) {
            if (mInstance == null)
                mInstance = new Fragment1();
        }
        mInstance.setArguments(args);
        return mInstance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        Button button = view.findViewById(R.id.fgmBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFunctionManager == null) {
                    mFunctionManager = FunctionManager.getInstance();
                }
                //无参无返回值

//                mFunctionManager.invokeFunction(INSTERFACE);
                //无参有返回值
                try {
                    mFunctionManager.invokeFunction(INSTERFACE_PARAMS, "halo");
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
