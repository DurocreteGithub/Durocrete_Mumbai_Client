package com.durocrete_client.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.durocrete_client.R;
import com.durocrete_client.model.Materiallist;

import java.util.List;

/**
 * Created by root on 26/5/17.
 */

public class Testlistadapter1 extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Materiallist> Alltestlist;


    public Testlistadapter1(FragmentActivity activity, List<Materiallist> alltestlist) {
        this.activity = activity;
        this.Alltestlist = alltestlist;
    }


    @Override
    public int getCount() {
        return Alltestlist.size();
    }

    @Override
    public Object getItem(int position) {
        return Alltestlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<Materiallist> Alltestlist() {
        return this.Alltestlist;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.testlist_single_row3, null);
        }
            TextView testname = (TextView) convertView.findViewById(R.id.testname);
//            TextView testrate = (TextView) convertView.findViewById(R.id.testrate);
            final CheckBox selectcheckbox = (CheckBox) convertView.findViewById(R.id.checkbox);
            final EditText quantity = (EditText) convertView.findViewById(R.id.spquantity);
           TextView quantityname=(TextView)convertView.findViewById(R.id.quantityname);


        if(position==0)
        {
            selectcheckbox.setVisibility(View.GONE);
            quantity.setVisibility(View.GONE);
            quantityname.setVisibility(View.VISIBLE);
        }
        else
        {
            selectcheckbox.setVisibility(View.VISIBLE);
            quantity.setVisibility(View.VISIBLE);
            quantityname.setVisibility(View.GONE);
        }




        final Materiallist testlist = Alltestlist.get(position);





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
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

//        quantity.setText(quantity.getText().toString());



        testname.setText(testlist.getMaterialName());


        selectcheckbox.setChecked(testlist.getIsChecked());

        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                    testlist.setQuantity("" +s);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        if(testlist.getQuantity()==null)
        {
            testlist.setQuantity(""+1);
        }




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

//

        return convertView;
    }


}


