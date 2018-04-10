package com.durocrete_client.fragments.logins;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.durocrete_client.R;
import com.durocrete_client.activity.LoginActivity;
import com.durocrete_client.model.newregistration;
import com.durocrete_client.network.CallWebservice;
import com.durocrete_client.network.IUrls;
import com.durocrete_client.network.VolleyResponseListener;
import com.durocrete_client.utils.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by root on 19/5/17.
 */

public class Fragmentregisternew extends Fragment {


    private EditText etxclientName;
    private EditText etxOfficeAddress;
    private EditText etxSiteName;
    private EditText etxSiteAddress;
    private EditText etxMobileNo;
    private EditText etxsitemobileno;
    private Button btnregisternew;
    private String clientName;
    private String OfficeAddress;
    private String SiteName;
    private String SiteAddress;
    private String MobileNo;
    private String siteMobileNo;
    private LoginActivity loginActivity;
    List<newregistration> newregisters;
    String regex = "[0-9]+";


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmentregisternew, container, false);

        Initview(view);

        Registrationprocess();

        return view;
    }

    private void Registrationprocess() {

        btnregisternew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Utility.hideSoftKeyboard(getActivity());
                clientName = etxclientName.getText().toString().trim();
                OfficeAddress = etxOfficeAddress.getText().toString().trim();
                MobileNo = etxMobileNo.getText().toString().trim();
                SiteName = etxSiteName.getText().toString().trim();
                SiteAddress = etxSiteAddress.getText().toString().trim();
                siteMobileNo = etxsitemobileno.getText().toString().trim();


                if (etxclientName.getText().toString().trim().length() == 0) {
                    etxclientName.setError("Please Enter Client Name");
                    etxclientName.requestFocus();
                } else if (clientName.matches(regex)) {


                    etxclientName.setError("Client name should contain characters.");
                    etxclientName.requestFocus();
                } else if ((etxOfficeAddress.getText().toString().trim().length() == 0)) {


                    etxOfficeAddress.setError("Please Enter Office Address");
                    etxOfficeAddress.requestFocus();
                } else if ((etxMobileNo.getText().toString().trim().length() == 0)) {


                    etxMobileNo.setError("Please Enter Mobile No.");
                    etxMobileNo.requestFocus();
                } else if ((etxMobileNo.getText().toString().trim().length() != 10)) {


                    etxMobileNo.setError("Invalid Client Mobile No.");
                    etxMobileNo.requestFocus();
                } else if ((etxSiteName.getText().toString().trim().length() == 0)) {


                    etxSiteName.setError("Please Enter Site name");
                    etxSiteName.requestFocus();
                } else if ((etxSiteAddress.getText().toString().trim().length() == 0)) {


                    etxSiteAddress.setError("Please Enter Site Address");
                    etxSiteAddress.requestFocus();
                } else if ((etxsitemobileno.getText().toString().trim().length() == 0)) {


                    etxsitemobileno.setError("Please Enter Site Mobile No");
                    etxsitemobileno.requestFocus();
                } else if ((etxsitemobileno.getText().toString().trim().length() != 10)) {


                    etxsitemobileno.setError("Invalid Site Mobile No.");
                    etxsitemobileno.requestFocus();
                } else {

                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("clientName", clientName);
                    hashMap.put("officeAddress", OfficeAddress);
                    hashMap.put("mobileNo", MobileNo);
                    hashMap.put("siteName", SiteName);
                    hashMap.put("siteAddress", SiteAddress);
                    hashMap.put("sitemobileNo", siteMobileNo);


                    CallWebservice.getWebservice(true, loginActivity, Request.Method.POST, IUrls.Add_client, hashMap, new VolleyResponseListener<newregistration>() {
                        @Override
                        public void onResponse(newregistration[] object, String message, String key) {

                            Toast.makeText(getActivity(), "Registration Successful..You will get Password Within 2 Working Days. ", Toast.LENGTH_SHORT).show();
                            Fragment fragment = null;
                            fragment = new Fragmentlogin1();
                            FragmentManager manager = getFragmentManager();
                            FragmentTransaction transaction = manager.beginTransaction();
                            transaction.replace(R.id.output, fragment);
                            transaction.commit();

                        }

                        @Override
                        public void onError(String message) {
                            Log.v("tag", message.toString());
                            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

                        }
                    }, newregistration[].class);

                }
            }
        });
    }


    private void Initview(View view) {

        etxclientName = (EditText) view.findViewById(R.id.etxClientName);
        etxOfficeAddress = (EditText) view.findViewById(R.id.etxOfficeAddress);
        etxSiteName = (EditText) view.findViewById(R.id.etxSiteName);
        etxSiteAddress = (EditText) view.findViewById(R.id.etxsiteAddress);
        etxMobileNo = (EditText) view.findViewById(R.id.etxMobileNo);
        etxsitemobileno = (EditText) view.findViewById(R.id.sitemobileno);
        btnregisternew = (Button) view.findViewById(R.id.btnregisternew);
        newregisters = new ArrayList<>();
        loginActivity = (LoginActivity) getActivity();
    }


    private boolean isValidEmail(String s) {
        String expression = "^([0-9\\+]|\\(\\d{1,3}\\))[0-9\\-\\. ]{3,15}$";
        CharSequence inputString = s;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
}