package com.icube.branch.Repo;

import com.icube.branch.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BranchRepository extends CrudRepository<Branch,Long> {
}
