package com.xuanluo.myshop.commons.dto;

import lombok.Data;

import java.util.List;
@Data
public class DatatableDTO<T> {
    private Integer draw;

    private Long recordsTotal;

    private Long recordsFiltered;

    private List<T> data;
}
