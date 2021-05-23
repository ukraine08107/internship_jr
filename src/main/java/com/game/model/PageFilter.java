package com.game.model;

import com.game.controller.PlayerOrder;

public class PageFilter {
    private PlayerOrder order;
    private Integer pageNumber;
    private Integer pageSize;

    public PageFilter() {
    }

    public PageFilter(PlayerOrder order,
                      Integer pageNumber,
                      Integer pageSize) {
        this.order = order;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public PlayerOrder getOrder() {
        return order;
    }

    public void setOrder(PlayerOrder order) {
        this.order = order;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
