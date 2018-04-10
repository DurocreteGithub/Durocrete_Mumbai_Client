package com.durocrete_client.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.durocrete_client.R;
import com.durocrete_client.model.Enquiryiddetails;
import com.durocrete_client.utils.ListviewheightClass;

import java.util.List;

/**
 * Created by root on 17/6/17.
 */

public class Enquiryiddetailslistadapter extends RecyclerView.Adapter<Enquiryiddetailslistadapter.MyViewHolder> {


    public Enquiryiddetailslistadapter(FragmentActivity activity, List<Enquiryiddetails> enquiryiddetailslist) {
        this.enquiryiddetailslist = enquiryiddetailslist;
        this.context = activity;
    }

    private List<Enquiryiddetails> enquiryiddetailslist;
    private Context context;



    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView materialname;
        private TextView quantity;
        private ListView Alltestlist;
        private TextView  price;

        private ImageView cancelbutton;
        private RelativeLayout enquirycardview;


        public MyViewHolder(View view) {
            super(view);
            materialname = (TextView) view.findViewById(R.id.materialname);
            quantity=(TextView)view.findViewById(R.id.quantity) ;
//            Alltestlist = (ListView) view.findViewById(R.id.Alltests);
             price=(TextView)view.findViewById(R.id.price);
        }
    }



    @Override
    public Enquiryiddetailslistadapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.getenquirydetails_singlerow, parent, false);
        return new Enquiryiddetailslistadapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Enquiryiddetailslistadapter.MyViewHolder holder, final int position) {


        Enquiryiddetails enquiryiddetails = enquiryiddetailslist.get(position);

        holder.materialname.setText(enquiryiddetails.getMaterial_name());
        holder.quantity.setText(enquiryiddetails.getMaterial_quantity());
//        holder.price.setText(enquiryiddetails.getPrice());
//
//        List<Testarr> tests = enquiryiddetails.getTestarr();
//
//
//        ArrayAdapter<Testarr> adapter = new ArrayAdapter<Testarr>(context,
//                android.R.layout.simple_list_item_2, android.R.id.text1, tests);

//
//        holder.Alltestlist.setAdapter(adapter);

        ListviewheightClass.setListViewHeightBasedOnChildrenDrawer(holder.Alltestlist);

    }

    @Override
    public int getItemCount() {
        return enquiryiddetailslist.size();
    }



}
