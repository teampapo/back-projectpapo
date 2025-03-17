package com.example.backprojectpapo.service.web;

import com.example.backprojectpapo.config.security.components.CustomUserDetails;
import com.example.backprojectpapo.dto.UserDto;
import com.example.backprojectpapo.model.AggregatorSpecialist;
import com.example.backprojectpapo.model.Customer;
import com.example.backprojectpapo.model.Organization;
import com.example.backprojectpapo.model.user.User;
import com.example.backprojectpapo.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userService.findUserByEmail(email);
        return getUser(user).map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException(email + " " +
                "not found."));
    }

    private Optional<UserDto> getUser(Optional<User> user) {
        if(user.isEmpty()) {
            return Optional.empty();
        }

        User usr = user.get();
        UserDto userDto = new UserDto();

        // получение id пользователя
        if(usr instanceof Customer customer){
            userDto.setId(customer.getId());
        } else if (usr instanceof AggregatorSpecialist aggregatorSpecialist) {
            userDto.setId(aggregatorSpecialist.getId());
        } else if (usr instanceof Organization organization) {
            userDto.setId(organization.getId());
        }

        userDto.setEmail(usr.getEmail());
        userDto.setPassword(usr.getPassword());
        userDto.setRole(usr.getRole());

        return Optional.of(userDto);
    }
}
