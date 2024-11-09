package com.HR.LeaveManagementSystem.services.imple;

import com.HR.LeaveManagementSystem.entities.AbsenceType;
import com.HR.LeaveManagementSystem.exceptions.ResourceNotFoundException;
import com.HR.LeaveManagementSystem.payloads.AbsenceTypeDto;
import com.HR.LeaveManagementSystem.repositories.AbsenceTypeRepo;
import com.HR.LeaveManagementSystem.services.AbsenceTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Cacheable("absenceType")
public class AbsenceTypeServiceImple implements AbsenceTypeService {

    @Autowired
    private AbsenceTypeRepo absenceTypeRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AbsenceTypeDto createAbsenceType(AbsenceTypeDto absenceTypeDto) {

        AbsenceType absenceType = this.modelMapper.map(absenceTypeDto, AbsenceType.class);

        AbsenceType createAbsenceType = this.absenceTypeRepo.save(absenceType);

        return this.modelMapper.map(createAbsenceType,AbsenceTypeDto.class);
    }

    @CachePut("absenceType")
    @Override
    public AbsenceTypeDto updateAbsenceType(AbsenceTypeDto absenceTypeDto, int absenceTypeId) {

        AbsenceType absenceType = this.absenceTypeRepo.findById(absenceTypeId).orElseThrow(()-> new ResourceNotFoundException("absenceType","AbsenceType",absenceTypeId));

        absenceType.setName(absenceTypeDto.getName());
        absenceType.setType(absenceTypeDto.getType());
        absenceType.setCategory(absenceTypeDto.getCategory());
        absenceType.setApproval_flow(absenceTypeDto.getApproval_flow());
        absenceType.setDescription(absenceTypeDto.getDescription());

        AbsenceType updatedAbsenceType = this.absenceTypeRepo.save(absenceType);

        return this.modelMapper.map(updatedAbsenceType,AbsenceTypeDto.class);
    }

    @CacheEvict("absenceType")
    @Override
    public void deleteAbsenceType(int absenceTypeId) {
        AbsenceType absenceType = this.absenceTypeRepo.findById(absenceTypeId).orElseThrow(()-> new ResourceNotFoundException("absenceType","AbsenceType",absenceTypeId));
        this.absenceTypeRepo.delete(absenceType);
    }

    @Override
    public AbsenceTypeDto getAbsenceType(int absenceTypeId) {
        AbsenceType absenceType = this.absenceTypeRepo.findById(absenceTypeId).orElseThrow(()-> new ResourceNotFoundException("absenceType","AbsenceType",absenceTypeId));
        return this.modelMapper.map(absenceType,AbsenceTypeDto.class);
    }

    @Override
    public List<AbsenceTypeDto> getAbsenceTypes() {
        List<AbsenceType> absenceTypes = this.absenceTypeRepo.findAll();
        return absenceTypes.stream().map(absenceType -> this.modelMapper.map(absenceType,AbsenceTypeDto.class)).collect(Collectors.toList());
    }
}
