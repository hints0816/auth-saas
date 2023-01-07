package org.hints.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.nutz.dao.pager.Pager;

import java.util.List;

@Data
@AllArgsConstructor
public class TablePageData<T> {

    private List<T> dataList;

    private Pager pager;
}