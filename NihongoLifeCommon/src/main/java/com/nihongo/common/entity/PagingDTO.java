package com.nihongo.common.entity;

import lombok.Data;

import java.util.List;
@Data
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
}
