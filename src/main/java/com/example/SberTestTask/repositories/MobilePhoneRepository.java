package com.example.SberTestTask.repositories;

import com.example.SberTestTask.models.MobilePhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobilePhoneRepository extends JpaRepository<MobilePhone, Long> {
}
