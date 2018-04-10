package com.durocrete_client.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.durocrete_client.R;
import com.durocrete_client.model.Enquirypojo;
import com.durocrete_client.model.Testlist;
import com.durocrete_client.utils.ListviewheightClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 19/5/17.
 */

public class Enquiryadapter extends RecyclerView.Adapter<Enquiryadapter.MyViewHolder> {

    private List<Enquirypojo> enquirylist;
    private Context context;
    private int Alltotal = 0;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvmaterialname;
        private TextView tvqty;
        private TextView tvTotal;
        private ListView Alltestlist;
        private ImageView cancelbutton;
        private RelativeLayout enquirycardview;


        public MyViewHolder(View view) {
            super(view);
            tvmaterialname = (TextView) view.findViewById(R.id.materialnameenquiry);
            tvqty = (TextView) view.findViewById(R.id.testquantityofenquiry);
            Alltestlist = (ListView) view.findViewById(R.id.alltestlist);
            cancelbutton = (ImageView) view.findViewById(R.id.cancelbutton);


//            tvTotal = (TextView) view.findViewById(R.id.total);

        }

       /* private  List<Testlist> getAlltestlists()
        {
            return (List<Testlist>) Alltestlist;+
        }*/
    }

    public Enquiryadapter(FragmentActivity activity, List<Enquirypojo> testlist) {
        this.enquirylist = testlist;
        this.context = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.getrequestform_singlerow, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        Enquirypojo enquirypojo = enquirylist.get(position);

//        holder.tvmaterialname.setText(enquirypojo.getMaterial());
//        holder.tvqty.setText(enquirypojo.getQuantity());

        List<Testlist> testlists = enquirypojo.getTestlistArrayList();


        Testnewadapters adapter = new Testnewadapters(context, testlists, enquirypojo.getQuantity());

        holder.Alltestlist.setAdapter(adapter);



        holder.tvmaterialname.setText(enquirypojo.getMaterial());
        holder.tvqty.setText(enquirypojo.getNoofquantity());

        ListviewheightClass.setListViewHeightBasedOnChildrenDrawer(holder.Alltestlist);

        holder.cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag", "enquiry" + enquirylist.size());
                enquirylist.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, enquirylist.size());
                holder.itemView.setVisibility(View.GONE);
                Log.d("tag", "enquiry" + enquirylist.size());
            }
        });


//        String totalamount=gettotalamount(enquirypojo.getQuantity() ,enquirylist.get(position).getTestlistArrayList());
//
//        holder.tvTotal.setText(totalamount);


    }

    @Override
    public int getItemCount() {
        return enquirylist.size();
    }

    private String gettotalamount(String quantity, ArrayList<Testlist> testListArratyList) {
        int totalAmount = 0;

        for (int i = 0; i < testListArratyList.size(); i++) {
            Log.d("tag", "" + totalAmount);
            totalAmount += Integer.parseInt(quantity) * Integer.parseInt(testListArratyList.get(i).getRate());
            Log.d("tag", "" + totalAmount);
        }
        return "" + totalAmount;
    }

}
