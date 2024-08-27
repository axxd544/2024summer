package com.example.contentservice.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.contentservice.client.ConsumerClient;
import com.example.contentservice.client.SocialClient;
import com.example.contentservice.common.R;
import com.example.contentservice.controller.MinioUploadController;
import com.example.contentservice.mapper.SongListConsumerDeletedMapper;
import com.example.contentservice.mapper.SongListConsumerMapper;
import com.example.contentservice.model.domain.Consumer;
import com.example.contentservice.model.domain.Follow;
import com.example.contentservice.model.domain.SongListConsumer;
import com.example.contentservice.model.domain.SongListConsumerDeleted;
import com.example.contentservice.model.request.NotificationRequest;
import com.example.contentservice.model.request.SongListConsumerRequest;
import com.example.contentservice.service.SongListConsumerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SongListConsumerImpl extends ServiceImpl<SongListConsumerMapper, SongListConsumer> implements SongListConsumerService {

    @Autowired
    private SongListConsumerMapper songListConsumerMapper;

    @Autowired
    private SongListConsumerDeletedMapper songListConsumerDeletedMapper;

    @Autowired
    private SocialClient socialClient;

    @Autowired
    private ConsumerClient consumerClient;



    @Value("${minio.bucket-name}")
    String bucketName;

    @Override
    public R updateSongListConsumerMsg(SongListConsumerRequest updateSongListConsumerRequest) {
        SongListConsumer songListConsumer = new SongListConsumer();
        BeanUtils.copyProperties(updateSongListConsumerRequest, songListConsumer);
        if (songListConsumerMapper.updateById(songListConsumer) > 0) {
            // 歌单创建者ID
            Integer creatorId = songListConsumer.getUserId();
            String songListTitle = songListConsumer.getTitle();

            // 获取歌单创建者的用户名
            //QueryWrapper<Consumer> queryWrapperUser = new QueryWrapper<>();
            //queryWrapperUser.eq("id",creatorId);
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
            // List<Follow> followers = (List<Follow>) socialClient.getFollowersByFollowed(creatorId).getData();

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
            return R.success("修改成功");
        } else {
            return R.error("修改失败");
        }
    }

    @Override
    public R deleteSongListConsumer(Integer id) {
        //获取该id的列表信息
        QueryWrapper<SongListConsumer> queryWrapperSongList = new QueryWrapper<>();
        queryWrapperSongList.eq("id", id);
        // 歌单创建者ID及title
        Integer creatorId = 0;
        String songListTitle = "";
        SongListConsumer songListConsumer = songListConsumerMapper.selectOne(queryWrapperSongList);
        if (songListConsumer != null){
            songListTitle = songListConsumer.getTitle();
            creatorId = songListConsumer.getUserId();
        }
        // 获取歌单创建者的用户名
        //QueryWrapper<Consumer> queryWrapperUser = new QueryWrapper<>();
        //queryWrapperUser.eq("id",creatorId);
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


        if (songListConsumerMapper.deleteById(id) > 0) {
            // 遍历所有关注者并发送通知
            for (Follow follower : followers) {
                NotificationRequest notificationRequest = new NotificationRequest();
                notificationRequest.setUserId(follower.getFollowerId());
                notificationRequest.setUserType("consumer");
                notificationRequest.setMessage("您关注的用户 \"" + username + "\" 删除了歌单 \"" + songListTitle + "\"。");
                notificationRequest.setType(1);

                // 使用 SocialClient 调用社交服务发送通知
                socialClient.addNotification(notificationRequest);
            }
            return R.success("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

    @Override
    public R deleteSongListConsumerByManager(Integer id, Integer complainterId) {
        //获取该id的列表信息
        QueryWrapper<SongListConsumer> queryWrapperSongList = new QueryWrapper<>();
        queryWrapperSongList.eq("id", id);
        // 歌单title
        String songListTitle = "";
        SongListConsumer songListConsumer = songListConsumerMapper.selectOne(queryWrapperSongList);
        if (songListConsumer != null){
            songListTitle = songListConsumer.getTitle();
        }
        // 将信息保存到 SongListConsumerDeleted 表中
        SongListConsumerDeleted songListConsumerDeleted = new SongListConsumerDeleted(songListConsumer);
        songListConsumerDeletedMapper.insert(songListConsumerDeleted);
        if (songListConsumerMapper.deleteById(id) > 0) {
            // 发送通知
            NotificationRequest notificationRequest = new NotificationRequest();
            notificationRequest.setUserId(complainterId);
            notificationRequest.setUserType("consumer");
            notificationRequest.setMessage("您投诉的歌单 \"" + songListTitle + "\"经审核，已删除。");
            notificationRequest.setType(4);

            // 使用 SocialClient 调用社交服务发送通知
            socialClient.addNotification(notificationRequest);
            return R.success("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

    @Override
    public R unDeleteSongListConsumerByManager(Integer id, Integer complainterId, Integer applealerId) {
        // 将信息恢复到 SongListConsumer 表中
        SongListConsumerDeleted songListConsumerDeleted = songListConsumerDeletedMapper.selectById(id);
        if (songListConsumerDeleted == null) {
            return R.error("删除的歌单不存在");
        }
        SongListConsumer songListConsumer = new SongListConsumer(songListConsumerDeleted);
        songListConsumerMapper.insert(songListConsumer);

        //获取该id的列表信息
        QueryWrapper<SongListConsumer> queryWrapperSongList = new QueryWrapper<>();
        queryWrapperSongList.eq("id", id);
        // 歌单title
        String songListTitle = "";
        songListConsumer = songListConsumerMapper.selectOne(queryWrapperSongList);
        if (songListConsumer != null){
            songListTitle = songListConsumer.getTitle();
        }

        if (songListConsumerDeletedMapper.deleteById(id) > 0) {
            // 发送通知
            NotificationRequest notificationRequest = new NotificationRequest();
            notificationRequest.setUserId(complainterId);
            notificationRequest.setUserType("consumer");
            notificationRequest.setMessage("您投诉的歌曲 \"" + songListTitle + "\"受到申诉，经审核，已撤销删除。");
            notificationRequest.setType(5);
            // 使用 SocialClient 调用社交服务发送通知
            socialClient.addNotification(notificationRequest);

            NotificationRequest notificationRequest1 = new NotificationRequest();
            notificationRequest1.setUserId(applealerId);
            notificationRequest1.setUserType("consumer");
            notificationRequest1.setMessage("您申诉的歌曲 \"" + songListTitle + "\"经审核，已撤销删除。");
            notificationRequest1.setType(5);
            // 使用 SocialClient 调用社交服务发送通知
            socialClient.addNotification(notificationRequest1);

            return R.success("撤销删除成功");
        } else {
            return R.error("撤销删除失败");
        }
    }

    @Override
    public R allSongListConsumer() {
        return R.success(null, songListConsumerMapper.selectList(null));
    }

    @Override
    public List<SongListConsumer> findAllSong() {
        return songListConsumerMapper.selectList(null);
    }


    @Override
    public R likeTitle(String title) {
        QueryWrapper<SongListConsumer> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title",title);
        return R.success(null, songListConsumerMapper.selectList(queryWrapper));
    }

    @Override
    public R likeStyle(String style) {
        QueryWrapper<SongListConsumer> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("style",style);
        return R.success(null, songListConsumerMapper.selectList(queryWrapper));
    }

    @Override
    public R byId(Integer id) {
        QueryWrapper<SongListConsumer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        List<SongListConsumer> result = songListConsumerMapper.selectList(queryWrapper);
        if (result == null || result.isEmpty()) {
            return R.error("没有找到对象");
        }
        return R.success(null, result);
    }

    @Override
    public R eqUserId(Integer user_id) {
        QueryWrapper<SongListConsumer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user_id);
        return R.success(null, songListConsumerMapper.selectList(queryWrapper));
    }

    @Override
    public R addSongListConsumer(SongListConsumerRequest addSongListConsumerRequest) {
        SongListConsumer songListConsumer = new SongListConsumer();
        BeanUtils.copyProperties(addSongListConsumerRequest, songListConsumer);
        String pic = "/img/songListPic/123.jpg";
        songListConsumer.setPic(pic);
        if (songListConsumerMapper.insert(songListConsumer) > 0) {
            // 歌单创建者ID
            Integer creatorId = songListConsumer.getUserId();
            String songListTitle = songListConsumer.getTitle();

            // 获取歌单创建者的用户名
            //QueryWrapper<Consumer> queryWrapperUser = new QueryWrapper<>();
            //queryWrapperUser.eq("id",creatorId);
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
                notificationRequest.setMessage("您关注的用户 \"" + username + "\" 添加了歌单 \"" + songListTitle + "\"，快来看看吧！");
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
    public R updateSongListConsumerImg(MultipartFile avatorFile, @RequestParam("id") int id) {
        String fileName =avatorFile.getOriginalFilename();
        String path="/"+bucketName+"/"+"songlist/";
        String imgPath = path + fileName;
        MinioUploadController.uploadSonglistConsumerImgFile(avatorFile);
        SongListConsumer songListConsumer = new SongListConsumer();
        songListConsumer.setId(id);
        songListConsumer.setPic(imgPath);
        if (songListConsumerMapper.updateById(songListConsumer) > 0) {
            // 歌单创建者ID
            Integer creatorId = songListConsumer.getUserId();
            String songListTitle = songListConsumer.getTitle();

            // 获取歌单创建者的用户名
            //QueryWrapper<Consumer> queryWrapperUser = new QueryWrapper<>();
            //queryWrapperUser.eq("id",creatorId);
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
                notificationRequest.setMessage("您关注的用户 \"" + username + "\" 更新了歌单 \"" + songListTitle + "\"的封面，快来看看吧！");
                notificationRequest.setType(1);

                // 使用 SocialClient 调用社交服务发送通知
                socialClient.addNotification(notificationRequest);
            }
            return R.success("上传成功", imgPath);
        } else {
            return R.error("上传失败");
        }
    }
}
