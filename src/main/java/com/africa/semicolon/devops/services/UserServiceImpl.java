package com.africa.semicolon.devops.services;

import com.africa.semicolon.devops.dto.requests.RegisterRequest;
import com.africa.semicolon.devops.dto.responses.RegisterResponse;
import com.africa.semicolon.devops.exceptions.IllegalInputException;
import com.africa.semicolon.devops.exceptions.ThisUserAlreadyExistException;
import com.africa.semicolon.devops.models.DevUser;
import com.africa.semicolon.devops.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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
    private boolean validateUser(String userName){
        var user = userRepository.findDevUserByUserNameIgnoreCase(userName);
        return user == null;
    }



}
