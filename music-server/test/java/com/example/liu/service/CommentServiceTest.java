package com.example.liu.service;
import com.example.liu.service.impl.*;

import com.example.liu.mapper.CommentMapper;
import com.example.liu.model.domain.Comment;
import com.example.liu.model.request.CommentRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;


import com.example.liu.common.R;


public class CommentServiceTest {

    @Mock
    private CommentMapper commentMapper;

    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void testUpdateCommentMsg_Success() {
        CommentRequest request = new CommentRequest();
        Comment comment = new Comment();
        doReturn(1).when(commentMapper).updateById(comment);

        R result = commentService.updateCommentMsg(request);

        assertEquals("点赞成功", result.getMessage());
    }

    @Test
    public void testUpdateCommentMsg_Failure() {
        CommentRequest request = new CommentRequest();
        Comment comment = new Comment();
        doReturn(0).when(commentMapper).updateById(comment);

        R result = commentService.updateCommentMsg(request);

        assertEquals("点赞失败", result.getMessage());
    }

    @Test
    public void testDeleteComment_Success() {
        Integer commentId = 1;
        doReturn(1).when(commentMapper).deleteById(commentId);

        R result = commentService.deleteComment(commentId);

        assertEquals("删除成功", result.getMessage());
    }

    @Test
    public void testDeleteComment_Failure() {
        Integer commentId = 1;
        doReturn(0).when(commentMapper).deleteById(commentId);

        R result = commentService.deleteComment(commentId);

        assertEquals("删除失败", result.getMessage());
    }

  
   

   
}
