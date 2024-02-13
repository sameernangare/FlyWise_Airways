package com.example.airline_reservation.services;

import com.example.airline_reservation.dtos.Signup;
import com.example.airline_reservation.entities.User;
import com.example.airline_reservation.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    //dep : dao layer i/f
    @Autowired
    private UserRepository userRepository;
    //dep
    @Autowired
    private ModelMapper mapper;
    //dep
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Signup userRegistration(Signup reqDTO) {
        //dto --> entity
        User user = mapper.map(reqDTO, User.class);
        user.setPassword(encoder.encode(user.getPassword()));//pwd : encrypted using SHA
        return mapper.map(userRepository.save(user), Signup.class);
    }

}
