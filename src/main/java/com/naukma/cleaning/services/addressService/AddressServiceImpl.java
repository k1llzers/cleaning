package com.naukma.cleaning.services.addressService;

import com.naukma.cleaning.dao.AddressDao;
import com.naukma.cleaning.dao.OrderDao;
import com.naukma.cleaning.dao.entities.AddressEntity;
import com.naukma.cleaning.dao.entities.UserEntity;
import com.naukma.cleaning.models.dtos.AddressDto;
import com.naukma.cleaning.models.dtos.UserDto;
import com.naukma.cleaning.models.order.Address;
import com.naukma.cleaning.models.user.User;
import com.naukma.cleaning.services.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.naukma.cleaning.models.order.Status;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressDao addressDao;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final OrderDao orderDao;

    @Override
    public void createAddress(User user, Address address) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        AddressEntity addressEntity = modelMapper.map(address, AddressEntity.class);
        addressEntity.setUserEntity(userEntity);
        addressDao.save(addressEntity);
    }

    @Override
    public void createAddress(long userId, AddressDto addressDto) {
        User user = userService.getUser(userId);
        Address address = modelMapper.map(addressDto, Address.class);
        createAddress(user, address);
    }

    @Override
    public void editAddress(AddressDto addressDto) {
        Address address = modelMapper.map(addressDto, Address.class);
        editAddress(address);
    }

    @Override
    public void editAddress(Address address) {
        
        var addressEntity = modelMapper.map(address, AddressEntity.class);
        var orders = orderDao.findOrderEntitiesByAddress(addressEntity);
        for (var order : orders) {
            if (!(order.getOrderStatus() == com.naukma.cleaning.models.order.Status.NOT_VERIFIED || order.getOrderStatus() == com.naukma.cleaning.models.order.Status.CANCELLED)) {
                throw new AccessDeniedException("You can`t edit address with id " + address.getId());
            }
        }
        var userEntity = addressDao.findById(address.getId()).get().getUserEntity();
        addressEntity.setUserEntity(userEntity);
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
    public AddressDto getAddressDto(long id) {
        Address address = getAddress(id);
        return modelMapper.map(address, AddressDto.class);
    }

    @Override
    public List<Address> getUserAddresses(User user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        List<AddressEntity> addresses = addressDao.findAddressEntitiesByUserEntity(userEntity);
        return addresses.stream().map(x -> modelMapper.map(x, Address.class)).toList();
    }

    @Override
    public List<AddressDto> getUserAddresses(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        List<Address> addresses = getUserAddresses(user);
        return addresses.stream().map(x -> modelMapper.map(x, AddressDto.class)).toList();
    }

    @Override
    public List<AddressDto> getAddressesByUserId(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> authorities = authentication.getAuthorities().stream().map(e -> e + "").toList();
        UserDto userDto = userService.getUserDto(id);
        if (!authorities.contains("ROLE_Admin") && !userDto.getEmail().equals(authentication.getName())){
            throw new AccessDeniedException("You can`t get addresses by id " + id);
        }
        return getUserAddresses(userDto);
    }

    @Override
    public boolean hasAttachedOrders(long id) {
        Address address = getAddress(id);
        return true;
        //return address.getOrders().stream().anyMatch(x -> x.getOrderStatus() != Status.NOT_VERIFIED && x.getOrderStatus() != Status.CANCELLED);
    }
}
