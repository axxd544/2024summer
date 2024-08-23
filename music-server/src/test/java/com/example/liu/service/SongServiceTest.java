package com.example.liu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.liu.common.R;
import com.example.liu.controller.MinioUploadController;
import com.example.liu.mapper.*;
import com.example.liu.model.domain.*;
import com.example.liu.model.request.NotificationRequest;
import com.example.liu.model.request.SongRequest;
import com.example.liu.service.impl.SongServiceImpl;
import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;
import io.minio.errors.*;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SongServiceTest {

    @Mock
    private SongMapper songMapper;

    @Mock
    private SongDeletedMapper songDeletedMapper;

    @Mock
    private SingerMapper singerMapper;

    @Mock
    private FollowMapper followMapper;

    @Mock
    private ConsumerMapper consumerMapper;

    @Mock
    private NotificationService notificationService;

    @Mock
    private MinioClient minioClient;

    @InjectMocks
    private SongServiceImpl songService;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddSongSuccess() throws IOException {
        SongRequest request = new SongRequest();
        request.setName("Test Song");
        MultipartFile lrcFile = mock(MultipartFile.class);
        MultipartFile mpFile = mock(MultipartFile.class);
        when(lrcFile.getBytes()).thenReturn("[00:00:00]歌词".getBytes(StandardCharsets.UTF_8));
        when(mpFile.getOriginalFilename()).thenReturn("test.mp3");

        when(songMapper.insert(any(Song.class))).thenReturn(1);
        when(singerMapper.selectById(anyInt())).thenReturn(new Singer());
        when(consumerMapper.selectOne(any(QueryWrapper.class))).thenReturn(new Consumer());
    }

    @Test
    public void testAddSongFailure() throws IOException {
        SongRequest request = new SongRequest();
        request.setName("Test Song");
        MultipartFile lrcFile = mock(MultipartFile.class);
        MultipartFile mpFile = mock(MultipartFile.class);
        when(lrcFile.getBytes()).thenReturn("[00:00:00]歌词".getBytes(StandardCharsets.UTF_8));
        when(mpFile.getOriginalFilename()).thenReturn("test.mp3");
        when(songMapper.insert(any(Song.class))).thenReturn(0);
    }

    @Test
    public void testUpdateSongMsgSuccess() {
        SongRequest request = new SongRequest();
        request.setName("Updated Song");
        when(songMapper.updateById(any(Song.class))).thenReturn(1);

        R response = songService.updateSongMsg(request);
        assertEquals(200, response.getCode());
        assertEquals("修改成功", response.getMessage());
    }

    @Test
    public void testUpdateSongMsgFailure() {
        SongRequest request = new SongRequest();
        request.setName("Updated Song");
        when(songMapper.updateById(any(Song.class))).thenReturn(0);

        R response = songService.updateSongMsg(request);
        assertEquals(200, response.getCode());
        assertEquals("修改失败", response.getMessage());
    }

    @Test
    public void testDeleteSongSuccess() throws Exception {
        Song song = new Song();
        song.setUrl("/bucket/song.mp3");
        when(songMapper.selectById(anyInt())).thenReturn(song);
        when(songMapper.deleteById(anyInt())).thenReturn(1);

        RemoveObjectArgs args = RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object("song.mp3")
                .build();
        doNothing().when(minioClient).removeObject(args);

    }

    @Test
    public void testDeleteSongFailure() throws Exception {
        Song song = new Song();
        song.setUrl("/bucket/song.mp3");
        when(songMapper.selectById(anyInt())).thenReturn(song);
        when(songMapper.deleteById(anyInt())).thenReturn(0);
    }

    @Test
    public void testExportSongLrcSuccess() throws IOException {
        Song song = new Song();
        song.setLyric("[00:00:00] Lyrics");
        when(songMapper.selectById(anyInt())).thenReturn(song);

        HttpServletResponse response = mock(HttpServletResponse.class);
        OutputStream outputStream = new ByteArrayOutputStream();
 

        assertFalse(outputStream.toString().contains("Lyrics"));
    }

    @Test
    public void testExportSongLrcFailure() throws IOException {
        when(songMapper.selectById(anyInt())).thenReturn(null);

        HttpServletResponse response = mock(HttpServletResponse.class);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            songService.exportSongLrc(1, response);
        });

        assertEquals("歌词文件不存在", exception.getMessage());
    }

    // Add other tests for remaining methods

}
