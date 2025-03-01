package com.example.backprojectpapo.service.web;

import com.example.backprojectpapo.config.security.components.CustomUserDetails;
import com.example.backprojectpapo.model.AggregatorSpecialist;
import com.example.backprojectpapo.model.Customer;
import com.example.backprojectpapo.model.Organization;
import com.example.backprojectpapo.repository.AggregatorSpecialistRepository;
import com.example.backprojectpapo.repository.CustomerRepository;
import com.example.backprojectpapo.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AggregatorSpecialistRepository aggregatorSpecialistRepository;
    private final CustomerRepository customerRepository;
    private final OrganizationRepository organizationRepository;

    @Autowired
    public CustomUserDetailsService(AggregatorSpecialistRepository aggregatorSpecialistRepository, CustomerRepository customerRepository, OrganizationRepository organizationRepository) {
        this.aggregatorSpecialistRepository = aggregatorSpecialistRepository;
        this.customerRepository = customerRepository;
        this.organizationRepository = organizationRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AggregatorSpecialist> aggregatorSpecialist = aggregatorSpecialistRepository.findByEmail(email);
        if(aggregatorSpecialist.isPresent()){
            return new CustomUserDetails(
                    aggregatorSpecialist.get().getEmail(),
                    null,
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
            );
        }

        Optional<Customer> customer = customerRepository.findByEmail(email);
        if(customer.isPresent()){
            return new CustomUserDetails(
                    customer.get().getEmail(),
                    null,
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_CUSTOMER"))
            );
        }

        Optional<Organization> organization = organizationRepository.findByResponsiblePersonEmail(email);
        if(organization.isPresent()){
            return new CustomUserDetails(
                    organization.get().getResponsiblePersonEmail(),
                    null,
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_ORGANIZATION"))
            );
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
