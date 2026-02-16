package com.tally.repository;

import com.tally.model.Branch;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch,Long> {
    List<Branch> getBranchesByStoreId(Long storeId);
}
