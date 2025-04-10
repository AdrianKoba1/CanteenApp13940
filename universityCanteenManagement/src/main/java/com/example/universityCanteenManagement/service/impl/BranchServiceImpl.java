package com.example.universityCanteenManagement.service.impl;

import com.example.universityCanteenManagement.domain.Branch;
import com.example.universityCanteenManagement.dto.BranchAddRequestDTO;
import com.example.universityCanteenManagement.dto.BranchResponseDTO;
import com.example.universityCanteenManagement.dto.BranchUpdateRequestDTO;
import com.example.universityCanteenManagement.repository.BranchRepository;
import com.example.universityCanteenManagement.service.BranchService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public BranchResponseDTO addBranch(BranchAddRequestDTO dto) {

        Branch branch = Branch.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .location(dto.getLocation())
                .contactInfo(dto.getContactInfo())
                .build();

        branchRepository.save(branch);
        return toBranchResponseDTO(branch);
    }

    @Override
    public List<BranchResponseDTO> getAllBranches() {
        List<Branch> branchList = branchRepository.findAll();

        List<BranchResponseDTO> branchResponseDTOList = new ArrayList<>();

        for (Branch branch : branchList){

            branchResponseDTOList.add(toBranchResponseDTO(branch));
        }

        return branchResponseDTOList;
    }

    @Override
    public BranchResponseDTO updateBranch(BranchUpdateRequestDTO dto) {

        Branch branch = branchRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Branch with the following ID is not found :("));

        branchRepository.save(toBranch(dto, branch));

        return toBranchResponseDTO(branch);
    }

    @Override
    public BranchResponseDTO deleteBranch(Long id) {

        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Branch with the following ID is not found :("));

        branchRepository.delete(branch);
        return toBranchResponseDTO(branch);
    }

    public BranchResponseDTO toBranchResponseDTO(Branch branch){

        return BranchResponseDTO.builder()
                .id(branch.getId())
                .name(branch.getName())
                .description(branch.getDescription())
                .location(branch.getLocation())
                .contactInfo(branch.getContactInfo())
                .build();
    }

    public Branch toBranch(BranchUpdateRequestDTO dto, Branch branch){

        branch.setName(dto.getName());
        branch.setDescription(dto.getDescription());
        branch.setLocation(dto.getLocation());
        branch.setContactInfo(dto.getContactInfo());

        return branch;
    }
}
