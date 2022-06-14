package com.example.showtrack.data.model.api.JSONObjects.seriesandfilms;

/**
 * Clase POJO para los objetos JSON que deveulve la API de series y peliculas
 */
public class JSONReleaseYear {
    String year;
    String endYear;

    public JSONReleaseYear(String year, String endYear) {
        this.year = year;
        this.endYear = endYear;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    @Override
    public String toString() {
        return "JSONReleaseYear{" +
                "year='" + year + '\'' +
                ", endYear='" + endYear + '\'' +
                '}';
    }
}
