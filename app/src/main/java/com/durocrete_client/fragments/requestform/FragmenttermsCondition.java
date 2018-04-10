package com.durocrete_client.fragments.requestform;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.volley.Request;
import com.durocrete_client.R;
import com.durocrete_client.activity.MainActivity;
import com.durocrete_client.activity.Testrequestform;
import com.durocrete_client.model.Qoutation;
import com.durocrete_client.network.CallWebservice;
import com.durocrete_client.network.IUrls;
import com.durocrete_client.network.VolleyResponseListener;
import com.durocrete_client.utils.Utility;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;

/**
 * Created by root on 7/9/17.
 */

public class FragmenttermsCondition extends Fragment {

    Button Submittestrequestform;
    MainActivity mainActivity;
    CheckBox checkbox;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.terms_condition, container, false);

        Submittestrequestform = (Button) view.findViewById(R.id.submitbutton);
        mainActivity = (MainActivity) getActivity();
        checkbox = (CheckBox) view.findViewById(R.id.checkboxselection);


        Submittestrequestform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (checkbox.isChecked()) {

                    Utility.hideSoftKeyboard(getActivity());
                    Gson gson = new Gson();
                    JsonElement element = gson.toJsonTree(getMyActivity().getTestrequestformList(), new TypeToken<List<Testrequestform>>() {
                    }.getType());
                    if (!element.isJsonArray()) {

                    }

                    JsonArray jsonArray = element.getAsJsonArray();
                    Log.d("tag1", jsonArray.toString());


                    if (jsonArray != null) {
                        HashMap<String, String> hashMap = new HashMap<>();

                        hashMap.put("data", String.valueOf(jsonArray));

                        CallWebservice.getWebservice(true, mainActivity, Request.Method.POST, IUrls.tets_request, hashMap, new VolleyResponseListener<Qoutation>() {
                            @Override
                            public void onResponse(Qoutation[] object, String s, String key) {

//                            if (object[0] instanceof Qoutation) {
                                Toast.makeText(getActivity(), "Test Request Form Successfully Submitted.", Toast.LENGTH_SHORT).show();
                                getMyActivity().removefragmewnt();
                                getMyActivity().getTestrequestformList().clear();
                            }


                            @Override
                            public void onError(String message) {
                                Log.v("tag", message.toString());
                                Toast.makeText(getActivity(), "Please Check Internet Connection." ,Toast.LENGTH_SHORT).show();
                            getMyActivity().removefragmewnt();
                                getMyActivity().getTestrequestformList().clear();

                            }
                        }, Qoutation[].class);
                    }
                } else {
                    Toast.makeText(mainActivity, "Please Accept the Terms and Conditions", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Terms & Condition");
    }

    public MainActivity getMyActivity() {
        return (MainActivity) getActivity();
    }
}