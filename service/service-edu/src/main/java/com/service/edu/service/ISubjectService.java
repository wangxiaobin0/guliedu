package com.service.edu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.service.edu.entity.Subject;
import com.service.edu.entity.vo.SubjectNestedVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author
 * @date 2020/5/2
 */
public interface ISubjectService extends IService<Subject> {
    /**
     * 上传Excel
     * @param file
     * @throws IOException
     */
    void importExcel(MultipartFile file) throws IOException;

    /**
     * 查询属性图
     * @param queryWrapper
     * @return
     */
    List<SubjectNestedVo> listSubjectNestedVo(QueryWrapper<Subject> queryWrapper);
}
