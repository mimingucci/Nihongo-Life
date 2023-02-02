package com.nihongo.common.entity;

import java.util.List;

public class PagingDTO {
    private Integer currentPage;
    private Integer totalPages;
    private Integer startCount;
    private Integer endCount;
    private Integer totalItems;
    private List<?> listItems;


    public PagingDTO(Integer currentPage, Integer totalPages, Integer startCount, Integer endCount, Integer totalItems, List<?> listItems) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.startCount = startCount;
        this.endCount = endCount;
        this.totalItems = totalItems;
        this.listItems = listItems;
    }

    public PagingDTO() {
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getStartCount() {
        return startCount;
    }

    public void setStartCount(Integer startCount) {
        this.startCount = startCount;
    }

    public Integer getEndCount() {
        return endCount;
    }

    public void setEndCount(Integer endCount) {
        this.endCount = endCount;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public List<?> getListItems() {
        return listItems;
    }

    public void setListItems(List<?> listItems) {
        this.listItems = listItems;
    }
}
