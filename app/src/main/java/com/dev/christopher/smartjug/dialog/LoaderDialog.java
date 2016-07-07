package com.dev.christopher.smartjug.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.dev.christopher.smartjug.R;

/**
 * Created by Christopher on 08/07/16.
 */
public class LoaderDialog extends DialogFragment {
    AlertDialog alertDialog;

    public static LoaderDialog newInstance(){
        return new LoaderDialog();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.view_loader,null);
        ImageView loaderImageView =(ImageView) view.findViewById(R.id.loader_pic);
        loaderImageView.setBackgroundResource(R.drawable.loader_drawer);
        AnimationDrawable animationDrawable = (AnimationDrawable) loaderImageView.getBackground();
        animationDrawable.start();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setCancelable(false);
        alertDialog = builder.create();
        return alertDialog;
    }
}
