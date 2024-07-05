package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Country {

    private String name;
    private String common;
    private String official;
    private String nativeName;
    private String tld;

    public String getCca2() {
        return cca2;
    }

    public void setCca2(String cca2) {
        this.cca2 = cca2;
    }

    private String cca2;
    private String ccn3;
    private String cca3;
    private String cioc;
    private String independenttruestatus;
    private String unMembertruecurrencies;
    // ... other currency related fields

    private String capital;
    private String[] altSpellings;
    private String region;
    private String subregion;
    private String languages;
    // ... other language related fields

    private double lat;
    private double lng;
    private boolean landlocked;
    private String[] borders;
    private double area;
    private String demonyms;
    // ... other demonym related fields

    private String flag;
    private Maps maps;
    private long population;
    private double gini;
    private String fifa;
    private CarSigns car;
    private String[] timezones;
    private String[] continents;
    private Flags flags;
    private CoatOfArms coatOfArms;
    private String startOfWeek;
    private CapitalInfo capitalInfo;

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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public String getOfficial() {
        return official;
    }

    public void setOfficial(String official) {
        this.official = official;
    }

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
