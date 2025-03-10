package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.dto.request.AggregatorSpecialistDTO;
import com.example.backprojectpapo.model.AggregatorSpecialist;
import com.example.backprojectpapo.repository.AggregatorSpecialistRepository;
import com.example.backprojectpapo.service.AggregatorSpecialistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AggregatorSpecialistServiceImpl implements AggregatorSpecialistService {

    private AggregatorSpecialistRepository aggregatorSpecialistRepository;

    @Override
    public AggregatorSpecialist save(AggregatorSpecialist aggregatorSpecialist) {
        return aggregatorSpecialistRepository.save(aggregatorSpecialist);
    }

    @Override
    public Optional<AggregatorSpecialistDTO> findByIdToDTO(Integer id) {
        AggregatorSpecialist aggregatorSpecialist = aggregatorSpecialistRepository.findById(id).orElse(null);

        assert aggregatorSpecialist != null;
        return Optional.of(new AggregatorSpecialistDTO(
                aggregatorSpecialist.getSurname(),
                aggregatorSpecialist.getName(),
                aggregatorSpecialist.getPatronymic(),
                aggregatorSpecialist.getDepartment(),
                aggregatorSpecialist.getPosition(),
                aggregatorSpecialist.getPhoneNumber(),
                aggregatorSpecialist.getAddInfo()
        ));
    }

    @Override
    public Optional<AggregatorSpecialist> findById(Integer id){
        return aggregatorSpecialistRepository.findById(id);
    }

    @Override
    public List<AggregatorSpecialist> findAll() {
        return aggregatorSpecialistRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        aggregatorSpecialistRepository.deleteById(id);
    }

}
