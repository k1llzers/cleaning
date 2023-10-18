package com.naukma.cleaning.services.addressService;

import com.naukma.cleaning.dao.AddressDao;
import com.naukma.cleaning.dao.entities.AddressEntity;
import com.naukma.cleaning.dao.entities.UserEntity;
import com.naukma.cleaning.models.order.Address;
import com.naukma.cleaning.models.user.User;
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
    public void createAddress(User user, Address address) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        AddressEntity addressEntity = modelMapper.map(address, AddressEntity.class);
        addressEntity.setUserEntity(userEntity);
        addressDao.save(addressEntity);
    }

    @Override
    public void editAddress(Address address) {
        AddressEntity addressEntity = modelMapper.map(address, AddressEntity.class);
        addressDao.save(addressEntity);
    }

    @Override
    public void deleteAddress(long id) {
        addressDao.deleteById(id);
    }

    @Override
    public Address getAddress(long id) {
        AddressEntity addressEntity = addressDao.findById(id).get();
        return modelMapper.map(addressEntity, Address.class);
    }

    @Override
    public List<Address> getUserAddresses(User user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        List<AddressEntity> addresses = addressDao.findAddressEntitiesByUserEntity(userEntity);
        return addresses.stream().map(x -> modelMapper.map(x, Address.class)).toList();
    }
}
