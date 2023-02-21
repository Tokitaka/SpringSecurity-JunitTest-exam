package shop.mtcoding.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.exam.dto.UserReqDto.JoinReqDto;
import shop.mtcoding.exam.dto.UserReqDto.LoginReqDto;
import shop.mtcoding.exam.handler.ex.CustomException;
import shop.mtcoding.exam.model.User;
import shop.mtcoding.exam.model.UserRepository;
import shop.mtcoding.exam.util.EncryptSHA256Salt;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void join(JoinReqDto joinReqDto) {
        User SameUsername = userRepository.findByUser(joinReqDto.getUsername());

        if (SameUsername != null) {
            throw new CustomException("동일한 Username이 존재합니다.");
        }
        String salt = EncryptSHA256Salt.getSalt();
        String EncryptPassword = EncryptSHA256Salt.encryptSHA256(joinReqDto.getPassword(), salt);

        int result = userRepository.insert(joinReqDto.getUsername(), EncryptPassword, salt,
                joinReqDto.getEmail());

        if (result != 1) {
            throw new CustomException("회원가입 실패");
        }
    }

    @Transactional
    public User login(LoginReqDto loginReqDto) {

        User user = userRepository.findByUser(loginReqDto.getUsername());
        String userSalt = user.getSalt();
        String PasswordSalt = loginReqDto.getPassword() + userSalt;

        String checkPassword = EncryptSHA256Salt.encryptSHA256(loginReqDto.getPassword(), userSalt);

        // System.out.println(checkPassword);
        // System.out.println(checkPassword);
        User principal = userRepository.findByUsernameAndPassword(loginReqDto.getUsername(), checkPassword);

        if (principal == null) {
            throw new CustomException("Username 또는 Password를 확인하세요.");
        }

        return principal;
    }

}
