package com.example.showtrack.data.model.api.JSONObjects.seriesandfilms.films;

public class JSONFilm {

    String imdbID;
    String tittle;
    String Rated;
    String Runtime;
    String Genre;
    String Language;
    String Plot;
    String Country;
    String Awards;
    String Poster;

    String Director;
    String Writer;
    String Actors;

    String imdbRating;
    String Type;
    String BoxOffice;

    public JSONFilm(String imdbID, String tittle, String rated, String runtime, String genre, String language, String plot, String country, String awards, String poster, String director, String writer, String actors, String imdbRating, String type, String boxOffice) {
        this.imdbID = imdbID;
        this.tittle = tittle;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getRated() {
        return Rated;
    }

    public void setRated(String rated) {
        Rated = rated;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getAwards() {
        return Awards;
    }

    public void setAwards(String awards) {
        Awards = awards;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getBoxOffice() {
        return BoxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        BoxOffice = boxOffice;
    }

    @Override
    public String toString() {
        return "JSONFilm{" +
                "imdbID='" + imdbID + '\'' +
                ", tittle='" + tittle + '\'' +
                ", Rated=" + Rated +
                ", Runtime=" + Runtime +
                ", Genre='" + Genre + '\'' +
                ", Language='" + Language + '\'' +
                ", Plot='" + Plot + '\'' +
                ", Country='" + Country + '\'' +
                ", Awards='" + Awards + '\'' +
                ", Poster='" + Poster + '\'' +
                ", Director='" + Director + '\'' +
                ", Writers='" + Writer + '\'' +
                ", Actors='" + Actors + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", Type='" + Type + '\'' +
                ", BoxOffice=" + BoxOffice +
                '}';
    }
}
