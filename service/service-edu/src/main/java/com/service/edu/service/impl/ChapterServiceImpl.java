package com.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.service.edu.entity.Chapter;
import com.service.edu.entity.Video;
import com.service.edu.entity.vo.ChapterVo;
import com.service.edu.entity.vo.VideoVo;
import com.service.edu.mapper.ChapterMapper;
import com.service.edu.service.IChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.service.edu.service.IVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author wxb
 * @since 2020-05-03
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements IChapterService {
    @Autowired
    IVideoService videoService;

    @Override
    public List<ChapterVo> listById(Long id) {
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", id);
        queryWrapper.orderByAsc("sort");
        List<Chapter> chapterList = this.list(queryWrapper);

        List<ChapterVo> chapterVoList = chapterList.stream().map(chapter -> {
            ChapterVo vo = new ChapterVo();
            BeanUtils.copyProperties(chapter, vo);
            QueryWrapper<Video> wrapper = new QueryWrapper<>();
            //同一课程
            wrapper.eq("course_id", id);
            //同一章节
            wrapper.eq("chapter_id", chapter.getId());
            wrapper.orderByAsc("sort");
            List<Video> videoList = videoService.list(wrapper);
            List<VideoVo> videoVoList = videoList.stream().map(video -> {
                VideoVo videoVo = new VideoVo();
                BeanUtils.copyProperties(video, videoVo);
                return videoVo;
            }).collect(Collectors.toList());
            vo.setChildren(videoVoList);
            return vo;
        }).collect(Collectors.toList());

        return chapterVoList;
    }

    @Override
    public ChapterVo byId(Long id) {
        Chapter chapter = this.getById(id);
        ChapterVo chapterVo = new ChapterVo();
        BeanUtils.copyProperties(chapter, chapterVo);
        return chapterVo;
    }
}
