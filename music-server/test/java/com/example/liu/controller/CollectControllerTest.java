package com.example.liu.controller;
import com.example.liu.common.R;
import com.example.liu.model.request.CollectRequest;
import com.example.liu.service.CollectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class CollectControllerTest {

    @Mock
    private CollectService collectService;

    @InjectMocks
    private CollectController collectController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddCollection_Success() {
        CollectRequest request = new CollectRequest();
        R expectedResponse = R.success("收藏成功");

        when(collectService.addCollection(request)).thenReturn(expectedResponse);

        R response = collectController.addCollection(request);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testAddCollection_Failure() {
        CollectRequest request = new CollectRequest();
        R expectedResponse = R.error("收藏失败");

        when(collectService.addCollection(request)).thenReturn(expectedResponse);

        R response = collectController.addCollection(request);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testDeleteCollection_Success() {
        Integer userId = 1;
        Integer songId = 1;
        R expectedResponse = R.success("取消收藏成功");

        when(collectService.deleteCollect(userId, songId)).thenReturn(expectedResponse);

        R response = collectController.deleteCollection(userId, songId);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testDeleteCollection_Failure() {
        Integer userId = 1;
        Integer songId = 1;
        R expectedResponse = R.error("取消收藏失败");

        when(collectService.deleteCollect(userId, songId)).thenReturn(expectedResponse);

        R response = collectController.deleteCollection(userId, songId);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testIsCollection_Success() {
        CollectRequest request = new CollectRequest();
        R expectedResponse = R.success("已收藏");

        when(collectService.existSongId(request)).thenReturn(expectedResponse);

        R response = collectController.isCollection(request);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testIsCollection_Failure() {
        CollectRequest request = new CollectRequest();
        R expectedResponse = R.error("未收藏");

        when(collectService.existSongId(request)).thenReturn(expectedResponse);

        R response = collectController.isCollection(request);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testCollectionOfUser_Success() {
        Integer userId = 1;
        R expectedResponse = R.success("获取收藏列表成功");

        when(collectService.collectionOfUser(userId)).thenReturn(expectedResponse);

        R response = collectController.collectionOfUser(userId);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testCollectionOfUser_Failure() {
        Integer userId = 1;
        R expectedResponse = R.error("获取收藏列表失败");

        when(collectService.collectionOfUser(userId)).thenReturn(expectedResponse);

        R response = collectController.collectionOfUser(userId);

        assertThat(response).isEqualTo(expectedResponse);
    }
}
