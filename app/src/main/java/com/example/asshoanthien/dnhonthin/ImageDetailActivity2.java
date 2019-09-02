package com.example.asshoanthien.dnhonthin;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.asshoanthien.dnhonthin.database.DAO;
import com.example.asshoanthien.dnhonthin.model.ModelFavorites;
import com.example.asshoanthien.dnhonthin.model.modelmediaofpost.MediaOfPost;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ImageDetailActivity2 extends AppCompatActivity {

    private ImageView mImgBack;
    private FloatingActionMenu mFabMenu;
    private ImageView mImgDetail;
    List<MediaOfPost> posts=new ArrayList<>();
    List<ModelFavorites> modelFavorites;
    Bitmap bm;
    private ImageView imgBack;
    private ImageView imgDetail;
    private FloatingActionMenu fabMenu;
    private FloatingActionButton btnsetbg,btnsave,btnsharem,btnHeart;
    private DAO dao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        initView();
        dao=new DAO(this);
        dao.open();
        Intent intent = getIntent();
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final int position = intent.getIntExtra("position", 0);
        final String url = intent.getStringExtra("position");
        Picasso.with(this).load(url).into(mImgDetail);
//        Hdwallpaper_Retrofit.getInstance().getMediaOfPost(position).enqueue(new Callback<List<MediaOfPost>>() {
//            @Override
//            public void onResponse(Call<List<MediaOfPost>> call, Response<List<MediaOfPost>> response) {
//
//
//                final String source = response.body().get(position).getSourceUrl();
//                Log.e("jaja",response.body().get(position).getSourceUrl()+"");
//                posts.addAll(response.body());
//                Picasso.with(ImageDetailActivity2.this).load(source).into(mImgDetail);
//
//            }
//
//            @Override
//            public void onFailure(Call<List<MediaOfPost>> call, Throwable t) {
//
//            }
//        });
        btnsetbg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String a = posts.get(position).getGuid().getRendered();
                startWall();

            }
        });

        btnsharem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                share.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post");
                share.putExtra(Intent.EXTRA_TEXT, url+"");
                startActivity(Intent.createChooser(share, "Share link!"));
            }
        });
        btnHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String a=posts.get(position).getGuid().getRendered();
                modelFavorites=new ArrayList<>();
                ModelFavorites md=new ModelFavorites(url);
                dao.Them(md, ImageDetailActivity2.this);
            }
        });

//       btnsave.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//                Hdwallpaper_Retrofit.getInstance().getSourceUrl().enqueue(new Callback<List<MediaOfPost>>() {
//                    @Override
//                    public void onResponse(Call<List<MediaOfPost>> call, Response<List<MediaOfPost>> response) {
//                        String source=response.body().get(position).getGuid().getRendered();
//                        imgdownload(ImageDetailActivity2.this, source, new ImgDownloadListen() {
//                            @Override
//                            public void success() {
//                                Toast.makeText(ImageDetailActivity2.this, "downloaded", Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void failed() {
//                                Toast.makeText(ImageDetailActivity2.this, "download failed", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<MediaOfPost>> call, Throwable t) {
//
//                    }
//                });
//            }
//        });


//        String source = posts.get(position).getEmbedded().getWpFeaturedmedia().get(0).getSourceUrl();
//        Log.e("source", " "+source );
//
//
//
//        mImgDetail.setScaleType(ImageView.ScaleType.CENTER_CROP);
//
//        // check to see if the file exists
//        File file = new File(source);
//        if (file.exists()){
//
//            bm = BitmapFactory.decodeFile(source);
//        }
//
//
//        // set the image and text
//        mImgDetail.setImageBitmap(bm);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgdownload(ImageDetailActivity2.this, url, new ImgDownloadListen() {

                    @Override
                    public void success() {
                        Toast.makeText(ImageDetailActivity2.this, "downloaded", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failed() {
                        Toast.makeText(ImageDetailActivity2.this, "download failed", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    public static Bitmap viewToBitmap(View view, int width, int height){
        Bitmap bitmap=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    private void startWall() {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            wallpaperManager.setBitmap(viewToBitmap(mImgDetail,mImgDetail.getWidth(),mImgDetail.getHeight()));
            Toast.makeText(this, "Succsess", Toast.LENGTH_SHORT).show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void initView() {
        mImgBack = findViewById(R.id.imgBack);
        mFabMenu = findViewById(R.id.fab_menu);
        mImgDetail = findViewById(R.id.imgDetail);
        btnsetbg = (FloatingActionButton) findViewById(R.id.btnsetbg);
        btnsave=findViewById(R.id.btnsave);
        btnsharem=findViewById(R.id.btnshare);
        btnHeart=findViewById(R.id.btnheart);

//        btnsetas.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceType")
//            @Override
//            public void onClick(View view) {
//                WallpaperManager myWallpaperManager
//                        = WallpaperManager.getInstance(getApplicationContext());
//                try {
//                    myWallpaperManager.setResource(R.id.mImgDetail);
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//        });
    }

    public static void imgdownload(Context context,String url, final ImgDownloadListen imgDownloadListen){
        Picasso.with(context).load(url).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                saveImg(bitmap);
                imgDownloadListen.success();
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                imgDownloadListen.failed();
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }

    private static void saveImg(Bitmap bitmap){
        String root= Environment.getExternalStorageDirectory().toString();
        File myDir=new File(root+ "/saved_img");
        if (!myDir.exists()){
            myDir.mkdirs();
        }
        Random random=new Random();
        int n=10000;
        n=random.nextInt(n);
        String fname="Img-"+n+".jpg";
        File file=new File(myDir,fname);
        if (file.exists()){
            file.delete();
        }
        try {
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,90,fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }
}

