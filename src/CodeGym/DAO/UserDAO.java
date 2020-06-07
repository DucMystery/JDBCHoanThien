package CodeGym.DAO;

import CodeGym.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO{
    private static final String jdbcURL ="jdbc:mysql://localhost:3306/demov2";
    private static final String jdbcName ="mystery1309";
    private static final String jdbcPassword ="13091997";

    private static final String INSERT_USERS_SQL ="INSERT INTO user(name,email,country)" +
            "VALUES (?,?,?);";
    private static final String SELECT_USER_BY_ID="SELECT id,name,email,country FROM user WHERE id=?;";
    private static final String SELECT_ALL_USERS ="SELECT*FROM user";
    private static final String DELETE_USERS_SQL ="DELETE FROM user WHERE id=?;";
    private static final String UPDATE_USERS_SQL = "update user set name=?, email=?, country=? where id = ?;";

    private static final String GET_USER_BY_ID ="CALL get_user_by_id(?);";
    private static final String INSERT_USER_PROCEDURE ="CALL insert_user(?,?,?);";

    protected  Connection getConnection(){
        Connection connection = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(jdbcURL,jdbcName,jdbcPassword);
                System.out.println("Done");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
    @Override
    public List<User> selectAllUser() {
        List<User> ListUser = new ArrayList<>();
        Connection connection =getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int id= rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                ListUser.add(new User(id,name,email,country));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListUser;
    }

    @Override
    public boolean deleteUserById(int id) {
        boolean rowDeleted = false;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_SQL);
            preparedStatement.setInt(1,id);
            rowDeleted =preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    @Override
    public boolean updateUser(User user) {
        boolean rowUpdated = false;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getCountry());
            preparedStatement.setInt(4,user.getId());

            rowUpdated = preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public void insertUserStore(String name, String email, String country) {
        Connection connection = getConnection();
            try {
                CallableStatement statement = connection.prepareCall(INSERT_USER_PROCEDURE);
                statement.setString(1,name);
                statement.setString(2,email);
                statement.setString(3,country);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }
}
