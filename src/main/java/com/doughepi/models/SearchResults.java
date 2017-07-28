package com.doughepi.models;

import com.doughepi.models.RecipeModel;

import java.util.List;

/**
 * Created by pjdoughe on 3/23/17.
 */
public class SearchResults<T> {

    String query;
    List<T> resultList;
    long duration;
    String unit;

    public SearchResults(String query, List<T> resultList, long duration, String unit) {
        this.query = query;
        this.resultList = resultList;
        this.duration = duration;
        this.unit = unit;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }


    public int getCount() {
        return resultList.size();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
