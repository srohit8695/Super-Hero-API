package com.example.hero_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;




public class HeroAdapter extends ArrayAdapter<Hero> {
    List<Hero> datalist=new ArrayList<>();
    //the list values in the List of type hero
    //activity context
    Context context;

    //the layout resource file for the list items
    int resource;

    //constructor initializing the values
    public HeroAdapter(Context context, int resource, List<Hero> dataList1) {
        super(context, resource, dataList1);
        this.context = context;
        this.resource = resource;
        datalist = dataList1;
    }

    //this will return the ListView Item as a View
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //we need to get the view of the xml for our list item
        //And for this we need a layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View view = layoutInflater.inflate(resource, null, false);

        ImageView image=view.findViewById(R.id.image);
        TextView name = view.findViewById(R.id.name);
        TextView intelligenc = view.findViewById(R.id.intelligenc);
        TextView speed = view.findViewById(R.id.speed);
        TextView power = view.findViewById(R.id.power);
        TextView height = view.findViewById(R.id.height);
        TextView weight = view.findViewById(R.id.weight);
        TextView publisher = view.findViewById(R.id.publisher);
        TextView strenght=view.findViewById(R.id.strenght);

        //getting the hero of the specified position
        try {
            Hero data = datalist.get(position);

            name.setText(data.getNamestr());
            intelligenc.setText(data.getIntelligenceint());
            speed.setText(data.getSpeedint());
            power.setText(data.getPowerint());
            height.setText(data.getHeightint());
            weight.setText(data.getWeightint());
            publisher.setText(data.getPublish());
            strenght.setText(data.getStrengthint());
            Glide.with(context)
                    .load(data.getImgstr())
                    .into(image);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

}