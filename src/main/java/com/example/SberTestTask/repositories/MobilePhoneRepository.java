package com.example.SberTestTask.repositories;

import com.example.SberTestTask.models.MobilePhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с MobilePhone
 *
 * @author Иван Осипов
 */
@Repository
public interface MobilePhoneRepository extends JpaRepository<MobilePhone, Long> {
}
