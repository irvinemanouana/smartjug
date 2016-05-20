package com.dev.christopher.smartjug.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dev.christopher.smartjug.LoginActivity;
import com.dev.christopher.smartjug.R;
import com.dev.christopher.smartjug.utility.Tag;

/**
 * Created by Christopher on 19/05/2016.
 */
public class MessageAlertDialog extends DialogFragment {

    private static final String MSG_KEY = "msg" ;
    private static final String ACTION_TAG = "action";
    private TextView msgTextView;
    private Button actionButton;
    private AlertDialog msgAlertDialog;


    public static MessageAlertDialog newInstance(String message,String tag){
        MessageAlertDialog  alertDialog = new MessageAlertDialog();
        Bundle bundle = new Bundle();
        bundle.putString(MSG_KEY,message);
        bundle.putString(ACTION_TAG,tag);
        alertDialog.setArguments(bundle);
        return alertDialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_layout,null);

        msgTextView = (TextView) view.findViewById(R.id.message);
        msgTextView.setText(getArguments().getString(MSG_KEY));
        actionButton = (Button) view.findViewById(R.id.actionbuttton);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = getArguments().getString(ACTION_TAG);
                if (tag.equals(Tag.REGISTER_TAG)){
                    msgAlertDialog.dismiss();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view);
        msgAlertDialog = builder.create();
        return msgAlertDialog;
    }
}
