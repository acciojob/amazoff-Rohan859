package com.driver.test;

import com.driver.Application;
import com.driver.DeliveryPartner;
import com.driver.Order;
import com.driver.OrderController;
import com.driver.Repository.OrderPartnerRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;

import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.driver.Service.DeliveryPartnerService;
import com.driver.Service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
@SpringBootTest(classes = Application.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCases {

    @Mock
    private OrderService orderService;

    @Mock
    private DeliveryPartnerService deliveryPartnerService;

    @InjectMocks
    private OrderPartnerRepository orderPartnerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getLastDeliveryTimeByPartnerId() {
        // Arrange
        String partnerId = "DP1";
        List<String> orderIds = Arrays.asList("O1", "O2");
        when(orderService.getOrderById("O1")).thenReturn(new Order("O1", "10:00"));
        when(orderService.getOrderById("O2")).thenReturn(new Order("O2", "15:00"));
        orderPartnerRepository.getDeliveryBoyListOfOrders().put(partnerId, orderIds);

        // Act
        int result = orderPartnerRepository.getLastDeliveryTimeByPartnerId(partnerId);

        // Assert
        assertEquals(900, result);
    }

    @Test
    void getOrderIdToPartnerIdMap() {
        // Act
        var result = orderPartnerRepository.getOrderIdToPartnerIdMap();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void getDeliveryBoyListOfOrders() {
        // Act
        var result = orderPartnerRepository.getDeliveryBoyListOfOrders();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void addOrderPartnerPair() {
        // Arrange
        String orderId = "O1";
        String partnerId = "DP1";

        // Act
        orderPartnerRepository.addOrderPartnerPair(orderId, partnerId);

        // Assert
        assertTrue(orderPartnerRepository.getDeliveryBoyListOfOrders().containsKey(partnerId));
        assertTrue(orderPartnerRepository.getDeliveryBoyListOfOrders().get(partnerId).contains(orderId));
        assertEquals(partnerId, orderPartnerRepository.getOrderIdToPartnerIdMap().get(orderId));
    }

    @Test
    void getOrderCountByPartnerId() {
        // Arrange
        String partnerId = "DP1";

        // Act
        int result = orderPartnerRepository.getOrderCountByPartnerId(partnerId);

        // Assert
        assertEquals(0, result);
        assertTrue(orderPartnerRepository.getDeliveryBoyListOfOrders().containsKey(partnerId));
    }

    @Test
    void getOrdersByPartnerId() {
        // Arrange
        String partnerId = "DP1";

        // Act
        List<String> result = orderPartnerRepository.getOrdersByPartnerId(partnerId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        assertTrue(orderPartnerRepository.getDeliveryBoyListOfOrders().containsKey(partnerId));
    }
}