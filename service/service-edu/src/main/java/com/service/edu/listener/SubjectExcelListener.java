package com.service.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.service.edu.entity.Subject;
import com.service.edu.entity.SubjectExcel;
import com.service.edu.service.ISubjectService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @date 2020/5/2
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectExcel> {

    private List<Subject> list;

    private ISubjectService subjectService;

    public SubjectExcelListener(ISubjectService subjectService) {
        this.subjectService = subjectService;
        this.list = new ArrayList<>();
    }

    @Override
    public void invoke(SubjectExcel subjectExcel, AnalysisContext analysisContext) {
        if (subjectExcel == null) {
            throw new RuntimeException("Excel文件读取异常");
        }
        Subject oneSubject = this.existOneSubject(subjectService, subjectExcel.getOneSubjectName());
        if (oneSubject == null) {
            oneSubject = new Subject();
            oneSubject.setTitle(subjectExcel.getOneSubjectName());
            oneSubject.setParentId(0L);
            subjectService.save(oneSubject);
        }
        Subject twoSubject = this.existTwoSubject(subjectService, oneSubject.getId(), subjectExcel.getTwoSubjectName());
        if (twoSubject == null) {
            twoSubject = new Subject();
            twoSubject.setTitle(subjectExcel.getTwoSubjectName());
            twoSubject.setParentId(oneSubject.getId());
            subjectService.save(twoSubject);
        }
    }

    /**
     * 判断一级分类是否存在
     * @param subjectService
     * @param id
     * @param twoSubjectName
     * @return
     */
    private Subject existTwoSubject(ISubjectService subjectService, Long id, String twoSubjectName) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", id);
        queryWrapper.eq("title", twoSubjectName);
        Subject two = subjectService.getOne(queryWrapper);
        return two;
    }

    /**
     * 判断二级分类是否存在
     * @param subjectService
     * @param oneSubjectName
     * @return
     */
    private Subject existOneSubject(ISubjectService subjectService, String oneSubjectName) {
        QueryWrapper queryWrapper =  new QueryWrapper();
        queryWrapper.eq("parent_id", 0);
        queryWrapper.eq("title", oneSubjectName);
        Subject one = subjectService.getOne(queryWrapper);
        return one;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {}
}
