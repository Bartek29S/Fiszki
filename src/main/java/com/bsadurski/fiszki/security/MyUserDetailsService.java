//package com.bsadurski.fiszki.security;
//
//import com.bsadurski.fiszki.entity.User;
//import com.bsadurski.fiszki.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service()
//public class MyUserDetailsService implements UserDetailsService {
//    @Autowired
//    UserRepository repo;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = repo.getUserByUserName(username);
//        //Todo: if nie dziala
//        if (user == null) {
//            throw new UsernameNotFoundException("Nie znaleziono użytkownika");
//        }
//        System.out.println("To co znalazl w bazie" + user.getEmail() + user.getName() + user.getRole() + user.getPassword());
//        return new MyUserDetails(user);
//    }
//}
