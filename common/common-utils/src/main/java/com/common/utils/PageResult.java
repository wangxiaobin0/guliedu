package com.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 分页结果
 * @author
 * @date 2020/5/1
 */
@Data
@AllArgsConstructor
public class PageResult<T> {
    /**
     * 总数
     */
    private Long totalNum;
    /**
     * 总页数
     */
    private Long totalPage;
    /**
     * 当前页数
     */
    private Long current;
    /**
     * 是否有上一页
     */
    private Boolean hasPrevious;
    /**
     * 是否有下一页
     */
    private Boolean hasNext;
    /**
     * 结果集
     */
    private List<T> data;
    private PageResult(){};
    public static PageResult build(IPage iPage){
        return new PageResult(iPage.getTotal(),
                iPage.getPages(),
                iPage.getCurrent(),
                ((Page) iPage).hasPrevious(),
                ((Page) iPage).hasPrevious(),
                iPage.getRecords());
    }
}
