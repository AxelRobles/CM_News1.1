package com.example.axel_.cm_news11;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    MyTask ms;
    // ArrayList<ModelNew>noticias;
    ListAdapter noticeAdapter;
    ListView noticiasListView;
    ListView menuLateral;
    private DrawerLayout drawerLayout;
    ListAdapter menuLateralAdapter;
    ArrayList options;
    String titleActionBar;
    private ActionBarDrawerToggle drawerListener;
    private static final String TAG = MainActivity.class.getSimpleName();



    // The path to the root of this app's internal storage
    private File mPrivateRootDir;
    // The path to the "images" subdirectory
    private File mImagesDir;
    // Array of files in the images subdirectory
    File[] mImageFiles;
    // Array of filenames corresponding to mImageFiles
    String[] mImageFilenames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final String url = "http://www.serverbpw.com/cm/2016-2/";
        final String urlComplementoSecciones = "secciones.php?idSec=";
        String urlComplemento2;
        getSupportActionBar().setTitle("CM News  Portada");

        StrictMode.ThreadPolicy policy = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.GINGERBREAD) {
            policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        //Empieza la configuracion del listado de las noticas iniciales

        urlComplemento2 = "1";
        ms = new MyTask(getApplicationContext(), url + urlComplementoSecciones + urlComplemento2, new MyTask.AsyncResponse() {
            @Override
            public void processFinish(ArrayList<ModelNew> noticias) {
                noticeAdapter = new CustomAdapter(getApplicationContext(), noticias, 1);
                noticiasListView = (ListView) findViewById(R.id.listViewPrincipal);
                assert noticiasListView != null;
                noticiasListView.setAdapter(noticeAdapter);
                noticiasListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        final String noticia = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(getApplicationContext(), noticia, Toast.LENGTH_SHORT).show();
                        ModelNew noticeSelected = (ModelNew) parent.getItemAtPosition(position);
                        String urlComplemento1 = "noticias.php?idNota=";
                        String urlComplemento2 = noticeSelected.getId();
                        String url = "http://www.serverbpw.com/cm/2016-2/";
                        MyTask ms2;
                        ms2 = new MyTask(getApplicationContext(), url + urlComplemento1 + urlComplemento2, new MyTask.AsyncResponse() {
                            @Override
                            public void processFinish(ArrayList<ModelNew> noticias) {
                                ModelNew noticeSelected = noticias.get(0);
                                Intent intent = new Intent(MainActivity.this, SingleNotice.class);
                                intent.putExtra("noticia", noticeSelected);
                                startActivity(intent);
                            }
                        }, 2);
                        ms2.execute();
                    }
                });

            }
        }, 1);
        ms.execute();


        //*****************************Todo sobre el menu lateral

        options=new ArrayList();
        options = createOption();
        menuLateralAdapter = new CustomAdapter(getApplicationContext(), options, 2);
        menuLateral = (ListView) findViewById(R.id.drawerList);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
        drawerListener = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.addDrawerListener(drawerListener);
        assert getSupportActionBar() != null;
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.drawer_menu);

        assert menuLateral != null;
        menuLateral.setAdapter(menuLateralAdapter);

        menuLateral.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MenuModel a=(MenuModel) parent.getItemAtPosition(position);
               if(a.getOption().equals("Galería Fotos")){
                   String urlFotos="http://www.serverbpw.com/cm/2016-2/galerias.php?idTipo=1";

                   ms = new MyTask(getApplicationContext(), urlFotos, new MyTask.AsyncResponse() {
                       @Override
                       public void processFinish(ArrayList<ModelNew> noticias) {
                           Intent intent = new Intent(MainActivity.this, Secciones.class);
                           intent.putExtra("seccion", noticias);
                           intent.putExtra("tituloSec", titleActionBar);
                           startActivity(intent);
                       }
                   }, 3);
                   ms.execute();

               }
                else if(a.getOption().equals("Galería Videos")){
                   String urlFotos="http://www.serverbpw.com/cm/2016-2/galerias.php?idTipo=2";
                   ms = new MyTask(getApplicationContext(), urlFotos, new MyTask.AsyncResponse() {
                       @Override
                       public void processFinish(ArrayList<ModelNew> noticias) {
                           Intent intent = new Intent(MainActivity.this, Secciones.class);
                           intent.putExtra("seccion", noticias);
                           intent.putExtra("tituloSec", titleActionBar);
                           startActivity(intent);
                       }
                   }, 4);
                   ms.execute();

               }
                else {

                   ms = new MyTask(getApplicationContext(), url + urlComplementoSecciones + a.getId(), new MyTask.AsyncResponse() {
                       @Override
                       public void processFinish(ArrayList<ModelNew> noticias) {
                           Intent intent = new Intent(MainActivity.this, Secciones.class);
                           intent.putExtra("seccion", noticias);
                           intent.putExtra("tituloSec", titleActionBar);
                           startActivity(intent);
                       }
                   }, 1);
                   ms.execute();
               }
                selecItem(position,a);
            }

            public  void selecItem(int position,MenuModel a){
                menuLateral.setItemChecked(position,true);
                setTitle(a);
            }
            public void setTitle(MenuModel a){
                titleActionBar= a.getOption();
            }

        });//*termina la configuracion del menu lateral


//Configuracion de botones de abajo, tendran diferentes acciones cada uno

        ///boton de compartir, todavia me falta programarlo
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.mensaje);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Revisa las noticias desde la aplicacion de CM News, descargala directo de su pagina");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent,"hola esto es una prueba"));

            }
        });


        FloatingActionButton fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        fab2.setImageResource(R.drawable.home);
        assert fab2 != null;
        fab2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ms = new MyTask(getApplicationContext(), url + urlComplementoSecciones + 1, new MyTask.AsyncResponse() {
                    @Override
                    public void processFinish(ArrayList<ModelNew> noticias) {
                        Intent intent = new Intent(MainActivity.this, Secciones.class);
                        intent.putExtra("seccion", noticias);
                        intent.putExtra("tituloSec", "Portada");
                        startActivity(intent);
                    }
                }, 1);
                ms.execute();
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    public ArrayList createOption() {
        MenuModel option1 = new MenuModel();
        MenuModel option2 = new MenuModel();
        MenuModel option3 = new MenuModel();
        MenuModel option4 = new MenuModel();
        MenuModel option5 = new MenuModel();
        MenuModel option6 = new MenuModel();
        MenuModel option7 = new MenuModel();
        MenuModel option8 = new MenuModel();
        Bitmap icono1= BitmapFactory.decodeResource(this.getResources(),R.drawable.portada);
        Bitmap icono2= BitmapFactory.decodeResource(this.getResources(),R.drawable.nacional);
        Bitmap icono3= BitmapFactory.decodeResource(this.getResources(),R.drawable.internacional);
        Bitmap icono4= BitmapFactory.decodeResource(this.getResources(),R.drawable.deportes);
        Bitmap icono5= BitmapFactory.decodeResource(this.getResources(),R.drawable.espectaculos);
        Bitmap icono6= BitmapFactory.decodeResource(this.getResources(),R.drawable.fotos);
        Bitmap icono7= BitmapFactory.decodeResource(this.getResources(),R.drawable.video);
        Bitmap icono8= BitmapFactory.decodeResource(this.getResources(),R.drawable.guardado);
        option1.setOption("Portada");
        option2.setOption("Nacional");
        option3.setOption("Internacional");
        option4.setOption("Deportes");
        option5.setOption("Espectaculos");
        option8.setOption("Guardados");
        option6.setOption("Galería Fotos");
        option7.setOption("Galería Videos");
        option1.setIcono(icono1);
        option2.setIcono(icono2);
        option3.setIcono(icono3);
        option4.setIcono(icono4);
        option5.setIcono(icono5);
        option6.setIcono(icono6);
        option7.setIcono(icono7);
        option8.setIcono(icono8);
        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);
        options.add(option5);
        options.add(option6);
        options.add(option7);
        //options.add(option8);
        return options;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

