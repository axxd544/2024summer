package com.example.socialservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.socialservice.common.R;
import com.example.socialservice.model.domain.Comment;
import com.example.socialservice.model.request.CommentRequest;

public interface CommentService extends IService<Comment> {

    R addComment(CommentRequest addCommentRequest);

    R updateCommentMsg(CommentRequest upCommentRequest);

    R deleteComment(Integer id);

    R commentOfSongId(Integer songId);

    R commentOfSongListId(Integer songListId);

    R commentOfSongListConsumerId(Integer songListConsumerId);

}
