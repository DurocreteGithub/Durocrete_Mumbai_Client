package com.durocrete_client.fragments.clientfinal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.durocrete_client.R;
import com.durocrete_client.activity.MainActivity;
import com.durocrete_client.adapter.ReportAdapter;
import com.durocrete_client.model.Clientinfo;
import com.durocrete_client.model.Materiallist;
import com.durocrete_client.model.Reportstatus;
import com.durocrete_client.model.Siteslist;
import com.durocrete_client.network.CallWebservice;
import com.durocrete_client.network.IUrls;
import com.durocrete_client.network.VolleyResponseListener;
import com.durocrete_client.utils.MyPreferenceManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by root on 19/5/17.
 */

public class Fragmentreportstatus extends Fragment {

    private Clientinfo clientinfo;
    private MainActivity mainActivity;
    private Spinner spsiteselection;
    private Spinner SpAllmateriallist;
    private List<Siteslist> Allsitelist;
    private ArrayAdapter<Siteslist> sitelistadapter;
    private TextView clientname;
    private List<Materiallist> Allmateriallist;
    private String ClientID;
    MyPreferenceManager sharedPref;
    private ArrayAdapter<Materiallist> materiallistadapter;
    private String stselectedmaterialid, selectedsiteid;
    private List<Reportstatus> Allreportlist;
    private ReportAdapter reportAdapter;
    private RecyclerView reportsummaryrecycler;
    private TextView nodata;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentreportstatus, container, false);

        Initview(view);
        sharedPref = new MyPreferenceManager(getActivity());

        clientname.setText(sharedPref.getStringPreferences(MyPreferenceManager.Clientname));
        ClientID = sharedPref.getStringPreferences(MyPreferenceManager.Clientid);


        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("clId", ClientID);


        CallWebservice.getWebservice(true, mainActivity, Request.Method.POST, IUrls.Client_Info, hashMap, new VolleyResponseListener<Siteslist>() {
            @Override
            public void onResponse(Siteslist[] object, String message, String key) {
//
                if (object[0] instanceof Siteslist) {
                    for (Siteslist siteslist : object) {
                        Allsitelist.add(siteslist);
                    }
                }
                getmaterialdata();
                sitelistadapter = new ArrayAdapter<Siteslist>(getActivity(),
                        android.R.layout.simple_spinner_item, Allsitelist);
                spsiteselection.setAdapter(sitelistadapter);

            }

            @Override
            public void onError(String message) {
                Log.v("tag", message.toString());

                Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        }, Siteslist[].class);


        spsiteselection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedsiteid = String.valueOf(Allsitelist.get(position).getSiteId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        SpAllmateriallist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stselectedmaterialid = Allmateriallist.get(position).getMaterialId();
                if (position != 0) {
                    senddata();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }

    private void senddata() {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("siteId", selectedsiteid);
        hashMap.put("ClientId", sharedPref.getStringPreferences(MyPreferenceManager.Clientid));
        hashMap.put("materialId", stselectedmaterialid);


        CallWebservice.getWebservice(true, mainActivity, Request.Method.POST, IUrls.get_report, hashMap, new VolleyResponseListener<Reportstatus>() {
            @Override
            public void onResponse(Reportstatus[] object, String s, String key) {

                Allreportlist = new ArrayList<Reportstatus>();

                if (object[0] instanceof Reportstatus) {
                    for (Reportstatus reportobject : object) {
                        Allreportlist.add(reportobject);
                    }

                    if (s.equalsIgnoreCase("0")) {
                        reportsummaryrecycler.setVisibility(View.GONE);
                        Toast.makeText(mainActivity, "No Reports Available", Toast.LENGTH_SHORT).show();
//                        nodata.setVisibility(View.VISIBLE);
//                        nodata.setText("No Reports Available.");

                    } else {
                        reportAdapter = new ReportAdapter(getActivity(), Allreportlist);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                        reportsummaryrecycler.setLayoutManager(mLayoutManager);
                        reportsummaryrecycler.setItemAnimator(new DefaultItemAnimator());
                        reportsummaryrecycler.setAdapter(reportAdapter);
                        nodata.setVisibility(View.GONE);

                    }
                }
                }

                @Override
                public void onError (String message){
                    Log.v("tag", message.toString());

                }
            },Reportstatus[].class);


        }

    private void getmaterialdata() {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("", "");

        Allmateriallist = new ArrayList<>();

        CallWebservice.getWebservice(true, mainActivity, Request.Method.POST, IUrls.GET_Material, hashMap, new VolleyResponseListener<Materiallist>() {
            @Override
            public void onResponse(Materiallist[] object, String s, String key) {

                if (object[0] instanceof Materiallist) {
                    for (Materiallist materialobject : object) {
                        Allmateriallist.add(materialobject);
                    }
                }

                materiallistadapter = new ArrayAdapter<Materiallist>(getActivity(),
                        android.R.layout.simple_spinner_item, Allmateriallist);
                SpAllmateriallist.setAdapter(materiallistadapter);


            }

            @Override
            public void onError(String message) {
                Log.v("tag", message.toString());

            }
        }, Materiallist[].class);

    }


    private void Initview(View view) {

        mainActivity = (MainActivity) getActivity();
        clientinfo = new Clientinfo();
        spsiteselection = (Spinner) view.findViewById(R.id.allsitesofclient);
        clientname = (TextView) view.findViewById(R.id.Clientnameinreport);
        SpAllmateriallist = (Spinner) view.findViewById(R.id.allmateriallist);
        reportsummaryrecycler = (RecyclerView) view.findViewById(R.id.reportsummaryrecycler);
        nodata = (TextView) view.findViewById(R.id.nodata);
        Allmateriallist = new ArrayList<>();
        Allsitelist = new ArrayList<>();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Report Status");
    }


}
