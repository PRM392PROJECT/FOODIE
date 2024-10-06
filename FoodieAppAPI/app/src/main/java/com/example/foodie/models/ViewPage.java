package com.example.foodie.models;

import java.util.List;

public class ViewPage <T>{
    private List<T> items;
    private int currentPage;
    private  int pageSize;
    private int totalItems;
    private int totalPages;
    private int nextPage;
    private int previousPage;
    public  ViewPage(List<T> items, int currentPage, int pageSize, int totalItems, int totalPages, int nextPage, int previousPage){
        this.items = items;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(int previousPage) {
        this.previousPage = previousPage;
    }
}
