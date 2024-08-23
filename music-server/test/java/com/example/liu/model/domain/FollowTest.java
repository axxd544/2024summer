package com.example.liu.model.domain;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FollowTest {

    @Test
    public void testGetterSetter() {
        Follow follow = new Follow();
        
        follow.setId(1);
        follow.setFollowerId(100);
        follow.setFollowedId(200);
        
        assertEquals(1, follow.getId());
        assertEquals(100, follow.getFollowerId());
        assertEquals(200, follow.getFollowedId());
    }
}
