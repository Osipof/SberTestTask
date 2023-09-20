package com.example.SberTestTask.controllers;

import com.example.SberTestTask.dto.MobilePhoneDTO;
import com.example.SberTestTask.models.MobilePhone;
import com.example.SberTestTask.services.MobilePhoneService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phones")
@AllArgsConstructor
public class MobilePhoneController {
    private final MobilePhoneService mobilePhoneService;

    @GetMapping()
    public ResponseEntity<List<MobilePhone>> getAll() {
        return mappingResponseListMobilePhone(mobilePhoneService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MobilePhone> getById(@PathVariable Long id) {
        return mappingResponseMobilePhone(mobilePhoneService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<MobilePhone> save(@RequestBody MobilePhoneDTO dto) {
        return mappingResponseMobilePhone(mobilePhoneService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MobilePhone> update(@PathVariable Long id,
                                              @RequestBody MobilePhoneDTO updatedMobilePhone) {
        MobilePhone existingMobilePhone = mobilePhoneService.findById(id);
        if (existingMobilePhone == null) {
            return ResponseEntity.notFound().build();
        }
        existingMobilePhone.setBrand(updatedMobilePhone.getBrand());
        existingMobilePhone.setModel(updatedMobilePhone.getModel());
        existingMobilePhone.setPrice(updatedMobilePhone.getPrice());
        existingMobilePhone.setColor(updatedMobilePhone.getColor());
        existingMobilePhone.setAmount(updatedMobilePhone.getAmount());

        mobilePhoneService.save(existingMobilePhone);
        return ResponseEntity.ok(existingMobilePhone);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        mobilePhoneService.delete(id);
        return HttpStatus.OK;
    }

    private ResponseEntity<MobilePhone> mappingResponseMobilePhone(MobilePhone mobilePhone) {
        return new ResponseEntity<>(mobilePhone, HttpStatus.OK);
    }

    private ResponseEntity<List<MobilePhone>> mappingResponseListMobilePhone(List<MobilePhone> mobilePhones) {
        return new ResponseEntity<>(mobilePhones, HttpStatus.OK);
    }
}
