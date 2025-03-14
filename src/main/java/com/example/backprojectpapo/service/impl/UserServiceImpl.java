package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.dto.AggregatorSpecialistDto;
import com.example.backprojectpapo.dto.CustomerDto;
import com.example.backprojectpapo.dto.OrganizationDto;
import com.example.backprojectpapo.exception.UserAlreadyExistsException;
import com.example.backprojectpapo.exception.UserNotFoundException;
import com.example.backprojectpapo.model.AggregatorSpecialist;
import com.example.backprojectpapo.model.Customer;
import com.example.backprojectpapo.model.Organization;
import com.example.backprojectpapo.model.enums.Role;
import com.example.backprojectpapo.model.user.User;
import com.example.backprojectpapo.repository.AggregatorSpecialistRepository;
import com.example.backprojectpapo.repository.CustomerRepository;
import com.example.backprojectpapo.repository.OrganizationRepository;
import com.example.backprojectpapo.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final AggregatorSpecialistRepository aggregatorSpecialistRepository;
    private final CustomerRepository customerRepository;
    private final OrganizationRepository organizationRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(AggregatorSpecialistRepository aggregatorSpecialistRepository,
                       CustomerRepository customerRepository,
                       OrganizationRepository organizationRepository,
                           PasswordEncoder passwordEncoder) {
        this.aggregatorSpecialistRepository = aggregatorSpecialistRepository;
        this.customerRepository = customerRepository;
        this.organizationRepository = organizationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        Optional<AggregatorSpecialist> aggregatorSpecialist= aggregatorSpecialistRepository.findByEmail(email);
        if (aggregatorSpecialist.isPresent()) {
            return Optional.of(aggregatorSpecialist.get());
        }

        Optional<Customer> customer = customerRepository.findByEmail(email);
        if (customer.isPresent()) {
            return Optional.of(customer.get());
        }

        Optional<Organization> organization = organizationRepository.findByEmail(email);
        if (organization.isPresent()) {
            return Optional.of(organization.get());
        }

        throw new UserNotFoundException("User with email " + email + " not found");
    }

    @Override
    @Transactional
    public User registerAggregatorSpecialist(AggregatorSpecialistDto dto) {
        AggregatorSpecialist aggregatorSpecialist = new AggregatorSpecialist();
        if(aggregatorSpecialistRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("Aggregator specialist is already exists");
        }

        //TODO 09.03 add exceptions for empty required parameters
        aggregatorSpecialist.setEmail(dto.getEmail());
        Optional.ofNullable(dto.getPassword()).map(passwordEncoder::encode).ifPresent(aggregatorSpecialist::setPassword);
        aggregatorSpecialist.setRole(Role.ADMIN);
        aggregatorSpecialist.setIsActive(true);

        Optional.ofNullable(dto.getSurname()).ifPresent(aggregatorSpecialist::setSurname);
        Optional.ofNullable(dto.getName()).ifPresent(aggregatorSpecialist::setName);
        Optional.ofNullable(dto.getPatronymic()).ifPresent(aggregatorSpecialist::setPatronymic);
        Optional.ofNullable(dto.getDepartment()).ifPresent(aggregatorSpecialist::setDepartment);
        Optional.ofNullable(dto.getPosition()).ifPresent(aggregatorSpecialist::setPosition);
        Optional.ofNullable(dto.getPhoneNumber()).ifPresent(aggregatorSpecialist::setPhoneNumber);
        Optional.ofNullable(dto.getAddInfo()).ifPresent(aggregatorSpecialist::setAddInfo);
        return aggregatorSpecialistRepository.save(aggregatorSpecialist);
    }

    @Override
    @Transactional
    public User registerCustomer(CustomerDto dto) {
        Customer customer = new Customer();
        if(customerRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("Customer is already exists");
        }

        //TODO 09.03 add exceptions for empty required parameters
        customer.setEmail(dto.getEmail());
        customer.setRole(Role.CUSTOMER);
        customer.setIsActive(true);

        Optional.ofNullable(dto.getSurname()).ifPresent(customer::setSurname);
        Optional.ofNullable(dto.getName()).ifPresent(customer::setName);
        Optional.ofNullable(dto.getPatronymic()).ifPresent(customer::setPatronymic);
        Optional.ofNullable(dto.getPhoneNumber()).ifPresent(customer::setPhoneNumber);
        Optional.ofNullable(dto.getAddInfo()).ifPresent(customer::setAddInfo);
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public User registerOrganization(OrganizationDto dto) {
        Organization organization = new Organization();
        if(organizationRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("Organization is already exists");
        }

        //TODO 09.03 add exceptions for empty required parameters
        organization.setEmail(dto.getEmail());
        organization.setRole(Role.ORGANIZATION);
        organization.setIsActive(true);


        Optional.ofNullable(dto.getFullName()).ifPresent(organization::setFullName);
        Optional.ofNullable(dto.getShortName()).ifPresent(organization::setShortName);
        Optional.ofNullable(dto.getInn()).ifPresent(organization::setInn);
        Optional.ofNullable(dto.getKpp()).ifPresent(organization::setKpp);
        Optional.ofNullable(dto.getOgrn()).ifPresent(organization::setOgrn);
        Optional.ofNullable(dto.getResponsiblePersonSurname()).ifPresent(organization::setResponsiblePersonSurname);
        Optional.ofNullable(dto.getResponsiblePersonName()).ifPresent(organization::setResponsiblePersonName);
        Optional.ofNullable(dto.getResponsiblePersonPatronymic()).ifPresent(organization::setResponsiblePersonPatronymic);
        Optional.ofNullable(dto.getResponsiblePersonEmail()).ifPresent(organization::setResponsiblePersonEmail);
        Optional.ofNullable(dto.getResponsiblePersonPhoneNumber()).ifPresent(organization::setResponsiblePersonPhoneNumber);
        Optional.ofNullable(dto.getAddInfo()).ifPresent(organization::setAddInfo);
        return organizationRepository.save(organization);
    }
}
