package com.service.edu.service.impl;

import com.service.edu.entity.Video;
import com.service.edu.entity.vo.VideoVo;
import com.service.edu.mapper.VideoMapper;
import com.service.edu.service.IVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author wxb
 * @since 2020-05-03
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {
    @Override
    public void save(VideoVo videoVo) {
        Video video = new Video();
        BeanUtils.copyProperties(videoVo, video);
        this.save(video);
    }

    @Override
    public void update(VideoVo videoVo) {
        Video video = new Video();
        BeanUtils.copyProperties(videoVo, video);
        this.updateById(video);
    }
}
