package com.example.liu.controller;
import com.example.liu.common.R;
import com.example.liu.model.request.CommentRequest;
import com.example.liu.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddComment_Success() {
        CommentRequest request = new CommentRequest();
        R expectedResponse = R.success("评论添加成功");

        when(commentService.addComment(request)).thenReturn(expectedResponse);

        R response = commentController.addComment(request);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testAddComment_Failure() {
        CommentRequest request = new CommentRequest();
        R expectedResponse = R.error("评论添加失败");

        when(commentService.addComment(request)).thenReturn(expectedResponse);

        R response = commentController.addComment(request);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testDeleteComment_Success() {
        Integer commentId = 1;
        R expectedResponse = R.success("评论删除成功");

        when(commentService.deleteComment(commentId)).thenReturn(expectedResponse);

        R response = commentController.deleteComment(commentId);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testDeleteComment_Failure() {
        Integer commentId = 1;
        R expectedResponse = R.error("评论删除失败");

        when(commentService.deleteComment(commentId)).thenReturn(expectedResponse);

        R response = commentController.deleteComment(commentId);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testCommentOfSongId_Success() {
        Integer songId = 1;
        R expectedResponse = R.success("获取评论成功");

        when(commentService.commentOfSongId(songId)).thenReturn(expectedResponse);

        R response = commentController.commentOfSongId(songId);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testCommentOfSongId_Failure() {
        Integer songId = 1;
        R expectedResponse = R.error("获取评论失败");

        when(commentService.commentOfSongId(songId)).thenReturn(expectedResponse);

        R response = commentController.commentOfSongId(songId);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testCommentOfSongListId_Success() {
        Integer songListId = 1;
        R expectedResponse = R.success("获取歌单评论成功");

        when(commentService.commentOfSongListId(songListId)).thenReturn(expectedResponse);

        R response = commentController.commentOfSongListId(songListId);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testCommentOfSongListId_Failure() {
        Integer songListId = 1;
        R expectedResponse = R.error("获取歌单评论失败");

        when(commentService.commentOfSongListId(songListId)).thenReturn(expectedResponse);

        R response = commentController.commentOfSongListId(songListId);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testCommentOfSongListConsumerId_Success() {
        Integer songListConsumerId = 1;
        R expectedResponse = R.success("获取用户歌单评论成功");

        when(commentService.commentOfSongListConsumerId(songListConsumerId)).thenReturn(expectedResponse);

        R response = commentController.commentOfSongListConsumerId(songListConsumerId);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testCommentOfSongListConsumerId_Failure() {
        Integer songListConsumerId = 1;
        R expectedResponse = R.error("获取用户歌单评论失败");

        when(commentService.commentOfSongListConsumerId(songListConsumerId)).thenReturn(expectedResponse);

        R response = commentController.commentOfSongListConsumerId(songListConsumerId);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testCommentOfLike_Success() {
        CommentRequest request = new CommentRequest();
        R expectedResponse = R.success("点赞成功");

        when(commentService.updateCommentMsg(request)).thenReturn(expectedResponse);

        R response = commentController.commentOfLike(request);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testCommentOfLike_Failure() {
        CommentRequest request = new CommentRequest();
        R expectedResponse = R.error("点赞失败");

        when(commentService.updateCommentMsg(request)).thenReturn(expectedResponse);

        R response = commentController.commentOfLike(request);

        assertThat(response).isEqualTo(expectedResponse);
    }
}
