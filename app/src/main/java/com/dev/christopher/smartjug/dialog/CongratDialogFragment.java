package com.dev.christopher.smartjug.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.dev.christopher.smartjug.R;

/**
 * Created by Christopher on 17/07/16.
 */
public class CongratDialogFragment extends DialogFragment {

    private AlertDialog alertDialog;
    public static CongratDialogFragment newInstance(){
        return new CongratDialogFragment();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.congratulation_dialog,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);

        Button button = (Button) view.findViewById(R.id.close_congratulation);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog = builder.create();
        return alertDialog;
    }
}
