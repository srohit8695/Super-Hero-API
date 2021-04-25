package com.example.hero_app;

public class Hero {
    String jsondata="";
    String genderstr="";
    String alignmentstr="";
    String racestr="";
    String namestr="";
    String heroid="";
    String intelligenceint,speedint,strengthint,durabilityint,powerint,combatint,heightint,weightint,imgstr,publish;

    public Hero(String jsondata, String heroid, String namestr, String genderstr, String alignmentstr, String racestr,
                String intelligenceint, String speedint, String strengthint, String durabilityint, String powerint, String combatint,
                String heightint, String weightint,String imgstr,String publish) {
        this.jsondata = jsondata;
        this.genderstr = genderstr;
        this.alignmentstr = alignmentstr;
        this.racestr = racestr;
        this.namestr = namestr;
        this.heroid = heroid;
        this.intelligenceint = intelligenceint;
        this.speedint = speedint;
        this.strengthint = strengthint;
        this.durabilityint = durabilityint;
        this.powerint = powerint;
        this.combatint = combatint;
        this.heightint = heightint;
        this.weightint = weightint;
        this.imgstr=imgstr;
        this.publish=publish;
    }

    public String getJsondata() {
        return jsondata;
    }

    public String getGenderstr() {
        return genderstr;
    }

    public String getAlignmentstr() {
        return alignmentstr;
    }

    public String getRacestr() {
        return racestr;
    }

    public String getNamestr() {
        return namestr;
    }

    public String getHeroid() {
        return heroid;
    }

    public String getIntelligenceint() {
        return intelligenceint;
    }

    public String getSpeedint() {
        return speedint;
    }

    public String getStrengthint() {
        return strengthint;
    }

    public String getDurabilityint() {
        return durabilityint;
    }

    public String getPowerint() {
        return powerint;
    }

    public String getCombatint() {
        return combatint;
    }

    public String getHeightint() {
        return heightint;
    }

    public String getWeightint() {
        return weightint;
    }

    public String getImgstr() {
        return imgstr;
    }

    public String getPublish() {
        return publish;
    }
}
