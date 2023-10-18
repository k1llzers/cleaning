package com.naukma.cleaning.services.orderService;

import com.naukma.cleaning.dao.OrderDao;
import com.naukma.cleaning.dao.entities.OrderEntity;
import com.naukma.cleaning.models.dtos.OrderDto;
import com.naukma.cleaning.models.order.Order;
import com.naukma.cleaning.models.order.Status;
import com.naukma.cleaning.services.pricingService.PricingService;
import com.naukma.cleaning.services.proposalService.CommercialProposalService;
import com.naukma.cleaning.services.commentService.CommentService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
        order.setPrice(pricingService.calculate(order));
        orderDao.save(modelMapper.map(order, OrderEntity.class));
    }

    @Override
    public void changeStatus(Order order, Status status) {
        order.setOrderStatus(status);
        orderDao.save(modelMapper.map(order, OrderEntity.class));
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
