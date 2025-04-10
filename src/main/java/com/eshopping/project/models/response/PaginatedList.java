package com.eshopping.project.models.response;

import lombok.Data;

import java.util.List;

@Data
public class PaginatedList<TBody> {
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPages;
    private Integer totalElements;
    private List<TBody> content;

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = ++pageNumber;
    }
}
