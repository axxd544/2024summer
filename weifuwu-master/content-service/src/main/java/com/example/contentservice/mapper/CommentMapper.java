package com.example.contentservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.contentservice.model.domain.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {

}
