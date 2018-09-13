package com.hunter.addemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

abstract class BaseFragment extends Fragment {

    protected MainActivity _activity;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _activity = (MainActivity)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _activity = null;
    }

    protected void sliding()
    {

    }

    protected void normal()
    {

    }

    protected void doubleTap()
    {

    }
}

