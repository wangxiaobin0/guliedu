package com.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
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
    private Long totalNum;
    private Long totalPage;
    private List<T> data;
    private PageResult(){};
    public static PageResult build(IPage iPage){
        return new PageResult(iPage.getTotal(), iPage.getPages(), iPage.getRecords());
    }
}
