package shop.mtcoding.exam.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.exam.model.User;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private MockHttpSession mockSession;

    @BeforeEach
    public void setUp() {
        // 세션 주입
        User user = new User();
        user.setId(1);
        user.setUsername("ssar");
        user.setPassword("1234");
        user.setEmail("ssar@nate.com");
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        mockSession = new MockHttpSession();
        mockSession.setAttribute("principal", user);
    }

    @Test
    public void join_test() throws Exception {
        // given
        // String requestBody = "username=&password=0000&email=heesun@nate.com";
        // String requestBody = "username=&password=&email=heesun@nate.com";
        // String requestBody = "username=&password=0000&email=";
        String requestBody = "username=heesun&password=0000&email=heesun@nate.com";
        // when
        ResultActions resultActions = mvc.perform(post("/join")
                .content(requestBody).contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
        // then
        resultActions.andExpect(status().is3xxRedirection());
    }

    @Test
    public void login_test() throws Exception {
        // String requsetBody = "username=''&password=1234";
        // String requsetBody = "username=&password=1234";
        String requsetBody = "username=ssar&password=1234";

        ResultActions resultActions = mvc.perform(post("/login").content(requsetBody)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE).session(mockSession));

        User principal = (User) mockSession.getAttribute("principal");

        assertThat(principal.getUsername()).isEqualTo("ssar");
        resultActions.andExpect(status().is3xxRedirection());

    }

    @Test
    public void main_test() throws Exception {

        ResultActions resultActions = mvc.perform(get("/main").session(mockSession));
        User principal = (User) mockSession.getAttribute("principal");
        assertThat(principal.getUsername()).isEqualTo("ssar");
        resultActions.andExpect(status().is3xxRedirection());

    }

}
