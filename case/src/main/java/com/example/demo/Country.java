package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Country {

    public Object name;
//    private String common;
//    private String official;
//    private String nativeName;
    public Object  tld;

    public String getCca2() {
        return cca2;
    }

    public void setCca2(String cca2) {
        this.cca2 = cca2;
    }

    public String cca2;
public String ccn3;
public String cca3;
public String cioc;
public boolean independenttruestatus;
public Object currencies;
    public String unMember;
//    // ... other currency related fields
//
public Object status;
        public Object idd;
    public Object root;
    public Object suffixes;
public Object capital;
public Object[] altSpellings;
    public String  region;
    public String  subregion;
    public Object languages;
    public Object translations;
//    // ... other language related fields
//
public Object latlng;
    public Object landlocked;
    public Object borders;
    public Object area;
    public Object demonyms;
//    // ... other demonym related fields
//
public String flag;
    public Object maps;
    public Object population;
    public Object gini;
    public Object fifa;
    public Object car;
public Object timezones;
    public Object continents;
    public Object independent;
    public Object alt;
    public Object flags;
    public Object svg;
    public Object coatOfArms;
    public Object startOfWeek;
public Object capitalInfo;

    public PostalCode getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(PostalCode postalCode) {
        this.postalCode = postalCode;
    }

    private PostalCode postalCode;

    public static Country parseJson(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Country.class);
    }

    // Getters and Setters for all fields


    // ... similar getters and setters for all fields

    // Nested classes for Maps, CarSigns, Flags, CoatOfArms, and CapitalInfo (if needed)
    public static class Maps {
        private String googleMaps;
        private String openStreetMaps;

        // Getters and Setters for maps fields
    }

    public static class CarSigns {
        private String[] signs;
        private String side;

        // Getters and Setters for carSigns fields
    }

    public static class Flags {
        private String png;
        private String svg;
        private String alt;

        // Getters and Setters for flags fields
    }

    public static class CoatOfArms {
        private String png;
        private String svg;

        // Getters and Setters for coatOfArms fields
    }

    public static class CapitalInfo {
        private double[] latlng;
        private String postalCode;
        private String format;
        private String regex;

        // Getters and Setters for capitalInfo fields
    }

}
