package xzz.library.service;

import xzz.library.dto.BooksDto;
import xzz.library.dto.UsersDto;
import xzz.library.pojo.Book;
import xzz.library.pojo.User;

public interface AdminService {

    //========================================================================================
    //Users
    //========================================================================================
    UsersDto getUsers(Integer limit, Integer page);

    User getUser(String id);

    String updateUser(User user);

    String addUser(User user);

    String deleteUser(String[] ids);

}
