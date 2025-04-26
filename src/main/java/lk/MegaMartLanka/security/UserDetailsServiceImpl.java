package lk.MegaMartLanka.security;

import lk.MegaMartLanka.entity.User;
import lk.MegaMartLanka.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername (username).orElse (null);

        if (user == null) {
            throw new UsernameNotFoundException ("User is Not Found");

        } else {
            return org.springframework.security.core.userdetails.User.builder ().username (user.getUsername ()).password (user.getPassword ()).build ();
        }
    }

}
