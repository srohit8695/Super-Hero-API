package com.example.hero_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class dashbod extends AppCompatActivity {

    TextView name,fullname,alteregos,aliases,firstappearance,placeofbirth,publisher,alignment,intelligence,streangth,speed,durability,power,combat,gender,race,height,weight;
    TextView eyecolor,haircolor,occupation,base,groupaffiliation,relatives;
    ImageView imageView;
        String jsondata="",genderstr="",alignmentstr="",racestr="",namestr="",heroid="";
    String intelligenceint="",speedint="",strengthint="",durabilityint="",powerint="",combatint="",heightdata="",weightdata="",imgstr="",publishstr="";
    database db;
    ScrollView linearlayout;
    String imagePath="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbod);
        setTitle("Dashboard");

        eyecolor = findViewById(R.id.eyecolor);
        haircolor = findViewById(R.id.haircolor);
        occupation = findViewById(R.id.occupation);
        base = findViewById(R.id.base);
        groupaffiliation = findViewById(R.id.groupaffiliation);
        relatives = findViewById(R.id.relatives);
        name = findViewById(R.id.name);
        fullname = findViewById(R.id.fullname);
        alteregos = findViewById(R.id.alteregos);
        aliases = findViewById(R.id.aliases);
        firstappearance = findViewById(R.id.firstappearance);
        placeofbirth = findViewById(R.id.placeofbirth);
        publisher = findViewById(R.id.publisher);
        alignment = findViewById(R.id.alignment);
        intelligence = findViewById(R.id.intelligence);
        streangth = findViewById(R.id.streangth);
        speed = findViewById(R.id.speed);
        durability = findViewById(R.id.durability);
        power = findViewById(R.id.power);
        combat = findViewById(R.id.combat);
        gender = findViewById(R.id.gender);
        race = findViewById(R.id.race);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        imageView=findViewById(R.id.imageView);
        linearlayout=findViewById(R.id.linearlayout);

        setTitle("Dashboard");
        jsondata=getIntent().getStringExtra("data");
        txt_Initialise(jsondata);

    }


    public void txt_Initialise(String data){

        try {
            JSONObject powerstats,biography,appearance,work,connections,image,jsonObject1,jsonObject=new JSONObject(data);
            JSONArray jsonArray=jsonObject.getJSONArray("results");
            jsonObject1=jsonArray.getJSONObject(0);
            namestr=jsonObject1.getString("name");
            name.setText(jsonObject1.getString("name"));
            heroid=jsonObject1.getString("id");
            biography = jsonObject1.getJSONObject("biography");
            powerstats = jsonObject1.getJSONObject("powerstats");
            appearance = jsonObject1.getJSONObject("appearance");
            work = jsonObject1.getJSONObject("work");
            connections = jsonObject1.getJSONObject("connections");
            image = jsonObject1.getJSONObject("image");
            db=new database(this);

            fullname.setText(biography.getString("full-name"));
            alteregos.setText(biography.getString("alter-egos"));

            String alisdata="";
            for(int i=0;i<biography.getJSONArray("aliases").length()-1;i++){
                alisdata+=biography.getJSONArray("aliases").getString(i)+",";
            }
            alisdata+=biography.getJSONArray("aliases").getString(biography.getJSONArray("aliases").length()-1);

            aliases.setText(alisdata);
            firstappearance.setText(biography.getString("first-appearance"));
            placeofbirth.setText(biography.getString("place-of-birth"));
            publishstr=biography.getString("publisher");
            publisher.setText(biography.getString("publisher"));
            alignmentstr=biography.getString("alignment");
            alignment.setText(biography.getString("alignment"));

            intelligenceint=powerstats.getString("intelligence");
            intelligence.setText(powerstats.getString("intelligence"));
            strengthint=powerstats.getString("strength");
            streangth.setText(powerstats.getString("strength"));
            speedint=powerstats.getString("speed");
            speed.setText(powerstats.getString("speed"));
            durabilityint=powerstats.getString("durability");
            durability.setText(powerstats.getString("durability"));
            powerint= powerstats.getString("power");
            power.setText(powerstats.getString("power"));
            combatint=powerstats.getString("combat");
            combat.setText(powerstats.getString("combat"));

            genderstr=appearance.getString("gender");
            gender.setText(appearance.getString("gender"));

            racestr=appearance.getString("race");
            race.setText(appearance.getString("race"));


            for(int i=0;i<appearance.getJSONArray("height").length()-1;i++){
                heightdata+=appearance.getJSONArray("height").getString(i)+",";
            }
            heightdata+=appearance.getJSONArray("height").getString(appearance.getJSONArray("height").length()-1);

            height.setText(heightdata);


            for(int i=0;i<appearance.getJSONArray("weight").length()-1;i++){
                weightdata+=appearance.getJSONArray("weight").getString(i)+",";
            }
            weightdata+=appearance.getJSONArray("weight").getString(appearance.getJSONArray("weight").length()-1);

            weight.setText(weightdata);

            eyecolor.setText(appearance.getString("eye-color"));
            haircolor.setText(appearance.getString("hair-color"));


            occupation.setText(work.getString("occupation"));
            base.setText(work.getString("base"));

            groupaffiliation.setText(connections.getString("group-affiliation"));
            relatives.setText(connections.getString("relatives"));

            imgstr=image.getString("url");
            Glide.with(dashbod.this)
                    .load(image.getString("url"))
                    .into(imageView);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashbode_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                Cursor c1 = db.tbl_show();
                if (c1.getCount() == 0) {
                    if(db.tbl_insert(jsondata,heroid,namestr,genderstr,alignmentstr,racestr,intelligenceint,
                            speedint,strengthint,durabilityint,powerint,combatint,heightdata,weightdata,imgstr,publishstr)){
                        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this, "Not Saved", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (c1.getCount() != 0){
                        c1.moveToFirst();
                        boolean flag=true;
                        while (c1.isAfterLast()==false) {
                            if(c1.getString(1).equals(heroid)){
                                flag=false;
                                Toast.makeText(this, "Already Saved", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            c1.moveToNext();
                        }
                        if(flag){
                            if(db.tbl_insert(jsondata,heroid,namestr,genderstr,alignmentstr,racestr,intelligenceint,
                                    speedint,strengthint,durabilityint,powerint,combatint,heightdata,weightdata,imgstr,publishstr)){
                                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(this, "Not Saved", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
                c1.close();
                return true;
            case R.id.screenshot:

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        System.out.println("Permission Granted");
                        Bitmap bitmap = getBitmapFromView(linearlayout, linearlayout.getChildAt(0).getHeight(), linearlayout.getChildAt(0).getWidth());
                        saveBitmap(bitmap);
//                shareIt();
                    } else
                        requestPermission();
                } else {
                      Bitmap bitmap =getBitmapFromView(linearlayout, linearlayout.getChildAt(0).getHeight(), linearlayout.getChildAt(0).getWidth());
                saveBitmap(bitmap);
//                shareIt();
                }

                return true;

            case R.id.delete:
                 c1 = db.tbl_show();
                if(c1.getCount() != 0){
                    c1.moveToFirst();
                    boolean flag=true;
                    while (c1.isAfterLast()==false) {
                        if(c1.getString(1).equals(heroid)){
                            flag=false;
                            if(db.tbl_delete(heroid)){
                                Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(this, "Deleted Unsuccessfully", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        }
                        c1.moveToNext();
                    }
                    if(flag){
                        Toast.makeText(this, "This record not saved to delete", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(this, "Nothing Saved to delete", Toast.LENGTH_SHORT).show();
                }

                return true;
            case R.id.share:
                shareIt();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    Bitmap getBitmapFromView(View view, int height, int width) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return bitmap;
    }

    private void shareIt() {

        try {
            Bitmap b =getBitmapFromView(linearlayout, linearlayout.getChildAt(0).getHeight(), linearlayout.getChildAt(0).getWidth());
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("image/jpeg");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(getContentResolver(), b, "Title", null);
            Uri imageUri =  Uri.parse(path);
            share.putExtra(Intent.EXTRA_STREAM, imageUri);
            startActivity(Intent.createChooser(share, "Select"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(dashbod.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        } else {
            getBitmapFromView(linearlayout, linearlayout.getChildAt(0).getHeight(), linearlayout.getChildAt(0).getWidth());
        }
    }
    private static final int REQUEST_WRITE_PERMISSION = 123;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getBitmapFromView(linearlayout, linearlayout.getChildAt(0).getHeight(), linearlayout.getChildAt(0).getWidth());
        }
    }



    private void saveBitmap(Bitmap bitmap) {
        File imagePath = new File(Environment.getExternalStorageDirectory() + "/"+namestr+".png"); ////File imagePath
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            Toast.makeText(this, "Screenshot Saved "+Environment.getExternalStorageDirectory() + "/"+namestr+".png", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }

}