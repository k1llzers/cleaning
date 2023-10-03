package com.naukma.cleaning.services.discountService;


import com.naukma.cleaning.dao.DiscountDao;
import com.naukma.cleaning.dao.entities.DiscountEntity;
import com.naukma.cleaning.models.order.DiscountDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DiscountServiceImpl implements DiscountService {
    private DiscountDao discountDao;
    private ModelMapper modelMapper;

    public DiscountServiceImpl(DiscountDao discountDao, ModelMapper modelMapper) {
        this.discountDao = discountDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createDiscount(DiscountDto discount) {
        DiscountEntity discountEntity = modelMapper.map(discount, DiscountEntity.class);
        discountDao.save(discountEntity);
    }

    @Override
    public void editDiscount(DiscountDto discount) {
        DiscountEntity discountEntity = modelMapper.map(discount, DiscountEntity.class);
        discountDao.save(discountEntity);
    }

    @Override
    public void deleteDiscount(long id) {
        discountDao.deleteById(id);
    }

    @Override
    public DiscountDto getDiscount(long id) {
        return modelMapper.map(discountDao.getReferenceById(id),DiscountDto.class);
    }

    @Override
    public DiscountDto getCurrentDiscount() {
        DiscountEntity currentDiscount = discountDao.getByStartLessThanEqualAndFinishGreaterThanEqual(LocalDateTime.now(),LocalDateTime.now());
        return modelMapper.map(currentDiscount, DiscountDto.class);
    }
}
