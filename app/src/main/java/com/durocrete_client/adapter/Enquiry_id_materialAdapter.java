package com.durocrete_client.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.durocrete_client.R;
import com.durocrete_client.model.Enquiryiddetails;
import com.durocrete_client.utils.MyPreferenceManager;

import java.util.List;

/**
 * Created by root on 11/9/17.
 */

public class Enquiry_id_materialAdapter extends RecyclerView.Adapter<Enquiry_id_materialAdapter.MyViewHolder> {

    private List<Enquiryiddetails> Allreportlist;
    private Context context;
    MyPreferenceManager sharedpref;

    public Enquiry_id_materialAdapter(FragmentActivity activity, List<Enquiryiddetails> allreportlist) {
        this.Allreportlist=allreportlist;
        this.context=activity;
    }

    @Override
    public Enquiry_id_materialAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.enquiry_sdetails_single_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Enquiry_id_materialAdapter.MyViewHolder holder, int position) {

        Enquiryiddetails report = Allreportlist.get(position);

        holder.tvrefernceno.setText(report.getMaterial_name());
        holder.tvdateoftesting.setText(report.getMaterial_quantity());
        holder.tvstatus.setText(report.getContactname());
        holder.tvlink.setText(report.getEmailId());
        holder.txtmobileno.setText(report.getMobileno());
    }

    @Override
    public int getItemCount() {
        return Allreportlist.size();
    }

    public class MyViewHolder  extends  RecyclerView.ViewHolder{

        private TextView tvrefernceno;
        private TextView tvdateoftesting;
        private TextView tvstatus;
        private TextView  tvlink;
        private TextView txtmobileno;




        public MyViewHolder(View view) {
            super(view);
            tvrefernceno = (TextView) view.findViewById(R.id.txtrefernceno);
            tvdateoftesting = (TextView) view.findViewById(R.id.testingdate);
            tvstatus = (TextView) view.findViewById(R.id.txtStatus);
            tvlink=(TextView)view.findViewById(R.id.txtlink);
            txtmobileno=(TextView)view.findViewById(R.id.txtmobileno) ;
            sharedpref= new MyPreferenceManager(context);

        }

    }
}
