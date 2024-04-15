
package com.fpt.job5project.service;

import com.fpt.job5project.dto.UserDTO;

import java.util.List;

public interface IUserService {

    public List<UserDTO> listOfUsers();

    public UserDTO getUserID(long id);

    public UserDTO getMyInfo();

    public UserDTO addUser(UserDTO user);

    public UserDTO updateUser(long id, UserDTO user);

    public void deleteUser(long id);
}
