package com.example.showtrack.data.model.api.JSONObjects.news;

import java.util.List;

/**
 * Clase POJO para los objetos JSON que deveulve la API de noticias
 */
public class JSONNews {

    String status;
    long totalResults;
    List<JSONNew> articles;

    public JSONNews(String status, long totalResults, List<JSONNew> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }

    public List<JSONNew> getArticles() {
        return articles;
    }

    public void setArticles(List<JSONNew> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "JSONNews{" +
                "status='" + status + '\'' +
                ", totalResults=" + totalResults +
                ", articles=" + articles +
                '}';
    }
}
