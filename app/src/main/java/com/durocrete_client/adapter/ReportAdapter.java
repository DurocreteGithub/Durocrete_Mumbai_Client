package com.durocrete_client.adapter;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.durocrete_client.R;
import com.durocrete_client.activity.FileDownloader1;
import com.durocrete_client.activity.reportstatuscallback;
import com.durocrete_client.model.Reportstatus;
import com.durocrete_client.utils.MyPreferenceManager;

import java.io.File;
import java.util.List;

/**
 * Created by root on 11/9/17.
 */

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.MyViewHolder> {

    private List<Reportstatus> Allreportlist;
    private Context context;
    MyPreferenceManager sharedpref;
    private reportstatuscallback listener;


    public ReportAdapter(FragmentActivity activity, List<Reportstatus> allreportlist) {
        this.Allreportlist = allreportlist;
        this.context = activity;

    }



    @Override
    public ReportAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.report_single_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ReportAdapter.MyViewHolder holder, int position) {

        Reportstatus report = Allreportlist.get(position);

        holder.tvrefernceno.setText(report.getRefernceno());
        holder.tvdateoftesting.setText(report.getDateoftesting());
        holder.tvstatus.setText(report.getStatus());
        holder.tvlink.setText(report.getDownload());
        holder.tvlink.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        holder.tvlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int indext = holder.tvlink.getText().toString().trim().lastIndexOf('/');
                final String name = holder.tvlink.getText().toString().trim().substring(indext+1);
                makeDirectory(v.getContext(),holder.tvlink.getText().toString().trim(),name);
//                new FileDownloader(v.getContext()).execute(holder.tvlink.getText().toString().trim(),name);

            }
        });

    }

    private void makeDirectory(Context context, String url, String name) {

        String extStorageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();

        File folderDownload = new File(extStorageDirectory);
        folderDownload.mkdir();
        File folder = new File(extStorageDirectory, "Durocrete");

        folder.mkdir();

        File directory = new File(folder, name);
        Log.v("www directory ", directory.toString());

        if (directory.exists()) {
            Log.v("www", "is presenet");
            File file = new File(folder.getAbsolutePath());
            Log.v("www file : ", file.toString());

            if (directory.toString().contains("pdf")) {
                Log.v("www", "pdf");
                Uri path = Uri.fromFile(directory);
                Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                pdfIntent.setDataAndType(path, "application/pdf");
                pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                try{
                    context.startActivity(pdfIntent);
                }catch(ActivityNotFoundException e){
                    Toast.makeText((Activity) context, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Log.v("www", "is not presenet");
            new FileDownloader1((Activity) context, directory ,folder).execute(url);
        }
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



        public MyViewHolder(View view) {
            super(view);
            tvrefernceno = (TextView) view.findViewById(R.id.txtrefernceno);
            tvdateoftesting = (TextView) view.findViewById(R.id.testingdate);
            tvstatus = (TextView) view.findViewById(R.id.txtStatus);
            tvlink=(TextView)view.findViewById(R.id.txtlink);
            sharedpref= new MyPreferenceManager(context);

        }

    }



}
