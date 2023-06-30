package com.rollingstone.repository;

import com.rollingstone.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository extends JpaRepository<Address, Long> {
    Page<Address> findAll(Pageable pageable);
}
