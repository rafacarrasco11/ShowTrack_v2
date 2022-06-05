package com.example.showtrack.data.repository;

import com.example.showtrack.data.model.New;
import com.example.showtrack.data.model.api.APIClasses.APINews;
import com.example.showtrack.ui.hm.HomeContract;

import java.util.ArrayList;

public class NewsRepository implements HomeContract.Repository {

    private static NewsRepository instance;
    private ArrayList<New> newsList;

    private NewsRepository()  {
        this.newsList = new ArrayList<>();
        iniNewsList();
    }

    public static NewsRepository getInstance()  {
        if (instance == null) {
            instance = new NewsRepository();
        }

        return instance;
    }

    private void iniNewsList()  {
        if (this.newsList.size() == 0) {
            this.newsList.addAll(APINews.getNewsOfTheDay());
        }
    }

    @Override
    public void cargarRvNews(HomeContract.OnRepositoryHomeFragmentCallback callback) {
        callback.onSuccessCargarRvNews(this.newsList);
    }
}
