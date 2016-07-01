package com.dev.christopher.smartjug.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.dev.christopher.smartjug.R;
import com.dev.christopher.smartjug.generator.ServiceGenerator;
import com.dev.christopher.smartjug.interfaceClient.UserInterfaceClient;
import com.dev.christopher.smartjug.manager.DataManager;
import com.dev.christopher.smartjug.model.UpdateProfileIconModel;
import com.dev.christopher.smartjug.model.User;
import com.dev.christopher.smartjug.result.ErrorResult;
import com.dev.christopher.smartjug.result.UserResult;
import com.dev.christopher.smartjug.sharedPreferences.SavePreferences;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Christopher on 14/06/16.
 */
public class ProfileFragment extends Fragment {
    private static final int RESULT_OK = 1;
    private ImageView profileImageView;
    String imgDecodableString;
    private static int RESULT_LOAD_IMG = 1;
    private UserResult user;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        user = DataManager.getInstance().getUserResult();
        View view = inflater.inflate(R.layout.frag_my_account,container,false);
        profileImageView = (ImageView) view.findViewById(R.id.profilicon);
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,RESULT_LOAD_IMG);
            }
        });

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                Log.d("ImageFile",imgDecodableString);
                cursor.close();
                profileImageView.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));
                UpdateProfileIconModel iconModel = new UpdateProfileIconModel(user.getEmail(),imgDecodableString);
                DataManager.getInstance().changeProfilIcon(iconModel);

            } else {
                Toast.makeText(getActivity(), "You haven't picked Image", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }
    }
    @Subscribe
    public void onEvent(UserResult result){
        DataManager.getInstance().setUserResult(result);
        Log.d("onEventProfil",result.toString());
    }

    @Subscribe
    public void onEvent(ErrorResult result){
        Log.d("onEventProfil",result.toString());
    }


}
