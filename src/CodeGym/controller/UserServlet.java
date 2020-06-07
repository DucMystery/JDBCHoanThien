package CodeGym.controller;

import CodeGym.DAO.UserDAO;
import CodeGym.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID =1L;
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO =new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String action = req.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action){
            case "create":
                insertUser(req,resp);
                break;
            case "edit":
                editUser(req,resp);
        }
        resp.setContentType("text/html;charset=UTF-8");
    }

    private void editUser(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");

        User user = new User(id,name,email,country);
        userDAO.updateUser(user);
        listUser(req, resp);
    }

    private void insertUser(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        userDAO.insertUserStore(name,email,country);
        listUser(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action ="";
        }
        switch (action){
            case "create":
                showCreateUser(req,resp);
                break;
            case "edit":
                showEditForm(req,resp);
                break;
            case "delete":
                deleteUser(req,resp);
                break;
            default:
                listUser(req,resp);
        }
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        userDAO.deleteUserById(id);
        listUser(req, resp);

    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        User existUser = userDAO.getUserById(id);
        req.setAttribute("user",existUser);
        RequestDispatcher dispatcher =req.getRequestDispatcher("Users/edit.jsp");
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateUser(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher =req.getRequestDispatcher("Users/create.jsp");
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listUser(HttpServletRequest req, HttpServletResponse resp) {
        List<User> ListUser = new ArrayList<>();
        ListUser = userDAO.selectAllUser();
        req.setAttribute("ListUser",ListUser);
        RequestDispatcher dispatcher = req.getRequestDispatcher("Users/list.jsp");
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
