package com.durocrete_client.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.durocrete_client.R;
import com.durocrete_client.model.Enquirypojo;
import com.durocrete_client.model.Testlist;

import java.util.List;

/**
 * Created by root on 26/5/17.
 */

class Testnewadapters extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Testlist> Alltestlist;
    private List<Enquirypojo> enquirylist;
    Context context;
    String quantities;


    public Testnewadapters(Context context, List<Testlist> testlists, String enquirylist) {
        this.context = context;
        this.Alltestlist = testlists;
        this.quantities = enquirylist;

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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {




        if (convertView == null) {
            convertView = ((LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.testlist_single_row1, null);
        }

        final LinearLayout testlayout=(LinearLayout)convertView.findViewById(R.id.testlayout);
        TextView testname = (TextView) convertView.findViewById(R.id.testname);

//        TextView testquantity = (TextView) convertView.findViewById(R.id.testquantity);

//        ImageView deletebutton=(ImageView)convertView.findViewById(R.id.deletebutton);



        final Testlist testlist = Alltestlist.get(position);

        testname.setText(testlist.getTestName());

        Enquirypojo enquirypojo = new Enquirypojo();
//
//        int tamount=0;
//
//        tamount = Integer.parseInt(testlist.getRate()) * Integer.parseInt(quantities);
//
//        testquantity.setText(testlist.getQuantity());


//        final View finalConvertView = convertView;
//        deletebutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Alltestlist.remove(position);
//                Testnewadapters.this.notifyDataSetChanged();
//              finalConvertView.setVisibility(View.GONE);
//
//
//            }
//        });



        return convertView;
    }

}



