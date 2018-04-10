package com.durocrete_client.fragments.clientfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.durocrete_client.R;
import com.durocrete_client.activity.LoginActivity;
import com.durocrete_client.enquiry.Fragmentenquiry;
import com.durocrete_client.fragments.Fragmentplaceorder;
import com.durocrete_client.fragments.enginner.Fragmentenginnersightkyc;
import com.durocrete_client.fragments.enginner.Fragmentreportstatus2;
import com.durocrete_client.fragments.extra.Fragmentalerts1;
import com.durocrete_client.fragments.extra.Fragmentbonuspoints;
import com.durocrete_client.fragments.extra.Fragmentpaytm;
import com.durocrete_client.fragments.sightincharge.Fragmentclientkyc1;
import com.durocrete_client.fragments.sightincharge.Fragmentsightkyc1;
import com.durocrete_client.utils.MyPreferenceManager;

/**
 * Created by root on 19/5/17.
 */

public class Fragmenthome extends Fragment implements View.OnClickListener  {


    private Button Enquiry;
    private Button Place_order;
    private Button Report_Status;
    private Button Alerts;
    private Button Bonus_points;
    private Button Client_kyc;
    private Button Site_kyc;
    private Button Paytm;
    private Button Feedback;
    private Button Logout;
    MyPreferenceManager sharedPref;
    private String message;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmenthome, container, false);

        sharedPref = new MyPreferenceManager(getActivity());

        message = sharedPref.getStringPreferences(MyPreferenceManager.Accesslevel);

        Initview(view);


//        View view1 = getActivity().getCurrentFocus();
//        if (view1 != null) {
//            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }

        return view;
    }

    private void Initview(View view) {
        Enquiry=(Button)view.findViewById(R.id.enquiry);
        Place_order=(Button)view.findViewById(R.id.place_order);
        Report_Status=(Button)view.findViewById(R.id.reports_status);
        Alerts=(Button)view.findViewById(R.id.alerts);
        Bonus_points=(Button)view.findViewById(R.id.bonus_points);
        Client_kyc=(Button)view.findViewById(R.id.update_client);
        Site_kyc=(Button)view.findViewById(R.id.update_site);
        Paytm=(Button)view.findViewById(R.id.paytm);
        Logout=(Button)view.findViewById(R.id.logout);
        Feedback=(Button)view.findViewById(R.id.feedback);

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPref.clearSharedPreference();
                Intent intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        Enquiry.setOnClickListener(this);
        Place_order.setOnClickListener(this);
        Report_Status.setOnClickListener(this);
        Alerts.setOnClickListener(this);
        Bonus_points.setOnClickListener(this);
        Client_kyc.setOnClickListener(this);
        Site_kyc.setOnClickListener(this);
        Paytm.setOnClickListener(this);
        Feedback.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        android.support.v4.app.Fragment fragment = null;

        if (message.equalsIgnoreCase("0")) {
            switch (v.getId()) {
                case R.id.update_client:
                    fragment = new Fragmentupdateclientkyc();
                    break;

                case R.id.place_order:
                    fragment = new Fragmentplaceorder();
                    break;

                case R.id.update_site:
                    fragment = new Fragmentsightkyc();
                    break;

                case R.id.enquiry:
                    fragment = new Fragmentenquiry();
                    break;

                case R.id.paytm:
                    fragment = new Fragmentpaytm();
                    break;
                case R.id.alerts:
                    fragment = new Fragmentalerts1();
                    break;
                case R.id.bonus_points:
                    fragment = new Fragmentbonuspoints();
                    break;
                case R.id.feedback:
                    fragment = new Fragmentfeedback();
                    break;
                case R.id.reports_status:
                    fragment = new Fragmentreportstatus();
                    break;

            }
        }
        if (message.equalsIgnoreCase("1")) {
            switch (v.getId()) {

                case R.id.update_client:
                    fragment = new Fragmentclientkyc1();
                    break;


                case R.id.place_order:
                    fragment = new Fragmentenquiry();
                    break;

                case R.id.update_site:
                    fragment = new Fragmentsightkyc1();
                    break;

                case R.id.enquiry:
                    fragment = new Fragmentenquiry();
                    break;

                case R.id.paytm:
                    fragment = new Fragmentpaytm();
                    break;
                case R.id.alerts:
                    fragment = new Fragmentalerts1();
                    break;
                case R.id.bonus_points:
                    fragment = new Fragmentbonuspoints();
                    break;
                case R.id.feedback:
                    fragment = new Fragmentfeedback();
                    break;
                case R.id.reports_status:
                    fragment = new Fragmentreportstatus2();
                    break;
            }
        }

        if (message.equalsIgnoreCase("2")) {
            switch (v.getId()) {
                case R.id.update_client:
                    fragment = new Fragmentclientkyc1();
                    break;


                case R.id.place_order:
                    fragment = new Fragmentenquiry();
                    break;

                case R.id.update_site:
                    fragment = new Fragmentenginnersightkyc();
                    break;

                case R.id.enquiry:
                    fragment = new Fragmentenquiry();
                    break;

                case R.id.paytm:
                    fragment = new Fragmentpaytm();
                    break;
                case R.id.alerts:
                    fragment = new Fragmentalerts1();
                    break;
                case R.id.bonus_points:
                    fragment = new Fragmentbonuspoints();
                    break;

                case R.id.feedback:
                    fragment = new Fragmentfeedback();
                    break;

                case R.id.reports_status:
                    fragment = new Fragmentreportstatus2();
                    break;
            }
        }

        if (fragment != null) {
            FragmentTransaction ft =getFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment).addToBackStack(null);
            ft.commit();
        }

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        if(message.equalsIgnoreCase("0")) {
            getActivity().setTitle("Client-"+"Home");
        }
        else if(message.equalsIgnoreCase("1"))
        {
            getActivity().setTitle("Site Incharge-"+"Home");
        }
        else
        {
            getActivity().setTitle("Engineer-"+"Home");
        }
    }

}
