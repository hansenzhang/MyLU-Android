package com.mylu.util;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mylu.main.R;

/**
 * Created by Hansen on 1/12/14.
 */
public class FragmentOne extends Fragment{
    public static Fragment newInstance (Context context) {
        FragmentOne f = new FragmentOne();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_one, null);
        return root;
    }
}
