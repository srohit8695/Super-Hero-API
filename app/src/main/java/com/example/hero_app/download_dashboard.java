package com.example.hero_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class download_dashboard extends AppCompatActivity {
    ListView list;
    ArrayList<Hero> data=new ArrayList<>();
    HeroAdapter heroAdapter;
    database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_dashboard);
        setTitle("Downloads");

        try {
            list=findViewById(R.id.list);
            db=new database(this);

            Cursor c1 = db.tbl_show();
            if (c1.getCount() != 0){
                c1.moveToFirst();
                while (c1.isAfterLast()==false) {
                    data.add(new Hero(c1.getString(0),c1.getString(1),c1.getString(2),c1.getString(3),c1.getString(4),c1.getString(5),
                            c1.getString(6),c1.getString(7),c1.getString(8),c1.getString(9),c1.getString(10),c1.getString(11),c1.getString(12),
                            c1.getString(13),c1.getString(14),c1.getString(15)));
                    c1.moveToNext();
                }
            }
            c1.close();

            heroAdapter=new HeroAdapter(download_dashboard.this,R.layout.herolist_template,data);
            list.setAdapter(heroAdapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Hero hero=data.get(position);
                    Intent i1=new Intent(download_dashboard.this,dashbod.class);
                    i1.putExtra("data",hero.getJsondata());
                    startActivity(i1);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.download_dashbod_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter:
                sector_data();
                return true;

            case R.id.nameasc:
                try {
                    Collections.sort(data, new NameComparatorAsc());
                    heroAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.namedsc:
                try {
                    Collections.sort(data, new NameComparatorDes());
                    heroAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.intellasc:
                try {
                    Collections.sort(data, new ComparatorAsc("intelligence"));
                    heroAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.intelldsc:
                try {
                    Collections.sort(data, new ComparatorDes("intelligence"));
                    heroAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.powerlow:
                try {
                    Collections.sort(data, new ComparatorAsc("power"));
                    heroAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.powerhigh:
                try {
                    Collections.sort(data, new ComparatorDes("power"));
                    heroAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.heighlow:
                try {
                    Collections.sort(data, new ComparatorAsc("height"));
                    heroAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.heighthigh:
                try {
                    Collections.sort(data, new ComparatorDes("height"));
                    heroAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.weightlow:
                try {
                    Collections.sort(data, new ComparatorAsc("weight"));
                    heroAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.weighthigh:
                try {
                    Collections.sort(data, new ComparatorDes("weight"));
                    heroAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.streangthlow:
                try {
                    Collections.sort(data, new ComparatorAsc("streangth"));
                    heroAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.streangthhigh:
                try {
                    Collections.sort(data, new ComparatorDes("streangth"));
                    heroAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.speedlow:
                try {
                    Collections.sort(data, new ComparatorAsc("speed"));
                    heroAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.speedhigh:
                try {
                    Collections.sort(data, new ComparatorDes("speed"));
                    heroAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }


    CheckBox male,female,good,bad;
    Button apply;
    public void sector_data(){
        try{
            final Dialog dialog = new Dialog(download_dashboard.this);
            dialog.setContentView(R.layout.filter_layout);

            try {
                male = dialog.findViewById(R.id.male);
                female = dialog.findViewById(R.id.female);
                good = dialog.findViewById(R.id.good);
                bad = dialog.findViewById(R.id.bad);
                apply = dialog.findViewById(R.id.apply);

                apply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
    int chk=0;
                        ArrayList<Hero> datafilter=new ArrayList<>();
                        data.clear();
                        Cursor c1 = db.tbl_show();
                        if (c1.getCount() != 0){
                            c1.moveToFirst();
                            while (c1.isAfterLast()==false) {
                                data.add(new Hero(c1.getString(0),c1.getString(1),c1.getString(2),c1.getString(3),c1.getString(4),c1.getString(5),
                                        c1.getString(6),c1.getString(7),c1.getString(8),c1.getString(9),c1.getString(10),c1.getString(11),c1.getString(12),
                                        c1.getString(13),c1.getString(14),c1.getString(15)));
                                c1.moveToNext();
                            }
                        }
                        c1.close();

                        if(male.isChecked()){
                            chk++;

                        }

                        if(female.isChecked()){
                            chk++;

                        }

                        if(good.isChecked()){
                            chk++;

                        }

                        if(bad.isChecked()){
                            chk++;

                        }


                        if(chk==1){
                            if(male.isChecked()){
                                for(Hero hero:data){
                                    if(hero.getGenderstr().trim().equals("Male")){
                                        datafilter.add(hero);
                                    }
                                }

                            }

                            else if(female.isChecked()){
                                for(Hero hero:data){
                                    if(hero.getGenderstr().trim().equals("Female")){
                                        datafilter.add(hero);
                                    }
                                }
                            }

                            else if(good.isChecked()){
                                for(Hero hero:data){
                                    if(hero.getAlignmentstr().trim().equals("good")){
                                        datafilter.add(hero);
                                    }
                                }
                            }

                            else if(bad.isChecked()){
                                for(Hero hero:data){
                                    if(hero.getAlignmentstr().trim().equals("bad")){
                                        datafilter.add(hero);
                                    }
                                }
                            }
                        }
                        else if(chk==2){
                            if(male.isChecked()&&good.isChecked()){
                                for(Hero hero:data){
                                    if(hero.getGenderstr().trim().equals("Male")&&hero.getAlignmentstr().trim().equals("good")){
                                        datafilter.add(hero);
                                    }
                                }

                            }
                            else if(male.isChecked()&&bad.isChecked()){
                                for(Hero hero:data){
                                    if(hero.getGenderstr().trim().equals("Male")&&hero.getAlignmentstr().trim().equals("bad")){
                                        datafilter.add(hero);
                                    }
                                }
                            }
                            else if(female.isChecked()&&good.isChecked()){
                                for(Hero hero:data){
                                    if(hero.getGenderstr().trim().equals("Female")&&hero.getAlignmentstr().trim().equals("good")){
                                        datafilter.add(hero);
                                    }
                                }
                            }
                            else if(female.isChecked()&&bad.isChecked()){
                                for(Hero hero:data){
                                    if(hero.getGenderstr().trim().equals("Female")&&hero.getAlignmentstr().trim().equals("bad")){
                                        datafilter.add(hero);
                                    }
                                }
                            }else if((female.isChecked()&&male.isChecked())||(bad.isChecked()&&good.isChecked())){
                                for(Hero hero:data){
                                        datafilter.add(hero);

                                }
                            }
                        }else if(chk==3){
                            if(male.isChecked()&&female.isChecked()&&good.isChecked()){
                                for(Hero hero:data){
                                    if(hero.getAlignmentstr().trim().equals("good")){
                                        datafilter.add(hero);
                                    }
                                }

                            }else if(male.isChecked()&&female.isChecked()&&bad.isChecked()){
                                for(Hero hero:data){
                                    if(hero.getAlignmentstr().trim().equals("bad")){
                                        datafilter.add(hero);
                                    }
                                }

                            }else if(male.isChecked()&&good.isChecked()&&bad.isChecked()){
                                for(Hero hero:data){
                                    if(hero.getGenderstr().trim().equals("Male")){
                                        datafilter.add(hero);
                                    }
                                }

                            }else if(female.isChecked()&&good.isChecked()&&bad.isChecked()){
                                for(Hero hero:data){
                                    if(hero.getGenderstr().trim().equals("Female")){
                                        datafilter.add(hero);
                                    }
                                }

                            }
                        }


                        if (chk>0) {
                            data.clear();
//                            data=datafilter;
                            for(int i=0;i<datafilter.size();i++){
                                data.add(datafilter.get(i));
                            }

                            heroAdapter.notifyDataSetChanged();
                        }
                        dialog.dismiss();

                    }
                });

                dialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


}

class NameComparatorAsc implements Comparator<Hero> {
    @Override
    public int compare(Hero lhs, Hero rhs) {
        try {
            try {
                return lhs.getNamestr().compareToIgnoreCase(rhs.getNamestr());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

 class NameComparatorDes implements Comparator<Hero> {
    @Override
    public int compare(Hero lhs, Hero rhs) {
        try {

            return rhs.getNamestr().compareToIgnoreCase(lhs.getNamestr());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

 class ComparatorAsc implements Comparator<Hero> {
    String comparefor="";
     public ComparatorAsc(String data) {
         comparefor=data;
     }

     @Override
    public int compare(Hero lhs, Hero rhs) {
        try {
            Double distance = null;
            Double distance1= null;
            if (comparefor.trim().equals("intelligence")) {
                distance = Double.valueOf(lhs.getIntelligenceint());
                distance1 = Double.valueOf(rhs.getIntelligenceint());
            } else if (comparefor.trim().equals("power")) {
                distance = Double.valueOf(lhs.getPowerint());
                distance1 = Double.valueOf(rhs.getPowerint());
            }else   if (comparefor.trim().equals("height")) {
                String ht1[]=lhs.getHeightint().split(",");
                String ht12[]=ht1[1].split("cm");
                distance = Double.valueOf(ht12[0]);

                String ht2[]=rhs.getHeightint().split(",");
                String ht22[]=ht2[1].split("cm");
                distance1 = Double.valueOf(ht22[0]);
            } else if (comparefor.trim().equals("weight")) {
                String ht1[]=lhs.getWeightint().split(",");
                String ht12[]=ht1[1].split("kg");
                distance = Double.valueOf(ht12[0]);

                String ht2[]=rhs.getWeightint().split(",");
                String ht22[]=ht2[1].split("kg");
                distance1 = Double.valueOf(ht22[0]);
            }else if (comparefor.trim().equals("speed")) {
                distance = Double.valueOf(lhs.getSpeedint());
                distance1 = Double.valueOf(rhs.getSpeedint());
            }else if (comparefor.trim().equals("streangth")) {
                distance = Double.valueOf(lhs.getStrengthint());
                distance1 = Double.valueOf(rhs.getStrengthint());
            }
            if (distance.compareTo(distance1) < 0) {
                return -1;
            } else if (distance.compareTo(distance1) > 0) {
                return 1;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

class ComparatorDes implements Comparator<Hero> {
    String comparefor="";
    public ComparatorDes(String data) {
        comparefor=data;
    }

    @Override
    public int compare(Hero lhs, Hero rhs) {
        try {
            Double distance = null;
            Double distance1 = null;
            if (comparefor.trim().equals("intelligence")) {
                distance = Double.valueOf(lhs.getIntelligenceint());
                distance1 = Double.valueOf(rhs.getIntelligenceint());
            } else if (comparefor.trim().equals("power")) {
                distance = Double.valueOf(lhs.getPowerint());
                distance1 = Double.valueOf(rhs.getPowerint());
            }else    if (comparefor.trim().equals("height")) {
                String ht1[]=lhs.getHeightint().split(",");
                String ht12[]=ht1[1].split("cm");
                distance = Double.valueOf(ht12[0]);

                String ht2[]=rhs.getHeightint().split(",");
                String ht22[]=ht2[1].split("cm");
                distance1 = Double.valueOf(ht22[0]);
            } else if (comparefor.trim().equals("weight")) {
                String ht1[]=lhs.getWeightint().split(",");
                String ht12[]=ht1[1].split("kg");
                distance = Double.valueOf(ht12[0]);

                String ht2[]=rhs.getWeightint().split(",");
                String ht22[]=ht2[1].split("kg");
                distance1 = Double.valueOf(ht22[0]);
            }else if (comparefor.trim().equals("speed")) {
                distance = Double.valueOf(lhs.getSpeedint());
                distance1 = Double.valueOf(rhs.getSpeedint());
            }else if (comparefor.trim().equals("streangth")) {
                distance = Double.valueOf(lhs.getStrengthint());
                distance1 = Double.valueOf(rhs.getStrengthint());
            }
            if (distance.compareTo(distance1) > 0) {
                return -1;
            } else if (distance.compareTo(distance1) < 0) {
                return 1;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
