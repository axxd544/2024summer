package com.example.liu.model.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    public void testOrder() {
        Order order = new Order();
        
        // 初始化对象，断言初始值为默认值
        assertEquals(0, order.getId()); // 默认值为0
        assertNull(order.getName()); // 默认值为null
        assertNull(order.getPassword()); // 默认值为null

        // 设置属性值，断言设置值成功
        order.setId(1);
        order.setName("Sample Order");
        order.setPassword("securePassword");

        assertEquals(1, order.getId());
        assertEquals("Sample Order", order.getName());
        assertEquals("securePassword", order.getPassword());

        // 反面测试：设置非法值
        order.setId(-1);
        assertEquals(-1, order.getId());
    }

    @Test
    public void testToString() {
        Order order = new Order();
        order.setId(1);
        order.setName("Sample Order");
        order.setPassword("securePassword");

        String orderString = order.toString();

        // 使用contains来断言toString方法是否包含特定字段
        assertTrue(orderString.contains("id=1"));
        assertTrue(orderString.contains("name=Sample Order"));
        assertTrue(orderString.contains("password=securePassword"));
    }
}
