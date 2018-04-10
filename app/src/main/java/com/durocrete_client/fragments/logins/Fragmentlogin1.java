package com.durocrete_client.fragments.logins;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.durocrete_client.R;
import com.durocrete_client.activity.LoginActivity;
import com.durocrete_client.activity.MainActivity;
import com.durocrete_client.model.Clientinfo;
import com.durocrete_client.model.Siteengineerinfo;
import com.durocrete_client.model.Siteinfo;
import com.durocrete_client.network.AppController;
import com.durocrete_client.network.IConstants;
import com.durocrete_client.network.IUrls;
import com.durocrete_client.utils.MyPreferenceManager;
import com.durocrete_client.utils.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by root on 19/5/17.
 */

public class Fragmentlogin1 extends Fragment {

    private EditText etxMobileno;
    private EditText etxpassword;
    private TextView tvregisternew;
    private Button btnLogin;
    private LoginActivity loginActivity;
    private String LoginMobileno;
    private String LoginPin;
    public ArrayList<Clientinfo> clientinfoArrayList;
    public ArrayList<Siteinfo> siteinfoArrayList;
    private JSONArray jsonArray1;
    private MyPreferenceManager sharedPref;
    public static ArrayList<Siteengineerinfo> siteengineerinfoArrayList;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmentlogin, container, false);

        Initview(view);

        if (sharedPref.getBooleanPreferences(MyPreferenceManager.Loggedin)) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                View view = getActivity().getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                LoginPin = etxpassword.getText().toString().trim();
                LoginMobileno = etxMobileno.getText().toString().trim();


                if (etxMobileno.getText().toString().length() == 0) {
                    Toast.makeText(getActivity(), "Please Enter Mobile No",
                            Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(etxMobileno.getText().toString())) {
                    Toast.makeText(getActivity(), "Invalid Mobile No",
                            Toast.LENGTH_SHORT).show();
                } else if (etxMobileno.getText().toString().length() < 10) {
                    Toast.makeText(getActivity(), "Invalid Mobile No",
                            Toast.LENGTH_SHORT).show();
                }
//                else if (etxpassword.getText().toString().length() == 0) {
//                    Toast.makeText(getActivity(), "Please Enter PIN No.",
//                            Toast.LENGTH_SHORT).show();
//                }
//                else if (etxpassword.getText().toString().length() <4) {
//                    Toast.makeText(getActivity(), "Invalid Pin No",
//                            Toast.LENGTH_SHORT).show();
//                }
                else {

                    if (Util.isNetworkConnected(getActivity())) {

                        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                        progressDialog.setMessage("Please Wait");
                        progressDialog.show();


                        StringRequest stringRequest = new StringRequest(Request.Method.POST, IUrls.Sign_in,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {


                                        JSONObject jsonObject = null;
                                        progressDialog.dismiss();
                                        try {
                                            jsonObject = new JSONObject(response);
                                            String key = jsonObject.getString(IConstants.RESPONSE_RESULT);
                                            String message = jsonObject.getString(IConstants.RESPONSE_MESSAGE);

                                            jsonArray1 = jsonObject.getJSONArray(IConstants.RESPONSE_ARRAY);
                                            GsonBuilder gsonBuilder = new GsonBuilder();
                                            Gson gson = gsonBuilder.create();
                                            if (message.equalsIgnoreCase("0")) {
                                                clientinfoArrayList = gson.fromJson(String.valueOf(jsonArray1), new TypeToken<List<Clientinfo>>() {
                                                }.getType());
                                                sharedPref.setStringPreferences(MyPreferenceManager.Clientid, clientinfoArrayList.get(0).getClientId());
                                                sharedPref.setStringPreferences(MyPreferenceManager.clientemailid, clientinfoArrayList.get(0).getEmailId());
                                                sharedPref.setStringPreferences(MyPreferenceManager.clientmobileno, clientinfoArrayList.get(0).getMobileNo());
                                                sharedPref.setStringPreferences(MyPreferenceManager.clientofficeaddress, clientinfoArrayList.get(0).getOfficeAddress());
                                                sharedPref.setStringPreferences(MyPreferenceManager.clientofficeno, clientinfoArrayList.get(0).getOfcNo());
                                                sharedPref.setStringPreferences(MyPreferenceManager.clientpanNO, clientinfoArrayList.get(0).getPAN());
                                                sharedPref.setStringPreferences(MyPreferenceManager.clientTanno, clientinfoArrayList.get(0).getTAN());
                                                sharedPref.setStringPreferences(MyPreferenceManager.clientpassword, clientinfoArrayList.get(0).getPASS());
                                                sharedPref.setStringPreferences(MyPreferenceManager.directorname, clientinfoArrayList.get(0).getDirectorName());
                                                sharedPref.setStringPreferences(MyPreferenceManager.Clientname, clientinfoArrayList.get(0).getClientName());

                                            }
                                            if (message.equalsIgnoreCase("1")) {
                                                siteinfoArrayList = gson.fromJson(String.valueOf(jsonArray1), new TypeToken<List<Siteinfo>>() {
                                                }.getType());
                                                sharedPref.setStringPreferences(MyPreferenceManager.Siteid, siteinfoArrayList.get(0).getSiteId());
                                                sharedPref.setStringPreferences(MyPreferenceManager.Siteaddress, siteinfoArrayList.get(0).getSiteaddress());
                                                sharedPref.setStringPreferences(MyPreferenceManager.SiteemailId, siteinfoArrayList.get(0).getSite_mail());
                                                sharedPref.setStringPreferences(MyPreferenceManager.Sitelatitude, siteinfoArrayList.get(0).getSiteLatitude());
                                                sharedPref.setStringPreferences(MyPreferenceManager.Sitelongitude, siteinfoArrayList.get(0).getSiteLongitude());
                                                sharedPref.setStringPreferences(MyPreferenceManager.Sitename, siteinfoArrayList.get(0).getSiteName());
                                                sharedPref.setStringPreferences(MyPreferenceManager.Clientid, siteinfoArrayList.get(0).getSITE_CL_Id());
                                                sharedPref.setStringPreferences(MyPreferenceManager.Clientname, siteinfoArrayList.get(0).getClientName());


                                            }

                                            if (message.equalsIgnoreCase("2")) {
                                                siteengineerinfoArrayList = gson.fromJson(String.valueOf(jsonArray1), new TypeToken<List<Siteengineerinfo>>() {
                                                }.getType());

                                                sharedPref.setStringPreferences(MyPreferenceManager.siteengineer, siteengineerinfoArrayList.get(0).getUserName());
                                                sharedPref.setStringPreferences(MyPreferenceManager.Clientid, siteengineerinfoArrayList.get(0).getClientID());
                                                sharedPref.setStringPreferences(MyPreferenceManager.Siteid, siteengineerinfoArrayList.get(0).getSiteID());
                                                sharedPref.setStringPreferences(MyPreferenceManager.Sitename, siteengineerinfoArrayList.get(0).getSiteName());
                                                sharedPref.setStringPreferences(MyPreferenceManager.Clientname, siteengineerinfoArrayList.get(0).getClientName());
                                            }
                                            sharedPref.setBooleanPreferences(MyPreferenceManager.Loggedin, true);
                                            sharedPref.setStringPreferences(MyPreferenceManager.Username, LoginMobileno);
                                            sharedPref.setStringPreferences(MyPreferenceManager.Password, LoginPin);
                                            Intent intent = new Intent(getActivity(), MainActivity.class);
                                            intent.putExtra("regcode", message);
                                            sharedPref.setStringPreferences(MyPreferenceManager.Accesslevel, message);
                                            startActivity(intent);
                                            Log.d("tag", message);
                                            getActivity().finish();

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            if (LoginPin.length() == 0) {
                                                Toast.makeText(loginActivity, "Please Enter Password.", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(loginActivity, "Incorrect Mobile No. Or Password.", Toast.LENGTH_SHORT).show();

                                            }
                                        }


                                    }
                                }, new Response.ErrorListener()

                        {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                VolleyLog.d("tag", "Error: " + error.getMessage());
                                Toast.makeText(getActivity(), "Please Check Internet Connection ", Toast.LENGTH_SHORT).show();
                                sharedPref.setBooleanPreferences(MyPreferenceManager.Loggedin, false);
                                progressDialog.dismiss();

                            }
                        }) {

                            @Override
                            protected Map<String, String> getParams() {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("clientMob", LoginMobileno);
                                params.put("pass", LoginPin);
                                return params;
                            }

                        };
                        AppController.getInstance().addToRequestQueue(stringRequest);
                    } else {
                        Util.noConnectionAlert(getActivity());
                    }

                }
            }
        });

        tvregisternew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = null;
                fragment = new Fragmentregisternew();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.output, fragment).addToBackStack(null);
                transaction.commit();

            }
        });


        return view;
    }

    private boolean isValidEmail(String s) {
        String expression = "^([0-9\\+]|\\(\\d{1,3}\\))[0-9\\-\\. ]{3,15}$";
        CharSequence inputString = s;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }


    private void Initview(View view) {
        etxMobileno = (EditText) view.findViewById(R.id.etxloginmobileno);
        etxpassword = (EditText) view.findViewById(R.id.etxloginpin);
        btnLogin = (Button) view.findViewById(R.id.btnlogin);
        tvregisternew = (TextView) view.findViewById(R.id.tvregisternew);
        loginActivity = (LoginActivity) getActivity();
        sharedPref = new MyPreferenceManager(getActivity());
        clientinfoArrayList = new ArrayList<>();
        siteinfoArrayList = new ArrayList<>();
    }


}


