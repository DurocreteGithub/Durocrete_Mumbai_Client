package com.durocrete_client.enquiry;

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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.durocrete_client.R;
import com.durocrete_client.activity.MainActivity;
import com.durocrete_client.adapter.Testlistadapter1;
import com.durocrete_client.fragments.client.Fragmenttestrequest1;
import com.durocrete_client.model.Enquiryform;
import com.durocrete_client.model.Login;
import com.durocrete_client.model.Materiallist;
import com.durocrete_client.model.Siteslist;
import com.durocrete_client.network.CallWebservice;
import com.durocrete_client.network.IUrls;
import com.durocrete_client.network.VolleyResponseListener;
import com.durocrete_client.utils.ListviewheightClass;
import com.durocrete_client.utils.MyPreferenceManager;
import com.durocrete_client.utils.Utility;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by root on 1/7/17.
 */

public class Fragmentenquiry extends Fragment {

    private List<Materiallist> Allrequestmateriallists;
    private ArrayAdapter<Materiallist> requestmateriallistadapter;
    MyPreferenceManager Sharedpref;
    MainActivity mainActivity;
    private Testlistadapter1 testlistadapter1;
    private ListView materiallistview;
    private TextView Clientname;
    private TextView sitename;
    private EditText contact_name;
    private EditText contact_number;
    private EditText email_id;
    private Button sendqoutation;
    private Button btnplaceorder;
    private List<Materiallist> Allmateriallist;
    Enquiryform enquiryform;
    ArrayList<Enquiryform> enquirylist;
    private List<Siteslist> Allsitelist;
    private ArrayAdapter<Siteslist> sitelistadapter;
    private Spinner spAllsiteslist;
    private Long Siteselction;
    private String selectedsitename;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newfragmentenquiry, container, false);

        Initview(view);

        getdata();

        spAllsiteslist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    Siteselction = parent.getItemIdAtPosition(position);
                }
                else
                {
                    Siteselction = parent.getItemIdAtPosition(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        sendqoutation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senddata();

            }
        });

        btnplaceorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeorder_senddata();
            }
        });


        return view;
    }

    private void placeorder_senddata() {

        ArrayList<Materiallist> selectedItems = new ArrayList<Materiallist>();
        for (int i = 0; i < Allrequestmateriallists.size(); i++) {
            if (testlistadapter1.Alltestlist().get(i).ischecked(true)) {
                selectedItems.add(testlistadapter1.Alltestlist().get(i));

            }
        }

        if (valid(selectedItems)) {
            enquiryform.setSite_id(String.valueOf(Siteselction));
            enquiryform.setTests(selectedItems);
            enquiryform.setContact_name(contact_name.getText().toString());
            enquiryform.setContact_number(contact_number.getText().toString());
            enquiryform.setEmail_id(email_id.getText().toString());
            enquiryform.setEnquiryflag(false);

            enquirylist.add(enquiryform);

            Gson gson = new Gson();
            JsonElement element = gson.toJsonTree(enquirylist, new TypeToken<List<Enquiryform>>() {
            }.getType());
            if (!element.isJsonArray()) {

            }
            JsonArray jsonArray = element.getAsJsonArray();
            Log.d("tag1", jsonArray.toString());

            if (jsonArray != null) {
                HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put("data", String.valueOf(jsonArray));

                CallWebservice.getWebservice(true, mainActivity, Request.Method.POST, IUrls.Enquiry_Submit, hashMap, new VolleyResponseListener<Login>() {
                    @Override
                    public void onResponse(Login[] object, String s, String key) {

                        Sharedpref.setStringPreferences(MyPreferenceManager.enquiryId, s);
                        Fragmenttestrequest1 fragmenthome = new Fragmenttestrequest1();
                        getFragmentManager().beginTransaction().replace(R.id.content_frame, fragmenthome).addToBackStack(null).commit();

                    }

                    @Override
                    public void onError(String message) {
                        Log.v("tag", message.toString());

                    }
                }, Login[].class);
            }
        } else {

        }

    }

    private void getdata() {

        String ClientID = Sharedpref.getStringPreferences(MyPreferenceManager.Clientid);

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
                spAllsiteslist.setAdapter(sitelistadapter);
                FetchMateriallist();

            }

            @Override
            public void onError(String message) {
                Log.v("tag", message.toString());


            }
        }, Siteslist[].class);


    }

    private void senddata() {


        ArrayList<Materiallist> selectedItems = new ArrayList<Materiallist>();
        for (int i = 0; i < Allrequestmateriallists.size(); i++) {
            if (testlistadapter1.Alltestlist().get(i).ischecked(true)) {
                selectedItems.add(testlistadapter1.Alltestlist().get(i));

            }
        }

        if (valid(selectedItems)) {
            enquiryform.setSite_id(String.valueOf(Siteselction));
            enquiryform.setTests(selectedItems);
            enquiryform.setContact_name(contact_name.getText().toString());
            enquiryform.setContact_number(contact_number.getText().toString());
            enquiryform.setEmail_id(email_id.getText().toString());
            enquiryform.setEnquiryflag(true);

            enquirylist.add(enquiryform);

            Gson gson = new Gson();
            JsonElement element = gson.toJsonTree(enquirylist, new TypeToken<List<Enquiryform>>() {
            }.getType());
            if (!element.isJsonArray()) {

            }
            JsonArray jsonArray = element.getAsJsonArray();
            Log.d("tag1", jsonArray.toString());

            if (jsonArray != null) {
                HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put("data", String.valueOf(jsonArray));

                CallWebservice.getWebservice(true, mainActivity, Request.Method.POST, IUrls.Enquiry_Submit, hashMap, new VolleyResponseListener<Login>() {
                    @Override
                    public void onResponse(Login[] object, String s, String key) {
                        Utility.hideSoftKeyboard(getActivity());
                        sendingdetails();

                    }

                    @Override
                    public void onError(String message) {
                        Log.v("tag", message.toString());

                    }
                }, Login[].class);
            }
        } else {

        }

    }

    private void sendingdetails() {

        ArrayList<Materiallist> selectedItems = new ArrayList<Materiallist>();
        for (int i = 0; i < Allrequestmateriallists.size(); i++) {
            if (testlistadapter1.Alltestlist().get(i).ischecked(true)) {
                selectedItems.add(testlistadapter1.Alltestlist().get(i));

            }
        }

        if (valid(selectedItems)) {
            enquiryform.setSite_id(String.valueOf(Siteselction));
            enquiryform.setTests(selectedItems);
            enquiryform.setContact_name(contact_name.getText().toString());
            enquiryform.setContact_number(contact_number.getText().toString());
            enquiryform.setEmail_id(email_id.getText().toString());
            enquiryform.setEnquiryflag(true);

            enquirylist.add(enquiryform);

            Gson gson = new Gson();
            JsonElement element = gson.toJsonTree(enquirylist, new TypeToken<List<Enquiryform>>() {
            }.getType());
            if (!element.isJsonArray()) {

            }
            JsonArray jsonArray = element.getAsJsonArray();
            Log.d("tag1", jsonArray.toString());

            if (jsonArray != null) {
                HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put("data", String.valueOf(jsonArray));

                CallWebservice.getWebservice(true, mainActivity, Request.Method.POST, IUrls.Test_Qoutation, hashMap, new VolleyResponseListener<Login>() {
                    @Override
                    public void onResponse(Login[] object, String s, String key) {
                        Utility.hideSoftKeyboard(getActivity());

                        Toast.makeText(getActivity(), "Thank You for Enquiry.You will get quotation on Email ID.", Toast.LENGTH_LONG).show();
                        getActivity().getSupportFragmentManager().popBackStack();

                    }

                    @Override
                    public void onError(String message) {
                        Log.v("tag", message.toString());

                    }
                }, Login[].class);
            }
        } else {

        }

    }

    private boolean valid(ArrayList<Materiallist> selectedItems) {


        Log.d("tag2", String.valueOf(Siteselction));
        if (Siteselction == 0) {
            Toast.makeText(mainActivity, "Please Select Site First", Toast.LENGTH_SHORT).show();
            return false;

        } else if (selectedItems.size() == 0) {
            Toast.makeText(mainActivity, "Please Select Test", Toast.LENGTH_SHORT).show();
            return false;
        } else if (contact_name.getText().toString().trim().length() == 0) {
            Toast.makeText(mainActivity, "Please enter the Contact Name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (contact_name.getText().toString().trim().length() == 0) {
            Toast.makeText(mainActivity, "Please enter the Contact Name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (contact_number.getText().toString().trim().length() == 0) {
            Toast.makeText(mainActivity, "Please enter the Contact Number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (contact_number.getText().length() != 0 && contact_number.getText().toString().trim().length() < 5) {
            Toast.makeText(mainActivity, "Invalid Contact Number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (email_id.getText().toString().trim().length() == 0) {
            Toast.makeText(mainActivity, "Please Enter the Email Id", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!(email_id.getText().toString().trim().isEmpty()) && !(loop(email_id.getText().toString().trim()))) {
            Toast.makeText(mainActivity, "Invalid Email ID", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

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

    public boolean validEmail(String str_newEmail) {

        return str_newEmail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }

    private void FetchMateriallist() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("", "");

        Allrequestmateriallists = new ArrayList<>();


        CallWebservice.getWebservice(true, mainActivity, Request.Method.POST, IUrls.GET_Material, hashMap, new VolleyResponseListener<Materiallist>() {
            @Override
            public void onResponse(Materiallist[] object, String s, String key) {

                if (object[0] instanceof Materiallist) {
                    for (Materiallist materialobject : object) {
                        Allrequestmateriallists.add(materialobject);
                    }
                }

                testlistadapter1 = new Testlistadapter1(getActivity(), Allrequestmateriallists);
                materiallistview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                materiallistview.setAdapter(testlistadapter1);
                ListviewheightClass.setListViewHeightBasedOnChildrenDrawer(materiallistview);

            }

            @Override
            public void onError(String message) {
                Log.v("tag", message.toString());

            }
        }, Materiallist[].class);
    }


    private void Initview(View view) {
        Sharedpref = new MyPreferenceManager(getActivity());
        mainActivity = (MainActivity) getActivity();
        sendqoutation = (Button) view.findViewById(R.id.btngetqoute);
//        Clientname = (TextView) view.findViewById(R.id.tvclientname);
//        sitename = (TextView) view.findViewById(R.id.tvsitename);
        contact_name = (EditText) view.findViewById(R.id.etxcontactname);
        contact_number = (EditText) view.findViewById(R.id.etxcontactnumber);
        email_id = (EditText) view.findViewById(R.id.etxemailid);
        materiallistview = (ListView) view.findViewById(R.id.lvmaterialtestlist);
        enquiryform = new Enquiryform();
        enquirylist = new ArrayList<>();
        btnplaceorder = (Button) view.findViewById(R.id.btnplaceorder);
        spAllsiteslist = (Spinner) view.findViewById(R.id.spAllsiteslist);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Enquiry");
    }


    public MainActivity getMyActivity() {
        return (MainActivity) getActivity();
    }
}
