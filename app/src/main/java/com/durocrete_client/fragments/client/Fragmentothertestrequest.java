package com.durocrete_client.fragments.client;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.durocrete_client.R;
import com.durocrete_client.activity.MainActivity;
import com.durocrete_client.activity.OtherTestrequestform;
import com.durocrete_client.activity.Testrequestform;
import com.durocrete_client.adapter.Other_MyRecyclerViewAdapter;
import com.durocrete_client.adapter.Testlistadapter;
import com.durocrete_client.model.Materiallist;
import com.durocrete_client.model.Testlist;
import com.durocrete_client.network.CallWebservice;
import com.durocrete_client.network.IUrls;
import com.durocrete_client.network.VolleyResponseListener;
import com.durocrete_client.utils.ListviewheightClass;
import com.durocrete_client.utils.MyPreferenceManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by root on 19/5/17.
 */

public class Fragmentothertestrequest extends Fragment {


    private Button btnnextsample;
    private Button btnSumbitSample;
    MainActivity mainActivity;
    MyPreferenceManager sharedPref;
    OtherTestrequestform otherTestrequestform;
    private List<Materiallist> Allrequestmateriallists;
    private List<Cubeset> Allcubesetlist;
    private ArrayAdapter<Materiallist> requestmateriallistadapter;
    String selectedmaterialId;
    String selectedmaterialname;
    String selectedgradename;
    Other_MyRecyclerViewAdapter adapter1;
    RecyclerView rvAnimals;
    private Testlistadapter testlistadapter;
    private List<Testlist> Alltestlist;
    private ListView lvtestlist;
    List<OtherTestrequestform> othertestrequestformlist = new ArrayList<>();
    RecyclerView recyclerView;
    private Spinner no_of_sets;
    String no_of_sets_quantity;
    Testrequestform testrequestform;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentothertestrequest, container, false);

        Initview(view);

        fetchtestlist(sharedPref.getStringPreferences(MyPreferenceManager.materialId));


        btnnextsample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitnextSample();
            }
        });


        no_of_sets.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                no_of_sets_quantity = parent.getItemAtPosition(position).toString();
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                adapter1 = new Other_MyRecyclerViewAdapter(getActivity(), position + 1);
                recyclerView.setAdapter(adapter1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

    private void fetchtestlist(String selectedmaterialId) {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("materialId", selectedmaterialId);

        Alltestlist = new ArrayList<Testlist>();

        CallWebservice.getWebservice(true, mainActivity, Request.Method.POST, IUrls.GET_Tests, hashMap, new VolleyResponseListener<Testlist>() {
            @Override
            public void onResponse(Testlist[] object, String s, String key) {

                if (object[0] instanceof Testlist) {
                    for (Testlist testobject : object) {
                        Alltestlist.add(testobject);
                    }
                }

//                        testlistadapter = new ArrayAdapter<Testlist>(getActivity(),
//                                android.R.layout.simple_list_item_multiple_choice, Alltestlist);

                testlistadapter = new Testlistadapter(getActivity(), Alltestlist);
                lvtestlist.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                lvtestlist.setAdapter(testlistadapter);
                ListviewheightClass.setListViewHeightBasedOnChildrenDrawer(lvtestlist);

            }

            @Override
            public void onError(String message) {
                Log.v("tag", message.toString());

            }
        }, Testlist[].class);


    }


    private void submitnextSample() {

        int selectedmaterialidvalue= Integer.parseInt(sharedPref.getStringPreferences(MyPreferenceManager.materialId));

        if (selectedmaterialidvalue==0) {
            Toast.makeText(mainActivity, "Please Select Material First", Toast.LENGTH_SHORT).show();

        } else {
            otherTestrequestform = new OtherTestrequestform();
            testrequestform = new Testrequestform();

            ArrayList<Otherset> selectedItems = new ArrayList<Otherset>(adapter1.Allotherlist());


            ArrayList<Testlist> selectedItems1 = new ArrayList<Testlist>();
            if (adapter1.Allotherlist().size() != 0) {
                for (int i = 0; i < Alltestlist.size(); i++) {
                    if (testlistadapter.Alltestlist().get(i).ischecked(true)) {
                        selectedItems1.add(testlistadapter.Alltestlist().get(i));

                    }
                }
            }

            if (valid(selectedItems) == 0) {

                no_of_sets.setSelection(0);
                adapter1.Allotherlist().clear();
                adapter1.initialiselist();

                otherTestrequestform.setMaterial_id(sharedPref.getStringPreferences(MyPreferenceManager.materialId));
                otherTestrequestform.setNo_of_sets(no_of_sets_quantity);
                otherTestrequestform.setSets(selectedItems);
                otherTestrequestform.setTests(selectedItems1);
                otherTestrequestform.setEnquiry_id(sharedPref.getStringPreferences(MyPreferenceManager.enquiryId));


                othertestrequestformlist.add(otherTestrequestform);
                testrequestform = new Testrequestform();

                testrequestform.setOtherTestrequestformList(othertestrequestformlist);

                getMyActivity().getTestrequestformList().add(testrequestform);

                Toast.makeText(mainActivity, "Successfully added Sample ", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mainActivity, "Please Fill information of Set " + valid(selectedItems), Toast.LENGTH_SHORT).show();

            }
        }

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


    private void Initview(View view) {


        btnnextsample = (Button) view.findViewById(R.id.btnnextsample);
        mainActivity = (MainActivity) getActivity();
        sharedPref = new MyPreferenceManager(getActivity());
        lvtestlist = (ListView) view.findViewById(R.id.lvmaterialtestlist);
        no_of_sets = (Spinner) view.findViewById(R.id.cubetestname);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvAnimals);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Test Request Form");
    }

    public MainActivity getMyActivity() {
        return (MainActivity) getActivity();
    }
}
