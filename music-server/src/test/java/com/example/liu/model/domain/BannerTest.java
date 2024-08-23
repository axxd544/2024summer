package com.example.liu.model.domain;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BannerTest {

    @Test
    public void testBanner() {
        Banner banner = new Banner();
        
        // 初始化对象，断言初始值为null
        assertNull(banner.getId());
        assertNull(banner.getPic());

        // 设置属性值，断言设置值成功
        banner.setId(1);
        banner.setPic("testPic.jpg");

        assertEquals(1, banner.getId());
        assertEquals("testPic.jpg", banner.getPic());

        // 反面测试：设置负数id
        banner.setId(-1);
        assertEquals(-1, banner.getId());
    }

    @Test
    public void testEqualsAndHashCode() {
        Banner banner1 = new Banner();
        banner1.setId(1);
        banner1.setPic("pic1.jpg");

        Banner banner2 = new Banner();
        banner2.setId(1);
        banner2.setPic("pic1.jpg");

        Banner banner3 = new Banner();
        banner3.setId(2);
        banner3.setPic("pic2.jpg");

        // 测试equals方法
        assertEquals(banner1, banner2);
        assertNotEquals(banner1, banner3);

        // 测试hashCode方法
        assertEquals(banner1.hashCode(), banner2.hashCode());
        assertNotEquals(banner1.hashCode(), banner3.hashCode());
    }

    @Test
    public void testToString() {
        Banner banner = new Banner();
        banner.setId(1);
        banner.setPic("testPic.jpg");

        String bannerString = banner.toString();

        // 使用contains来断言toString方法是否包含特定字段
        assertTrue(bannerString.contains("id=1"));
        assertTrue(bannerString.contains("pic=testPic.jpg"));
        assertTrue(bannerString.contains("serialVersionUID"));
    }
}
