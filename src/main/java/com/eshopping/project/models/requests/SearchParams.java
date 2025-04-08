package com.eshopping.project.models.requests;

public abstract class SearchParams {
    Integer pageNumber;
    Integer pageSize;
    String sortBy;

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    String sortOrder;

    private final Integer maxPageSize = 50;

    public void setPageNumber(Integer pageNumber) {
        if(pageNumber != null && pageNumber > 0){
            this.pageNumber = pageNumber--;
        }else{
            this.pageNumber = 0;
        }
    }

    public void setPageSize(Integer pageSize) {
        if(pageSize == null){
            this.pageSize = 10;
        }
        else if(pageSize > maxPageSize){
            this.pageSize = maxPageSize;
        }else{
            this.pageSize = pageSize;
        }
    }

    public Integer getPageNumber() {
        return this.pageNumber++;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }
}
