package com.example.contentservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.contentservice.client.ConsumerClient;
import com.example.contentservice.client.SocialClient;
import com.example.contentservice.common.R;
import com.example.contentservice.mapper.ListSongConsumerMapper;
import com.example.contentservice.mapper.SongListConsumerMapper;
import com.example.contentservice.model.domain.Consumer;
import com.example.contentservice.model.domain.Follow;
import com.example.contentservice.model.domain.ListSongConsumer;
import com.example.contentservice.model.domain.SongListConsumer;
import com.example.contentservice.model.request.ListSongConsumerRequest;
import com.example.contentservice.model.request.NotificationRequest;
import com.example.contentservice.service.ListSongConsumerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ListSongConsumerImpl extends ServiceImpl<ListSongConsumerMapper, ListSongConsumer> implements ListSongConsumerService {
    @Autowired
    private  ListSongConsumerMapper listSongConsumerMapper;


    @Autowired
    private SongListConsumerMapper songListConsumerMapper;


    @Autowired
    private ConsumerClient consumerClient;

    @Autowired
    private SocialClient socialClient;

    @Override
    public List<ListSongConsumer> allListSongConsumer() {
        return listSongConsumerMapper.selectList(null);
    }

    @Override
    public R updateListSongConsumerMsg(ListSongConsumerRequest updateListSongConsumerRequest) {
        ListSongConsumer listSongConsumer = new ListSongConsumer();
        BeanUtils.copyProperties(updateListSongConsumerRequest, listSongConsumer);

        if (listSongConsumerMapper.updateById(listSongConsumer) > 0) {
            // 歌单创建者ID和title
            Integer songListConsumerId = listSongConsumer.getSongListConsumerId();
            QueryWrapper<SongListConsumer> songListConsumerQueryWrapper = new QueryWrapper<>();
            songListConsumerQueryWrapper.eq("id", songListConsumerId);
            SongListConsumer songListConsumer = songListConsumerMapper.selectOne(songListConsumerQueryWrapper);
            Integer creatorId = songListConsumer.getUserId();
            String songListTitle = songListConsumer.getTitle();

            // 获取歌单创建者的用户名
            //QueryWrapper<Consumer> queryWrapperUser = new QueryWrapper<>();
            //queryWrapperUser.eq("id", creatorId);
            String username = ""; // 初始化用户名

            Consumer consumer = consumerClient.userOfId(creatorId);
            if (consumer != null) {
                username = consumer.getUsername();
            }

            // 查询所有关注该歌单创建者的关注者
            R response = socialClient.getFollowersByFollowed(creatorId);
            List<Follow> followers=null;
            if (response.getSuccess()) {
                // 处理 List 数据
                if (response.getData() instanceof List<?>) {
                    @SuppressWarnings("unchecked")
                    List<Map<String, Object>> dataList = (List<Map<String, Object>>) response.getData();
                    followers = dataList.stream()
                            .map(dataMap -> {
                                // 将 Map 转换为 Follow 对象
                                Follow follow = new Follow();
                                follow.setId((Integer) dataMap.get("id"));
                                follow.setFollowerId((Integer) dataMap.get("followerId"));
                                follow.setFollowedId((Integer) dataMap.get("followedId"));
                                return follow;
                            })
                            .collect(Collectors.toList());
                }}

            // 遍历所有关注者并发送通知
            for (Follow follower : followers) {
                NotificationRequest notificationRequest = new NotificationRequest();
                notificationRequest.setUserId(follower.getFollowerId());
                notificationRequest.setUserType("consumer");
                notificationRequest.setMessage("您关注的用户 \"" + username + "\" 更新了歌单 \"" + songListTitle + "\", 快来看看吧！");
                notificationRequest.setType(1);
                // 使用 SocialClient 调用社交服务发送通知
                socialClient.addNotification(notificationRequest);
            }
            return R.success("修改成功");
        } else {
            return R.error("修改失败");
        }
    }

    @Override
    public R deleteListSongConsumer(Integer songId, Integer songListConsumerId) {
        QueryWrapper<ListSongConsumer> listSongConsumerQueryWrapper = new QueryWrapper<>();
        listSongConsumerQueryWrapper.eq("song_id", songId).eq("song_list_consumer_id", songListConsumerId);
        // 歌单创建者ID和title
        QueryWrapper<SongListConsumer> songListConsumerQueryWrapper = new QueryWrapper<>();
        songListConsumerQueryWrapper.eq("id", songListConsumerId);
        SongListConsumer songListConsumer = songListConsumerMapper.selectOne(songListConsumerQueryWrapper);
        Integer creatorId = songListConsumer.getUserId();
        String songListTitle = songListConsumer.getTitle();

        // 获取歌单创建者的用户名
        //QueryWrapper<Consumer> queryWrapperUser = new QueryWrapper<>();
       // queryWrapperUser.eq("id", creatorId);
        String username = ""; // 初始化用户名
        Consumer consumer = consumerClient.userOfId(creatorId);
        if (consumer != null) {
            username = consumer.getUsername();
        }

        // 查询所有关注该歌单创建者的关注者
        //QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        //queryWrapper.eq("followed_id", creatorId);
        R response = socialClient.getFollowersByFollowed(creatorId);
        List<Follow> followers=null;
        if (response.getSuccess()) {
            // 处理 List 数据
            if (response.getData() instanceof List<?>) {
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> dataList = (List<Map<String, Object>>) response.getData();
                followers = dataList.stream()
                        .map(dataMap -> {
                            // 将 Map 转换为 Follow 对象
                            Follow follow = new Follow();
                            follow.setId((Integer) dataMap.get("id"));
                            follow.setFollowerId((Integer) dataMap.get("followerId"));
                            follow.setFollowedId((Integer) dataMap.get("followedId"));
                            return follow;
                        })
                        .collect(Collectors.toList());
            }}

        // 遍历所有关注者并发送通知
        for (Follow follower : followers) {
            NotificationRequest notificationRequest = new NotificationRequest();
            notificationRequest.setUserId(follower.getFollowerId());
            notificationRequest.setUserType("consumer");
            notificationRequest.setMessage("您关注的用户 \"" + username + "\" 在歌单 \"" + songListTitle + "\"中删除了歌曲。");
            notificationRequest.setType(1);

            // 使用 SocialClient 调用社交服务发送通知
            socialClient.addNotification(notificationRequest);
        }
        if (listSongConsumerMapper.delete(listSongConsumerQueryWrapper) > 0) {
            return R.success("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

    @Override
    public R addListSongConsumer(ListSongConsumerRequest addListSongConsumerRequest) {
        ListSongConsumer listSongConsumer = new ListSongConsumer();
        BeanUtils.copyProperties(addListSongConsumerRequest, listSongConsumer);
        if (listSongConsumerMapper.insert(listSongConsumer) > 0) {
            // 歌单创建者ID和title
            Integer songListConsumerId = listSongConsumer.getSongListConsumerId();
            QueryWrapper<SongListConsumer> songListConsumerQueryWrapper = new QueryWrapper<>();
            songListConsumerQueryWrapper.eq("id", songListConsumerId);
            SongListConsumer songListConsumer = songListConsumerMapper.selectOne(songListConsumerQueryWrapper);
            Integer creatorId = songListConsumer.getUserId();
            String songListTitle = songListConsumer.getTitle();

            // 获取歌单创建者的用户名
            //QueryWrapper<Consumer> queryWrapperUser = new QueryWrapper<>();
           // queryWrapperUser.eq("id", creatorId);
            String username = ""; // 初始化用户名
            Consumer consumer =consumerClient.userOfId(creatorId);
            if (consumer != null) {
                username = consumer.getUsername();
            }

            // 查询所有关注该歌单创建者的关注者
            //QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
           // queryWrapper.eq("followed_id", creatorId);
            R response = socialClient.getFollowersByFollowed(creatorId);
            List<Follow> followers=null;
            if (response.getSuccess()) {
                // 处理 List 数据
                if (response.getData() instanceof List<?>) {
                    @SuppressWarnings("unchecked")
                    List<Map<String, Object>> dataList = (List<Map<String, Object>>) response.getData();
                    followers = dataList.stream()
                            .map(dataMap -> {
                                // 将 Map 转换为 Follow 对象
                                Follow follow = new Follow();
                                follow.setId((Integer) dataMap.get("id"));
                                follow.setFollowerId((Integer) dataMap.get("followerId"));
                                follow.setFollowedId((Integer) dataMap.get("followedId"));
                                return follow;
                            })
                            .collect(Collectors.toList());
                }}

            // 遍历所有关注者并发送通知
            for (Follow follower : followers) {
                NotificationRequest notificationRequest = new NotificationRequest();
                notificationRequest.setUserId(follower.getFollowerId());
                notificationRequest.setUserType("consumer");
                notificationRequest.setMessage("您关注的用户 \"" + username + "\" 更新了歌单 \"" + songListTitle + "\"，快来看看吧！");
                notificationRequest.setType(1);

                // 使用 SocialClient 调用社交服务发送通知
                socialClient.addNotification(notificationRequest);
            }
            return R.success("添加成功");
        } else {
            return R.error("添加失败");
        }
    }

    @Override
    public R listSongConsumerOfSongId(Integer songListConsumerId) {
        QueryWrapper<ListSongConsumer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_list_consumer_id", songListConsumerId);
        return R.success("查询成功", listSongConsumerMapper.selectList(queryWrapper));
    }

}
