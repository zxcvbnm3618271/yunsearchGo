package com.github.gin.yunsearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@ToString
@Data
@NoArgsConstructor
public class PageResult implements Serializable {
    private static final long serialVersionUID = 3739432000715879711L;

    /**
     * 当前页码
     */
    private int page;
    /**
     * 总记录数
     */

    private long total;
    /**
     * 每页记录数
     */

    private int pageSize;
    /**
     * 每页数据记录
     */

    private List<YunData> rows;

}
