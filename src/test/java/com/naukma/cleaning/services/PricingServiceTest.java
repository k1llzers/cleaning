package com.naukma.cleaning.services;

import com.naukma.cleaning.models.order.CommercialProposal;
import com.naukma.cleaning.models.order.Order;
import com.naukma.cleaningstarter.Discount;
import com.naukma.cleaningstarter.DiscountService;
import com.naukma.cleaning.services.pricingService.PricingService;
import com.naukma.cleaning.services.pricingService.PricingServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(SpringExtension.class)
public class PricingServiceTest {

    @Autowired
    private DiscountService discountService;

    @Autowired
    private PricingService pricingService;

    @Value("${fee:20}")
    private int fee;

    @Test
    public void calculateTestOne() {
        discountService.createDiscount(null);
        Set<CommercialProposal> proposals = createProposals();
        Order order = createOrder(proposals);
        Assertions.assertEquals(pricingService.calculate(order), 0);
    }

    @Test
    public void calculateTestTwo() {
        discountService.createDiscount(new Discount("test", 0.5));
        Set<CommercialProposal> proposals = createProposals();
        Order order = createOrder(proposals);
        Assertions.assertEquals(pricingService.calculate(order), 0);
    }

    @Test
    public void calculateTestThree() {
        discountService.createDiscount(null);
        Set<CommercialProposal> proposals = createProposals(100, 100, 100);
        Order order = createOrder(proposals);
        Assertions.assertEquals(pricingService.calculate(order), 300 * (1 + fee / 100.0));
    }

    @Test
    public void calculateTestFour() {
        discountService.createDiscount(new Discount("test", 0.5));
        Set<CommercialProposal> proposals = createProposals(100, 100, 100);
        Order order = createOrder(proposals);
        Assertions.assertEquals(pricingService.calculate(order), 300 * (1 - 0.5) * (1 + fee / 100.0));
    }

    @Test
    public void calculateTestFive() {
        discountService.createDiscount(new Discount("test", 0));
        Set<CommercialProposal> proposals = createProposals(100, 100, 100);
        Order order = createOrder(proposals);
        Assertions.assertEquals(pricingService.calculate(order), 300 * (1 + fee / 100.0));
    }

    @Test
    public void calculateTestSix() {
        discountService.createDiscount(new Discount("test", 1));
        Set<CommercialProposal> proposals = createProposals(100, 100, 100);
        Order order = createOrder(proposals);
        Assertions.assertEquals(pricingService.calculate(order), 300 * 0 * (1 + fee / 100.0));
    }

    private Set<CommercialProposal> createProposals(double... prices) {
        Set<CommercialProposal> proposals = new HashSet<>();
        for (double price : prices) {
            CommercialProposal proposal = new CommercialProposal();
            proposal.setPrice(price);
            proposals.add(proposal);
        }
        return proposals;
    }

    private Order createOrder(Set<CommercialProposal> proposals) {
        Order order = new Order();
        order.setCommercialProposals(proposals);
        return order;
    }

    @TestConfiguration
    static class PricingServiceTestConfiguration {

        @Bean
        public DiscountService discountService() {
            return new DiscountService() {

                private Discount discount;

                @SneakyThrows
                @Override
                public void createDiscount(Discount discount) {
                    this.discount = discount;
                }

                @Override
                public void editDiscount(Discount discount) {
                    this.discount = discount;
                }

                @Override
                public void deleteDiscount(long id) {
                    this.discount = null;
                }

                @Override
                public Discount getDiscount(long id) {
                    return this.discount;
                }

                @Override
                public Discount getCurrentDiscount() {
                    return this.discount;
                }
            };
        }

        @Bean
        @Autowired
        public PricingService pricingService(DiscountService discountService) {
            return new PricingServiceImpl(discountService);
        }
    }

}
