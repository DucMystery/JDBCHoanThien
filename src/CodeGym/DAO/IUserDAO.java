package CodeGym.DAO;

import CodeGym.model.User;

import java.util.List;

public interface IUserDAO {
    List<User> selectAllUser();
    boolean deleteUserById(int id);
    boolean updateUser(User user);
    User getUserById(int id);
    void insertUserStore(String name, String email, String country);

}
