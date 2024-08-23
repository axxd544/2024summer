package com.example.liu.controller;
import com.example.liu.common.R;
import com.example.liu.model.domain.Banner;
import com.example.liu.service.BannerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class BannerControllerTest {

    @Mock
    private BannerService bannerService;

    @InjectMocks
    private BannerController bannerController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBanner_Success() {
        List<Banner> banners = Collections.singletonList(new Banner());  // 确保使用 Banner 类型
        R expectedResponse = R.success("成功获取轮播图与", banners);
        when(bannerService.getAllBanner()).thenReturn(banners);

        R response = bannerController.getAllBanner();

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testGetAllBanner_Failure() {
        when(bannerService.getAllBanner()).thenReturn(null);
        R expectedResponse = R.success("成功获取轮播图与", null);

        R response = bannerController.getAllBanner();

        assertThat(response).isEqualTo(expectedResponse);
    }
}
