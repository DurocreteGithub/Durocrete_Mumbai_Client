package com.durocrete_client.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.durocrete_client.R;
import com.durocrete_client.model.Mixmateriallist;

import java.util.List;

/**
 * Created by root on 27/10/17.
 */

public class Mixdesignviewadapter extends RecyclerView.Adapter<Mixdesignviewadapter.ViewHolder> {

    private Activity activity;
    private List<Mixmateriallist> Alltestlist;
    private ListView listView;
    private LayoutInflater inflater;
    private Context context;
    private LayoutInflater mInflater;

    public Mixdesignviewadapter(FragmentActivity activity, List<Mixmateriallist> allrequestmateriallists) {
        this.mInflater = LayoutInflater.from(activity);
        this.context=activity;
        this.Alltestlist=allrequestmateriallists;
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
        View view = mInflater.inflate(R.layout.mixdesign_single_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public List<Mixmateriallist> Alltestlist() {
        return this.Alltestlist;
    }


    // binds the data to the textview in each row
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Mixmateriallist testlist = Alltestlist.get(position);

        holder.make.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                testlist.setMake(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        holder.Description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                testlist.setDescription(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        holder.quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                testlist.setQuantity(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        holder.testname.setText(testlist.getMaterialName());

        holder.selectcheckbox.setChecked(testlist.getIsChecked());


        holder.selectcheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.selectcheckbox.isChecked()) {
                    testlist.setIschecked(true);

                } else {
                    testlist.setIschecked(false);

                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return Alltestlist.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView testname;
        private CheckBox selectcheckbox;
        private EditText make;
        private EditText Description;
        private EditText quantity;
        private LinearLayout linearmaterialdetails;


        public ViewHolder(View itemView) {
            super(itemView);
            testname = (TextView) itemView.findViewById(R.id.testname);
            selectcheckbox = (CheckBox) itemView.findViewById(R.id.checkbox);
            make = (EditText) itemView.findViewById(R.id.txtmake);
            Description = (EditText) itemView.findViewById(R.id.txtdescript);
            quantity = (EditText) itemView.findViewById(R.id.txtquantity);

        }

    }

}

