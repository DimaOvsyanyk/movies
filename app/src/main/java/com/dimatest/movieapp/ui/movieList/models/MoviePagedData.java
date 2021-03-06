package com.dimatest.movieapp.ui.movieList.models;

public class MoviePagedData {

    private int page;
    private long total_results;
    private int total_pages;

    public MoviePagedData(int page, long total_results, int total_pages) {
        this.page = page;
        this.total_results = total_results;
        this.total_pages = total_pages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getTotal_results() {
        return total_results;
    }

    public void setTotal_results(long total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getNextPage() {
        return page + 1;
    }

    public boolean hasMoreMovies() {
        return page < total_pages;
    }
}
