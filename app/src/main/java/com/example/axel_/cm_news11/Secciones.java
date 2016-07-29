package com.example.axel_.cm_news11;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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

import java.util.ArrayList;

public class Secciones extends AppCompatActivity {
    ListAdapter noticeAdapter;
    ListView noticiasListView;
    MyTask ms;
    ListView menuLateral;
    ListAdapter menuLateralAdapter;
    ArrayList options;
    final String url = "http://www.serverbpw.com/cm/2016-2/";
    final String urlComplementoSecciones = "secciones.php?idSec=";
    String titleSec2;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerListener;
    String mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secciones);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        ArrayList noticias = (ArrayList) extras.get("seccion");
        final String titleSec = (String) extras.get("tituloSec");
        mensaje=titleSec;
        getSupportActionBar().setTitle(titleSec);

        if (titleSec.equals("Galería Videos")) {

            noticeAdapter = new CustomAdapter(getApplicationContext(), noticias, 1);
            noticiasListView = (ListView) findViewById(R.id.listViewSecciones);
            assert noticiasListView != null;
            noticiasListView.setAdapter(noticeAdapter);
            noticiasListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    ModelNew noticeSelected = (ModelNew) parent.getItemAtPosition(position);

                    Intent intent = new Intent(Secciones.this, VideoYouTube.class);
                    intent.putExtra("video", noticeSelected);
                    startActivity(intent);

                }
            });

        } else if (titleSec.equals("Galería Fotos")) {
            noticeAdapter = new CustomAdapter(getApplicationContext(), noticias, 1);
            noticiasListView = (ListView) findViewById(R.id.listViewSecciones);
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
                            Intent intent = new Intent(Secciones.this, SingleNotice.class);
                            intent.putExtra("noticia", noticeSelected);
                            startActivity(intent);
                        }
                    }, 2);
                    ms2.execute();
                }
            });
        } else {
            noticeAdapter = new CustomAdapter(getApplicationContext(), noticias, 1);
            noticiasListView = (ListView) findViewById(R.id.listViewSecciones);
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
                            Intent intent = new Intent(Secciones.this, SingleNotice.class);
                            intent.putExtra("noticia", noticeSelected);
                            startActivity(intent);
                        }
                    }, 2);
                    ms2.execute();
                }
            });
        }


        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout2);
        drawerListener = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
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

        options = new ArrayList();
        options = createOption();
        menuLateralAdapter = new CustomAdapter(getApplicationContext(), options, 2);
        menuLateral = (ListView) findViewById(R.id.drawerList);
        assert menuLateral != null;
        menuLateral.setAdapter(menuLateralAdapter);

        menuLateral.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MenuModel a = (MenuModel) parent.getItemAtPosition(position);

                if (a.getOption().equals("Galería Fotos")) {
                    String urlFotos = "http://www.serverbpw.com/cm/2016-2/galerias.php?idTipo=1";
                    titleSec2 = a.getOption();
                    ms = new MyTask(getApplicationContext(), urlFotos, new MyTask.AsyncResponse() {
                        @Override
                        public void processFinish(ArrayList<ModelNew> noticias) {
                            Intent intent = new Intent(Secciones.this, Secciones.class);
                            intent.putExtra("seccion", noticias);
                            intent.putExtra("tituloSec", titleSec2);
                            startActivity(intent);
                        }
                    }, 3);
                    ms.execute();

                } else if (a.getOption().equals("Galería Videos")) {
                    String urlFotos="http://www.serverbpw.com/cm/2016-2/galerias.php?idTipo=2";
                    titleSec2 = a.getOption();
                    ms = new MyTask(getApplicationContext(), urlFotos, new MyTask.AsyncResponse() {
                        @Override
                        public void processFinish(ArrayList<ModelNew> noticias) {
                            Intent intent = new Intent(Secciones.this, Secciones.class);
                            intent.putExtra("seccion", noticias);
                            intent.putExtra("tituloSec", titleSec2);
                            startActivity(intent);
                        }
                    }, 4);
                    ms.execute();

                } else {

                    titleSec2 = a.getOption();
                    ms = new MyTask(getApplicationContext(), url + urlComplementoSecciones + a.getId(), new MyTask.AsyncResponse() {
                        @Override
                        public void processFinish(ArrayList<ModelNew> noticias) {
                            Intent intent = new Intent(Secciones.this, Secciones.class);
                            intent.putExtra("seccion", noticias);
                            intent.putExtra("tituloSec", titleSec2);
                            startActivity(intent);
                        }
                    }, 1);
                    ms.execute();
                }

            }
        });//*termina la configuracion del menu lateral



        //Configuracion de botones de hasta abajo, tendran diferentes acciones
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setImageResource(R.drawable.mensaje);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Revisa todas las noticias sobre el tema: "+mensaje+"  de la aplicacion de CM News, descargala directo de su pagina");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent,"hola esto es una prueba"));
            }
        });

        FloatingActionButton fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        assert fab2 != null;
        fab2.setImageResource(R.drawable.home);
        fab2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ms = new MyTask(getApplicationContext(), url + urlComplementoSecciones + 1, new MyTask.AsyncResponse() {
                    @Override
                    public void processFinish(ArrayList<ModelNew> noticias) {
                        Intent intent = new Intent(Secciones.this, Secciones.class);
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


    public ArrayList createOption() {
        MenuModel option1 = new MenuModel();
        MenuModel option2 = new MenuModel();
        MenuModel option3 = new MenuModel();
        MenuModel option4 = new MenuModel();
        MenuModel option5 = new MenuModel();
        MenuModel option6 = new MenuModel();
        MenuModel option7 = new MenuModel();
        MenuModel option8 = new MenuModel();
        Bitmap icono1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.portada);
        Bitmap icono2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.nacional);
        Bitmap icono3 = BitmapFactory.decodeResource(this.getResources(), R.drawable.internacional);
        Bitmap icono4 = BitmapFactory.decodeResource(this.getResources(), R.drawable.deportes);
        Bitmap icono5 = BitmapFactory.decodeResource(this.getResources(), R.drawable.espectaculos);
        Bitmap icono6 = BitmapFactory.decodeResource(this.getResources(), R.drawable.fotos);
        Bitmap icono7 = BitmapFactory.decodeResource(this.getResources(), R.drawable.video);
        Bitmap icono8 = BitmapFactory.decodeResource(this.getResources(), R.drawable.guardado);
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
       // options.add(option8);
        return options;
    }
}
