package com.napier.gp.world;

public class Country {
    public String Code;
    public String Name;
    public String Continent;
    public String Region;
    public Double SurfaceArea;
    public int IndepYear;
    public Double LifeExpectancy;
    public Double GNP;
    public Double GNPOld;
    public String LocalName;
    public String GovernmentForm;
    public String HeadOfState;
    public int Capital;
    public String Code2;

    public Country(String code, String name, String continent, String region, Double surfaceArea, int indepYear, Double lifeExpectancy, Double GNP, Double GNPOld, String localName, String governmentForm, String headOfState, int capital, String code2) {
        Code = code;
        Name = name;
        Continent = continent;
        Region = region;
        SurfaceArea = surfaceArea;
        IndepYear = indepYear;
        LifeExpectancy = lifeExpectancy;
        this.GNP = GNP;
        this.GNPOld = GNPOld;
        LocalName = localName;
        GovernmentForm = governmentForm;
        HeadOfState = headOfState;
        Capital = capital;
        Code2 = code2;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContinent() {
        return Continent;
    }

    public void setContinent(String continent) {
        Continent = continent;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public Double getSurfaceArea() {
        return SurfaceArea;
    }

    public void setSurfaceArea(Double surfaceArea) {
        SurfaceArea = surfaceArea;
    }

    public int getIndepYear() {
        return IndepYear;
    }

    public void setIndepYear(int indepYear) {
        IndepYear = indepYear;
    }

    public Double getLifeExpectancy() {
        return LifeExpectancy;
    }

    public void setLifeExpectancy(Double lifeExpectancy) {
        LifeExpectancy = lifeExpectancy;
    }

    public Double getGNP() {
        return GNP;
    }

    public void setGNP(Double GNP) {
        this.GNP = GNP;
    }

    public Double getGNPOld() {
        return GNPOld;
    }

    public void setGNPOld(Double GNPOld) {
        this.GNPOld = GNPOld;
    }

    public String getLocalName() {
        return LocalName;
    }

    public void setLocalName(String localName) {
        LocalName = localName;
    }

    public String getGovernmentForm() {
        return GovernmentForm;
    }

    public void setGovernmentForm(String governmentForm) {
        GovernmentForm = governmentForm;
    }

    public String getHeadOfState() {
        return HeadOfState;
    }

    public void setHeadOfState(String headOfState) {
        HeadOfState = headOfState;
    }

    public int getCapital() {
        return Capital;
    }

    public void setCapital(int capital) {
        Capital = capital;
    }

    public String getCode2() {
        return Code2;
    }

    public void setCode2(String code2) {
        Code2 = code2;
    }

    @Override
    public String toString() {
        return "Country{" +
                "Code='" + Code + '\'' +
                ", Name='" + Name + '\'' +
                ", Continent='" + Continent + '\'' +
                ", Region='" + Region + '\'' +
                ", SurfaceArea=" + SurfaceArea +
                ", IndepYear=" + IndepYear +
                ", LifeExpectancy=" + LifeExpectancy +
                ", GNP=" + GNP +
                ", GNPOld=" + GNPOld +
                ", LocalName='" + LocalName + '\'' +
                ", GovernmentForm='" + GovernmentForm + '\'' +
                ", HeadOfState='" + HeadOfState + '\'' +
                ", Capital=" + Capital +
                ", Code2='" + Code2 + '\'' +
                '}';
    }
}
