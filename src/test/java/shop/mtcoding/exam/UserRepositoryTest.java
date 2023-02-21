package shop.mtcoding.exam;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.exam.model.User;
import shop.mtcoding.exam.model.UserRepository;

@MybatisTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAll_test() throws Exception {
        ObjectMapper om = new ObjectMapper();

        List<User> userList = userRepository.findAll();
        String responseBody = om.writeValueAsString(userList);

        assertThat(userList.get(0).getUsername()).isEqualTo("ssar");
    }
}
