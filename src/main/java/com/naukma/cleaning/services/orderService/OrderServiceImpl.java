package com.naukma.cleaning.services.orderService;

import com.naukma.cleaning.dao.OrderDao;
import com.naukma.cleaning.dao.entities.OrderEntity;
import com.naukma.cleaning.models.dtos.OrderDto;
import com.naukma.cleaning.models.dtos.UserDto;
import com.naukma.cleaning.models.order.Order;
import com.naukma.cleaning.models.order.Status;
import com.naukma.cleaning.services.pricingService.PricingService;
import com.naukma.cleaning.services.proposalService.CommercialProposalService;
import com.naukma.cleaning.services.commentService.CommentService;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private PricingService pricingService;
    private CommentService commentService;
    private CommercialProposalService commercialProposalService;
    private OrderDao orderDao;
    private ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(PricingService pricingService, CommentService commentService, CommercialProposalService commercialProposalService, ModelMapper modelMapper, OrderDao orderDao) {
        this.pricingService = pricingService;
        this.commentService = commentService;
        this.commercialProposalService = commercialProposalService;
        this.modelMapper = modelMapper;
        this.orderDao = orderDao;
    }


    @Override
    public void createOrder(Order order) {
        order.setPrice(pricingService.calculate(order));
        orderDao.save(modelMapper.map(order, OrderEntity.class));
    }

    @Override
    public void editOrder(Order order) {
        if (order.getOrderStatus() != orderDao.findById(order.getId()).get().getOrderStatus()) {
            if(order.getOrderStatus() == Status.CANCELLED){
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                long userId = ((UserDto) authentication.getPrincipal()).getId();
                if(order.getClient().getId() != userId)
                {
                    throw new AccessDeniedException("Access denied for other users");
                }
                orderDao.save(modelMapper.map(order, OrderEntity.class));
                return;
            }
            else{
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                List<String> authorities = authentication.getAuthorities().stream().map(e -> e + "").toList();
                if (!authorities.contains("ROLE_Admin") && !authorities.contains("ROLE_Employee")){
                    throw new AccessDeniedException("Access denied for users");
                }
                /* 
                if(authorities.contains("ROLE_Employee")){
                    var execs = order.getExecutors();
                    long userId = ((UserDto) authentication.getPrincipal()).getId();
                    if(execs.stream().noneMatch(e -> e.getId() == userId)){
                        throw new AccessDeniedException("Access denied for other employees");
                    }
                }
                */
                
                orderDao.save(modelMapper.map(order, OrderEntity.class));
                return;
            }
        }

        order.setPrice(pricingService.calculate(order));
        orderDao.save(modelMapper.map(order, OrderEntity.class));
    }

    @Override
    public void changeStatus(Order order, Status status) {
        order.setOrderStatus(status);
        editOrder(order);
    }

    @Override
    public Order getOrder(long id) {
        OrderEntity orderById = orderDao.findById(id).get();
        return modelMapper.map(orderById, Order.class);
    }

    @Override
    public void createOrder(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        createOrder(order);
    }

    @Override
    public void editOrder(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        editOrder(order);
    }

    @Override
    public void changeStatus(OrderDto orderDto, Status status) {
        Order order = modelMapper.map(orderDto, Order.class);
        changeStatus(order, status);
    }

    @Override
    public OrderDto getOrderDto(long id) {
        return modelMapper.map(getOrder(id), OrderDto.class);
    }
}
