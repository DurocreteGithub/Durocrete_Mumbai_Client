package com.durocrete_client.fragments.sightincharge;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.durocrete_client.R;
import com.durocrete_client.activity.MainActivity;
import com.durocrete_client.fragments.clientfinal.Fragmenthome;
import com.durocrete_client.model.Siteinfo;
import com.durocrete_client.model.Siteslist;
import com.durocrete_client.model.Updatesite;
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

public class Fragmentsightkyc1 extends Fragment {

    private Button btnuUpdatesitekyc;
    private TextView tvClientname;
    private TextView  tvsitename;
    private EditText tvSiteaddress;
    private TextView tvSiteMobileNo;
    private EditText tvSiteEmailId;
    private TextView tvSiteinchargename;


    private MainActivity mainActivity;
    private List<Siteslist> Allsitelist;
    private String selectedsiteid;
    private ArrayAdapter<Siteslist> sitelistadapter;
    private List<Siteinfo> siteinfoarraylist;
    MyPreferenceManager sharedPref;

    String Siteaddress;
    String Sitename;
    String SitemobileNo;
    String emailid;
    String Siteid;
    String Sitelat;
    String Sitelog;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentsightkyc1, container, false);

        Initview(view);

//
//        tvSiteaddress.setText(sharedPref.getStringPreferences(MyPreferenceManager.Siteaddress));
//        tvSiteLat.setText(sharedPref.getStringPreferences(MyPreferenceManager.Sitelatitude));
//        tvSiteEmailId.setText(sharedPref.getStringPreferences(MyPreferenceManager.SiteemailId));
//        tvSiteMobileNo.setText(sharedPref.getStringPreferences(MyPreferenceManager.Sitemobileno));
//        tvSiteid.setText(sharedPref.getStringPreferences(MyPreferenceManager.Siteid));
//        tvSiteLong.setText(sharedPref.getStringPreferences(MyPreferenceManager.Sitelongitude));
//        tvSiteName.setText(sharedPref.getStringPreferences(MyPreferenceManager.Sitename));


//
      HashMap<String, String> hashMap1 = new HashMap<>();
                hashMap1.put("siteId",sharedPref.getStringPreferences(MyPreferenceManager.Siteid));

                siteinfoarraylist=new ArrayList<>();

                CallWebservice.getWebservice(true, mainActivity, Request.Method.POST, IUrls.get_site_details, hashMap1, new VolleyResponseListener<Siteinfo>() {
                    @Override
                    public void onResponse(Siteinfo[] object, String s, String key) {

                        if (object[0] instanceof Siteinfo) {
                            for (Siteinfo testobject : object) {
                                siteinfoarraylist.add(testobject);
                            }

                            tvSiteaddress.setText(siteinfoarraylist.get(0).getSiteaddress());
                            tvSiteEmailId.setText(siteinfoarraylist.get(0).getSite_mail());
                            tvSiteMobileNo.setText(siteinfoarraylist.get(0).getContactno());
                            tvSiteinchargename.setText(siteinfoarraylist.get(0).getContactpersonname());
                            tvsitename.setText(siteinfoarraylist.get(0).getSiteName());
                            tvClientname.setText(siteinfoarraylist.get(0).getClientName());
                        }
                    }

                    @Override
                    public void onError(String message) {
                        Log.v("tag", "Please Check Internet Connection");
                        Toast.makeText(mainActivity, "Please Check Internet Connection", Toast.LENGTH_SHORT).show();
                        FragmentTransaction ft =getFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, new Fragmenthome()).addToBackStack(null);
                        ft.commit();

                    }
                }, Siteinfo[].class);


        btnuUpdatesitekyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.hideSoftKeyboard(getActivity());
                if (tvSiteaddress.getText().toString().trim().length()==0){
//                    Toast.makeText(mainActivity, "Address Should not be Blank", Toast.LENGTH_SHORT).show();
                    tvSiteaddress.setError("Address Should not be Blank");
                    tvSiteaddress.requestFocus();
                }
                else if(tvSiteMobileNo.getText().toString().length()!=0 && tvSiteMobileNo.getText().toString().length()<10)
                {
//                    Toast.makeText(mainActivity, "Invalid Mobile No.", Toast.LENGTH_SHORT).show();
                    tvSiteMobileNo.setError("Invalid Mobile No.");
                    tvSiteMobileNo.requestFocus();
                }
                else if (tvSiteEmailId.getText().toString().trim().length()!=0) {
                    if (!isValidEmail(tvSiteEmailId.getText().toString().trim())) {
//                        Toast.makeText(mainActivity, "Invalid Email ID", Toast.LENGTH_SHORT).show();
                        tvSiteEmailId.setError("Invalid Email ID");
                        tvSiteEmailId.requestFocus();
                    }
                    else
                    {
                        Makerequest();
                    }
                }else {
                    Makerequest();
                }

            }
        });

        return view;
    }

    private boolean isValidEmail(String emailid) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(emailid);
        return matcher.matches();
    }

    private void Makerequest() {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("siteId",sharedPref.getStringPreferences(MyPreferenceManager.Siteid));
        hashMap.put("Contactpersonname",tvSiteinchargename.getText().toString());
        hashMap.put("Siteaddress",tvSiteaddress.getText().toString().trim());
        hashMap.put("Site_mail",tvSiteEmailId.getText().toString().trim());
        hashMap.put("Contactno",tvSiteMobileNo.getText().toString());


        CallWebservice.getWebservice(true, mainActivity, Request.Method.POST, IUrls.Update_site, hashMap, new VolleyResponseListener<Updatesite>() {
            @Override
            public void onResponse(Updatesite[] object, String message, String key) {
//
//                        if (object[0] instanceof newregistration) {
//                            for (newregistration materialobject : object) {
//                                registers.add(materialobject);
//                            }
//                        }
                Toast.makeText(getActivity(), "Site Data Updated Successfully."
                        , Toast.LENGTH_SHORT).show();
                FragmentTransaction ft =getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new Fragmenthome()).addToBackStack(null);
                ft.commit();

            }

            @Override
            public void onError(String message) {
                Log.v("tag", message.toString());
                Toast.makeText(mainActivity, "Please Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        }, Updatesite[].class);
    }


    private void Initview(View view) {
        btnuUpdatesitekyc = (Button) view.findViewById(R.id.btnupdatesitekyc);
        tvClientname=(TextView)view.findViewById(R.id.tvclientname);
        tvsitename=(TextView)view.findViewById(R.id.tvstsitename)  ;
        tvSiteaddress = (EditText) view.findViewById(R.id.tvstsiteaddress);
        tvSiteMobileNo = (TextView) view.findViewById(R.id.tvstMobileno);
        tvSiteEmailId = (EditText) view.findViewById(R.id.tvstsiteemailid);
        tvSiteinchargename = (TextView) view.findViewById(R.id.tvnameofincharge);
        mainActivity = (MainActivity) getActivity();
        sharedPref = new MyPreferenceManager(getActivity());
        siteinfoarraylist=new ArrayList<>();

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Site KYC");
    }
}