
package com.fpt.job5project.service;

import java.util.List;

import com.fpt.job5project.dto.ForgetPasswordDTO;
import com.fpt.job5project.dto.UserChangeDTO;
import com.fpt.job5project.dto.UserDTO;

public interface IUserService {

    public List<UserDTO> listOfUsers();

    public UserDTO getUserID(long id);

    public UserDTO getMyInfo();

    public UserDTO addUser(UserDTO user);

    public UserDTO updateUser(long id, UserDTO user);

    public void deleteUser(long id);

    public void changePassword(long userId, UserChangeDTO request);

    public void lockAccount(long userId);

    public void forgetPassword(ForgetPasswordDTO request);

}
