package com.service.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.service.edu.entity.Subject;
import com.service.edu.entity.SubjectExcel;
import com.service.edu.entity.vo.SubjectNestedVo;
import com.service.edu.entity.vo.SubjectVo;
import com.service.edu.listener.SubjectExcelListener;
import com.service.edu.mapper.SubjectMapper;
import com.service.edu.service.ISubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author
 * @date 2020/5/2
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements ISubjectService {

    @Override
    public void importExcel(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), SubjectExcel.class, new SubjectExcelListener(this)).sheet().doRead();
    }

    @Override
    public List<SubjectNestedVo> listSubjectNestedVo(QueryWrapper<Subject> queryWrapper) {
        //查询一级分类列表
        queryWrapper.eq("parent_id", 0L);
        queryWrapper.orderByAsc("sort");
        List<Subject> parent = this.list(queryWrapper);

        List<SubjectNestedVo> nestedVoList = parent.stream().map(item -> {
            //组装SubjectNestedVo
            SubjectNestedVo nestedVo = new SubjectNestedVo();
            BeanUtils.copyProperties(item, nestedVo);
            QueryWrapper<Subject> wrapper = new QueryWrapper<>();
            wrapper.eq("parent_id", item.getId());
            wrapper.orderByAsc("sort");
            List<Subject> subjectList = this.list(wrapper);
            //组装children
            List<SubjectVo> collect = subjectList.stream().map(subject -> {
                SubjectVo vo = new SubjectVo();
                BeanUtils.copyProperties(subject, vo);
                return vo;
            }).collect(Collectors.toList());
            nestedVo.setChildren(collect);
            return nestedVo;
        }).collect(Collectors.toList());
        return nestedVoList;
    }
}
