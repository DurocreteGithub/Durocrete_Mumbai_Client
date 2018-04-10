package com.durocrete_client.activity;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.durocrete_client.R;
import com.durocrete_client.fragments.clientfinal.Fragmenthome;
import com.durocrete_client.model.Enquirypojo;
import com.durocrete_client.utils.MyPreferenceManager;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity  implements reportstatuscallback{

    List<Enquirypojo> listtests;
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    private Button startBtn;
    private ProgressDialog mProgressDialog;
    List<Testrequestform> testrequestformList;
    MyPreferenceManager sharedPref;
    private String message;
    private boolean doubleBackToExitPressedOnce = false;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 99;


    public List<Testrequestform> getTestrequestformList() {
        return testrequestformList;
    }

    public void setTestrequestformList(List<Testrequestform> testrequestformList) {
        this.testrequestformList = testrequestformList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if (Build.VERSION.SDK_INT >= M) {
            checkLocationPermission();
        }



        listtests = new ArrayList<>();
        testrequestformList = new ArrayList<>();


        testrequestformList = new ArrayList<>();


        sharedPref = new MyPreferenceManager(this);

        message = sharedPref.getStringPreferences(MyPreferenceManager.Accesslevel);
        String Clientname = sharedPref.getStringPreferences(MyPreferenceManager.Clientname);


        android.support.v4.app.Fragment fragment = null;
        fragment = new Fragmenthome();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();




    }


    @Override
    protected void onResume() {
        super.onResume();
        checkLocationPermission();
    }

    private void checkLocationPermission() {
        if (android.os.Build.VERSION.SDK_INT >= M) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) + ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                //Request Location Permission
                checkALLPermission();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkALLPermission() {

        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) + ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
        android.Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

        // Show an explanation to the user *asynchronously* -- don't block
        // this thread waiting for the user's response! After the user
        // sees the explanation, try again to request the permission.
        new AlertDialog.Builder(MainActivity.this)
        .setTitle("Permission Needed")
        .setMessage("This app needs the Permissions for proper functionality")
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
@RequiresApi(api = Build.VERSION_CODES.M)
@Override
public void onClick(DialogInterface dialogInterface, int i) {
        Log.v("AAA 99 ", "permission granted");
        //Prompt the user once explanation has been shown
        requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
        REQUEST_ID_MULTIPLE_PERMISSIONS);
        }
        })
        .create()
        .show();

            /*requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA},
                    REQUEST_ID_MULTIPLE_PERMISSIONS);*/

        } else {
        // No explanation needed, we can request the permission.
        requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
        REQUEST_ID_MULTIPLE_PERMISSIONS);
        }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            sharedPref.clearSharedPreference();
//            Intent intent=new Intent(this,LoginActivity.class);
//            startActivity(intent);
//            finish();
//        }

        return super.onOptionsItemSelected(item);
    }


    public List<Enquirypojo> getListtests() {
        return listtests;
    }

    public void setListtests(List<Enquirypojo> listtests) {
        this.listtests = listtests;
    }

    public List<Testrequestform> getTestrequestdetailsList() {
        return testrequestformList;
    }

    public void setTestrequestdetailsList(List<Testrequestform> testrequestformList) {
        this.testrequestformList = testrequestformList;
    }

    public void removefragmewnt() {
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().popBackStack();

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_DOWNLOAD_PROGRESS:
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setMessage("Downloading file..");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();
                return mProgressDialog;
            default:
                return null;
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                sharedPref.clearSharedPreference();
                finish();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(MainActivity.this, "Double click to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 1000);

        }
    }


    @Override
    public void foo(Context context, String trim) {
        new DownloadFileAsync().execute(trim);
    }



}


