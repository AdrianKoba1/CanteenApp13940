package com.example.universityCanteenManagement.controller;

import com.example.universityCanteenManagement.dto.*;
import com.example.universityCanteenManagement.service.BranchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branch")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping("/add")
    public ResponseEntity<BranchResponseDTO> add(@RequestBody BranchAddRequestDTO dto) {
        return ResponseEntity.ok().body(branchService.addBranch(dto));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BranchResponseDTO>> getAll() {
        return ResponseEntity.ok(branchService.getAllBranches());
    }

    @PutMapping("/update")
    public ResponseEntity<BranchResponseDTO> update(@RequestBody BranchUpdateRequestDTO dto) {
        return ResponseEntity.ok(branchService.updateBranch(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BranchResponseDTO> delete(@PathVariable Long id) {
        return ResponseEntity.ok(branchService.deleteBranch(id));
    }
}
