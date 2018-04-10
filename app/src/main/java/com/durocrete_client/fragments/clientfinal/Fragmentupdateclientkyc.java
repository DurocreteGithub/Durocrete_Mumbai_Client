package com.durocrete_client.fragments.clientfinal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.durocrete_client.R;
import com.durocrete_client.activity.MainActivity;
import com.durocrete_client.model.Clientinfo;
import com.durocrete_client.model.Updateclient;
import com.durocrete_client.network.CallWebservice;
import com.durocrete_client.network.IUrls;
import com.durocrete_client.network.VolleyResponseListener;
import com.durocrete_client.utils.MyPreferenceManager;
import com.durocrete_client.utils.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by root on 19/5/17.
 */

public class Fragmentupdateclientkyc extends Fragment {

    private TextView ClientID;
    private TextView tvclientname;
    private EditText etxOfficeAddress;
    private EditText etxOwnerName;
    private EditText etxofcNo;
    private EditText etxEmailId;
    private EditText etxPanNO;
    private EditText etxTaNo;
    private EditText etxMobileNo;
    private MainActivity mainActivity;
    private Button btnUpdateClientKyc;
    private String officeAddress;
    private String ownerName;
    private String emailId;
    private String PanNo;
    private String TanNo;
    private String LandNo;
    private EditText etxAccountname;
    private EditText etxAccount_contact;

    private List<Clientinfo> clientinfolist;
    MyPreferenceManager sharedPref;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmnetupdateclientkyc2, container, false);

        Initview(view);

//
//        ClientID.setText(sharedPref.getStringPreferences(MyPreferenceManager.Clientid));
//        etxofcNo.setText(sharedPref.getStringPreferences(MyPreferenceManager.clientofficeno));
//        tvclientname.setText(sharedPref.getStringPreferences(MyPreferenceManager.Clientname));
//        etxOfficeAddress.setText(sharedPref.getStringPreferences(MyPreferenceManager.clientofficeaddress));
//        etxOwnerName.setText(sharedPref.getStringPreferences(MyPreferenceManager.directorname));
//        etxMobileNo.setText(sharedPref.getStringPreferences(MyPreferenceManager.clientmobileno));
//        etxEmailId.setText(sharedPref.getStringPreferences(MyPreferenceManager.clientemailid));
//        etxPanNO.setText(sharedPref.getStringPreferences(MyPreferenceManager.clientpanNO));
//        etxTaNo.setText(sharedPref.getStringPreferences(MyPreferenceManager.clientTanno));


//
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("cl_id", sharedPref.getStringPreferences(MyPreferenceManager.Clientid));


        clientinfolist = new ArrayList<>();

        CallWebservice.getWebservice(true, mainActivity, Request.Method.POST, IUrls.get_client_by_clientID, hashMap, new VolleyResponseListener<Clientinfo>() {
            @Override
            public void onResponse(Clientinfo[] object, String message, String key) {

                if (object[0] instanceof Clientinfo) {
                    for (Clientinfo siteslist : object) {
                        clientinfolist.add(siteslist);
                    }
                }

                tvclientname.setText(clientinfolist.get(0).getClientName());
                etxOfficeAddress.setText(clientinfolist.get(0).getOfficeAddress());
                etxOwnerName.setText(clientinfolist.get(0).getDirectorName());
                etxMobileNo.setText(clientinfolist.get(0).getMobileNo());
                etxEmailId.setText(clientinfolist.get(0).getEmailId());
                etxPanNO.setText(clientinfolist.get(0).getPAN());
                etxTaNo.setText(clientinfolist.get(0).getTAN());
                etxAccountname.setText(clientinfolist.get(0).getAc_person());
                etxAccount_contact.setText(clientinfolist.get(0).getAc_contact());
                sharedPref.setStringPreferences(MyPreferenceManager.Clientname, clientinfolist.get(0).getClientName().toString());


            }

            @Override
            public void onError(String message) {
                Log.v("tag", message.toString());

                Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_SHORT).show();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new Fragmenthome());
                ft.commit();


            }
        }, Clientinfo[].class);


        btnUpdateClientKyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (valid()) {
                    makerequest();
                }
            }
        });

        return view;
    }

    private boolean valid() {

        if (etxOfficeAddress.getText().toString().trim().length() == 0) {
            etxOfficeAddress.setError("Please Enter client Address");
            etxOfficeAddress.requestFocus();
            return false;

        } else if (etxMobileNo.getText().toString().trim().length() == 0) {
            etxMobileNo.setError("Please enter Client Mobile No.");
            etxMobileNo.requestFocus();
            return false;
        } else if (etxMobileNo.getText().toString().trim().length() < 5) {
            etxMobileNo.setError("Invalid Client Mobile No.");
            etxMobileNo.requestFocus();
            return false;
        } else if (etxAccount_contact.getText().toString().trim().length() != 0 && etxAccount_contact.getText().toString().trim().length() < 5) {
            etxAccount_contact.setError("Invalid Account Person Mobile No.");
            etxAccount_contact.requestFocus();
            return false;
        } else if (!(etxEmailId.getText().toString().trim().isEmpty()) && !(loop(etxEmailId.getText().toString().trim()))) {
            etxEmailId.setError("Invalid Email ID.");
            etxEmailId.requestFocus();
            return false;
        }

        return true;

    }


    private void makerequest() {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("clId", sharedPref.getStringPreferences(MyPreferenceManager.Clientid));
        hashMap.put("officeAddress", etxOfficeAddress.getText().toString());
        hashMap.put("emailId", etxEmailId.getText().toString());
        hashMap.put("PanNo", etxPanNO.getText().toString());
        hashMap.put("TanNo", etxTaNo.getText().toString());
        hashMap.put("directorName", etxOwnerName.getText().toString());
        hashMap.put("mobileNo", etxMobileNo.getText().toString());
        hashMap.put("ac_person", etxAccountname.getText().toString());
        hashMap.put("ac_contact", etxAccount_contact.getText().toString());


        CallWebservice.getWebservice(true, mainActivity, Request.Method.POST, IUrls.Update_client, hashMap, new VolleyResponseListener<Updateclient>() {
            @Override
            public void onResponse(Updateclient[] object, String message, String key) {
//
//                        if (object[0] instanceof newregistration) {
//                            for (newregistration materialobject : object) {
//                                registers.add(materialobject);
//                            }
//                        }
                Toast.makeText(getActivity(), "Client KYC Data Updated Successfully", Toast.LENGTH_SHORT).show();
                Utility.hideSoftKeyboard(getActivity());
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(R.id.content_frame, new Fragmenthome());
//                ft.commit();
                getActivity().getSupportFragmentManager().popBackStack();

            }

            @Override
            public void onError(String message) {
                Log.v("tag", message.toString());
                Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        }, Updateclient[].class);

    }

    private boolean loop(String trim) {
        boolean val = true;
        String[] test = trim.split(",");
        for (int i = 0; i < test.length; i++) {
            if (!validEmail(test[i])) {
                val = false;
                break;

            }
            val = true;
        }
        return val;
    }


    private void Initview(View view) {
//        ClientID = (TextView) view.findViewById(R.id.tvclientid);
        tvclientname = (TextView) view.findViewById(R.id.tvclientname);
        etxOfficeAddress = (EditText) view.findViewById(R.id.etxofficeaddress);
        etxOwnerName = (EditText) view.findViewById(R.id.tvownername);
        etxEmailId = (EditText) view.findViewById(R.id.etxclientemailid);
        etxMobileNo = (EditText) view.findViewById(R.id.etxmobileno);
        etxPanNO = (EditText) view.findViewById(R.id.etxpanno);
        etxTaNo = (EditText) view.findViewById(R.id.etxtanno);
        etxAccountname = (EditText) view.findViewById(R.id.Accountname);
        etxAccount_contact = (EditText) view.findViewById(R.id.Accountcontact);
        mainActivity = (MainActivity) getActivity();
        btnUpdateClientKyc = (Button) view.findViewById(R.id.btnclUpdateclient);
        sharedPref = new MyPreferenceManager(getActivity());
        clientinfolist = new ArrayList<>();

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Client KYC");
    }

    private boolean isvalidPanNo(String panNo) {
        String regex = "\\d+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(panNo);
        return m.matches();
    }


    public boolean validEmail(String str_newEmail) {

        return str_newEmail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }

}
