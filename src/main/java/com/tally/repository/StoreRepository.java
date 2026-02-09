package com.tally.repository;

import com.tally.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,Long> {
    Store findByStoreAdminId(Long adminId);
}
