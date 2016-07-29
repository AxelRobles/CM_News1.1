package com.example.axel_.cm_news11;


import android.util.Log;

import com.longevitysoft.android.xml.plist.PListXMLHandler;
import com.longevitysoft.android.xml.plist.PListXMLParser;
import com.longevitysoft.android.xml.plist.domain.Array;
import com.longevitysoft.android.xml.plist.domain.Dict;
import com.longevitysoft.android.xml.plist.domain.PList;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class Parser {
    ByteArrayInputStream inputStream;
    int tipo;//tipo de parseo que se necesita para cada seccion de la app
    private static final String TAG = "Parser";

    Parser(ByteArrayInputStream inputStream, int tipo) throws IOException {
        this.inputStream = inputStream;//recibo la informacion en bruto del servidor
        this.tipo = tipo;//recibo el tipo de parseo que se necesita
    }

    public ArrayList<ModelNew> parsing() {
        PListXMLParser parser = new PListXMLParser();//Plist
        PListXMLHandler handler = new PListXMLHandler();//Manejador
        parser.setHandler(handler);//Le asigno al parser un manejador
        ArrayList<ModelNew> noticias = null;//arrayList donde se guardaran las noticias

        if (tipo == 1) {
            try {

                parser.parse(inputStream);//se le manda a al metodo parse la informacion en bruto, internamente guarda el resultado
                Log.d(TAG, "**************************************");
                PList actualPList = ((PListXMLHandler) parser.getHandler()).getPlist(); //se pide un Plist del objeto parser

                //con este array crear un objeto noticia
                Array a;
                boolean termino = false;
                int contadorNoticia = 1;
                noticias = new ArrayList<ModelNew>();//ArrayList de Modelos de noticias
                while (!termino) {
                    a = ((Dict) actualPList.getRootElement()).getConfigurationArray("NOTA" + contadorNoticia);
                    //y despues hacemos otro arrya de esos objetos para que se lo regresemos al main
                    com.longevitysoft.android.xml.plist.domain.String str;
                    Log.d(TAG, "**********************************222222222****");
                    if (a != null) {

                        Iterator itr = a.iterator();
                        ///este while nos va a ayudar a crear el objeto noticia
                        int i = 0;
                        ModelNew modelNew = null;
                        modelNew = new ModelNew();
                        //se llena toda la informacion de la noticia nueva
                        while (itr.hasNext()) {
                            Object e = itr.next();
                            str = (com.longevitysoft.android.xml.plist.domain.String) (e);
                            //textViewArrayList.get(i).setText(str.getValue());
                            switch (i) {
                                case 0:
                                    modelNew.setId(str.getValue());
                                    Log.d(TAG, modelNew.getId());
                                    break;
                                case 1:
                                    modelNew.setHeadline(str.getValue());
                                    Log.d(TAG, modelNew.getHeadline());
                                    break;
                                case 2:
                                    modelNew.setUrlthumb(str.getValue());
                                    Log.d(TAG, modelNew.getUrlthumb());
                                    break;
                                case 3:
                                    modelNew.setUrlimag(str.getValue());
                                    Log.d(TAG, modelNew.getUrlimag());
                                    break;
                            }
                            i++;
                        }
                        noticias.add(modelNew);//se guarda la nueva noticia
                        Log.d(TAG, "*****************&%&%&%&%&%&%&***********" + noticias.size());
                    } else {
                        termino = true;//se terminaron todas las noticias
                    }
                    contadorNoticia++;
                }
            } catch (java.io.IOException e) {
                Log.d(TAG, "error");
            }

        }
        else if(tipo==2){
            try {

                parser.parse(inputStream);
                Log.d(TAG, "**************************************");
                PList actualPList = ((PListXMLHandler) parser.getHandler()).getPlist();

                //con este array crear un objeto noticia
                Array a;

                noticias = new ArrayList<ModelNew>();

                    a = ((Dict) actualPList.getRootElement()).getConfigurationArray("INFONOTA");
                    //y despues hacemos otro arrya de esos objetos para que se lo regresemos al main
                    com.longevitysoft.android.xml.plist.domain.String str;
                   // Log.d(TAG, "**********************************222222222****");
                    if (a != null) {

                        Iterator itr = a.iterator();
                        ///este while nos va a ayudar a crear el objeto noticia
                        int i = 0;
                        ModelNew modelNew = null;
                        modelNew = new ModelNew();

                        while (itr.hasNext()) {
                            Object e = itr.next();
                            str = (com.longevitysoft.android.xml.plist.domain.String) (e);
                            //textViewArrayList.get(i).setText(str.getValue());
                            switch (i) {

                                case 0:
                                    modelNew.setHeadline(str.getValue());
                                    Log.d(TAG, modelNew.getHeadline());
                                    break;
                                case 2:
                                    modelNew.setUrlthumb(str.getValue());
                                    Log.d(TAG, modelNew.getUrlthumb());
                                    break;
                                case 3:
                                    modelNew.setUrlimag(str.getValue());
                                    Log.d(TAG, modelNew.getUrlimag());
                                    break;
                                case 1:
                                    modelNew.setTexto(str.getValue());
                                    Log.d(TAG, modelNew.getTexto());
                                    break;

                            }
                            i++;
                        }
                        noticias.add(modelNew);
                        Log.d(TAG, "*****************&%&%&%&%&%&%&***********" + noticias.size());
                    } else {

                    }

            } catch (java.io.IOException e) {
                Log.d(TAG, "error");
            }
        }
        else if(tipo==3){
            try {

                parser.parse(inputStream);
                Log.d(TAG, "**************************************");
                PList actualPList = ((PListXMLHandler) parser.getHandler()).getPlist();

                //con este array crear un objeto noticia
                Array a;
                boolean termino = false;
                int contadorNoticia = 1;
                noticias = new ArrayList();
                while (!termino) {
                    a = ((Dict) actualPList.getRootElement()).getConfigurationArray("GALERIA" + contadorNoticia);
                    //y despues hacemos otro arrya de esos objetos para que se lo regresemos al main
                    com.longevitysoft.android.xml.plist.domain.String str;
                    Log.d(TAG, "**********************************222222222****");
                    if (a != null) {

                        Iterator itr = a.iterator();
                        ///este while nos va a ayudar a crear el objeto noticia
                        int i = 0;
                        ModelNew modelNew = null;
                        modelNew = new ModelNew();

                        while (itr.hasNext()) {
                            Object e = itr.next();
                            str = (com.longevitysoft.android.xml.plist.domain.String) (e);
                            //textViewArrayList.get(i).setText(str.getValue());
                            switch (i) {
                                case 0:
                                    modelNew.setId(str.getValue());
                                    Log.d(TAG, modelNew.getId());
                                    break;
                                case 1:
                                    modelNew.setHeadline(str.getValue());
                                    Log.d(TAG, modelNew.getHeadline());
                                    break;
                                case 2:
                                    modelNew.setUrlthumb(str.getValue());
                                    Log.d(TAG, modelNew.getUrlthumb());
                                    break;
                                case 3:
                                    modelNew.setUrlimag(str.getValue());
                                    Log.d(TAG, modelNew.getUrlimag());
                                    break;
                            }
                            i++;
                        }
                        noticias.add(modelNew);
                        Log.d(TAG, "*****************&%&%&%&%&%&%&***********" + noticias.size());
                    } else {
                        termino = true;
                    }
                    contadorNoticia++;
                }
            } catch (java.io.IOException e) {
                Log.d(TAG, "error");
            }
        }
        else if(tipo==4){

            try {

                parser.parse(inputStream);
                Log.d(TAG, "**************************************");
                PList actualPList = ((PListXMLHandler) parser.getHandler()).getPlist();

                //con este array crear un objeto noticia
                Array a;
                boolean termino = false;
                int contadorNoticia = 1;
                noticias = new ArrayList();
                while (!termino) {
                    a = ((Dict) actualPList.getRootElement()).getConfigurationArray("VIDEO" + contadorNoticia);
                    //y despues hacemos otro arrya de esos objetos para que se lo regresemos al main
                    com.longevitysoft.android.xml.plist.domain.String str;
                    Log.d(TAG, "**********************************222222222****");
                    if (a != null) {

                        Iterator itr = a.iterator();
                        ///este while nos va a ayudar a crear el objeto noticia
                        int i = 0;
                        ModelNew modelNew = null;
                        modelNew = new ModelNew();

                        while (itr.hasNext()) {
                            Object e = itr.next();
                            str = (com.longevitysoft.android.xml.plist.domain.String) (e);
                            //textViewArrayList.get(i).setText(str.getValue());
                            switch (i) {
                                case 3:
                                    modelNew.setId(str.getValue());
                                    Log.d(TAG, modelNew.getId());
                                    break;
                                case 0:
                                    modelNew.setHeadline(str.getValue());
                                    Log.d(TAG, modelNew.getHeadline());
                                    break;
                                case 1:
                                    modelNew.setUrlthumb(str.getValue());
                                    Log.d(TAG, modelNew.getUrlthumb());
                                    break;
                                case 2:
                                    modelNew.setUrlimag(str.getValue());
                                    Log.d(TAG, modelNew.getUrlimag());
                                    break;
                            }
                            i++;
                        }
                        noticias.add(modelNew);
                        Log.d(TAG, "*****************&%&%&%&%&%&%&***********" + noticias.size());
                    } else {
                        termino = true;
                    }
                    contadorNoticia++;
                }
            } catch (java.io.IOException e) {
                Log.d(TAG, "error");
            }

        }
        return noticias;

    }


}

