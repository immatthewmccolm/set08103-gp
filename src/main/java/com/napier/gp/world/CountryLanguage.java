package com.napier.gp.world;

public class CountryLanguage {
    private String _countryCode;
    private String _language;
    private String _isOfficial;
    private Double _percentage;

    public CountryLanguage(String countryCode, String language, String isOfficial, Double percentage) {
        _countryCode = countryCode;
        _language = language;
        _isOfficial = isOfficial;
        _percentage = percentage;
    }

    public String getCountryCode() {
        return _countryCode;
    }

    public void setCountryCode(String countryCode) {
        _countryCode = countryCode;
    }

    public String getLanguage() {
        return _language;
    }

    public void setLanguage(String language) {
        _language = language;
    }

    public String getIsOfficial() {
        return _isOfficial;
    }

    public void setIsOfficial(String isOfficial) {
        _isOfficial = isOfficial;
    }

    public Double getPercentage() {
        return _percentage;
    }

    public void setPercentage(Double percentage) {
        _percentage = percentage;
    }

    @Override
    public String toString() {
        return "CountryLanguage{" +
                ", Language='" + _language + '\'' +
                ", IsOfficial='" + _isOfficial + '\'' +
                ", Percentage=" + _percentage +
                '}';
    }
}
