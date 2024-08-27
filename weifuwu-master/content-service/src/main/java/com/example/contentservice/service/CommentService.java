package com.example.contentservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.contentservice.common.R;
import com.example.contentservice.model.domain.Comment;
import com.example.contentservice.model.request.CommentRequest;

public interface CommentService extends IService<Comment> {

    R addComment(CommentRequest addCommentRequest);

    R updateCommentMsg(CommentRequest upCommentRequest);

    R deleteComment(Integer id);

    R commentOfSongId(Integer songId);

    R commentOfSongListId(Integer songListId);

    R commentOfSongListConsumerId(Integer songListConsumerId);

}
