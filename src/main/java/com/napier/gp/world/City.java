

package com.napier.gp.world;


public class City {
    public int ID;
    public String Name;
    public String CountryCode;
    public String District;
    public int population;

    public City(int ID, String name, String countryCode, String district, int population) {
        this.ID = ID;
        Name = name;
        CountryCode = countryCode;
        District = district;
        this.population = population;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "World{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", CountryCode='" + CountryCode + '\'' +
                ", District='" + District + '\'' +
                ", population=" + population +
                '}';
    }
}
