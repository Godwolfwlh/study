package com.common;

import java.io.Serializable;
import java.util.List;

/**
 * 分页类
 * @author Administrator
 *
 */
public class Page<E> implements Serializable{
	private static final long serialVersionUID = 9126912421501466145L;
	private Integer pageNow;

	private Integer pageSize;

	private Integer total = 0;

	private List<?> rows;

    public Integer getPageNow() {
        return pageNow;
    }

    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
