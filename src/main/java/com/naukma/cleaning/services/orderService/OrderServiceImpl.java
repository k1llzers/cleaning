package com.naukma.cleaning.services.orderService;

import com.naukma.cleaning.models.order.Order;
import com.naukma.cleaning.models.order.Status;
import com.naukma.cleaning.services.pricingService.PricingService;
import com.naukma.cleaning.services.proposalService.CommercialProposalService;
import com.naukma.cleaning.services.commentService.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private PricingService pricingService;
    private CommentService commentService;
    private CommercialProposalService commercialProposalService;

    @Autowired
    public OrderServiceImpl(PricingService pricingService, CommentService commentService, CommercialProposalService commercialProposalService) {
        this.pricingService = pricingService;
        this.commentService = commentService;
        this.commercialProposalService = commercialProposalService;
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
