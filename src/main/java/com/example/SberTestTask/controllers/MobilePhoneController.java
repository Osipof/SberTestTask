package com.example.SberTestTask.controllers;

import com.example.SberTestTask.dto.MobilePhoneDTO;
import com.example.SberTestTask.models.MobilePhone;
import com.example.SberTestTask.services.MobilePhoneService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер мобильного телефона.
 *
 * @author Иван Осипов
 */
@RestController
@RequestMapping("/phones")
@AllArgsConstructor
public class MobilePhoneController {
    private final MobilePhoneService mobilePhoneService;

    /**
     * Метод для получения всех мобильных телефонов
     *
     * @return список мобильных телефонов
     */
    @GetMapping()
    public ResponseEntity<List<MobilePhone>> getAll() {
        return mappingResponseListMobilePhone(mobilePhoneService.findAll());
    }

    /**
     * Метод для получения мобильного телефона по ID
     *
     * @param id ID мобильного телефона
     * @return мобильный телефон по ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<MobilePhone> getById(@PathVariable Long id) {
        return mappingResponseMobilePhone(mobilePhoneService.findById(id));
    }

    /**
     * Метод для сохранения мобильного телефона в Базу Данных
     *
     * @param mobilePhoneDTO данные мобильного телефона
     * @return сохраненный мобильный телефон
     */
    @PostMapping()
    public ResponseEntity<MobilePhone> save(@RequestBody MobilePhoneDTO mobilePhoneDTO) {
        return mappingResponseMobilePhone(mobilePhoneService.save(mobilePhoneDTO));
    }

    /**
     * Метод для обновления мобильного телефона в Базу Данных
     *
     * @param id                 ID мобильного телефона
     * @param updatedMobilePhone данные обновленного мобильного телефона
     * @return обновленный мобильный телефон
     */
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

        mobilePhoneService.update(existingMobilePhone);
        return ResponseEntity.ok(existingMobilePhone);
    }

    /**
     * Метод для удаления мобильного телефона из Базы Данных
     *
     * @param id ID мобильного телефона
     * @return статус успешного удаления
     */
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        mobilePhoneService.delete(id);
        return HttpStatus.OK;
    }

    /**
     * Упаковка объекта MobilePhone в HTTP-ответ
     * @param mobilePhone Мобильный телефон
     * @return ResponseEntity для отправки ответа клиенту
     */
    private ResponseEntity<MobilePhone> mappingResponseMobilePhone(MobilePhone mobilePhone) {
        return new ResponseEntity<>(mobilePhone, HttpStatus.OK);
    }

    /**
     * Упаковка списка объектов MobilePhone в HTTP-ответ
     * @param  mobilePhones Список мобильных телефонов
     * @return ResponseEntity для отправки ответа клиенту
     */
    private ResponseEntity<List<MobilePhone>> mappingResponseListMobilePhone(List<MobilePhone> mobilePhones) {
        return new ResponseEntity<>(mobilePhones, HttpStatus.OK);
    }
}
