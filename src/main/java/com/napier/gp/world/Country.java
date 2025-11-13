package com.napier.gp.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Country {
    private String _code;
    private String _name;
    private String _continent;
    private String _region;
    private Double _surfaceArea;
    private int _indepYear;
    private int _population;
    private Double _lifeExpectancy;
    private Double _gnp;
    private Double _gnpOld;
    private String _localName;
    private String _governmentForm;
    private String _headOfState;
    private int _capital;
    private String _code2;

    private List<City> _cities;
    private List<CountryLanguage> _languages;

    public Country(String code, String name, String continent, String region, Double surfaceArea, int indepYear, int population, Double lifeExpectancy, Double gnp, Double gnpOld, String localName, String governmentForm, String headOfState, int capital, String code2) {
        _code = code;
        _name = name;
        _continent = continent;
        _region = region;
        _surfaceArea = surfaceArea;
        _indepYear = indepYear;
        _population = population;
        _lifeExpectancy = lifeExpectancy;
        _gnp = gnp;
        _gnpOld = gnpOld;
        _localName = localName;
        _governmentForm = governmentForm;
        _headOfState = headOfState;
        _capital = capital;
        _code2 = code2;
        _cities = new ArrayList<>();
        _languages = new ArrayList<>();
    }

    public String getCode() {
        return _code;
    }

    public void setCode(String code) {
        _code = code;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getContinent() {
        return _continent;
    }

    public void setContinent(String continent) {
        _continent = continent;
    }

    public String getRegion() {
        return _region;
    }

    public void setRegion(String region) {
        _region = region;
    }

    public Double getSurfaceArea() {
        return _surfaceArea;
    }

    public void setSurfaceArea(Double surfaceArea) {
        _surfaceArea = surfaceArea;
    }

    public int getIndepYear() {
        return _indepYear;
    }

    public void setIndepYear(int indepYear) {
        _indepYear = indepYear;
    }

    public int getPopulation() { return _population; }

    public void setPopulation(int population) { _population = population; }

    public Double getLifeExpectancy() {
        return _lifeExpectancy;
    }

    public void setLifeExpectancy(Double lifeExpectancy) {
        _lifeExpectancy = lifeExpectancy;
    }

    public Double getGNP() {
        return _gnp;
    }

    public void setGNP(Double GNP) {
        this._gnp = GNP;
    }

    public Double getGNPOld() {
        return _gnpOld;
    }

    public void setGNPOld(Double GNPOld) {
        this._gnpOld = GNPOld;
    }

    public String getLocalName() {
        return _localName;
    }

    public void setLocalName(String localName) {
        _localName = localName;
    }

    public String getGovernmentForm() {
        return _governmentForm;
    }

    public void setGovernmentForm(String governmentForm) {
        _governmentForm = governmentForm;
    }

    public String getHeadOfState() {
        return _headOfState;
    }

    public void setHeadOfState(String headOfState) {
        _headOfState = headOfState;
    }

    public int getCapital() {
        return _capital;
    }

    public void setCapital(int capital) {
        _capital = capital;
    }

    public String getCode2() {
        return _code2;
    }

    public void setCode2(String code2) {
        _code2 = code2;
    }

    public List<City> getCities() { return _cities; }

    public void addCity(City city) { _cities.add(city); }

    public void removeCity(City city) { _cities.remove(city); }

    public List<CountryLanguage> getLanguages() { return _languages; }

    public void addLanguage(CountryLanguage language) { _languages.add(language); }

    public void removeLanguage(CountryLanguage language) { _languages.remove(language); }

    @Override
    public String toString() {
        String output = "Country{" +
                "Code='" + _code + '\'' +
                ", Name='" + _name + '\'' +
                ", Continent='" + _continent + '\'' +
                ", Region='" + _region + '\'' +
                ", SurfaceArea=" + _surfaceArea +
                ", IndepYear=" + _indepYear +
                ", LifeExpectancy=" + _lifeExpectancy +
                ", GNP=" + _gnp +
                ", GNPOld=" + _gnpOld +
                ", LocalName='" + _localName + '\'' +
                ", GovernmentForm='" + _governmentForm + '\'' +
                ", HeadOfState='" + _headOfState + '\'' +
                ", Capital=" + _capital +
                ", Code2='" + _code2 + '\'';

        for(City city : _cities){
            output += ", '" + city.toString() + '\'';
        }

        for(CountryLanguage language : _languages){
            output += ", '" + language.toString() + '\'';
        }

        return output;
    }
}
