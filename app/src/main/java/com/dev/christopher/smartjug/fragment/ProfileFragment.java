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
import android.widget.TextView;
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
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import az.plainpie.PieView;
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
    TextView size,weight,gender,name,lastname,email,date;
    PieView pieView;
    ImageView profilicon;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStop() {
        //EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public float imc(int weight,int height){
        double size =Math.pow((double)height,2);
        float result = (float) ((double)weight/size);
        return result*100;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        user = SavePreferences.newInstance(getActivity()).getUserInfo();
        View view = inflater.inflate(R.layout.frag_my_account,container,false);
        size = (TextView) view.findViewById(R.id.size_);
        gender = (TextView) view.findViewById(R.id.gender_);
        weight = (TextView) view.findViewById(R.id.weight_);
        name = (TextView) view.findViewById(R.id.name_);
        lastname = (TextView) view.findViewById(R.id.lastname_);
        email =(TextView) view.findViewById(R.id.email_);
        date = (TextView) view.findViewById(R.id.date_);
        profilicon= (ImageView) view.findViewById(R.id.profilicon);

        name.setText(user.getName().toUpperCase());
        lastname.setText(user.getLastname().toUpperCase());
        email.setText(user.getEmail());
        date.setText(user.getCreated_at());
        if (user.getPathPicture()!=null)
            profilicon.setImageBitmap(BitmapFactory
                    .decodeFile(user.getPathPicture()));

        size.setText("Taille "+String.valueOf(user.getHeight())+" CM");
        weight.setText("Poids "+String.valueOf(user.getWeight())+" Kilos");
        if (user.getSex().equals("men")){
            gender.setText(getString(R.string.string_men));
        }else {
            gender.setText(getString(R.string.string_woman));
        }


        pieView =(PieView) view.findViewById(R.id.pieView);
        Log.d("IMC",String.valueOf(imc(70,160)*100));
        float imcIndice = (float)imc(70,160)*100;
        pieView.setInnerTextVisibility(View.VISIBLE);
        pieView.setInnerText("IMC");
        pieView.setmPercentage((float)imc(70,160)*100);

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
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UserResult result){
        DataManager.getInstance().setUserResult(result);
        SavePreferences.newInstance(getActivity()).DestroyUserSession();
        SavePreferences.newInstance(getActivity()).createUserSession(result);
        Log.d("onEventProfil",result.toString());
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ErrorResult result){
        Log.d("onEventProfil",result.toString());
    }


}
