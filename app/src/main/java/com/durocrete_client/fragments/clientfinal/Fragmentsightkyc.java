package com.durocrete_client.fragments.clientfinal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.durocrete_client.R;
import com.durocrete_client.activity.MainActivity;
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

public class Fragmentsightkyc extends Fragment {

    private Button btnuUpdatesitekyc;
    private Spinner spAllsiteslist;
    private EditText tvSiteaddress;
    private TextView tvSiteLat;
    private EditText tvSiteMobileNo;
    private TextView tvclientname;
    private EditText tvSiteEmailId;
    private TextView tvSiteid;
    private EditText tvSiteName;
    private TextView tvSiteLong;
    private String Spinnerposition;

    private MainActivity mainActivity;
    private List<Siteslist> Allsitelist;
    private String selectedsiteid = "";
    private ArrayAdapter<Siteslist> sitelistadapter;
    private List<Siteinfo> siteinfoarraylist;
    MyPreferenceManager sharedPref;
    long selectedsiteidvalue;

    String Siteaddress;
    String Sitename;
    String SitemobileNo;
    String emailid;
    String Siteid;
    String Sitelat;
    String Sitelog;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentsiteupdatekyc, container, false);

        Initview(view);
        sharedPref = new MyPreferenceManager(getActivity());

        String clientid = sharedPref.getStringPreferences(MyPreferenceManager.Clientid);
        tvclientname.setText(sharedPref.getStringPreferences(MyPreferenceManager.Clientname));

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("clId", clientid);
        Allsitelist = new ArrayList<>();
        CallWebservice.getWebservice(true, mainActivity, Request.Method.POST, IUrls.Client_Info, hashMap, new VolleyResponseListener<Siteslist>() {
            @Override
            public void onResponse(Siteslist[] object, String message, String key) {
//
                if (object[0] instanceof Siteslist) {
                    for (Siteslist siteslist : object) {
                        Allsitelist.add(siteslist);
                    }
                }

                sitelistadapter = new ArrayAdapter<Siteslist>(getContext(),
                        android.R.layout.simple_spinner_item, Allsitelist);
                spAllsiteslist.setAdapter(sitelistadapter);

            }

            @Override
            public void onError(String message) {
                Log.v("tag", message.toString());

                Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_SHORT).show();
//                FragmentTransaction ft =getFragmentManager().beginTransaction();
//                ft.replace(R.id.content_frame, new Fragmenthome());
//                ft.commit();


            }
        }, Siteslist[].class);


        spAllsiteslist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Spinnerposition = String.valueOf(position);
                selectedsiteidvalue=parent.getItemIdAtPosition(position);
                if (position != 0) {

                    Siteslist siteslist = (Siteslist) parent.getItemAtPosition(position);
                    selectedsiteidvalue=siteslist.getSiteId();
                    selectedsiteid = String.valueOf(siteslist.getSiteId());


                    HashMap<String, String> hashMap1 = new HashMap<>();
                    hashMap1.put("siteId", selectedsiteid);

                    siteinfoarraylist = new ArrayList<>();

                    CallWebservice.getWebservice(true, mainActivity, Request.Method.POST, IUrls.get_site_details
                            , hashMap1, new VolleyResponseListener<Siteinfo>() {
                                @Override
                                public void onResponse(Siteinfo[] object, String s, String key) {

                                    if (object[0] instanceof Siteinfo) {
                                        for (Siteinfo testobject : object) {
                                            siteinfoarraylist.add(testobject);
                                        }

                                        tvSiteaddress.setText(siteinfoarraylist.get(0).getSiteaddress());
                                        tvSiteEmailId.setText(siteinfoarraylist.get(0).getSite_mail());
                                        tvSiteMobileNo.setText(siteinfoarraylist.get(0).getContactno());
                                        tvSiteName.setText(siteinfoarraylist.get(0).getContactpersonname());
                                        sharedPref.setStringPreferences(MyPreferenceManager.SiteclientId, siteinfoarraylist.get(0).getSITE_CL_Id());
                                    }
                                }

                                @Override
                                public void onError(String message) {
                                    Log.v("tag", "Please Check Internet Connection");
                                    Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_SHORT).show();

                                }
                            }, Siteinfo[].class);

                }
                 else {


                    selectedsiteid = "";
                    tvSiteaddress.setText("");
                    tvSiteEmailId.setText("");
                    tvSiteName.setText("");
                    tvSiteMobileNo.setText("");

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnuUpdatesitekyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (valid()) {
                    Makerequest();
                }

            }
        });


        return view;
    }


    private void Makerequest() {
        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("siteId", selectedsiteid);
        hashMap.put("Siteaddress", tvSiteaddress.getText().toString());
        hashMap.put("Site_mail", tvSiteEmailId.getText().toString());
        hashMap.put("Contactno", tvSiteMobileNo.getText().toString());
        hashMap.put("Contactpersonname", tvSiteName.getText().toString());
        hashMap.put("SITE_CL_Id", sharedPref.getStringPreferences(MyPreferenceManager.SiteclientId));


        CallWebservice.getWebservice(true, mainActivity, Request.Method.POST, IUrls.Update_site, hashMap, new VolleyResponseListener<Updatesite>() {
            @Override
            public void onResponse(Updatesite[] object, String message, String key) {
//
//                        if (object[0] instanceof newregistration) {
//                            for (newregistration materialobject : object) {
//                                registers.add(materialobject);
//                            }
//                                 }
                Utility.hideSoftKeyboard(getActivity());
                Toast.makeText(getActivity(), "Site KYC Data Updated Succesfully.", Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().popBackStack();

            }

            @Override
            public void onError(String message) {
                Log.v("tag", message.toString());
                Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        }, Updatesite[].class);
    }

    private boolean valid() {

        if (selectedsiteidvalue==0) {
            Toast.makeText(mainActivity, "Please Select Site First", Toast.LENGTH_SHORT).show();
            return false;

        } else if (tvSiteaddress.getText().toString().trim().length() == 0) {
            Toast.makeText(mainActivity, "Please Enter Site Address.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (tvSiteMobileNo.getText().toString().trim().length() != 0 && tvSiteMobileNo.getText().toString().trim().length() < 5) {
            Toast.makeText(mainActivity, "Invalid Mobile No.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!(tvSiteEmailId.getText().toString().trim().isEmpty()) && !(loop(tvSiteEmailId.getText().toString().trim()))) {
            Toast.makeText(mainActivity, "Invalid Email ID", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }


    private void Initview(View view) {
        btnuUpdatesitekyc = (Button) view.findViewById(R.id.btnupdatesitekyc);
        tvSiteaddress = (EditText) view.findViewById(R.id.tvstsiteaddress);
        tvclientname = (TextView) view.findViewById(R.id.tvclientname);
        tvSiteMobileNo = (EditText) view.findViewById(R.id.tvstMobileno);
        tvSiteEmailId = (EditText) view.findViewById(R.id.tvstsiteemailid);
//        tvSiteid = (TextView) view.findViewById(R.id.tvstsiteid);
        tvSiteName = (EditText) view.findViewById(R.id.tvstsitename);
//        tvSiteLong = (TextView) view.findViewById(R.id.tvstSitelongitude);
        mainActivity = (MainActivity) getActivity();
        spAllsiteslist = (Spinner) view.findViewById(R.id.allsitesofclients);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Site KYC");
    }

    private boolean isValidEmail(String emailInput) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(emailInput);
        return matcher.matches();
    }

    private boolean loop(String trim) {
        boolean val = true;
        String[] test = trim.split(",");
        for (int i = 0; i < test.length; i++) {
            String abc=test[i].trim();
            if (!validEmail(abc)) {
                val = false;
                break;

            }
            val = true;
        }
        return val;
    }

    public boolean validEmail(String str_newEmail) {

        return str_newEmail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }
}