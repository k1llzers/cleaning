package com.naukma.cleaning.services.addressService;

import com.naukma.cleaning.dao.AddressDao;
import com.naukma.cleaning.dao.UserDao;
import com.naukma.cleaning.dao.entities.AddressEntity;
import com.naukma.cleaning.dao.entities.UserEntity;
import com.naukma.cleaning.models.order.AddressDto;
import com.naukma.cleaning.models.user.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressDao addressDao;
    private ModelMapper modelMapper;

    public AddressServiceImpl(AddressDao addressDao, ModelMapper modelMapper) {
        this.addressDao = addressDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createAddress(UserDto userDto, AddressDto addressDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        AddressEntity addressEntity = modelMapper.map(addressDto, AddressEntity.class);
        addressEntity.setUserEntity(userEntity);
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
        AddressEntity addressEntity = addressDao.getReferenceById(id);
        return modelMapper.map(addressEntity, AddressDto.class);
    }

    @Override
    public List<AddressDto> getUserAddresses(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        List<AddressEntity> addresses = addressDao.findAddressEntitiesByUserEntity(userEntity);
        return addresses.stream().map(x -> modelMapper.map(x, AddressDto.class)).toList();
    }
}
