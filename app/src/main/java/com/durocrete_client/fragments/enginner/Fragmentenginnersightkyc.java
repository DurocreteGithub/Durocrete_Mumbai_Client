package com.durocrete_client.fragments.enginner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.durocrete_client.R;
import com.durocrete_client.activity.MainActivity;
import com.durocrete_client.model.Siteinfo;
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

public class  Fragmentenginnersightkyc extends Fragment {

    private TextView tvClientname;
    private TextView tvSiteName;
    private TextView tvSiteaddress;
    private TextView tvsightinchargename;
    private TextView tvSiteMobileNo;
    private TextView tvSiteEmailId;

    private MainActivity mainActivity;
    private List<Siteinfo> siteinfoarraylist;
    private String selectedsiteid;
    private ArrayAdapter<Siteslist> sitelistadapter;
    MyPreferenceManager sharedPref;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentengineersightkyc, container, false);

        Initview(view);



                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("siteId",sharedPref.getStringPreferences(MyPreferenceManager.Siteid));

                siteinfoarraylist=new ArrayList<>();

                CallWebservice.getWebservice(true, mainActivity, Request.Method.POST, IUrls.get_site_details, hashMap, new VolleyResponseListener<Siteinfo>() {
                    @Override
                    public void onResponse(Siteinfo[] object, String s, String key) {

                        if (object[0] instanceof Siteinfo) {
                            for (Siteinfo testobject : object) {
                                siteinfoarraylist.add(testobject);
                            }

                            tvSiteaddress.setText(siteinfoarraylist.get(0).getSiteaddress());
                            tvSiteEmailId.setText(siteinfoarraylist.get(0).getSite_mail());
                            tvSiteMobileNo.setText(siteinfoarraylist.get(0).getContactno());
                            tvSiteName.setText(siteinfoarraylist.get(0).getSiteName());
                            tvsightinchargename.setText(siteinfoarraylist.get(0).getContactpersonname());
                            tvClientname.setText(siteinfoarraylist.get(0).getClientName());

                        }
                    }

                    @Override
                    public void onError(String message) {
                        Log.v("tag", message.toString());
                        Toast.makeText(mainActivity, "Please Check Internet Connection", Toast.LENGTH_SHORT).show();

                    }
                }, Siteinfo[].class);




                return view;
            }


    private void Initview(View view)
    {
        tvClientname=(TextView)view.findViewById(R.id.tvclientname) ;
        tvSiteName=(TextView)view.findViewById(R.id.tvstsitename);
        tvSiteaddress=(TextView)view.findViewById(R.id.tvstsiteaddress);
        tvsightinchargename=(TextView)view.findViewById(R.id.tvsightinchargename) ;
        tvSiteMobileNo=(TextView)view.findViewById(R.id.tvstMobileno);
        tvSiteEmailId=(TextView)view.findViewById(R.id.tvstsiteemailid);
        mainActivity=(MainActivity)getActivity();
        sharedPref=new MyPreferenceManager(getActivity());

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Site KYC");
    }
}