package com.example.liu.service;
import com.example.liu.service.impl.*;

import com.example.liu.mapper.BannerMapper;
import com.example.liu.model.domain.Banner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

public class BannerServiceTest {

    @Mock
    private BannerMapper bannerMapper;

    @InjectMocks
    private BannerServiceImpl bannerService;

    private CacheManager cacheManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("banner")));
        simpleCacheManager.initializeCaches(); 
        cacheManager = simpleCacheManager;

    }

    @Test
    public void testGetAllBanner_CacheMiss() {
        List<Banner> banners = Arrays.asList(
            new Banner() {{
                setId(1);
                setPic("Banner 1");
            }},
            new Banner() {{
                setId(2);
                setPic("Banner 2");
            }}
        );

        doReturn(banners).when(bannerMapper).selectList(null);

        List<Banner> result = bannerService.getAllBanner();
        assertEquals(banners, result);

        Cache cache = cacheManager.getCache("banner");
        assert cache != null;
       
    }

    @Test
    public void testGetAllBanner_CacheHit() {
        List<Banner> banners = Collections.emptyList();

        doReturn(banners).when(bannerMapper).selectList(null);

        bannerService.getAllBanner();

        Cache cache = cacheManager.getCache("banner");
        assert cache != null;
        cache.clear();

        List<Banner> result = bannerService.getAllBanner();
        assertEquals(banners, result);


    }
}
