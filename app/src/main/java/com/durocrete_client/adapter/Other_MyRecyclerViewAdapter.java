package com.durocrete_client.adapter;

import android.content.Context;
import android.graphics.Color;
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
import android.widget.TextView;
import android.widget.Toast;

import com.durocrete_client.R;
import com.durocrete_client.activity.MainActivity;
import com.durocrete_client.fragments.client.Otherset;
import com.durocrete_client.utils.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 27/10/17.
 */

public class Other_MyRecyclerViewAdapter extends RecyclerView.Adapter<Other_MyRecyclerViewAdapter.ViewHolder> {

    private List<Otherset> Allotherlist = new ArrayList<>();
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private int no;
    String no_specimen;
    private Context context;


    // data is passed into the constructor
    public Other_MyRecyclerViewAdapter(Context context, int i) {
        this.mInflater = LayoutInflater.from(context);
        this.no = i;
        this.context=context;
        setHasStableIds(true);
        initialiselist();
    }

    public void initialiselist() {
        for (int i = 0; i < no; i++) {
            Allotherlist.add(new Otherset());
        }
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.horizontal_other_recycleview_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public List<Otherset> Allotherlist() {
        return this.Allotherlist;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    // binds the data to the textview in each row
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        Log.d("tag1", "" + position);
        final int position1 = position + 1;


        holder.setno.setText("" + position1 + "/" + "" + no);

        holder.No_of_specimen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                no_specimen = parent.getItemAtPosition(position).toString();


                switch (no_specimen) {

                    case "0":
                        holder.linearidmark1.setVisibility(View.VISIBLE);
                        holder.linearidmark2.setVisibility(View.VISIBLE);
                        holder.linearidmark3.setVisibility(View.VISIBLE);
                        holder.idmark1.setEnabled(true);
                        holder.idmark2.setEnabled(true);
                        holder.idmark3.setEnabled(true);


                    case "1":
                        holder.idmark1.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark2.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark3.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark4.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark5.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark6.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark7.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark8.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark9.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark10.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark1.setEnabled(true);
                        holder.idmark2.setEnabled(false);
                        holder.idmark3.setEnabled(false);
                        holder.idmark4.setEnabled(false);
                        holder.idmark5.setEnabled(false);
                        holder.idmark6.setEnabled(false);
                        holder.idmark7.setEnabled(false);
                        holder.idmark8.setEnabled(false);
                        holder.idmark9.setEnabled(false);
                        holder.idmark10.setEnabled(false);
                        break;

                    case "2":
                        holder.idmark1.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark2.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark3.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark4.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark5.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark6.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark7.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark8.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark9.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark10.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark1.setEnabled(true);
                        holder.idmark2.setEnabled(true);
                        holder.idmark3.setEnabled(false);
                        holder.idmark4.setEnabled(false);
                        holder.idmark5.setEnabled(false);
                        holder.idmark6.setEnabled(false);
                        holder.idmark7.setEnabled(false);
                        holder.idmark8.setEnabled(false);
                        holder.idmark9.setEnabled(false);
                        holder.idmark10.setEnabled(false);
                        break;

                    case "3":
                        holder.idmark1.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark2.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark3.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark4.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark5.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark6.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark7.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark8.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark9.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark10.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark1.setEnabled(true);
                        holder.idmark2.setEnabled(true);
                        holder.idmark3.setEnabled(true);
                        holder.idmark4.setEnabled(false);
                        holder.idmark5.setEnabled(false);
                        holder.idmark6.setEnabled(false);
                        holder.idmark7.setEnabled(false);
                        holder.idmark8.setEnabled(false);
                        holder.idmark9.setEnabled(false);
                        holder.idmark10.setEnabled(false);
                        break;

                    case "4":
                        holder.idmark1.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark2.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark3.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark4.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark5.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark6.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark7.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark8.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark9.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark10.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark1.setEnabled(true);
                        holder.idmark2.setEnabled(true);
                        holder.idmark3.setEnabled(true);
                        holder.idmark4.setEnabled(true);
                        holder.idmark5.setEnabled(false);
                        holder.idmark6.setEnabled(false);
                        holder.idmark7.setEnabled(false);
                        holder.idmark8.setEnabled(false);
                        holder.idmark9.setEnabled(false);
                        holder.idmark10.setEnabled(false);
                        break;

                    case "5":
                        holder.idmark1.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark2.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark3.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark4.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark5.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark6.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark7.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark8.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark9.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark10.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark1.setEnabled(true);
                        holder.idmark2.setEnabled(true);
                        holder.idmark3.setEnabled(true);
                        holder.idmark4.setEnabled(true);
                        holder.idmark5.setEnabled(true);
                        holder.idmark6.setEnabled(false);
                        holder.idmark7.setEnabled(false);
                        holder.idmark8.setEnabled(false);
                        holder.idmark9.setEnabled(false);
                        holder.idmark10.setEnabled(false);
                        break;

                    case "6":
                        holder.idmark1.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark2.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark3.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark4.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark5.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark6.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark7.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark8.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark9.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark10.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark1.setEnabled(true);
                        holder.idmark2.setEnabled(true);
                        holder.idmark3.setEnabled(true);
                        holder.idmark4.setEnabled(true);
                        holder.idmark5.setEnabled(true);
                        holder.idmark6.setEnabled(true);
                        holder.idmark7.setEnabled(false);
                        holder.idmark8.setEnabled(false);
                        holder.idmark9.setEnabled(false);
                        holder.idmark10.setEnabled(false);
                        break;

                    case "7":
                        holder.idmark1.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark2.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark3.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark4.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark5.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark6.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark7.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark8.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark9.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark10.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark1.setEnabled(true);
                        holder.idmark2.setEnabled(true);
                        holder.idmark3.setEnabled(true);
                        holder.idmark4.setEnabled(true);
                        holder.idmark5.setEnabled(true);
                        holder.idmark6.setEnabled(true);
                        holder.idmark7.setEnabled(true);
                        holder.idmark8.setEnabled(false);
                        holder.idmark9.setEnabled(false);
                        holder.idmark10.setEnabled(false);
                        break;

                    case "8":
                        holder.idmark1.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark2.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark3.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark4.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark5.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark6.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark7.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark8.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark9.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark10.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark1.setEnabled(true);
                        holder.idmark2.setEnabled(true);
                        holder.idmark3.setEnabled(true);
                        holder.idmark4.setEnabled(true);
                        holder.idmark5.setEnabled(true);
                        holder.idmark6.setEnabled(true);
                        holder.idmark7.setEnabled(true);
                        holder.idmark8.setEnabled(true);
                        holder.idmark9.setEnabled(false);
                        holder.idmark10.setEnabled(false);
                        break;

                    case "9":
                        holder.idmark1.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark2.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark3.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark4.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark5.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark6.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark7.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark8.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark9.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark10.setBackgroundColor(Color.parseColor("#eeeeee"));
                        holder.idmark1.setEnabled(true);
                        holder.idmark2.setEnabled(true);
                        holder.idmark3.setEnabled(true);
                        holder.idmark4.setEnabled(true);
                        holder.idmark5.setEnabled(true);
                        holder.idmark6.setEnabled(true);
                        holder.idmark7.setEnabled(true);
                        holder.idmark8.setEnabled(true);
                        holder.idmark9.setEnabled(true);
                        holder.idmark10.setEnabled(false);
                        break;

                    case "10":
                        holder.idmark1.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark2.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark3.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark4.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark5.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark6.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark7.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark8.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark9.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark10.setBackgroundResource(R.drawable.edit_text_border);
                        holder.idmark1.setEnabled(true);
                        holder.idmark2.setEnabled(true);
                        holder.idmark3.setEnabled(true);
                        holder.idmark4.setEnabled(true);
                        holder.idmark5.setEnabled(true);
                        holder.idmark6.setEnabled(true);
                        holder.idmark7.setEnabled(true);
                        holder.idmark8.setEnabled(true);
                        holder.idmark9.setEnabled(true);
                        holder.idmark10.setEnabled(true);
                        break;

                    default:
                        holder.linearidmark1.setVisibility(View.VISIBLE);
                        holder.linearidmark2.setVisibility(View.VISIBLE);
                        holder.linearidmark3.setVisibility(View.VISIBLE);
                        holder.idmark1.setEnabled(true);
                        holder.idmark2.setEnabled(true);
                        holder.idmark3.setEnabled(true);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        holder.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.make.getText().toString().trim().length() == 0) {
                    holder.make.setError("Please Enter Make");
                    holder.make.requestFocus();
                } else if (holder.supplier.getText().toString().trim().length() == 0) {
                    holder.supplier.setError("Please Enter Supplier");
                    holder.supplier.requestFocus();

                } else if (holder.specification.getText().toString().trim().length() == 0) {
                    holder.specification.setError("Please Enter Specification");
                    holder.specification.requestFocus();

                } else if (holder.Description.getText().toString().trim().length() == 0) {
                    holder.Description.setError("Please Enter Description");
                    holder.Description.requestFocus();

                } else if ((no_specimen.equalsIgnoreCase("1")) && (holder.idmark1.getText().toString().trim().length() == 0)) {
                    holder.idmark1.setError("Please Enter Idmark1");
                    holder.idmark1.requestFocus();

                } else if ((no_specimen.equalsIgnoreCase("2")) && (holder.idmark1.getText().toString().trim().length() == 0)) {
                    holder.idmark1.setError("Please Enter Idmark1");
                    holder.idmark1.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("2")) && (holder.idmark2.getText().toString().trim().length() == 0)) {
                    holder.idmark2.setError("Please Enter Idmark2");
                    holder.idmark2.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("3")) && (holder.idmark1.getText().toString().trim().length() == 0)) {
                    holder.idmark1.setError("Please Enter Idmark1");
                    holder.idmark1.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("3")) && (holder.idmark2.getText().toString().trim().length() == 0)) {
                    holder.idmark2.setError("Please Enter Idmark2");
                    holder.idmark2.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("3")) && (holder.idmark3.getText().toString().trim().length() == 0)) {
                    holder.idmark3.setError("Please Enter Idmark3");
                    holder.idmark3.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("4")) && (holder.idmark1.getText().toString().trim().length() == 0)) {
                    holder.idmark1.setError("Please Enter Idmark1");
                    holder.idmark1.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("4")) && (holder.idmark2.getText().toString().trim().length() == 0)) {
                    holder.idmark2.setError("Please Enter Idmark2");
                    holder.idmark2.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("4")) && (holder.idmark3.getText().toString().trim().length() == 0)) {
                    holder.idmark3.setError("Please Enter Idmark3");
                    holder.idmark3.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("4")) && (holder.idmark4.getText().toString().trim().length() == 0)) {
                    holder.idmark4.setError("Please Enter Idmark4");
                    holder.idmark4.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("5")) && (holder.idmark1.getText().toString().trim().length() == 0)) {
                    holder.idmark1.setError("Please Enter Idmark1");
                    holder.idmark1.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("5")) && (holder.idmark2.getText().toString().trim().length() == 0)) {
                    holder.idmark2.setError("Please Enter Idmark2");
                    holder.idmark2.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("5")) && (holder.idmark3.getText().toString().trim().length() == 0)) {
                    holder.idmark3.setError("Please Enter Idmark3");
                    holder.idmark3.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("5")) && (holder.idmark4.getText().toString().trim().length() == 0)) {
                    holder.idmark4.setError("Please Enter Idmark4");
                    holder.idmark4.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("5")) && (holder.idmark5.getText().toString().trim().length() == 0)) {
                    holder.idmark5.setError("Please Enter Idmark5");
                    holder.idmark5.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("6")) && (holder.idmark1.getText().toString().trim().length() == 0)) {
                    holder.idmark1.setError("Please Enter Idmark1");
                    holder.idmark1.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("6")) && (holder.idmark2.getText().toString().trim().length() == 0)) {
                    holder.idmark2.setError("Please Enter Idmark2");
                    holder.idmark2.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("6")) && (holder.idmark3.getText().toString().trim().length() == 0)) {
                    holder.idmark3.setError("Please Enter Idmark3");
                    holder.idmark3.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("6")) && (holder.idmark4.getText().toString().trim().length() == 0)) {
                    holder.idmark4.setError("Please Enter Idmark4");
                    holder.idmark4.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("6")) && (holder.idmark5.getText().toString().trim().length() == 0)) {
                    holder.idmark5.setError("Please Enter Idmark5");
                    holder.idmark5.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("6")) && (holder.idmark6.getText().toString().trim().length() == 0)) {
                    holder.idmark6.setError("Please Enter Idmark6");
                    holder.idmark6.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("7")) && (holder.idmark1.getText().toString().trim().length() == 0)) {
                    holder.idmark1.setError("Please Enter Idmark1");
                    holder.idmark1.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("7")) && (holder.idmark2.getText().toString().trim().length() == 0)) {
                    holder.idmark2.setError("Please Enter Idmark2");
                    holder.idmark2.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("7")) && (holder.idmark3.getText().toString().trim().length() == 0)) {
                    holder.idmark3.setError("Please Enter Idmark3");
                    holder.idmark3.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("7")) && (holder.idmark4.getText().toString().trim().length() == 0)) {
                    holder.idmark4.setError("Please Enter Idmark4");
                    holder.idmark4.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("7")) && (holder.idmark5.getText().toString().trim().length() == 0)) {
                    holder.idmark5.setError("Please Enter Idmark5");
                    holder.idmark5.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("7")) && (holder.idmark6.getText().toString().trim().length() == 0)) {
                    holder.idmark6.setError("Please Enter Idmark6");
                    holder.idmark6.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("7")) && (holder.idmark7.getText().toString().trim().length() == 0)) {
                    holder.idmark7.setError("Please Enter Idmark7");
                    holder.idmark7.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("8")) && (holder.idmark1.getText().toString().trim().length() == 0)) {
                    holder.idmark1.setError("Please Enter Idmark1");
                    holder.idmark1.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("8")) && (holder.idmark2.getText().toString().trim().length() == 0)) {
                    holder.idmark2.setError("Please Enter Idmark2");
                    holder.idmark2.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("8")) && (holder.idmark3.getText().toString().trim().length() == 0)) {
                    holder.idmark3.setError("Please Enter Idmark3");
                    holder.idmark3.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("8")) && (holder.idmark4.getText().toString().trim().length() == 0)) {
                    holder.idmark4.setError("Please Enter Idmark4");
                    holder.idmark4.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("8")) && (holder.idmark5.getText().toString().trim().length() == 0)) {
                    holder.idmark5.setError("Please Enter Idmark5");
                    holder.idmark5.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("8")) && (holder.idmark6.getText().toString().trim().length() == 0)) {
                    holder.idmark6.setError("Please Enter Idmark6");
                    holder.idmark6.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("8")) && (holder.idmark7.getText().toString().trim().length() == 0)) {
                    holder.idmark7.setError("Please Enter Idmark7");
                    holder.idmark7.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("8")) && (holder.idmark8.getText().toString().trim().length() == 0)) {
                    holder.idmark8.setError("Please Enter Idmark8");
                    holder.idmark8.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("9")) && (holder.idmark1.getText().toString().trim().length() == 0)) {
                    holder.idmark1.setError("Please Enter Idmark1");
                    holder.idmark1.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("9")) && (holder.idmark2.getText().toString().trim().length() == 0)) {
                    holder.idmark2.setError("Please Enter Idmark2");
                    holder.idmark2.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("9")) && (holder.idmark3.getText().toString().trim().length() == 0)) {
                    holder.idmark3.setError("Please Enter Idmark3");
                    holder.idmark3.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("9")) && (holder.idmark4.getText().toString().trim().length() == 0)) {
                    holder.idmark4.setError("Please Enter Idmark4");
                    holder.idmark4.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("9")) && (holder.idmark5.getText().toString().trim().length() == 0)) {
                    holder.idmark5.setError("Please Enter Idmark5");
                    holder.idmark5.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("9")) && (holder.idmark6.getText().toString().trim().length() == 0)) {
                    holder.idmark6.setError("Please Enter Idmark6");
                    holder.idmark6.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("9")) && (holder.idmark7.getText().toString().trim().length() == 0)) {
                    holder.idmark7.setError("Please Enter Idmark7");
                    holder.idmark7.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("9")) && (holder.idmark8.getText().toString().trim().length() == 0)) {
                    holder.idmark8.setError("Please Enter Idmark8");
                    holder.idmark8.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("9")) && (holder.idmark9.getText().toString().trim().length() == 0)) {
                    holder.idmark9.setError("Please Enter Idmark9");
                    holder.idmark9.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("10")) && (holder.idmark1.getText().toString().trim().length() == 0)) {
                    holder.idmark1.setError("Please Enter Idmark1");
                    holder.idmark1.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("10")) && (holder.idmark2.getText().toString().trim().length() == 0)) {
                    holder.idmark2.setError("Please Enter Idmark2");
                    holder.idmark2.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("10")) && (holder.idmark3.getText().toString().trim().length() == 0)) {
                    holder.idmark3.setError("Please Enter Idmark3");
                    holder.idmark3.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("10")) && (holder.idmark4.getText().toString().trim().length() == 0)) {
                    holder.idmark4.setError("Please Enter Idmark4");
                    holder.idmark4.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("10")) && (holder.idmark5.getText().toString().trim().length() == 0)) {
                    holder.idmark5.setError("Please Enter Idmark5");
                    holder.idmark5.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("10")) && (holder.idmark6.getText().toString().trim().length() == 0)) {
                    holder.idmark6.setError("Please Enter Idmark6");
                    holder.idmark6.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("10")) && (holder.idmark7.getText().toString().trim().length() == 0)) {
                    holder.idmark7.setError("Please Enter Idmark7");
                    holder.idmark7.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("10")) && (holder.idmark8.getText().toString().trim().length() == 0)) {
                    holder.idmark8.setError("Please Enter Idmark8");
                    holder.idmark8.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("10")) && (holder.idmark9.getText().toString().trim().length() == 0)) {
                    holder.idmark9.setError("Please Enter Idmark9");
                    holder.idmark9.requestFocus();
                } else if ((no_specimen.equalsIgnoreCase("10")) && (holder.idmark10.getText().toString().trim().length() == 0)) {
                    holder.idmark10.setError("Please Enter Idmark10");
                    holder.idmark10.requestFocus();
                } else {
                    Otherset otherset = new Otherset();
                    otherset.setMake(holder.make.getText().toString().trim());
                    otherset.setSupplier(holder.supplier.getText().toString().trim());
                    otherset.setSpecification(holder.specification.getText().toString().trim());
                    otherset.setDescription(holder.Description.getText().toString().trim());
                    otherset.setNo_of_specimen(no_specimen);
                    otherset.setIdmark1(holder.idmark1.getText().toString().trim());
                    otherset.setIdmark2(holder.idmark2.getText().toString().trim());
                    otherset.setIdmark3(holder.idmark3.getText().toString().trim());
                    otherset.setIdmark4(holder.idmark4.getText().toString().trim());
                    otherset.setIdmark5(holder.idmark5.getText().toString().trim());
                    otherset.setIdmark6(holder.idmark6.getText().toString().trim());
                    otherset.setIdmark7(holder.idmark7.getText().toString().trim());
                    otherset.setIdmark8(holder.idmark8.getText().toString().trim());
                    otherset.setIdmark9(holder.idmark9.getText().toString().trim());
                    otherset.setIdmark10(holder.idmark10.getText().toString().trim());
                    otherset.setIsfilled(true);
                    Allotherlist.set(position, otherset);
                    Utility.hideSoftKeyboard((MainActivity)context);
                    Toast.makeText(v.getContext(), "Saved Succesfully", Toast.LENGTH_SHORT).show();
                }

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
        private TextView setno;
        private EditText make;
        private EditText supplier;
        private EditText specification;
        private EditText Description;
        private Spinner No_of_specimen;
        private EditText idmark1;
        private EditText idmark2;
        private EditText idmark3;
        private EditText idmark4;
        private EditText idmark5;
        private EditText idmark6;
        private EditText idmark7;
        private EditText idmark8;
        private EditText idmark9;
        private EditText idmark10;
        private Button btnSave;
        private LinearLayout linearidmark1, linearidmark2, linearidmark3, linearidmark4, linearidmark5, linearidmark6, linearidmark7, linearidmark8, linearidmark9, linearidmark10;


        public ViewHolder(View itemView) {
            super(itemView);
            setno = (TextView) itemView.findViewById(R.id.setno);
            make = (EditText) itemView.findViewById(R.id.make);
            supplier = (EditText) itemView.findViewById(R.id.supplier);
            specification = (EditText) itemView.findViewById(R.id.specification);
            Description = (EditText) itemView.findViewById(R.id.Description);
            No_of_specimen = (Spinner) itemView.findViewById(R.id.specimen);
            idmark1 = (EditText) itemView.findViewById(R.id.Idmark1);
            idmark2 = (EditText) itemView.findViewById(R.id.Idmark2);
            idmark3 = (EditText) itemView.findViewById(R.id.Idmark3);
            idmark4 = (EditText) itemView.findViewById(R.id.Idmark4);
            idmark5 = (EditText) itemView.findViewById(R.id.Idmark5);
            idmark6 = (EditText) itemView.findViewById(R.id.Idmark6);
            idmark7 = (EditText) itemView.findViewById(R.id.Idmark7);
            idmark8 = (EditText) itemView.findViewById(R.id.Idmark8);
            idmark9 = (EditText) itemView.findViewById(R.id.Idmark9);
            idmark10 = (EditText) itemView.findViewById(R.id.Idmark10);
            linearidmark1 = (LinearLayout) itemView.findViewById(R.id.linear_idmark1);
            linearidmark2 = (LinearLayout) itemView.findViewById(R.id.linear_idmark2);
            linearidmark3 = (LinearLayout) itemView.findViewById(R.id.linear_idmark3);
            linearidmark4 = (LinearLayout) itemView.findViewById(R.id.linear_idmark4);
            linearidmark5 = (LinearLayout) itemView.findViewById(R.id.linear_idmark5);
            linearidmark6 = (LinearLayout) itemView.findViewById(R.id.linear_idmark6);
            linearidmark7 = (LinearLayout) itemView.findViewById(R.id.linear_idmark7);
            linearidmark8 = (LinearLayout) itemView.findViewById(R.id.linear_idmark8);
            linearidmark9 = (LinearLayout) itemView.findViewById(R.id.linear_idmark9);
            linearidmark10 = (LinearLayout) itemView.findViewById(R.id.linear_idmark10);
            btnSave = (Button) itemView.findViewById(R.id.save);
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

