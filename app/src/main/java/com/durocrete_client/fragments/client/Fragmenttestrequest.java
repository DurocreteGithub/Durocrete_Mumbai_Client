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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.durocrete_client.R;
import com.durocrete_client.activity.CubeTestrequestform;
import com.durocrete_client.activity.MainActivity;
import com.durocrete_client.activity.Testrequestform;
import com.durocrete_client.adapter.Cube_MyRecyclerViewAdapter;
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

public class Fragmenttestrequest extends Fragment {


    private Spinner spGrade;
    private EditText cubetestname;
    private EditText etxcastingdt;
    private EditText nature_of_work;
    private EditText make_supplier;
    private Spinner no_of_sets;
    private Button btnnextsample;
    MainActivity mainActivity;
    MyPreferenceManager sharedPref;
    CubeTestrequestform cubeTestrequestform;
    private List<Materiallist> Allrequestmateriallists;
    private List<Cubeset> Allcubesetlist;
    private ArrayAdapter<Materiallist> requestmateriallistadapter;
    String selectedmaterialId;
    String selectedmaterialname;
    String selectedgradename;
    Cube_MyRecyclerViewAdapter adapter;
    RecyclerView rvAnimals;
    RecyclerView recyclerView;
    String no_of_sets_quantity;
    private ListView lvtestlist;
    ;
    private EditText othergrade;
    LinearLayout linearothergrade;
    private List<Testlist> Alltestlist;
    private Testlistadapter testlistadapter;
    Testrequestform testrequestform;
    private List<CubeTestrequestform> cubeTestrequestformList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentcubetestrequest, container, false);

        Initview(view);

        fetchtestlist(sharedPref.getStringPreferences(MyPreferenceManager.materialId));
        btnnextsample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitnextSample();
            }
        });


        spGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 13) {
                    linearothergrade.setVisibility(View.VISIBLE);
                    selectedgradename = othergrade.getText().toString();
                } else {
                    linearothergrade.setVisibility(View.GONE);
                    selectedgradename = parent.getItemAtPosition(position).toString();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        no_of_sets.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                no_of_sets_quantity = parent.getItemAtPosition(position).toString();
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

                adapter = new Cube_MyRecyclerViewAdapter(getActivity(), position + 1);
                recyclerView.setAdapter(adapter);

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

        int selectedmaterialidvalue = Integer.parseInt(sharedPref.getStringPreferences(MyPreferenceManager.materialId));

        if (selectedmaterialidvalue == 0) {
            Toast.makeText(mainActivity, "Please Select Material First", Toast.LENGTH_SHORT).show();

        } else {

            cubeTestrequestform = new CubeTestrequestform();

            ArrayList<Cubeset> selectedItems1 = new ArrayList<Cubeset>(adapter.Allcubelist());

            ArrayList<Testlist> selectedItems = new ArrayList<Testlist>();
            if (adapter.Allcubelist().size() != 0) {
                for (int i = 0; i < Alltestlist.size(); i++) {
                    if (testlistadapter.Alltestlist().get(i).ischecked(true)) {
                        selectedItems.add(testlistadapter.Alltestlist().get(i));

                    }
                }
            }


            if (valid(selectedItems1) == 0) {
                no_of_sets.setSelection(0);
                adapter.Allcubelist().clear();
                adapter.initialiselist();

                cubeTestrequestform.setMaterial_id(sharedPref.getStringPreferences(MyPreferenceManager.materialId));
                cubeTestrequestform.setEnquiry_id(sharedPref.getStringPreferences(MyPreferenceManager.enquiryId));
                cubeTestrequestform.setMaterial(selectedmaterialname);
                cubeTestrequestform.setTests(selectedItems);
                cubeTestrequestform.setGrade(selectedgradename);
                cubeTestrequestform.setCasting_dt(etxcastingdt.getText().toString().trim());
                cubeTestrequestform.setNature_of_work(nature_of_work.getText().toString());
                cubeTestrequestform.setNo_of_sets(no_of_sets_quantity);
                cubeTestrequestform.setSupplier(make_supplier.getText().toString());
                cubeTestrequestform.setSets(selectedItems1);

                cubeTestrequestformList.add(cubeTestrequestform);

                testrequestform = new Testrequestform();
                testrequestform.setCubeTestrequestformList(cubeTestrequestformList);

                getMyActivity().getTestrequestformList().add(testrequestform);

                Toast.makeText(mainActivity, "Successfully added Sample ", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mainActivity, "Please Fill information of Set" + valid(selectedItems1), Toast.LENGTH_SHORT).show();
            }
        }


    }

    private int valid(ArrayList<Cubeset> selectedItems1) {
        int val = 0;
        for (int i = 0; i < selectedItems1.size(); i++) {
            if (selectedItems1.get(i).getISsfilled() == false) {
                val = i + 1;
                break;
            }
        }
        return val;
    }


    private void Initview(View view) {

        spGrade = (Spinner) view.findViewById(R.id.spselectgrade);
        othergrade = (EditText) view.findViewById(R.id.etxothergrade);
        btnnextsample = (Button) view.findViewById(R.id.btnnextsample);
        mainActivity = (MainActivity) getActivity();
        sharedPref = new MyPreferenceManager(getActivity());
        lvtestlist = (ListView) view.findViewById(R.id.lvmaterialtestlist);
        etxcastingdt = (EditText) view.findViewById(R.id.etxcastingdt);
        nature_of_work = (EditText) view.findViewById(R.id.etxnatureofwork);
        make_supplier = (EditText) view.findViewById(R.id.etxmake);
        no_of_sets = (Spinner) view.findViewById(R.id.spNoofsets);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvAnimals);
        linearothergrade = (LinearLayout) view.findViewById(R.id.linearothergrade);

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
