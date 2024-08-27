package com.example.consumerservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.consumerservice.common.R;
import com.example.consumerservice.model.domain.Collect;
import com.example.consumerservice.model.request.CollectRequest;

public interface CollectService extends IService<Collect> {

    R addCollection(CollectRequest addCollectRequest);

    R existSongId(CollectRequest isCollectRequest);

    R deleteCollect(Integer userId,Integer songId);

    R collectionOfUser(Integer userId);
}
