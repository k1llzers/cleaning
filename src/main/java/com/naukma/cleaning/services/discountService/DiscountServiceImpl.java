package com.naukma.cleaning.services.discountService;


import com.naukma.cleaning.dao.DiscountDao;
import com.naukma.cleaning.dao.entities.DiscountEntity;
import com.naukma.cleaning.models.order.Discount;
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
    public void createDiscount(Discount discount) {
        DiscountEntity discountEntity = modelMapper.map(discount, DiscountEntity.class);
        discountDao.save(discountEntity);
    }

    @Override
    public void editDiscount(Discount discount) {
        DiscountEntity discountEntity = modelMapper.map(discount, DiscountEntity.class);
        discountDao.save(discountEntity);
    }

    @Override
    public void deleteDiscount(long id) {
        discountDao.deleteById(id);
    }

    @Override
    public Discount getDiscount(long id) {
        return modelMapper.map(discountDao.findById(id).get(), Discount.class);
    }

    @Override
    public Discount getCurrentDiscount() {
        DiscountEntity currentDiscount = discountDao.getByStartLessThanEqualAndFinishGreaterThanEqual(LocalDateTime.now(),LocalDateTime.now());
        return modelMapper.map(currentDiscount, Discount.class);
    }
}
