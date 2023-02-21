package shop.mtcoding.exam.model;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {

        public List<User> findAll();

        public User findById();

        public User findByUser(String username);

        public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

        public int insert(@Param("username") String username, @Param("password") String password,
                        @Param("email") String email);

        public int update(@Param("id") int id, @Param("username") String username, @Param("password") String password,
                        @Param("email") String email, @Param("createdAt") Timestamp createdAt);

        public int deleteById(int id);
}
