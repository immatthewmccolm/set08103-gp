

package com.napier.gp.world;

public class City {
    private int _id;
    private String _name;
    private String _countryCode;
    private String _district;
    private int _population;

    public City(int ID, String name, String countryCode, String district, int population) {
        _id = ID;
        _name = name;
        _countryCode = countryCode;
        _district = district;
        _population = population;
    }

    public int getID() {
        return _id;
    }

    public void setID(int ID) { _id = ID; }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getCountryCode() {
        return _countryCode;
    }

    public void setCountryCode(String countryCode) {
        _countryCode = countryCode;
    }

    public String getDistrict() {
        return _district;
    }

    public void setDistrict(String district) {
        _district = district;
    }

    public int getPopulation() {
        return _population;
    }

    public void setPopulation(int population) {
        _population = population;
    }

    @Override
    public String toString() {
        return "World{" +
                "ID=" + _id +
                ", Name='" + _name + '\'' +
                ", CountryCode='" + _countryCode + '\'' +
                ", District='" + _district + '\'' +
                ", Population=" + _population +
                '}';
    }
}
