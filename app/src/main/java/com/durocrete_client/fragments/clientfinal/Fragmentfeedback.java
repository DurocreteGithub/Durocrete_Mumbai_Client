package com.durocrete_client.fragments.clientfinal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.durocrete_client.R;
import com.durocrete_client.activity.MainActivity;
import com.durocrete_client.model.Qoutation;
import com.durocrete_client.network.CallWebservice;
import com.durocrete_client.network.IUrls;
import com.durocrete_client.network.VolleyResponseListener;
import com.durocrete_client.utils.MyPreferenceManager;
import com.durocrete_client.utils.Utility;

import java.util.HashMap;

/**
 * Created by root on 19/5/17.
 */

public class Fragmentfeedback extends Fragment {

    WebView mWebView;
    ImageView extremelyhappy,happy,soso,sad,sosad;
    String feeling;
    String Comment;
    MainActivity mainActivity;
    MyPreferenceManager sharedPref;
    EditText comment;
    Button feedbacksubmit;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentalerts1, container, false);



        extremelyhappy=(ImageView)view.findViewById(R.id.extremelyhappy);
        happy=(ImageView)view.findViewById(R.id.happy);
        soso=(ImageView)view.findViewById(R.id.soso);
        sad=(ImageView)view.findViewById(R.id.sad);
        sosad=(ImageView)view.findViewById(R.id.sosad);
        mainActivity = (MainActivity) getActivity();
        sharedPref= new MyPreferenceManager(getActivity());
        comment=(EditText)view.findViewById(R.id.comment);
        feedbacksubmit=(Button)view.findViewById(R.id.submitfb);



        feedbacksubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (feeling==null) {
                    Toast.makeText(mainActivity, "Please Select one of the icon", Toast.LENGTH_SHORT).show();
                } else {
                sendData();
                }
            }
        });


        extremelyhappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extremelyhappy.setBackgroundColor(Color.GREEN);
                happy.setBackgroundColor(Color.TRANSPARENT);
                soso.setBackgroundColor(Color.TRANSPARENT);
                sad.setBackgroundColor(Color.TRANSPARENT);
                sosad.setBackgroundColor(Color.TRANSPARENT);
                feeling="extremelyhappy";
//                commentdialog();
            }
        });

        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extremelyhappy.setBackgroundColor(Color.TRANSPARENT);
                happy.setBackgroundColor(Color.GREEN);
                soso.setBackgroundColor(Color.TRANSPARENT);
                sad.setBackgroundColor(Color.TRANSPARENT);
                sosad.setBackgroundColor(Color.TRANSPARENT);
                feeling="happy";
//                commentdialog();



            }
        });

        soso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extremelyhappy.setBackgroundColor(Color.TRANSPARENT);
                happy.setBackgroundColor(Color.TRANSPARENT);
                soso.setBackgroundColor(Color.GREEN);
                sad.setBackgroundColor(Color.TRANSPARENT);
                sosad.setBackgroundColor(Color.TRANSPARENT);
                feeling="soso";
//                commentdialog();

            }
        });

        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extremelyhappy.setBackgroundColor(Color.TRANSPARENT);
                happy.setBackgroundColor(Color.TRANSPARENT);
                soso.setBackgroundColor(Color.TRANSPARENT);
                sad.setBackgroundColor(Color.GREEN);
                sosad.setBackgroundColor(Color.TRANSPARENT);
                feeling="sad";
//                commentdialog();

            }
        });
        final String finalFeeling = feeling;
        sosad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extremelyhappy.setBackgroundColor(Color.TRANSPARENT);
                happy.setBackgroundColor(Color.TRANSPARENT);
                soso.setBackgroundColor(Color.TRANSPARENT);
                sad.setBackgroundColor(Color.TRANSPARENT);
                sosad.setBackgroundColor(Color.GREEN);
                feeling ="sosad";
//                commentdialog();

            }
        });

        return view;
    }

    private void commentdialog() {

        LayoutInflater li = LayoutInflater.from(getActivity());
        View promptsView = li.inflate(R.layout.construnction_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.construnctionmanagement);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Utility.hideSoftKeyboard(getActivity());
                                if (userInput.getText().toString().length() == 0) {
                                    Toast.makeText(getActivity(), "Please enter Comment ", Toast.LENGTH_SHORT).show();
                                } else {
                                    Comment = userInput.getText().toString().trim();
                                    extremelyhappy.setBackgroundColor(Color.TRANSPARENT);
                                    happy.setBackgroundColor(Color.TRANSPARENT);
                                    soso.setBackgroundColor(Color.TRANSPARENT);
                                    sad.setBackgroundColor(Color.TRANSPARENT);
                                    sosad.setBackgroundColor(Color.TRANSPARENT);
                                    sendData();
                                    dialog.dismiss();
                                }
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Utility.hideSoftKeyboard(getActivity());
                                extremelyhappy.setBackgroundColor(Color.TRANSPARENT);
                                happy.setBackgroundColor(Color.TRANSPARENT);
                                soso.setBackgroundColor(Color.TRANSPARENT);
                                sad.setBackgroundColor(Color.TRANSPARENT);
                                sosad.setBackgroundColor(Color.TRANSPARENT);
                                dialog.cancel();

                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    private void sendData() {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userId", sharedPref.getStringPreferences(MyPreferenceManager.Username));
        hashMap.put("Comment", comment.getText().toString().trim());
        hashMap.put("feeling", feeling);


        CallWebservice.getWebservice(true, mainActivity, Request.Method.POST, IUrls.feedback, hashMap, new VolleyResponseListener<Qoutation>() {
            @Override
            public void onResponse(Qoutation[] object, String message, String key) {

                    Utility.hideSoftKeyboard(getActivity());
                Toast.makeText(mainActivity, "Thank You for Your Valuable Feedback", Toast.LENGTH_SHORT).show();
//                FragmentTransaction ft =getFragmentManager().beginTransaction();
//                ft.replace(R.id.content_frame, new Fragmenthome());
//                ft.commit();
                getActivity().getSupportFragmentManager().popBackStack();
            }

            @Override
            public void onError(String message) {
                Log.v("tag", message.toString());
                Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_SHORT).show();
            }
        },Qoutation[].class);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Feedback");
    }
}
