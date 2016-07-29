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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

public class VideoYouTube extends AppCompatActivity implements YouTubePlayer.OnInitializedListener{
    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer.OnInitializedListener onInitializedListener;

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


    ModelNew video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_you_tube);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        Bundle extras = getIntent().getExtras();
       video =(ModelNew) extras.get("video");

        mensaje= video.getHeadline();


        YouTubePlayerSupportFragment frag = (YouTubePlayerSupportFragment)getSupportFragmentManager()
                .findFragmentById(R.id.youtube_fragment);
        frag.initialize("AIzaSyBTmMhIZwSpu1huXRE-qhowbocy6NmqQAE",this);
/*
        //*configuracion del video en youtube

        youTubePlayerView= (YouTubePlayerView)findViewById(R.id.youtube_view);
        onInitializedListener = new YouTubePlayer.OnInitializedListener(){

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                String videoid=video.getId().substring(32);
                youTubePlayer.loadVideo(videoid);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        youTubePlayerView.initialize("AIzaSyBTmMhIZwSpu1huXRE-qhowbocy6NmqQAE",onInitializedListener);

        //** Termina configuracion de video

        */

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout4);
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
                            Intent intent = new Intent(VideoYouTube.this, Secciones.class);
                            intent.putExtra("seccion", noticias);
                            intent.putExtra("tituloSec", titleSec2);
                            startActivity(intent);
                        }
                    }, 3);
                    ms.execute();

                } else if (a.getOption().equals("Galería Videos")) {
                    titleSec2 = a.getOption();
                    ms = new MyTask(getApplicationContext(), url + urlComplementoSecciones + a.getId(), new MyTask.AsyncResponse() {
                        @Override
                        public void processFinish(ArrayList<ModelNew> noticias) {
                            Intent intent = new Intent(VideoYouTube.this, Secciones.class);
                            intent.putExtra("seccion", noticias);
                            intent.putExtra("tituloSec", titleSec2);
                            startActivity(intent);
                        }
                    }, 1);
                    ms.execute();

                } else {

                    titleSec2 = a.getOption();
                    ms = new MyTask(getApplicationContext(), url + urlComplementoSecciones + a.getId(), new MyTask.AsyncResponse() {
                        @Override
                        public void processFinish(ArrayList<ModelNew> noticias) {
                            Intent intent = new Intent(VideoYouTube.this, Secciones.class);
                            intent.putExtra("seccion", noticias);
                            intent.putExtra("tituloSec", titleSec2);
                            startActivity(intent);
                        }
                    }, 1);
                    ms.execute();
                }

            }
        });//*termina la configuracion del menu lateral


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.mensaje);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Revisa el video "+ mensaje +"");
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
                        Intent intent = new Intent(VideoYouTube.this, Secciones.class);
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
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        String videoid=video.getId().substring(32);
        youTubePlayer.loadVideo(videoid);
    }
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

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
     //   options.add(option8);
        return options;
    }
}
