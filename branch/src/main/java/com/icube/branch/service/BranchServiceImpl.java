package com.icube.branch.service;

import com.icube.branch.Repo.BranchRepository;
import com.icube.branch.model.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Filter;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository repo;

    @Override
    public List<Branch> getAllBranch() {
        return (List<Branch>) repo.findAll();
    }

    @Override
    public Branch getBranchById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public void saveOrUpdate(Branch branch) {
        repo.save(branch);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
