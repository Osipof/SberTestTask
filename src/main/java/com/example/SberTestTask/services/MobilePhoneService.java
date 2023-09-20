package com.example.SberTestTask.services;

import com.example.SberTestTask.dto.MobilePhoneDTO;
import com.example.SberTestTask.models.MobilePhone;
import com.example.SberTestTask.repositories.MobilePhoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MobilePhoneService {
    private final MobilePhoneRepository mobilePhoneRepository;

    public MobilePhone save(MobilePhoneDTO dto) {
        return mobilePhoneRepository.save(MobilePhone.builder()
                .brand(dto.getBrand())
                .model(dto.getModel())
                .price(dto.getPrice())
                .color(dto.getColor())
                .amount(dto.getAmount())
                .build());
    }

    public MobilePhone save(MobilePhone mobilePhone) {
        return mobilePhoneRepository.save(mobilePhone);
    }

    public List<MobilePhone> findAll() {
        return mobilePhoneRepository.findAll();
    }

    public MobilePhone findById(Long id) {
        return mobilePhoneRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        mobilePhoneRepository.deleteById(id);
    }
}
