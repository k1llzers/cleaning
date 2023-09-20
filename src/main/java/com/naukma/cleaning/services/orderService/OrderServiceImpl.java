package com.naukma.cleaning.services.orderService;

import com.naukma.cleaning.models.order.Order;
import com.naukma.cleaning.models.order.Status;
import com.naukma.cleaning.services.pricingService.PricingService;
import com.naukma.cleaning.services.proposalService.ProposalService;
import com.naukma.cleaning.services.commentService.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private PricingService pricingService;
    private CommentService commentService;
    private ProposalService proposalService;

    @Autowired
    public OrderServiceImpl(PricingService pricingService, CommentService commentService, ProposalService proposalService) {
        this.pricingService = pricingService;
        this.commentService = commentService;
        this.proposalService = proposalService;
    }


    @Override
    public void createOrder(Order order) {

    }

    @Override
    public void editOrder(Order order) {

    }

    @Override
    public void changeStatus(Order order, Status status) {

    }

    @Override
    public Order getOrder(long id) {
        return null;
    }
}
