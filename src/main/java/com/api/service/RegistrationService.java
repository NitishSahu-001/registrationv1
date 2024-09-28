package com.api.service;

import com.api.entity.Registration;
import com.api.exception.ResourceNotFoundException;
import com.api.payload.RegistrationDto;
import com.api.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        this.registrationRepository = registrationRepository;
        this.modelMapper = modelMapper;
    }

    private ModelMapper modelMapper;

    public List<RegistrationDto> getAllRegistration() {
        List<Registration> alls = registrationRepository.findAll();
        List<RegistrationDto> dtos = alls.stream().map(a -> mapToDto(a)).collect(Collectors.toList());

        return dtos;
    }

    public RegistrationDto createRegistration(RegistrationDto registrationDto) {
       //convert the Dto object data into Registration(Entity) object data

        Registration registration = mapToEntity(registrationDto);

        // again convert the Registration(Entity) object data into Dto object data
        Registration savedEntity = registrationRepository.save(registration);
        RegistrationDto dto = mapToDto(savedEntity);
        return dto;
    }

    public void deleteRegistration(long id) {
        registrationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Record Not Found")
        );
        registrationRepository.deleteById(id);
    }

    public Registration updateRegistration(long id,  Registration registration) {
        Registration reg = registrationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Record Not Found")
        );
        reg.setName(registration.getName());
        reg.setEmail(registration.getEmail());
        reg.setMobile(registration.getMobile());
        Registration savedEntity = registrationRepository.save(reg);
        return savedEntity;
    }
    //convert Dto into Entity
    Registration mapToEntity(RegistrationDto registrationDto){

        Registration registration = modelMapper.map(registrationDto, Registration.class);

        /*Registration registration=new Registration();
        registration.setName(registrationDto.getName());
        registration.setEmail(registrationDto.getEmail());
        registration.setMobile(registrationDto.getMobile());*/
        return registration;
    }

    //convert Entity into Dto
    RegistrationDto mapToDto(Registration registration){
        RegistrationDto dto = modelMapper.map(registration, RegistrationDto.class);
        
       /* RegistrationDto dto=new RegistrationDto();
        dto.setName(registration.getName());
        dto.setEmail(registration.getEmail());
        dto.setMobile(registration.getMobile());*/
        return dto;
    }

    public RegistrationDto getRegistrationById(long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Record not found"));
        return mapToDto(registration);
    }
}
