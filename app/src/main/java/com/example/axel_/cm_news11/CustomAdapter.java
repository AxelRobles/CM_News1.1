package com.example.axel_.cm_news11;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    static ImageLoader imageLoader = ImageLoader.getInstance();

    private static final String TAG = CustomAdapter.class.getSimpleName();
    Context context;
    int tipo;
    LayoutInflater axelInflater;
    View noticeView;

    public CustomAdapter(Context context, ArrayList resource,int tipo) {
        super(context,R.layout.notice_prin_layout ,resource);
        CustomAdapter.imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        this.context=context;
        this.tipo=tipo;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(tipo==1) {
            axelInflater = LayoutInflater.from(getContext());
            noticeView = axelInflater.inflate(R.layout.notice_prin_layout, parent, false);
            ModelNew singleNew = (ModelNew) getItem(position);
            TextView headline = (TextView) noticeView.findViewById(R.id.headLine);

            ImageView imageNotice = (ImageView) noticeView.findViewById(R.id.imageNotice);
            if (imageNotice == null) {
                imageNotice = new ImageView(context);
            }
            String url = singleNew.getUrlimag();

            Picasso.with(context).load(url).resize(1920, 1080).centerCrop().into(imageNotice);

            // TextView id =(TextView)noticeView.findViewById(R.id.id);
            Log.d(TAG, "*******************/////////////*******************" + singleNew.getHeadline());
            headline.setText(singleNew.getHeadline());
            // urlThum.setText(singleNew.getUrlthumb());
            // urlImage.setText(singleNew.getUrlimag());
            // id.setText(singleNew.getId());
            imageLoader.clearMemoryCache();
            // imageLoader.clearDiskCache();
        }
        else if(tipo==2){
            axelInflater = LayoutInflater.from(getContext());
            noticeView = axelInflater.inflate(R.layout.menu_lateral, parent, false);
            MenuModel menu = (MenuModel) getItem(position);
            TextView option = (TextView)noticeView.findViewById(R.id.menuOptions);
            ImageView icono=(ImageView)noticeView.findViewById(R.id.iconoMenu);
            option.setText(menu.getOption());
            icono.setImageBitmap(menu.getIcono());
        }
        return  noticeView;
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
                matrix, false);

        return resizedBitmap;
    }
}



   /*  //***********************imagen
       // TextView urlThum =(TextView)noticeView.findViewById(R.id.urlThumb);



        imageLoader.displayImage(singleNew.getUrlimag(),imageNotice);
       //  Bitmap bmp= getResizedBitmap(imageLoader.loadImageSync(singleNew.getUrlimag()),10,10);
        DisplayImageOptions  op = new DisplayImageOptions.Builder()
                .displayer(new RoundedBitmapDisplayer(20))
                .build();
      Bitmap bmp=imageLoader.loadImageSync(singleNew.getUrlimag(),op);

        /*

        ImageSize targetSize = new ImageSize(80, 50); // result Bitmap will be fit to this size
        Bitmap bmp = imageLoader.loadImageSync(singleNew.getUrlimag(), targetSize, op);


        imageNotice.setImageBitmap(bmp);

*/