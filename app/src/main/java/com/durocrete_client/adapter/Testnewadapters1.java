package com.durocrete_client.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.durocrete_client.R;
import com.durocrete_client.model.Enquirypojo;
import com.durocrete_client.model.Testlist;

import java.util.List;

/**
 * Created by root on 14/6/17.
 */

class Testnewadapters1 extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Testlist> Allrequesttestlist;
    private List<Enquirypojo> enquirylist;
    Context context;
    String quantities;


    public Testnewadapters1(Context context, List<Testlist> testlists) {
        this.context = context;
        this.Allrequesttestlist = testlists;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {




        if (convertView == null) {
            convertView = ((LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.testlist_single_row1, null);
        }


        TextView testname = (TextView) convertView.findViewById(R.id.testname);

//        TextView testquantity = (TextView) convertView.findViewById(R.id.testquantity);


        final Testlist testlist = Allrequesttestlist.get(position);

        testname.setText(testlist.getTestName());

        Enquirypojo enquirypojo = new Enquirypojo();
//
//        int tamount=0;
//
//        tamount = Integer.parseInt(testlist.getRate()) * Integer.parseInt(quantities);
//
//        testquantity.setText(testlist.getQuantity());


        return convertView;
    }

}

