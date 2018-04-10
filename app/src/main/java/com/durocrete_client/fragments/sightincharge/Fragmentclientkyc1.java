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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.durocrete_client.R;
import com.durocrete_client.activity.MainActivity;
import com.durocrete_client.fragments.clientfinal.Fragmenthome;
import com.durocrete_client.model.Clientinfo;
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

public class Fragmentclientkyc1 extends Fragment {

    private TextView tvclientname;
    private TextView tvOfficeaddress;
    private TextView tvtanNO;
    private TextView tvPanNo;
    private TextView tvOwnerName;
    private TextView tvMobileNo;
    private TextView tvEmailId;
    private TextView  tvAccountcontact;
    private TextView Account_mob;


    private MainActivity mainActivity;
    private List<Clientinfo> clientinfolist;
    private ArrayAdapter<Clientinfo> clientaspersitelistadapter;
    MyPreferenceManager sharedPref;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentclientkyc2, container, false);

        Initview(view);

        String siteid= sharedPref.getStringPreferences(MyPreferenceManager.Siteid);

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("siteId", siteid);


       clientinfolist=new ArrayList<>();

        CallWebservice.getWebservice(true, mainActivity, Request.Method.POST, IUrls.get_client_by_site, hashMap, new VolleyResponseListener<Clientinfo>() {
            @Override
            public void onResponse(Clientinfo[] object, String message, String key) {
//
                if (object[0] instanceof Clientinfo) {
                    for (Clientinfo siteslist : object) {
                    clientinfolist.add(siteslist);
                    }
                }
                tvclientname.setText(clientinfolist.get(0).getClientName());
                tvOfficeaddress.setText(clientinfolist.get(0).getOfficeAddress());
                tvOwnerName.setText(clientinfolist.get(0).getDirectorName());
                tvEmailId.setText(clientinfolist.get(0).getEmailId());
                tvMobileNo.setText(clientinfolist.get(0).getMobileNo());
                tvPanNo.setText(clientinfolist.get(0).getPAN());
                tvtanNO.setText(clientinfolist.get(0).getTAN());
                tvAccountcontact.setText(clientinfolist.get(0).getAc_person());
                Account_mob.setText(clientinfolist.get(0).getAc_contact());
                sharedPref.setStringPreferences(MyPreferenceManager.Clientname,clientinfolist.get(0).getClientName());
            }

            @Override
            public void onError(String message) {
                Log.v("tag", message.toString());

                Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_SHORT).show();
                FragmentTransaction ft =getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new Fragmenthome()).addToBackStack(null);
                ft.commit();

            }
        }, Clientinfo[].class);

        return view;
    }

    private void Initview(View view) {

        tvclientname=(TextView) view.findViewById(R.id.tvclientnames);
        tvOfficeaddress=(TextView)view.findViewById(R.id.tvofficeaddress);
        tvtanNO=(TextView)view.findViewById(R.id.tvtanno);
        tvPanNo=(TextView)view.findViewById(R.id.tvpanno);
        tvOwnerName=(TextView)view.findViewById(R.id.tvclientname);
        tvMobileNo=(TextView)view.findViewById(R.id.tvmobileno);
        tvEmailId=(TextView)view.findViewById(R.id.tvclientemailid);
        tvAccountcontact=(TextView)view.findViewById(R.id.Accountname);
        Account_mob=(TextView)view.findViewById(R.id.Accountcontact);
        mainActivity=(MainActivity)getActivity();
        sharedPref=new MyPreferenceManager(getActivity());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Client KYC");
    }
}
