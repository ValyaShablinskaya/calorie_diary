package by.it_academy.calorie_diary.security;

import by.it_academy.calorie_diary.entity.User;
import by.it_academy.calorie_diary.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final String USER_NOT_FOUND_EXCEPTION = "Specified user is not found";
    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        User user = userRepository.findByMail(mail).orElseThrow(() ->
                new UsernameNotFoundException(USER_NOT_FOUND_EXCEPTION));
        return SecurityUser.fromUser(user);
    }
}
