package com.service.edu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.utils.PageResult;
import com.service.edu.entity.Teacher;
import com.service.edu.entity.vo.TeacherVo;
import com.service.edu.mapper.TeacherMapper;
import com.service.edu.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.bouncycastle.util.io.TeeInputStream;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author wxb
 * @since 2020-05-01
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {
    @Override
    public PageResult<Teacher> byPage(Long page, Long limit) {
        Page<Teacher> pageParam = new Page<Teacher>(page, limit);
        IPage<Teacher> iPage = this.page(pageParam, null);
        return PageResult.build(iPage);
    }

    @Override
    public List<TeacherVo> list() {
        List<Teacher> teacherList = this.list(null);
        List<TeacherVo> list = teacherList.stream().map(teacher -> {
            TeacherVo teacherVo = new TeacherVo();
            BeanUtils.copyProperties(teacher, teacherVo);
            teacherVo.setId(teacher.getId().toString());
            return teacherVo;
        }).collect(Collectors.toList());
        return list;
    }
}
