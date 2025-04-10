package com.example.universityCanteenManagement.service;

import com.example.universityCanteenManagement.dto.BranchAddRequestDTO;
import com.example.universityCanteenManagement.dto.BranchResponseDTO;
import com.example.universityCanteenManagement.dto.BranchUpdateRequestDTO;

import java.util.List;

public interface BranchService {
    BranchResponseDTO addBranch(BranchAddRequestDTO dto);

    List<BranchResponseDTO> getAllBranches();

    BranchResponseDTO updateBranch(BranchUpdateRequestDTO dto);

    BranchResponseDTO deleteBranch(Long id);
}
