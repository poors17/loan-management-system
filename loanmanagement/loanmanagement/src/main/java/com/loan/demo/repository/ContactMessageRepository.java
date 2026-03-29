package com.loan.demo.repository;

import com.loan.demo.model.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
    // No extra methods for now — basic CRUD from JpaRepository
}
