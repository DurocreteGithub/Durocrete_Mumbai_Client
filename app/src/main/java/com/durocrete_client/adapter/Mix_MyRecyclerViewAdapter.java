package com.durocrete_client.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.durocrete_client.R;
import com.durocrete_client.activity.MainActivity;
import com.durocrete_client.fragments.client.Mixset;
import com.durocrete_client.utils.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 27/10/17.
 */

public class Mix_MyRecyclerViewAdapter extends RecyclerView.Adapter<Mix_MyRecyclerViewAdapter.ViewHolder> {

    private List<Mixset> Allmixmateriallist = new ArrayList<>();
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private int no;
    private Context context;
    String grade_name;
    int grade_selection;
    String retention_name;
    String material_type;


    // data is passed into the constructor
    public Mix_MyRecyclerViewAdapter(Context context, int i) {
        this.mInflater = LayoutInflater.from(context);
        this.no = i;
        this.context = context;
        setHasStableIds(true);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.horizontal_mixl_recycleview_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public List<Mixset> Allmixmateriallist() {
        return this.Allmixmateriallist;
    }


    // binds the data to the textview in each row
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        Log.d("tag1", "" + position);
        final int position1 = position + 1;


        holder.spmaterialtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 4) {
                    holder.linearothertype.setVisibility(View.VISIBLE);
                    material_type = holder.othertype.getText().toString().trim();
                } else {
                    holder.linearothertype.setVisibility(View.GONE);
                    material_type = parent.getItemAtPosition(position).toString();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        holder.grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                grade_selection = position;

                if (position == 3) {
                    holder.linearotherdiameter.setVisibility(View.VISIBLE);
                    grade_name = holder.otherdiameter.getText().toString().trim();
                } else {
                    holder.linearotherdiameter.setVisibility(View.GONE);
                    grade_name = parent.getItemAtPosition(position).toString();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        holder.retention_period.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                retention_name = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        holder.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Mixset mixset = new Mixset();
                mixset.setGrade(grade_name);
                mixset.setMaterial_type(material_type);
                mixset.setRetention_period(retention_name);
                mixset.setMaterial_combination(holder.material_combination.getText().toString().trim());
                mixset.setWorkability(holder.workability.getText().toString().trim());
                mixset.setSlump(holder.slump.getText().toString().trim());
                mixset.setFlow(holder.flow.getText().toString().trim());
                mixset.setNature_of_work(holder.nature_of_work.getText().toString().trim());
                Allmixmateriallist.add(mixset);
                Utility.hideSoftKeyboard((MainActivity) context);
                Toast.makeText(v.getContext(), "Saved Succesfully", Toast.LENGTH_SHORT).show();
                cleardata();


            }

            private void cleardata() {
                holder.spmaterialtype.setSelection(0);
                holder.retention_period.setSelection(0);
                holder.grade.setSelection(0);
                holder.material_combination.setText("");
                holder.workability.setText("");
                holder.slump.setText("");
                holder.flow.setText("");
                holder.nature_of_work.setText("");


            }

        });


    }


    // total number of rows
    @Override
    public int getItemCount() {
        return no;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Spinner spmaterialtype;
        private Spinner grade;
        private EditText material_combination;
        private EditText otherdiameter, othertype;
        private EditText workability;
        private EditText slump;
        private Spinner retention_period;
        private EditText flow;
        private EditText nature_of_work;
        private Button btnSave;
        private LinearLayout linearotherdiameter, linearothertype, row_layout;


        public ViewHolder(View itemView) {
            super(itemView);

            spmaterialtype = (Spinner) itemView.findViewById(R.id.typeselection);
            othertype = (EditText) itemView.findViewById(R.id.othertype);
            grade = (Spinner) itemView.findViewById(R.id.gradeselection);
            otherdiameter = (EditText) itemView.findViewById(R.id.otherdiameter);
            material_combination = (EditText) itemView.findViewById(R.id.material_combination);
            workability = (EditText) itemView.findViewById(R.id.workbility);
            slump = (EditText) itemView.findViewById(R.id.slump);
            retention_period = (Spinner) itemView.findViewById(R.id.retention_period);
            flow = (EditText) itemView.findViewById(R.id.flow);
            nature_of_work = (EditText) itemView.findViewById(R.id.nature_of_work);
            btnSave = (Button) itemView.findViewById(R.id.save);
            linearotherdiameter = (LinearLayout) itemView.findViewById(R.id.linearotherdiameter);
            linearothertype = (LinearLayout) itemView.findViewById(R.id.linearothertype);
            row_layout = (LinearLayout) itemView.findViewById(R.id.row_layout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

