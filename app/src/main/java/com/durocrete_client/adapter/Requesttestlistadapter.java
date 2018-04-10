package com.durocrete_client.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.durocrete_client.R;
import com.durocrete_client.model.Testlist;

import java.util.List;

/**
 * Created by root on 14/6/17.
 */

public class Requesttestlistadapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Testlist> Allrequesttestlist;

    public Requesttestlistadapter(FragmentActivity activity, List<Testlist> Allrequesttestlists) {
        this.activity = activity;
        this.Allrequesttestlist = Allrequesttestlists;
    }


    @Override
    public int getCount() {
        return Allrequesttestlist.size();
    }

    @Override
    public Object getItem(int position) {
        return Allrequesttestlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<Testlist> Alltestlist() {
        return this.Allrequesttestlist;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.testlist_single_row2, null);
        }
        TextView testname = (TextView) convertView.findViewById(R.id.testname);
//            TextView testrate = (TextView) convertView.findViewById(R.id.testrate);
        final CheckBox selectcheckbox = (CheckBox) convertView.findViewById(R.id.checkbox);
        Spinner quantity = (Spinner) convertView.findViewById(R.id.spquantity);

        if (position == 0) {
            selectcheckbox.setVisibility(View.INVISIBLE);
        }
        else
        {
            selectcheckbox.setVisibility(View.VISIBLE);
        }


        final Testlist testlist = Allrequesttestlist.get(position);


//        quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position == 0) {
//                    Toast.makeText(activity, "plz select position", Toast.LENGTH_SHORT).show();
//                } else {
//                    String selectedquantity = parent.getItemAtPosition(position).toString();
//                    testlist.setQuantity(selectedquantity);
//                    Log.d("qu", selectedquantity);
//                }
//            }

//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


        testname.setText(testlist.getTestName());
        selectcheckbox.setChecked(testlist.getIsChecked());
//        testrate.setText(testlist.getRate());

        selectcheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectcheckbox.isChecked()) {
                    testlist.setIschecked(true);
                } else {
                    testlist.setIschecked(false);
                }


            }
        });

        return convertView;
    }


}


