package com.service.edu.service;

import com.service.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.service.edu.entity.vo.VideoVo;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author wxb
 * @since 2020-05-03
 */
public interface IVideoService extends IService<Video> {

    /**
     * 新增课时
     * @param videoVo
     */
    void save(VideoVo videoVo);

    /**
     * 更新课时
     * @param videoVo
     */
    void update(VideoVo videoVo);
}
