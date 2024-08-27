package com.africa.semicolon.devops.services;

import com.africa.semicolon.devops.dto.requests.LoginRequest;
import com.africa.semicolon.devops.dto.requests.RegisterRequest;
import com.africa.semicolon.devops.dto.responses.LoginResponse;
import com.africa.semicolon.devops.dto.responses.RegisterResponse;
import com.africa.semicolon.devops.exceptions.IllegalInputException;
import com.africa.semicolon.devops.exceptions.ThisUserAlreadyExistException;
import com.africa.semicolon.devops.models.DevUser;
import com.africa.semicolon.devops.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final   ModelMapper modelMapper;
    private final UserRepository userRepository;


    public RegisterResponse register(RegisterRequest registerRequest) {
        if(registerRequest.getUserName().isEmpty() ||registerRequest.getPassword().isEmpty()){
            throw new IllegalInputException("you can not register with an empty space");
        }
        if(!validateUser(registerRequest.getUserName())) throw new ThisUserAlreadyExistException("This user already exist enter another user name");
        DevUser devUser = modelMapper.map(registerRequest,DevUser.class);
        userRepository.save(devUser);
        RegisterResponse response = modelMapper.map(devUser, RegisterResponse.class);
        response.setMessage("Registered Successful");
        return response;

    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        DevUser user = findUserByName(loginRequest.getUserName());
        validatePassword(loginRequest);

        return modelMapper.map(user, LoginResponse.class);
    }

    private void validatePassword(LoginRequest loginRequest) {
         DevUser user = findUserByName(loginRequest.getUserName());
        if(!user.getPassword().equals(loginRequest.getPassword())) throw new RuntimeException("Wrong password");
    }

    private DevUser findUserByName(String userName) {
        DevUser user = userRepository.findDevUserByUserNameIgnoreCase(userName);
        if (user == null) throw new RuntimeException(userName + " does not exist");
        return user;
    }

    private boolean validateUser(String userName){
        var user = userRepository.findDevUserByUserNameIgnoreCase(userName);
        return user == null;
    }



}
