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

}