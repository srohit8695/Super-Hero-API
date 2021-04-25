package com.example.hero_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.SearchView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeScreen extends AppCompatActivity {
    ArrayList<String> list=new ArrayList<>();
    ArrayAdapter<String> adapter,searchadapter;
    ListView listview,listsearch;
    SearchView searchView,serversearchView;
  ArrayList<String> searchList=new ArrayList<>();
Internet_status internet_status;


  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        listview=findViewById(R.id.list);
        internet_status=new Internet_status(this);
    searchView = findViewById(R.id.searchView);
    serversearchView =findViewById(R.id.serversearchView);
    listsearch=findViewById(R.id.listsearch);
    setTitle("Home");
    insert_data_list();

      adapter=new ArrayAdapter<>(HomeScreen.this, android.R.layout.simple_list_item_1,list);
      listview.setAdapter(adapter);


      listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          asynch_call(1,""+adapter.getItem(position));
        }
      });

      listsearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          if (internet_status.internet_status()) {
            asynch_call(1,""+searchadapter.getItem(position));
          } else {
            Toast.makeText(HomeScreen.this, "Check Internet Connectivity", Toast.LENGTH_SHORT).show();
          }
        }
      });

    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        if(list.contains(query)){
          adapter.getFilter().filter(query);
        }else{
          Toast.makeText(HomeScreen.this, "No Hero found",Toast.LENGTH_LONG).show();
        }

        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        adapter.getFilter().filter(newText);
        return false;
      }
    });

    serversearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        if (!query.trim().equals("")) {
          asynch_call(2,query);
        }
        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        if (!newText.trim().equals("")) {
          asynch_call(2,newText);
        }
        return false;
      }
    });


    }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.homescreen_menu, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {

      case R.id.download:
        try {
          Intent i1=new Intent(HomeScreen.this,download_dashboard.class);
          startActivity(i1);
        } catch (Exception e) {
          e.printStackTrace();
        }
        return true;


      default:
        return super.onOptionsItemSelected(item);
    }
  }


  public void asynch_call(int n,String data){
    {
      String[] str=new String[3];

      if(n==1){
        str[0]="https://www.superheroapi.com/api.php/3695526247211386/search/"+data;
        str[1]="GET";
      }else if(n==2){
        searchList.clear();
        str[0]="https://www.superheroapi.com/api.php/3695526247211386/search/"+data;
        str[1]="GET";
      }


      new Asynch_GET(HomeScreen.this, new AsynchCallback() {
        @Override
        public void responsecall(String s1) {

          try{
            JSONObject jsonObject=new JSONObject(s1);
            if(n==1){
              if(jsonObject.getString("response").trim().equals("success")){

                Intent i1=new Intent(HomeScreen.this,dashbod.class);
                i1.putExtra("data",s1);
                startActivity(i1);

              }else{
                Toast.makeText(HomeScreen.this, "Character with given name not found", Toast.LENGTH_SHORT).show();
              }
            }else if(n==2){
              if(jsonObject.getString("response").trim().equals("success")){
                JSONArray jsonArray=jsonObject.getJSONArray("results");
                for(int i=0;i<jsonArray.length();i++){
                  JSONObject jsonObject1=jsonArray.getJSONObject(i);
                  searchList.add(jsonObject1.getString("name"));
                }
                searchadapter=new ArrayAdapter<>(HomeScreen.this, android.R.layout.simple_list_item_1,searchList);
                listsearch.setAdapter(searchadapter);
              }else{
                Toast.makeText(HomeScreen.this, "Character with given name not found", Toast.LENGTH_SHORT).show();
              }

            }

          }
          catch (JSONException e){
            Toast.makeText(HomeScreen.this, "Json Error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
          }
          catch(Exception e){
            e.printStackTrace();

          }
        }

        @Override
        public void exceptioncall(Exception e) {
          Toast.makeText(HomeScreen.this, "Exceptional Issue", Toast.LENGTH_SHORT).show();
        }
      }).execute(str);
    }
  }

  public void insert_data_list(){

    list.add("A-Bomb");
    list.add("Abe Sapien");
    list.add("Abin Sur");
    list.add("Abomination");
    list.add("Abraxas");
    list.add("Absorbing Man");
    list.add("Adam Monroe");
    list.add("Adam Strange");
    list.add("Agent 13");
    list.add("Agent Bob");
    list.add("Agent Zero");
    list.add("Air-Walker");
    list.add("Ajax");
    list.add("Alan Scott");
    list.add("Alex Mercer");
    list.add("Alex Woolsly");
    list.add("Alfred Pennyworth");
    list.add("Alien");
    list.add("Allan Quatermain");
    list.add("Amazo");
    list.add("Ammo");
    list.add("Ando Masahashi");
    list.add("Angel");
    list.add("Angel");
    list.add("Angel Dust");
    list.add("Angel Salvadore");
    list.add("Angela");
    list.add("Animal Man");
    list.add("Annihilus");
    list.add("Ant-Man");
    list.add("Ant-Man II");
    list.add("Anti-Monitor");
    list.add("Anti-Spawn");
    list.add("Anti-Venom");
    list.add("Apocalypse");
    list.add("Aquababy");
    list.add("Aqualad");
    list.add("Aquaman");
    list.add("Arachne");
    list.add("Archangel");
    list.add("Arclight");
    list.add("Ardina");
    list.add("Ares");
    list.add("Ariel");
    list.add("Armor");
    list.add("Arsenal");
    list.add("Astro Boy");
    list.add("Atlas");
    list.add("Atlas");
    list.add("Atom");
    list.add("Atom");
    list.add("Atom Girl");
    list.add("Atom II");
    list.add("Atom III");
    list.add("Atom IV");
    list.add("Aurora");
    list.add("Azazel");
    list.add("Azrael");
    list.add("Aztar");
    list.add("Bane");
    list.add("Banshee");
    list.add("Bantam");
    list.add("Batgirl");
    list.add("Batgirl");
    list.add("Batgirl III");
    list.add("Batgirl IV");
    list.add("Batgirl V");
    list.add("Batgirl VI");
    list.add("Batman");
    list.add("Batman");
    list.add("Batman II");
    list.add("Battlestar");
    list.add("Batwoman V");
    list.add("Beak");
    list.add("Beast");
    list.add("Beast Boy");
    list.add("Beetle");
    list.add("Ben 10");
    list.add("Beta Ray Bill");
    list.add("Beyonder");
    list.add("Big Barda");
    list.add("Big Daddy");
    list.add("Big Man");
    list.add("Bill Harken");
    list.add("Billy Kincaid");
    list.add("Binary");
    list.add("Bionic Woman");
    list.add("Bird-Brain");
    list.add("Bird-Man");
    list.add("Bird-Man II");
    list.add("Birdman");
    list.add("Bishop");
    list.add("Bizarro");
    list.add("Black Abbott");
    list.add("Black Adam");
    list.add("Black Bolt");
    list.add("Black Canary");
    list.add("Black Canary");
    list.add("Black Cat");
    list.add("Black Flash");
    list.add("Black Goliath");

    list.add("Black Knight III");
    list.add("Black Lightning");
    list.add("Black Mamba");
    list.add("Black Manta");
    list.add("Black Panther");
    list.add("Black Widow");
    list.add("Black Widow II");
    list.add("Blackout");
    list.add("Blackwing");
    list.add("Blackwulf");
    list.add("Blade");
    list.add("Blaquesmith");
    list.add("Bling!");
    list.add("Blink");
    list.add("Blizzard");
    list.add("Blizzard");
    list.add("Blizzard II");
    list.add("Blob");
    list.add("Bloodaxe");
    list.add("Bloodhawk");
    list.add("Bloodwraith");
    list.add("Blue Beetle");
    list.add("Blue Beetle");
    list.add("Blue Beetle II");
    list.add("Blue Beetle III");
    list.add("Boba Fett");
    list.add("Bolt");
    list.add("Bomb Queen");
    list.add("Boom-Boom");
    list.add("Boomer");
    list.add("Booster Gold");
    list.add("Box");
    list.add("Box III");
    list.add("Box IV");
    list.add("Brainiac");
    list.add("Brainiac 5");
    list.add("Brother Voodoo");
    list.add("Brundlefly");
    list.add("Buffy");
    list.add("Bullseye");
    list.add("Bumblebee");
    list.add("Bumbleboy");
    list.add("Bushido");
    list.add("Cable");
    list.add("Callisto");
    list.add("Cameron Hicks");
    list.add("Cannonball");
    list.add("Captain America");
    list.add("Captain Atom");
    list.add("Captain Britain");
    list.add("Captain Cold");
    list.add("Captain Epic");
    list.add("Captain Hindsight");
    list.add("Captain Mar-vell");
    list.add("Captain Marvel");
    list.add("Captain Marvel");
    list.add("Captain Marvel II");
    list.add("Captain Midnight");
    list.add("Captain Planet");
    list.add("Captain Universe");
    list.add("Carnage");
    list.add("Cat");
    list.add("Cat II");
    list.add("Catwoman");
    list.add("Cecilia Reyes");
    list.add("Century");
    list.add("Cerebra");
    list.add("Chamber");
    list.add("Chameleon");
    list.add("Changeling");
    list.add("Cheetah");
    list.add("Cheetah II");
    list.add("Cheetah III");
    list.add("Chromos");
    list.add("Chuck Norris");
    list.add("Citizen Steel");
    list.add("Claire Bennet");
    list.add("Clea");
    list.add("Cloak");
    list.add("Clock King");
    list.add("Cogliostro");
    list.add("Colin Wagner");
    list.add("Colossal Boy");
    list.add("Colossus");
    list.add("Copycat");
    list.add("Corsair");
    list.add("Cottonmouth");
    list.add("Crimson Crusader");
    list.add("Crimson Dynamo");
    list.add("Crystal");
    list.add("Curse");
    list.add("Cy-Gor");
    list.add("Cyborg");
    list.add("Cyborg Superman");
    list.add("Cyclops");
    list.add("Cypher");
    list.add("Dagger");
    list.add("Danny Cooper");
    list.add("Daphne Powell");
    list.add("Daredevil");
    list.add("Darkhawk");
    list.add("Darkman");
    list.add("Darkseid");
    list.add("Darkside");
    list.add("Darkstar");
    list.add("Darth Maul");
    list.add("Darth Vader");
    list.add("Dash");
    list.add("Data");
    list.add("Dazzler");
    list.add("Deadman");
    list.add("Deadpool");
    list.add("Deadshot");
    list.add("Deathlok");
    list.add("Deathstroke");
    list.add("Demogoblin");
    list.add("Destroyer");
    list.add("Diamondback");
    list.add("DL Hawkins");
    list.add("Doc Samson");
    list.add("Doctor Doom");
    list.add("Doctor Doom II");
    list.add("Doctor Fate");
    list.add("Doctor Octopus");
    list.add("Doctor Strange");
    list.add("Domino");
    list.add("Donatello");
    list.add("Donna Troy");
    list.add("Doomsday");
    list.add("Doppelganger");
    list.add("Dormammu");
    list.add("Dr Manhattan");
    list.add("Drax the Destroyer");
    list.add("Ego");
    list.add("Elastigirl");
    list.add("Electro");
    list.add("Elektra");
    list.add("Elle Bishop");
    list.add("Elongated Man");
    list.add("Emma Frost");
    list.add("Enchantress");
    list.add("Energy");
    list.add("ERG-1");

    list.add("Ethan Hunt");
    list.add("Etrigan");
    list.add("Evil Deadpool");
    list.add("Evilhawk");
    list.add("Exodus");
    list.add("Fabian Cortez");
    list.add("Falcon");
    list.add("Fallen One II");
    list.add("Faora");
    list.add("Feral");
    list.add("Fighting Spirit");
    list.add("Fin Fang Foom");
    list.add("Firebird");
    list.add("Firelord");
    list.add("Firestar");
    list.add("Firestorm");
    list.add("Firestorm");
    list.add("Fixer");
    list.add("Flash");
    list.add("Flash Gordon");
    list.add("Flash II");
    list.add("Flash III");
    list.add("Flash IV");
    list.add("Forge");
    list.add("Franklin Richards");
    list.add("Franklin Storm");
    list.add("Frenzy");
    list.add("Frigga");
    list.add("Galactus");
    list.add("Gambit");
    list.add("Gamora");
    list.add("Garbage Man");
    list.add("Gary Bell");
    list.add("General Zod");
    list.add("Genesis");
    list.add("Ghost Rider");
    list.add("Ghost Rider II");
    list.add("Giant-Man");
    list.add("Giant-Man II");
    list.add("Giganta");
    list.add("Gladiator");
    list.add("Goblin Queen");
    list.add("Godzilla");
    list.add("Gog");
    list.add("Goku");
    list.add("Goliath");
    list.add("Goliath");
    list.add("Goliath");
    list.add("Goliath IV");
    list.add("Gorilla Grodd");
    list.add("Granny Goodness");
    list.add("Gravity");
    list.add("Greedo");
    list.add("Green Arrow");
    list.add("Green Goblin");
    list.add("Green Goblin II");
    list.add("Green Goblin III");
    list.add("Green Goblin IV");
    list.add("Groot");
    list.add("Guardian");
    list.add("Guy Gardner");
    list.add("Hal Jordan");
    list.add("Han Solo");
    list.add("Hancock");
    list.add("Harley Quinn");
    list.add("Harry Potter");
    list.add("Havok");
    list.add("Hawk");
    list.add("Hawkeye");
    list.add("Hawkeye II");
    list.add("Hawkgirl");
    list.add("Hawkman");
    list.add("Hawkwoman");
    list.add("Hawkwoman II");
    list.add("Hawkwoman III");
    list.add("Heat Wave");
    list.add("Hela");
    list.add("Hellboy");
    list.add("Hellcat");
    list.add("Hellstorm");
    list.add("Hercules");
    list.add("Hiro Nakamura");
    list.add("Hit-Girl");
    list.add("Hobgoblin");
    list.add("Hollow");
    list.add("Hope Summers");
    list.add("Howard the Duck");
    list.add("Hulk");
    list.add("Human Torch");
    list.add("Huntress");
    list.add("Husk");
    list.add("Hybrid");
    list.add("Hydro-Man");
    list.add("Hyperion");
    list.add("Iceman");
    list.add("Impulse");
    list.add("Indiana Jones");
    list.add("Indigo");
    list.add("Ink");
    list.add("Invisible Woman");
    list.add("Iron Fist");
    list.add("Iron Man");
    list.add("Iron Monger");
    list.add("Isis");
    list.add("Jack Bauer");
    list.add("Jack of Hearts");
    list.add("Jack-Jack");
    list.add("James Bond");
    list.add("James T. Kirk");
    list.add("Jar Jar Binks");
    list.add("Jason Bourne");
    list.add("Jean Grey");
    list.add("Jean-Luc Picard");
    list.add("Jennifer Kale");
    list.add("Jesse Quick");
    list.add("Jessica Cruz");
    list.add("Jessica Jones");
    list.add("Jessica Sanders");
    list.add("Jigsaw");
    list.add("Jim Powell");
    list.add("JJ Powell");
    list.add("Johann Krauss");
    list.add("John Constantine");
    list.add("John Stewart");
    list.add("John Wraith");
    list.add("Joker");
    list.add("Jolt");
    list.add("Jubilee");
    list.add("Judge Dredd");
    list.add("Juggernaut");
    list.add("Junkpile");
    list.add("Justice");
    list.add("Jyn Erso");
    list.add("K-2SO");
    list.add("Kang");
    list.add("Kathryn Janeway");
    list.add("Katniss Everdeen");
    list.add("Kevin 11");
    list.add("Kick-Ass");
    list.add("Kid Flash");
    list.add("Kid Flash II");
    list.add("Killer Croc");
    list.add("Killer Frost");
    list.add("Kilowog");
    list.add("King Kong");
    list.add("King Shark");
    list.add("Kingpin");
    list.add("Klaw");
    list.add("Kool-Aid Man");
    list.add("Kraven II");
    list.add("Kraven the Hunter");
    list.add("Krypto");
    list.add("Kyle Rayner");
    list.add("Kylo Ren");
    list.add("Lady Bullseye");
    list.add("Lady Deathstrike");

    list.add("Leader");
    list.add("Leech");
    list.add("Legion");
    list.add("Leonardo");
    list.add("Lex Luthor");
    list.add("Light Lass");
    list.add("Lightning Lad");
    list.add("Lightning Lord");
    list.add("Living Brain");
    list.add("Living Tribunal");
    list.add("Liz Sherman");
    list.add("Lizard");
    list.add("Lobo");
    list.add("Loki");
    list.add("Longshot");
    list.add("Luke Cage");
    list.add("Luke Campbell");
    list.add("Luke Skywalker");
    list.add("Luna");
    list.add("Lyja");
    list.add("Mach-IV");
    list.add("Machine Man");
    list.add("Magneto");
    list.add("Magog");
    list.add("Magus");
    list.add("Man of Miracles");
    list.add("Man-Bat");
    list.add("Man-Thing");
    list.add("Man-Wolf");
    list.add("Mandarin");
    list.add("Mantis");
    list.add("Martian Manhunter");
    list.add("Marvel Girl");
    list.add("Master Brood");
    list.add("Master Chief");
    list.add("Match");
    list.add("Matt Parkman");
    list.add("Maverick");
    list.add("Maxima");
    list.add("Maya Herrera");
    list.add("Medusa");
    list.add("Meltdown");
    list.add("Mephisto");
    list.add("Mera");
    list.add("Metallo");
    list.add("Metamorpho");
    list.add("Meteorite");
    list.add("Metron");
    list.add("Micah Sanders");
    list.add("Michelangelo");
    list.add("Micro Lad");
    list.add("Mimic");
    list.add("Minna Murray");
    list.add("Misfit");
    list.add("Miss Martian");
    list.add("Mister Fantastic");
    list.add("Mister Freeze");
    list.add("Mister Knife");
    list.add("Mister Mxyzptlk");
    list.add("Mister Sinister");
    list.add("Mister Zsasz");
    list.add("Mockingbird");
    list.add("MODOK");
    list.add("Mogo");
    list.add("Mohinder Suresh");
    list.add("Moloch");
    list.add("Molten Man");
    list.add("Monarch");
    list.add("Monica Dawson");
    list.add("Moon Knight");
    list.add("Moonstone");
    list.add("Morlun");
    list.add("Morph");
    list.add("Moses Magnum");
    list.add("Mr Immortal");
    list.add("Mr Incredible");
    list.add("Ms Marvel II");
    list.add("Multiple Man");
    list.add("Mysterio");
    list.add("Mystique");
    list.add("Namor");
    list.add("Namor");
    list.add("Namora");
    list.add("Namorita");
    list.add("Naruto Uzumaki");
    list.add("Nathan Petrelli");
    list.add("Nebula");
    list.add("Negasonic Teenage Warhead");

    list.add("Nick Fury");
    list.add("Nightcrawler");
    list.add("Nightwing");
    list.add("Niki Sanders");
    list.add("Nina Theroux");
    list.add("Nite Owl II");
    list.add("Northstar");
    list.add("Nova");
    list.add("Nova");
    list.add("Odin");
    list.add("Offspring");
    list.add("Omega Red");
    list.add("Omniscient");
    list.add("One Punch Man");
    list.add("One-Above-All");
    list.add("Onslaught");
    list.add("Oracle");
    list.add("Osiris");
    list.add("Overtkill");
    list.add("Ozymandias");
    list.add("Parademon");
    list.add("Paul Blart");
    list.add("Penance");
    list.add("Penance I");
    list.add("Penance II");
    list.add("Penguin");
    list.add("Phantom");
    list.add("Phantom Girl");
    list.add("Phoenix");
    list.add("Plantman");
    list.add("Plastic Lad");
    list.add("Plastic Man");
    list.add("Plastique");
    list.add("Poison Ivy");
    list.add("Polaris");
    list.add("Power Girl");
    list.add("Power Man");
    list.add("Predator");
    list.add("Professor X");
    list.add("Professor Zoom");
    list.add("Psylocke");
    list.add("Punisher");
    list.add("Purple Man");
    list.add("Pyro");
    list.add("Q");
    list.add("Quantum");
    list.add("Question");
    list.add("Quicksilver");
    list.add("Quill");
    list.add("Ra's Al Ghul");
    list.add("Rachel Pirzad");
    list.add("Rambo");
    list.add("Raphael");
    list.add("Raven");
    list.add("Ray");
    list.add("Razor-Fist II");
    list.add("Red Arrow");
    list.add("Red Hood");
    list.add("Red Hulk");
    list.add("Red Mist");
    list.add("Red Robin");
    list.add("Red Skull");
    list.add("Red Tornado");
    list.add("Redeemer II");
    list.add("Redeemer III");
    list.add("Renata Soliz");
    list.add("Rey");
    list.add("Rhino");
    list.add("Rick Flag");
    list.add("Riddler");
    list.add("Rip Hunter");
    list.add("Ripcord");
    list.add("Robin");
    list.add("Robin II");
    list.add("Robin III");
    list.add("Robin V");
    list.add("Robin VI");
    list.add("Rocket Raccoon");
    list.add("Rogue");
    list.add("Ronin");
    list.add("Rorschach");
    list.add("Sabretooth");
    list.add("Sage");
    list.add("Sandman");
    list.add("Sasquatch");
    list.add("Sauron");
    list.add("Savage Dragon");
    list.add("Scarecrow");
    list.add("Scarlet Spider");
    list.add("Scarlet Spider II");
    list.add("Scarlet Witch");
    list.add("Scorpia");
    list.add("Scorpion");
    list.add("Sebastian Shaw");
    list.add("Sentry");
    list.add("Shadow King");
    list.add("Shadow Lass");
    list.add("Shadowcat");
    list.add("Shang-Chi");
    list.add("Shatterstar");
    list.add("She-Hulk");
    list.add("She-Thing");
    list.add("Shocker");
    list.add("Shriek");
    list.add("Shrinking Violet");
    list.add("Sif");
    list.add("Silk");
    list.add("Silk Spectre");
    list.add("Silk Spectre II");
    list.add("Silver Surfer");
    list.add("Silverclaw");
    list.add("Simon Baz");
    list.add("Sinestro");
    list.add("Siren");
    list.add("Siren II");
    list.add("Siryn");
    list.add("Skaar");
    list.add("Snake-Eyes");
    list.add("Snowbird");
    list.add("Sobek");
    list.add("Solomon Grundy");
    list.add("Songbird");
    list.add("Space Ghost");
    list.add("Spawn");
    list.add("Spectre");
    list.add("Speedball");
    list.add("Speedy");
    list.add("Speedy");
    list.add("Spider-Carnage");
    list.add("Spider-Girl");
    list.add("Spider-Gwen");
    list.add("Spider-Man");
    list.add("Spider-Man");
    list.add("Spider-Man");
    list.add("Spider-Woman");
    list.add("Spider-Woman II");
    list.add("Spider-Woman III");
    list.add("Spider-Woman IV");
    list.add("Spock");
    list.add("Spyke");
    list.add("Stacy X");
    list.add("Star-Lord");
    list.add("Stardust");
    list.add("Starfire");
    list.add("Stargirl");
    list.add("Static");
    list.add("Steel");
    list.add("Stephanie Powell");
    list.add("Steppenwolf");
    list.add("Storm");
    list.add("Stormtrooper");
    list.add("Sunspot");
    list.add("Superboy");
    list.add("Superboy-Prime");
    list.add("Supergirl");
    list.add("Superman");
    list.add("Swamp Thing");
    list.add("Swarm");
    list.add("Sylar");
    list.add("Synch");
    list.add("T-1000");
    list.add("T-800");
    list.add("T-850");
    list.add("T-X");
    list.add("Taskmaster");
    list.add("Tempest");
    list.add("Thanos");
    list.add("The Cape");
    list.add("The Comedian");
    list.add("Thing");
    list.add("Thor");
    list.add("Thor Girl");
    list.add("Thunderbird");
    list.add("Thunderbird II");
    list.add("Thunderbird III");
    list.add("Thunderstrike");
    list.add("Thundra");
    list.add("Tiger Shark");
    list.add("Tigra");
    list.add("Tinkerer");
    list.add("Titan");
    list.add("Toad");
    list.add("Toxin");
    list.add("Toxin");
    list.add("Tracy Strauss");
    list.add("Trickster");
    list.add("Trigon");
    list.add("Triplicate Girl");
    list.add("Triton");
    list.add("Two-Face");
    list.add("Ultragirl");
    list.add("Ultron");
    list.add("Utgard-Loki");
    list.add("Vagabond");
    list.add("Valerie Hart");
    list.add("Valkyrie");
    list.add("Vanisher");
    list.add("Vegeta");
    list.add("Venom");
    list.add("Venom II");
    list.add("Venom III");
    list.add("Venompool");
    list.add("Vertigo II");
    list.add("Vibe");
    list.add("Vindicator");
    list.add("Vindicator");
    list.add("Violator");
    list.add("Violet Parr");
    list.add("Vision");
    list.add("Vision II");
    list.add("Vixen");
    list.add("Vulcan");
    list.add("Vulture");
    list.add("Walrus");
    list.add("War Machine");
    list.add("Warbird");
    list.add("Warlock");
    list.add("Warp");
    list.add("Warpath");
    list.add("Wasp");
    list.add("Watcher");
    list.add("Weapon XI");
    list.add("White Canary");
    list.add("White Queen");
    list.add("Wildfire");
    list.add("Winter Soldier");
    list.add("Wiz Kid");
    list.add("Wolfsbane");
    list.add("Wolverine");
    list.add("Wonder Girl");
    list.add("Wonder Man");
    list.add("Wonder Woman");
    list.add("Wondra");
    list.add("Wyatt Wingfoot");
    list.add("X-23");
    list.add("X-Man");
    list.add("Yellow Claw");
    list.add("Yellowjacket");
    list.add("Yellowjacket II");
    list.add("Ymir");
    list.add("Yoda");
    list.add("Zatanna");
    list.add("Zoom");
  }

  @Override
  public void onBackPressed() {
    try{
      AlertDialog.Builder close_dialog = new AlertDialog.Builder(this);
      close_dialog.setTitle("Exit Application");
      close_dialog.setMessage("Wish to exit application?");
      close_dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
          ActivityCompat.finishAffinity(HomeScreen.this);
        }
      });
      close_dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

        }
      });
      close_dialog.show();
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }


}