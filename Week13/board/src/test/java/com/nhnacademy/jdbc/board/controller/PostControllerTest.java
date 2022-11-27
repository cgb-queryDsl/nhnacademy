package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.domain.User;
import com.nhnacademy.jdbc.board.domain.dto.FileNameAndUuid;
import com.nhnacademy.jdbc.board.domain.dto.ReplyByPost;
import com.nhnacademy.jdbc.board.domain.dto.ViewPost;
import com.nhnacademy.jdbc.board.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class PostControllerTest {

    private static final String SESSION = "session";
    MockMvc mockMvc;
    MockHttpSession session;
    UserService userService;
    PostService postService;
    FileService fileService;
    UuidService uuidService;
    ReplyService replyService;

    private static final String DIR = "/Users/gyeongseo/Downloads/";
    private static final String FILE = "1.jpeg";
    private static final String CONTENT_TYPE = "image/jpeg";

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        postService = mock(PostService.class);
        fileService = mock(FileService.class);
        uuidService = mock(UuidService.class);
        replyService = mock(ReplyService.class);
        session = new MockHttpSession();

        mockMvc = MockMvcBuilders.standaloneSetup(new PostController(
                userService, postService, fileService, uuidService, replyService)).build();
    }

    @Test
    void postRegisterForm() throws Exception {
        session.setAttribute(SESSION, "2-user-on");

        User user = new User(1L, "aaa", "123", "name", new Date());
        Optional<User> optional = Optional.of(user);

        when(userService.getUser(2l)).thenReturn(optional);

        mockMvc.perform(get("/post/register")
                            .session(session))
                .andExpect(view().name("post/postRegisterForm"))
                .andExpect(model().attribute("userNickname", optional.get().getNickname()))
                .andDo(print());
    }

    @Test
    void postRegister_validation() throws Exception {
        mockMvc.perform(post("/post/register")
                    .param("title", "")
                    .param("content", ""))
                .andExpect(view().name("validation/postValidationError"))
                .andDo(print());
    }

    @Test
    void postRegister() throws Exception {
        session.setAttribute(SESSION, "2-user-on");

        FileInputStream fileInputStream = new FileInputStream(DIR + FILE);
        MockMultipartFile file1 = new MockMultipartFile("files", FILE, CONTENT_TYPE, fileInputStream);

        mockMvc.perform(multipart("/post/register")
                        .file(file1)
                        .session(session)
                        .param("title", "title")
                        .param("content", "content"))
                .andExpect(view().name("redirect:/community?page=1"))
                .andDo(print());
    }

    @Test
    void viewPost_user() throws Exception {
        session.setAttribute(SESSION, "2-user-on");

        ViewPost post = new ViewPost(1l, "a", "b", new Date(), "a");
        when(postService.getViewPostData(1l)).thenReturn(post);

        List<FileNameAndUuid> files = Arrays.asList(new FileNameAndUuid(1l, "a.txt", "b.txt"));
        when(fileService.getFiles(1l)).thenReturn(files);

        List<ReplyByPost> replies = Arrays.asList(new ReplyByPost(1l, 2l, "a", "b", new Date(), "N"));
        when(replyService.getAllRepliesNotDeleted(1l)).thenReturn(replies);

        mockMvc.perform(get("/post/{postId}", 1)
                            .session(session))
                .andExpect(view().name("post/viewPost"))
                .andExpect(model().attribute("post", post))
                .andExpect(model().attribute("files", files))
                .andExpect(model().attribute("replies", replies))
                .andExpect(model().attribute("loginId", 2l))
                .andDo(print());
    }

    @Test
    void viewPost_admin() throws Exception {
        session.setAttribute(SESSION, "1-admin-on");

        ViewPost post = new ViewPost(1l, "a", "b", new Date(), "a");
        when(postService.getViewPostData(1l)).thenReturn(post);

        List<FileNameAndUuid> files = Arrays.asList(new FileNameAndUuid(1l, "a.txt", "b.txt"));
        when(fileService.getFiles(1l)).thenReturn(files);

        List<ReplyByPost> replies = Arrays.asList(new ReplyByPost(1l, 2l, "a", "b", new Date(), "N"));
        when(replyService.getAllReplies(1l)).thenReturn(replies);

        mockMvc.perform(get("/post/{postId}", 1)
                        .session(session))
                .andExpect(view().name("post/viewPost"))
                .andExpect(model().attribute("post", post))
                .andExpect(model().attribute("files", files))
                .andExpect(model().attribute("replies", replies))
                .andExpect(model().attribute("loginId", 1l))
                .andDo(print());
    }

    @Test
    void updatePostForm() throws Exception {
        ViewPost post = new ViewPost(1l, "a", "b", new Date(), "aa");
        List<FileNameAndUuid> files = Arrays.asList(new FileNameAndUuid(1l, "a.txt", "aa1.txt"));

        when(postService.getViewPostData(1l)).thenReturn(post);
        when(fileService.getFiles(1l)).thenReturn(files);

        mockMvc.perform(get("/post/update/{postId}", 1))
                .andExpect(model().attribute("post", post))
                .andExpect(model().attribute("files", files))
                .andExpect(view().name("post/postUpdateForm"))
                .andDo(print());
    }

    @Test
    void update_validation() throws Exception {
        mockMvc.perform(post("/post/update")
                        .param("title", "")
                        .param("content", ""))
                .andExpect(view().name("validation/postValidationError"))
                .andDo(print());
    }

    @Test
    void updatePost() throws Exception {
        session.setAttribute(SESSION, "2-user-on");

        mockMvc.perform(post("/post/update")
                        .param("title", "title")
                        .param("content", "content"))
                .andExpect(view().name("redirect:/community?page=1"))
                .andDo(print());
    }

    @Test
    void deletePost() throws Exception {
        mockMvc.perform(get("/post/delete/{postId}", 1))
                .andExpect(view().name("redirect:/community?page=1"))
                .andDo(print());
    }

    @Test
    void restorePost() throws Exception {
        mockMvc.perform(get("/post/restore/{postId}", 1))
                .andExpect(view().name("redirect:/community?page=1"))
                .andDo(print());
    }
}