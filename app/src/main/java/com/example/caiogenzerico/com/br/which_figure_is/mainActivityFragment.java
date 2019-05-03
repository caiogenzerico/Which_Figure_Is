package com.example.caiogenzerico.com.br.which_figure_is;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class mainActivityFragment extends Fragment {

    private DoodleView doodleView;

    private float acceleration;
    private float lastAcceleretion;
    private float currentAccleration;

    private boolean dialogOnScreen = false;

    private static final int ACCELERATION_THRESHOLD = 1000000;

    private static final int SAVE_IMAGE_PERMISSION_REQUEST_CODE = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View raiz =
                inflater.inflate(
                        R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        doodleView = raiz.findViewById(R.id.doodleView);
        //currentAccleration = SensorManager.GRAVITY_EARTH;
        //lastAcceleretion = SensorManager.GRAVITY_EARTH;
        //acceleration = 0.00f;
        return raiz;
    }


}
