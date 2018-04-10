package com.durocrete_client.enquiry;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.durocrete_client.R;
import com.durocrete_client.activity.MainActivity;
import com.durocrete_client.activity.Mixdesigntestrequestform;
import com.durocrete_client.activity.Testrequestform;
import com.durocrete_client.adapter.Mix_MyRecyclerViewAdapter;
import com.durocrete_client.adapter.Mixdesignviewadapter;
import com.durocrete_client.adapter.Testlistadapter1;
import com.durocrete_client.fragments.client.Mixset;
import com.durocrete_client.fragments.client.Otherset;
import com.durocrete_client.model.Enquiryform;
import com.durocrete_client.model.Materiallist;
import com.durocrete_client.model.Mixmateriallist;
import com.durocrete_client.model.Siteslist;
import com.durocrete_client.network.CallWebservice;
import com.durocrete_client.network.IUrls;
import com.durocrete_client.network.VolleyResponseListener;
import com.durocrete_client.utils.MyPreferenceManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by root on 1/7/17.
 */

public class Fragmentmixdesignrequest extends Fragment {

    private List<Mixmateriallist> Allrequestmateriallists;
    private ArrayAdapter<Materiallist> requestmateriallistadapter;
    MyPreferenceManager Sharedpref;
    MainActivity mainActivity;
    private Testlistadapter1 testlistadapter1;
    private RecyclerView materiallistview;
    private TextView Clientname;
    private TextView sitename;
    private EditText contact_name;
    private EditText contact_number;
    private EditText email_id;
    private Button sendqoutation;
    private Button btnplaceorder;
    private List<Materiallist> Allmateriallist;
    ArrayList<Enquiryform> enquirylist;
    private List<Siteslist> Allsitelist;
    private ArrayAdapter<Siteslist> sitelistadapter;
    private Spinner spmaterialtype;
    private Long Siteselction;
    private String selectedsitename;
    private Mixdesignviewadapter mixdesignadapter;
    private Mixdesigntestrequestform mixdesigntestrequestform;
    Mix_MyRecyclerViewAdapter mix_myRecyclerViewAdapter;
    Testrequestform testrequestform;
    RecyclerView recyclematerialtype;
    private List<Mixdesigntestrequestform> mixrequestformlist = new ArrayList<>();;




    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newfragmentenquiry1, container, false);

        Initview(view);
        FetchMateriallist();


        recyclematerialtype.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mix_myRecyclerViewAdapter = new Mix_MyRecyclerViewAdapter(getActivity(), 1);
        recyclematerialtype.setAdapter(mix_myRecyclerViewAdapter);


        btnplaceorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeorder_senddata();
            }
        });

        return view;
    }

    private void placeorder_senddata() {

        ArrayList<Mixset> selectedItems = new ArrayList<Mixset>(mix_myRecyclerViewAdapter.Allmixmateriallist());

        ArrayList<Mixmateriallist> selectedItems1 = new ArrayList<Mixmateriallist>();
        for (int i = 0; i <Allrequestmateriallists.size(); i++) {
                if (mixdesignadapter.Alltestlist().get(i).ischecked(true)) {
                    selectedItems1.add(mixdesignadapter.Alltestlist().get(i));
                }
        }

        mixdesigntestrequestform = new Mixdesigntestrequestform();
        mixdesigntestrequestform.setSets(selectedItems);
        mixdesigntestrequestform.setTests(selectedItems1);
        mixdesigntestrequestform.setEnquiry_id(Sharedpref.getStringPreferences(MyPreferenceManager.enquiryId));

        mixrequestformlist.add(mixdesigntestrequestform);

        testrequestform = new Testrequestform();
        testrequestform.setMixdesigntestrequestformList(mixrequestformlist);

        getMyActivity().getTestrequestformList().add(testrequestform);

        Toast.makeText(mainActivity, "Successfully added Sample ", Toast.LENGTH_SHORT).show();

    }

    private int valid(ArrayList<Otherset> selectedItems) {
        int val = 0;
        for (int i = 0; i < selectedItems.size(); i++) {
            if (selectedItems.get(i).getIsfilled() == false) {
                val = i + 1;
                break;
            }
        }
        return val;
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


        CallWebservice.getWebservice(true, mainActivity, Request.Method.POST, IUrls.Mixmaterial_list, hashMap, new VolleyResponseListener<Mixmateriallist>() {
            @Override
            public void onResponse(Mixmateriallist[] object, String s, String key) {

                if (object[0] instanceof Mixmateriallist) {
                    for (Mixmateriallist materialobject : object) {
                        Allrequestmateriallists.add(materialobject);
                    }
                }


                materiallistview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                mixdesignadapter = new Mixdesignviewadapter(getActivity(), Allrequestmateriallists);
                materiallistview.setAdapter(mixdesignadapter);


            }

            @Override
            public void onError(String message) {
                Log.v("tag", message.toString());

            }
        }, Mixmateriallist[].class);
    }


    private void Initview(View view) {

        Sharedpref = new MyPreferenceManager(getActivity());
        mainActivity = (MainActivity) getActivity();
        sendqoutation = (Button) view.findViewById(R.id.btngetqoute);
        contact_name = (EditText) view.findViewById(R.id.etxcontactname);
        contact_number = (EditText) view.findViewById(R.id.etxcontactnumber);
        email_id = (EditText) view.findViewById(R.id.etxemailid);
        materiallistview = (RecyclerView) view.findViewById(R.id.lvmaterialtestlist);
        recyclematerialtype = (RecyclerView) view.findViewById(R.id.recyclematerialtype);
        mixdesigntestrequestform = new Mixdesigntestrequestform();
        enquirylist = new ArrayList<>();
        btnplaceorder = (Button) view.findViewById(R.id.btnplaceorder);

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
