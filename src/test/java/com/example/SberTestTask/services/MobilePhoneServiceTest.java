package com.example.SberTestTask.services;

import com.example.SberTestTask.dto.MobilePhoneDTO;
import com.example.SberTestTask.models.MobilePhone;
import com.example.SberTestTask.repositories.MobilePhoneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MobilePhoneServiceTest {
    @InjectMocks
    private MobilePhoneService mobilePhoneService;

    @Mock
    private MobilePhoneRepository mobilePhoneRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        MobilePhoneDTO dto = new MobilePhoneDTO();
        dto.setBrand("TestBrand");
        dto.setModel("TestModel");
        dto.setPrice(500);
        dto.setColor("TestColor");
        dto.setAmount(10);

        MobilePhone savedMobilePhone = new MobilePhone(1L, "TestBrand", "TestModel", 500, "TestColor", 10);
        when(mobilePhoneRepository.save(any())).thenReturn(savedMobilePhone);
        MobilePhone result = mobilePhoneService.save(dto);

        assertNotNull(result);
        assertSame(savedMobilePhone, result);
        assertEquals("TestBrand", result.getBrand());
        assertEquals("TestModel", result.getModel());
        assertEquals(500, result.getPrice());
        assertEquals("TestColor", result.getColor());
        assertEquals(10, result.getAmount());

        verify(mobilePhoneRepository, times(1)).save(any());
    }

    @Test
    void testUpdate() {
        MobilePhone mobilePhone = new MobilePhone();

        when(mobilePhoneRepository.save(any())).thenReturn(mobilePhone);

        mobilePhoneService.update(mobilePhone);

        verify(mobilePhoneRepository, times(1)).save(any());
    }

    @Test
    void testFindAll() {
        List<MobilePhone> mobilePhones = new ArrayList<>();
        mobilePhones.add(new MobilePhone());
        mobilePhones.add(new MobilePhone());

        when(mobilePhoneRepository.findAll()).thenReturn(mobilePhones);

        List<MobilePhone> foundMobilePhones = mobilePhoneService.findAll();

        assertEquals(2, foundMobilePhones.size());

        verify(mobilePhoneRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Long id = 1L;
        MobilePhone mobilePhone = new MobilePhone();
        mobilePhone.setId(id);

        when(mobilePhoneRepository.findById(id)).thenReturn(Optional.of(mobilePhone));

        MobilePhone foundMobilePhone = mobilePhoneService.findById(id);

        assertNotNull(foundMobilePhone);
        assertEquals(id, foundMobilePhone.getId());

        verify(mobilePhoneRepository, times(1)).findById(id);
    }

    @Test
    void testFindByIdNotFound() {
        Long id = 1L;

        when(mobilePhoneRepository.findById(id)).thenReturn(Optional.empty());

        MobilePhone foundMobilePhone = mobilePhoneService.findById(id);

        assertNull(foundMobilePhone);

        verify(mobilePhoneRepository, times(1)).findById(id);
    }

    @Test
    void testDelete() {
        Long id = 1L;

        mobilePhoneService.delete(id);

        verify(mobilePhoneRepository, times(1)).deleteById(id);
    }
}

