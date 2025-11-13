package com.napier.gp.world;

import java.util.List;

public class World {
    private World() { }
    private static World _instance;

    public static World getInstance() {
        if(_instance != null) { return _instance; }
        else {
            _instance = new World();
            return _instance;
        }
    }

    private List<Country> _countries;
    private List<City> _cities;
    private List<CountryLanguage> _languages;

    public List<Country> getCountries() { return _countries; }

    public void addCountry(Country country) { _countries.add(country); }

    public void removeCountry(Country country) { _countries.remove(country); }

    public List<City> getCities() { return _cities; }

    public void addCity(City city) { _cities.add(city); }

    public void removeCity(City city) { _cities.remove(city); }

    public List<CountryLanguage> getLanguages() { return _languages; }

    public void addLanguage(CountryLanguage language) { _languages.add(language); }

    public void removeLanguage(CountryLanguage language) { _languages.remove(language); }
}
