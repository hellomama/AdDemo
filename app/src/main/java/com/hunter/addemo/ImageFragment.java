package com.hunter.addemo;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageFragment extends BaseFragment {
    public static ImageFragment getInstance(int path)
    {
        ImageFragment fragment = new ImageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("data",path);
        fragment.setArguments(bundle);

        return fragment;
    }

    private int url;

    private void init()
    {
        if (getArguments() != null)
        {
            url = getArguments().getInt("data");
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        init();
        View view = inflater.inflate(R.layout.fragment_image,container,false);
        ImageView imageView = view.findViewById(R.id.image);
        if (url != 0) {
            Uri uri = Uri.parse("android.resource://" + _activity.getPackageName() + "/" + url);
            GlideApp.with(this).load(uri).into(imageView);
        }
        return view;
    }
}
