package org.hints.common.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String searchValue;

    @JsonIgnore
    private Integer pageNum;

    @JsonIgnore
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum==null?1:pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize==null?20:pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setNull() {
        this.searchValue = null;
        this.pageNum = null;
        this.pageSize = null;
    }

}