package com.naukma.cleaning.services.addressService;

import com.naukma.cleaning.dao.AddressDao;
import com.naukma.cleaning.dao.entities.AddressEntity;
import com.naukma.cleaning.models.order.AddressDto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService{
    private AddressDao addressDao;
    private ModelMapper modelMapper;

    public AddressServiceImpl(AddressDao addressDao, ModelMapper modelMapper) {
        this.addressDao = addressDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createAddress(AddressDto addressDto) {
        AddressEntity addressEntity = modelMapper.map(addressDto, AddressEntity.class);
        addressDao.save(addressEntity);
    }

    @Override
    public void editAddress(AddressDto addressDto) {
        AddressEntity addressEntity = modelMapper.map(addressDto, AddressEntity.class);
        addressDao.save(addressEntity);
    }

    @Override
    public void deleteAddress(long id) {
        addressDao.deleteById(id);
    }

    @Override
    public AddressDto getAddress(long id) {
        AddressEntity addressById = addressDao.getReferenceById(id);
        return modelMapper.map(addressById,AddressDto.class);
    }
}
