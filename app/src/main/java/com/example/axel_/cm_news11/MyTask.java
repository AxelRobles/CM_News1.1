package com.example.axel_.cm_news11;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by axel_ on 23/05/2016.
 */
public class MyTask extends AsyncTask<String,String,Void> {

    ArrayList<ModelNew> noticias2;
    ListAdapter noticeAdapter;
    ListView noticiasListView;
    private static  final String TAG =MyTask.class.getSimpleName();
    ArrayList<ModelNew> noticias=null;
    private Context context;
    String urlPlus;
    int tipo;
    MyTask(Context context,String urlPlus,AsyncResponse delegate, int tipo){
        this.context=context;
        this.delegate=delegate;
        this.urlPlus=urlPlus;
        this.tipo=tipo;
    }


    public ArrayList<ModelNew> getArray(){
        return noticias;
    }
    @Override
    protected Void doInBackground(String... params) {
        ArrayList<TextView> textViewArrayList = new ArrayList<TextView>();

        //**********Conexion
        HttpURLConnection conection=null;
        BufferedReader reader =null;
        try {
            URL url = new URL(urlPlus);
            conection = (HttpURLConnection)url.openConnection();
            conection.connect();
            InputStream stream=conection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            reader.toString();
            String plist =  org.apache.commons.io.IOUtils.toString(reader);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(plist.getBytes());
            Parser pAxel=new Parser(inputStream,tipo);
            Log.d(TAG,"lo que le mando al parser:"+pAxel);
            noticias = pAxel.parsing();
            Log.d(TAG, "*****************&%&%&%&%&%&%&***********" + noticias.size());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public interface AsyncResponse{
        void processFinish(ArrayList<ModelNew> noticeAdapterR);
    }
    public AsyncResponse delegate=null;

    @Override
    protected void onPostExecute(Void aVoid) {
        delegate.processFinish(noticias);

    }
}
