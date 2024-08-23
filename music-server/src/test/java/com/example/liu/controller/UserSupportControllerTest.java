package com.example.liu.controller;

import com.example.liu.common.R;
import com.example.liu.model.request.UserSupportRequest;
import com.example.liu.service.UserSupportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserSupportControllerTest {

    @Mock
    private UserSupportService userSupportService;

    @InjectMocks
    private UserSupportController userSupportController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIsUserSupportComment() {
        UserSupportRequest request = new UserSupportRequest();
        R expectedResponse = R.success("Support status checked");
        when(userSupportService.isUserSupportComment(request)).thenReturn(expectedResponse);

        R actualResponse = userSupportController.isUserSupportComment(request);

        assertEquals(expectedResponse, actualResponse);
        verify(userSupportService, times(1)).isUserSupportComment(request);
    }

    @Test
    public void testInsertCommentSupport() {
        UserSupportRequest request = new UserSupportRequest();
        R expectedResponse = R.success("Comment support inserted");
        when(userSupportService.insertCommentSupport(request)).thenReturn(expectedResponse);

        R actualResponse = userSupportController.insertCommentSupport(request);

        assertEquals(expectedResponse, actualResponse);
        verify(userSupportService, times(1)).insertCommentSupport(request);
    }

    @Test
    public void testDeleteCommentSupport() {
        UserSupportRequest request = new UserSupportRequest();
        R expectedResponse = R.success("Comment support deleted");
        when(userSupportService.deleteCommentSupport(request)).thenReturn(expectedResponse);

        R actualResponse = userSupportController.deleteCommentSupport(request);

        assertEquals(expectedResponse, actualResponse);
        verify(userSupportService, times(1)).deleteCommentSupport(request);
    }

    @Test
    public void testIsUserSupportComment_InvalidRequest() {
        UserSupportRequest invalidRequest = new UserSupportRequest();
        R expectedResponse = R.error("Invalid request");
        when(userSupportService.isUserSupportComment(invalidRequest)).thenReturn(expectedResponse);

        R actualResponse = userSupportController.isUserSupportComment(invalidRequest);

        assertEquals(expectedResponse, actualResponse);
        verify(userSupportService, times(1)).isUserSupportComment(invalidRequest);
    }

    @Test
    public void testInsertCommentSupport_InvalidRequest() {
        UserSupportRequest invalidRequest = new UserSupportRequest();
        R expectedResponse = R.error("Invalid request");
        when(userSupportService.insertCommentSupport(invalidRequest)).thenReturn(expectedResponse);

        R actualResponse = userSupportController.insertCommentSupport(invalidRequest);

        assertEquals(expectedResponse, actualResponse);
        verify(userSupportService, times(1)).insertCommentSupport(invalidRequest);
    }

    @Test
    public void testDeleteCommentSupport_InvalidRequest() {
        UserSupportRequest invalidRequest = new UserSupportRequest();
        R expectedResponse = R.error("Invalid request");
        when(userSupportService.deleteCommentSupport(invalidRequest)).thenReturn(expectedResponse);

        R actualResponse = userSupportController.deleteCommentSupport(invalidRequest);

        assertEquals(expectedResponse, actualResponse);
        verify(userSupportService, times(1)).deleteCommentSupport(invalidRequest);
    }
}
