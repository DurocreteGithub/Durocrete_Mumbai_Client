package com.durocrete_client.fragments;

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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.durocrete_client.R;
import com.durocrete_client.activity.MainActivity;
import com.durocrete_client.adapter.Enquiry_id_materialAdapter;
import com.durocrete_client.adapter.Enquiryiddetailslistadapter;
import com.durocrete_client.adapter.Testlistadapter;
import com.durocrete_client.fragments.client.Fragmenttestrequest1;
import com.durocrete_client.model.Enquiryid;
import com.durocrete_client.model.Enquiryiddetails;
import com.durocrete_client.model.Enquirypojo;
import com.durocrete_client.model.Materiallist;
import com.durocrete_client.model.Siteslist;
import com.durocrete_client.model.Testlist;
import com.durocrete_client.network.CallWebservice;
import com.durocrete_client.network.IUrls;
import com.durocrete_client.network.VolleyResponseListener;
import com.durocrete_client.utils.MyPreferenceManager;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by root on 19/5/17.
 */

public class Fragmentplaceorder extends Fragment {

    private Button btnenquirysubmit;
    private TextView siteselection;
    private Spinner spenquirylist;
    private Spinner spquantity;
    private ListView lvtestlist;
    private MainActivity mainActivity;
    private String stselectedmaterial;
    private String stselectedquantity;
    private Enquirypojo enquirypojo;
    private List<Materiallist> Allmateriallist;
    private List<Testlist> Alltestlist;
    private Testlistadapter testlistadapter;
    private ArrayAdapter<Materiallist> materiallistadapter;
    private String stselectedmaterialid;
    private long  selectedsiteid;
    private ArrayAdapter<Siteslist> sitelistadapter;
    private List<Siteslist> Allsitelist;
    private JSONArray jsonArray1;
    MyPreferenceManager sharedPref;
    private String ClientID;
    private List<Enquiryid> Enquiryidlist;
    private ArrayAdapter<Enquiryid> Enquiryidlistadapter;
    private List<Enquiryiddetails> Enquiryiddetailslist;
    private Enquiryiddetailslistadapter enquiryiddetailslistadapter;
    private TextView Materialname;
    private TextView materialquantity;
    private TextView Contactname;
    private TextView Mobileno;
    private TextView EmailId;
    private String enquiryididposition;
    private long enquiryid;
    private LinearLayout linearenquiryid;
    private LinearLayout linearemail;
    private LinearLayout linearmaterial;
    private LinearLayout linearquantity;
    private LinearLayout linearcontactname;
    private LinearLayout linearmobile;
    TextView noenquiry;
    private Enquiry_id_materialAdapter enquiry_id_materialAdapter;
    private RecyclerView reportsummaryrecycler;
    private TextView nodata;
    private Spinner allsitesofclient;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmentplaceorder1, container, false);

        Initview(view);

        getsites();

        allsitesofclient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    selectedsiteid=parent.getItemIdAtPosition(position);
                    getenquiryId(parent.getItemIdAtPosition(position));
                }
                else
                {
                    selectedsiteid=parent.getItemIdAtPosition(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spenquirylist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position != 0) {
                    enquiryid=parent.getItemIdAtPosition(position);
                    Enquiryid enquiryidlist = (Enquiryid) parent.getItemAtPosition(position);
                    enquiryididposition = enquiryidlist.getEnqId();
                    getenquirydetails(enquiryididposition);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnenquirysubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valid()) {
                    sharedPref.setStringPreferences(MyPreferenceManager.enquiryId, enquiryididposition);
                    sharedPref.setIntPreferences(MyPreferenceManager.materialId, Enquiryiddetailslist.get(0).getMaterial_id());
                    Fragmenttestrequest1 getqoutefragment = new Fragmenttestrequest1();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, getqoutefragment).addToBackStack(null).commit();
                }
            }
        });

        return view;
    }


        private boolean valid() {

            if (selectedsiteid==0) {
                Toast.makeText(mainActivity, "Please Select Site First", Toast.LENGTH_SHORT).show();
                return false;
            }
            else if (enquiryid == 0) {
                Toast.makeText(mainActivity, "Please Select Enquiry Id", Toast.LENGTH_SHORT).show();
                return false;
            }

            return true;

        }




    private void getsites() {

        String ClientID = sharedPref.getStringPreferences(MyPreferenceManager.Clientid);

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("clId", ClientID);
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
                allsitesofclient.setAdapter(sitelistadapter);

            }

            @Override
            public void onError(String message) {
                Log.v("tag", message.toString());


            }
        }, Siteslist[].class);

    }

    private void getenquirydetails(String enquiryididposition) {


        if (enquiryididposition != null) {

            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("enqid", String.valueOf(enquiryididposition));

            Enquiryiddetailslist = new ArrayList<>();

            CallWebservice.getWebservice(true, mainActivity, Request.Method.POST, IUrls.get_enquiry_material_test, hashMap, new VolleyResponseListener<Enquiryiddetails>() {
                @Override
                public void onResponse(Enquiryiddetails[] object, String s, String key) {

                    if (object[0] instanceof Enquiryiddetails) {
                        for (Enquiryiddetails testobject : object) {
                            Enquiryiddetailslist.add(testobject);
                        }

                        enquiry_id_materialAdapter = new Enquiry_id_materialAdapter(getActivity(), Enquiryiddetailslist);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                        reportsummaryrecycler.setLayoutManager(mLayoutManager);
                        reportsummaryrecycler.setItemAnimator(new DefaultItemAnimator());
                        reportsummaryrecycler.setAdapter(enquiry_id_materialAdapter);
                        nodata.setVisibility(View.GONE);
                    } else {
                        reportsummaryrecycler.setVisibility(View.GONE);
                        nodata.setVisibility(View.VISIBLE);
                        nodata.setText("No Enquiry Available.");
                    }


                }

                @Override
                public void onError(String message) {
                    Log.v("tag", message.toString());

                }
            }, Enquiryiddetails[].class);
        }
    }


    private void getenquiryId(long itemIdAtPosition) {


        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Site_Id", String.valueOf(itemIdAtPosition));
        Enquiryidlist = new ArrayList<>();

        CallWebservice.getWebservice(true, mainActivity, Request.Method.POST, IUrls.Enquiry_by_site, hashMap, new VolleyResponseListener<Enquiryid>() {
            @Override
            public void onResponse(Enquiryid[] object, String s, String key) {

                if (object[0] instanceof Enquiryid) {
                    for (Enquiryid testobject : object) {
                        Enquiryidlist.add(testobject);
                    }

                    if (s.equalsIgnoreCase("0")) {
                        noenquiry.setVisibility(View.VISIBLE);
                        linearenquiryid.setVisibility(View.GONE);
                        btnenquirysubmit.setVisibility(View.GONE);
                    } else {
                        linearenquiryid.setVisibility(View.VISIBLE);
                        btnenquirysubmit.setVisibility(View.VISIBLE);
                        noenquiry.setVisibility(View.GONE);
                        Enquiryidlistadapter = new ArrayAdapter<Enquiryid>(getActivity(),
                                android.R.layout.simple_list_item_1, Enquiryidlist);
                        spenquirylist.setAdapter(Enquiryidlistadapter);
                    }
                }
            }

            @Override
            public void onError(String message) {
                Log.v("tag", message.toString());

            }
        }, Enquiryid[].class);

    }


    private void Initview(View view) {

        lvtestlist = (ListView) view.findViewById(R.id.lvmaterialtestlist);
        btnenquirysubmit = (Button) view.findViewById(R.id.btnsubmitenquiry);
        spenquirylist = (Spinner) view.findViewById(R.id.spmaterialselection);
        mainActivity = (MainActivity) getActivity();
        enquirypojo = new Enquirypojo();
        siteselection = (TextView) view.findViewById(R.id.tvsiteselect);
        sharedPref = new MyPreferenceManager(getActivity());
        reportsummaryrecycler = (RecyclerView) view.findViewById(R.id.reportsummaryrecycler);
        noenquiry = (TextView) view.findViewById(R.id.noenquiry);
        nodata = (TextView) view.findViewById(R.id.nodata);
        allsitesofclient = (Spinner) view.findViewById(R.id.allsitesofclient);
        linearenquiryid=(LinearLayout)view.findViewById(R.id.linearenquiryid);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Place Order");
    }


    public MainActivity getMyActivity() {
        return (MainActivity) getActivity();
    }

}
