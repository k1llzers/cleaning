package com.naukma.cleaning.services.orderService;

import com.naukma.cleaning.dao.OrderDao;
import com.naukma.cleaning.dao.entities.OrderEntity;
import com.naukma.cleaning.models.order.OrderDto;
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
    public OrderServiceImpl(PricingService pricingService, CommentService commentService, CommercialProposalService commercialProposalService, ModelMapper modelMapper) {
        this.pricingService = pricingService;
        this.commentService = commentService;
        this.commercialProposalService = commercialProposalService;
        this.modelMapper = modelMapper;
    }


    @Override
    public void createOrder(OrderDto orderDto) {
        orderDto.setPrice(pricingService.calculate(orderDto));
        orderDao.save(modelMapper.map(orderDto, OrderEntity.class));
    }

    @Override
    public void editOrder(OrderDto orderDto) {
        orderDto.setPrice(pricingService.calculate(orderDto));
        orderDao.save(modelMapper.map(orderDto, OrderEntity.class));
    }

    @Override
    public void changeStatus(OrderDto orderDto, Status status) {
        orderDto.setOrderStatus(status);
        orderDao.save(modelMapper.map(orderDto, OrderEntity.class));
    }

    @Override
    public OrderDto getOrder(long id) {
        OrderEntity orderById = orderDao.getReferenceById(id);
        return modelMapper.map(orderById, OrderDto.class);
    }
}
