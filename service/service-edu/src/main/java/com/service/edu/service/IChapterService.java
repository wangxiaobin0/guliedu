package com.service.edu.service;

import com.service.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.service.edu.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author wxb
 * @since 2020-05-03
 */
public interface IChapterService extends IService<Chapter> {

    /**
     * 根据课程id查询所有章节
     * @param id   课程id
     * @return
     */
    List<ChapterVo> listById(Long id);

    /**
     * 根据章节id查询章节
     * @param id
     * @return
     */
    ChapterVo byId(Long id);

}
