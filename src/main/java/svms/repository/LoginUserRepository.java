package svms.repository;

import svms.entity.LoginUser;

import java.util.List;

public interface LoginUserRepository {

    List<LoginUser> findAllLoginUsers();

    LoginUser findLoginUserById(Long Id);

    LoginUser findLoginUserByUsername(String username);

    List<LoginUser> findLoginUserByStatus(String status);

    List<LoginUser> findLoginUserByLastAttemptStatus(String lastAttemptStatus);

    int saveLoginUser(LoginUser loginUser);

    int deleteLoginUser(Long id);

    int updateLoginUserPassword(Long id, String password);

    int updateLoginUserStatus(Long id, String status);

    int cleanUp();

}
