package com.example.SberTestTask.services;

import com.example.SberTestTask.dto.MobilePhoneDTO;
import com.example.SberTestTask.models.MobilePhone;
import com.example.SberTestTask.repositories.MobilePhoneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для работы с MobilePhone с реализованными CRUD операциями
 *
 * @author Иван Осипов
 */
@Slf4j
@Service
@AllArgsConstructor
public class MobilePhoneService {
    private final MobilePhoneRepository mobilePhoneRepository;

    public MobilePhone save(MobilePhoneDTO dto) {
        var saved = mobilePhoneRepository.save(MobilePhone.builder()
                .brand(dto.getBrand())
                .model(dto.getModel())
                .price(dto.getPrice())
                .color(dto.getColor())
                .amount(dto.getAmount())
                .build());
        log.info("MobilePhone Saved " + saved);
        return saved;
    }

    public void update(MobilePhone mobilePhone) {
        var saved = mobilePhoneRepository.save(mobilePhone);
        log.info("MobilePhone updated " + saved);
    }

    public List<MobilePhone> findAll() {
        log.info("found all MobilePhones");
        return mobilePhoneRepository.findAll();
    }

    public MobilePhone findById(Long id) {
        var found = mobilePhoneRepository.findById(id).orElse(null);
        String message = found == null ? " wasn't found" : " found";
        log.info("MobilePhone with id = " + id + message);
        return found;
    }

    public void delete(Long id) {
        mobilePhoneRepository.deleteById(id);
        log.info("MobilePhone with id = " + id + " deleted");
    }
}
