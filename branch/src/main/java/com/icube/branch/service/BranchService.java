package com.icube.branch.service;

import com.icube.branch.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchService {

    List<Branch> getAllBranch();

    Branch getBranchById(Long id);

    void saveOrUpdate(Branch branch);

    void delete(Long id);
}
