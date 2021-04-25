package com.example.hero_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {
    String table="HERO_TABEL";
    String data="DATA";
    String movie_id="HERO_ID",genderstr="GENDER",alignmentstr="ALIGNMENT",racestr="RACE",namestr="NAME",intelligenceint="INTELLIGENCE",speedint="SPEED";
    String strengthint="STREANGTH",durabilityint="DURABILITY",powerint="POWER",combatint="COMBAT",heightint="HEIGHT",weightint="WEIGHT",imgstr="IMAGE",publisher="PUBLISHER";




    public database( Context context) {
        super(context, "hero.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+table+"(DATA TEXT,HERO_ID TEXT,NAME TEXT,GENDER TEXT,ALIGNMENT TEXT,RACE TEXT,INTELLIGENCE TEXT,SPEED TEXT,STREANGTH TEXT,DURABILITY TEXT,POWER TEXT,COMBAT TEXT,HEIGHT TEXT,WEIGHT TEXT,IMAGE TEXT,PUBLISHER TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+table);
        onCreate(db);
    }

    public boolean tbl_insert(String info,String id,String name,String gender,String alignment,String race,String intell,
                              String speed,String streangth,String durable,String power,String combat,String height,String weight,String img,String publish){
        SQLiteDatabase dbms=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(data,info);
        cv.put(movie_id,id);
        cv.put(namestr,name);
        cv.put(genderstr,gender);
        cv.put(alignmentstr,alignment);
        cv.put(racestr,race);
        cv.put(intelligenceint,intell);
        cv.put(speedint,speed);
        cv.put(strengthint,streangth);
        cv.put(durabilityint,durable);
        cv.put(powerint,power);
        cv.put(combatint,combat);
        cv.put(heightint,height);
        cv.put(weightint,weight);
        cv.put(imgstr,img);
        cv.put(publisher,publish);
        if(dbms.insert(table,null,cv)!=-1){
            return true;
        }
        return false;
    }

    public Cursor tbl_show () {
        SQLiteDatabase dblt = this.getWritableDatabase();
        String s = "SELECT * FROM " + table;
        Cursor cr = dblt.rawQuery(s, null);
        return cr;
    }

    public boolean tbl_delete(String name)
    {
        SQLiteDatabase dbms=this.getWritableDatabase();
        return dbms.delete(table, movie_id + "=" + name, null) > 0;
    }

}
