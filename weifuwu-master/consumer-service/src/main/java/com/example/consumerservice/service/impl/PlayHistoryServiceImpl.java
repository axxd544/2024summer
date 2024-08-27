package com.example.consumerservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.consumerservice.client.ContentClient;
import com.example.consumerservice.common.R;
import com.example.consumerservice.mapper.ConsumerMapper;
import com.example.consumerservice.mapper.PlayHistoryMapper;
import com.example.consumerservice.model.domain.Consumer;
import com.example.consumerservice.model.domain.PlayHistory;
import com.example.consumerservice.model.domain.Song;
import com.example.consumerservice.model.request.PlayHistoryRequest;
import com.example.consumerservice.service.PlayHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * @author 544
 * @Description:记录播放历史
 * @date 2024/5/29 9:26
 */
@Service
public class PlayHistoryServiceImpl extends ServiceImpl<PlayHistoryMapper, PlayHistory> implements PlayHistoryService{

    @Autowired
    private PlayHistoryMapper playHistoryMapper;
    @Autowired
    private ConsumerMapper consumerMapper;

    @Autowired
    private ContentClient contentClient;

    @Override
    public R recodePlayHistory(PlayHistoryRequest playHistoryRequest) {
        PlayHistory playHistory = new PlayHistory();

        int userId = playHistoryRequest.getUserId();
        Consumer consumer = consumerMapper.selectById(userId);
        if (consumer == null) {
            return R.error("不存在该用户");
        }

        int songId = playHistoryRequest.getSongId();
        //这里调用了内容模块的服务
        Song song = contentClient.songOfId(songId);
        if (song == null) {
            return R.error("不存在该歌曲");
        }

        QueryWrapper<PlayHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).eq("song_id", songId);

        PlayHistory oldPlayHistory = playHistoryMapper.selectOne(queryWrapper);
        if (oldPlayHistory != null) {   // 以前听过这首歌
            oldPlayHistory.setPlayCount(oldPlayHistory.getPlayCount() + 1);

            Instant now = Instant.now();
            Timestamp timestamp = Timestamp.from(now);
            oldPlayHistory.setPlayTimeStamp(timestamp);

            if (playHistoryMapper.updateById(oldPlayHistory) > 0) {
                return R.success("更新播放历史成功");
            } else {
                return R.error("更新播放历史失败");
            }

        } else {
            playHistory.setUserId(userId);
            playHistory.setSongId(songId);
            playHistory.setPlayCount(playHistoryRequest.getPlayCount());
            playHistory.setPlayTimeStamp(playHistoryRequest.getPlayTimeStamp());
            if (playHistoryMapper.insert(playHistory) > 0) {
                return R.success("添加播放历史成功");
            } else {
                return R.error("添加播放历史失败");
            }
        }
    }

    @Override
    public R getPlayHistory(int userId) {
        QueryWrapper<PlayHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<PlayHistory> result = playHistoryMapper.selectList(queryWrapper);
        if (result == null || result.isEmpty()) {
            return R.error("当前没有播放历史记录");
        }
        return R.success("查询成功", result);
    }
}