package com.example.netcallback.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.netcallback.R;
import com.example.netcallback.struct.FunctionManager;

public class Fragment2 extends BaseFragment{
    public static final String INSTERFACE_RESULT=Fragment2.class.getName()+"withResult";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment2,container,false);
        Button button= view.findViewById(R.id.frg2btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFunctionManager == null) {
                    mFunctionManager = FunctionManager.getInstance();
                }
                String s=mFunctionManager.invokeFunction(INSTERFACE_RESULT,String.class);
                Toast.makeText(getContext(),"Fragment*来自activity"+s,Toast.LENGTH_LONG).show();


            }
        });
        return view;
    }
}
