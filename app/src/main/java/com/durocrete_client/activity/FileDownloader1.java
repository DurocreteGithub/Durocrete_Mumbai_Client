package com.durocrete_client.activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.durocrete_client.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.Context.NOTIFICATION_SERVICE;


public class FileDownloader1 extends AsyncTask<String, Integer, String> {


    private static final int MEGABYTE = 1024 * 1024;
    ProgressDialog mProgressDialog;
    int id = 1;
    private Activity context;
    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder build;
    private File directory , folder;

    public FileDownloader1(Activity context ,File directory , File folder) {
        this.context = context;
        mNotifyManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        build = new NotificationCompat.Builder(context);
        this.directory = directory;
        this.folder = folder;

    }

    public void downloadFile(String fileUrl, File directory) {
        try {

            URL url = new URL(fileUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            //urlConnection.setRequestMethod("GET");
            //urlConnection.setDoOutput(true);
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(directory);
            int totalSize = urlConnection.getContentLength();

            byte[] buffer = new byte[MEGABYTE];
            int bufferLength = 0;
            while ((bufferLength = inputStream.read(buffer)) > 0) {

                fileOutputStream.write(buffer, 0, bufferLength);
            }
            fileOutputStream.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
//        String fileName = strings[1];  // -> maven.pdf
//
//        String extStorageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
//
//        File folderDownload = new File(extStorageDirectory);
//        folderDownload.mkdir();
//        File folder = new File(extStorageDirectory, "VaaJobs");
//
//        folder.mkdir();
//
//        File directory = new File(folder, fileName);
//        Log.v("www directory ", directory.toString());
//
//        if (directory.exists()) {
//            Log.v("www", "is presenet");
//            File file = new File(folder.getAbsolutePath());
//            Log.v("www file : ", file.toString());
//
//            if (directory.toString().contains("pdf")) {
//                Log.v("www", "pdf");
//                Uri path = Uri.fromFile(directory);
//                Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
//                pdfIntent.setDataAndType(path, "application/pdf");
//                pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                context.startActivity(pdfIntent);
//            } else {
//                Log.v("www", "others");
//                Log.v("www fileUrl : ", fileUrl);
//                WebViewFragment fragment = new WebViewFragment();
//                Bundle bundle = new Bundle();
//                bundle.putString("currentURL", fileUrl);
//                fragment.setArguments(bundle);
//                ((UserDashboardActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.rlUserDashboardContainer, fragment).addToBackStack(null).commit();
//
//            }
//        } else {
            Log.v("www", "is not presenet");
            try {
                directory.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        /*for Notification*/
            int i;
            for (i = 0; i <= 100; i += 5) {
                // Sets the progress indicator completion percentage
                publishProgress(Math.min(i, 100));
                try {
                    // Sleep for 5 seconds
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException e) {
                    Log.d("Failure", "sleeping failure");
                }
            }
            //*****************//
//        FileDownloader.downloadFile(fileUrl, directory);
            //start download file and save in folder
            try {
                URL url = new URL(fileUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream(directory);
                int lenghtOfFile = urlConnection.getContentLength();

                byte[] buffer = new byte[MEGABYTE];
                int bufferLength = 0;
                long total = 0;
                while ((bufferLength = inputStream.read(buffer)) > 0) {
                    total += bufferLength;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
//                    publishProgress((int) ((total * 100) / lenghtOfFile));
                    fileOutputStream.write(buffer, 0, bufferLength);
                }


//                Log.v("AAA : ", " filURL : " + fileUrl);
//                Log.v("AAA : ", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/VaaJobs/");
//                Log.v("AAA :", "folder :" + folder);
//                Log.v("AAA :", "directory :" + directory);
//
//
                File file = new File(folder.getAbsolutePath());

                Log.v("AAA file : ", file.toString());

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setDataAndType(Uri.fromFile(file), "*/*");
//            context.startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));

                PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);
                build.setContentIntent(pIntent);

                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //end download file and save in folder
//        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage("Please wait, Download in progress.");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        /*for Notification*/
        build.setContentTitle("Download")
                .setContentText("Please wait, Download in progress.")
                .setSmallIcon(R.drawable.carat_bottom);
        build.setProgress(100, 0, false);
        mNotifyManager.notify(id, build.build());
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mProgressDialog.setProgress(values[0]);
          /*for Notification*/
        build.setProgress(100, values[0], false);
        mNotifyManager.notify(id, build.build());

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mProgressDialog.dismiss();
        /*for Notification*/
        build.setContentText("Click here to open.");
        // Removes the progress bar
        build.setProgress(0, 0, false);
        Toast.makeText(context ,"Download Completed, Click again to open it.",Toast.LENGTH_LONG).show();
        mNotifyManager.notify(id, build.build());
    }
}
