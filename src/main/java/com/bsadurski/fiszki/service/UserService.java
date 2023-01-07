package com.bsadurski.fiszki.service;

import com.bsadurski.fiszki.dto.LoginDto;
import com.bsadurski.fiszki.entity.User;
import com.bsadurski.fiszki.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public boolean verify(LoginDto loginDto) {
        return true;
    }

    public int postUser(User user) {
        return repo.postUser(user);
    }

    public User getUser(int userId) {
        return repo.getUser(userId);
    }
}
