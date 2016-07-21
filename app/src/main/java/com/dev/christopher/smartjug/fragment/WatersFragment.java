package com.dev.christopher.smartjug.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.dev.christopher.smartjug.R;
import com.dev.christopher.smartjug.dialog.LoaderDialog;
import com.dev.christopher.smartjug.manager.DataManager;
import com.dev.christopher.smartjug.result.DataResult;
import com.dev.christopher.smartjug.sharedPreferences.SavePreferences;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Christopher on 14/06/16.
 */
public class WatersFragment extends Fragment {
    private ListView dataListView;
    private Date today;
    private ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        today = new Date();

        String bottle_id= SavePreferences.newInstance(getActivity()).getBottle().get_id();

        DataManager.getInstance().getDayData(bottle_id);
        View view = inflater.inflate(R.layout.frag_chart,container,false);
        dataListView = (ListView)view.findViewById(R.id.all_data);

        return view;
    }

}
