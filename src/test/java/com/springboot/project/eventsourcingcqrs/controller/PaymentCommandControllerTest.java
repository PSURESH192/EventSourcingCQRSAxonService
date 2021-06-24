package com.springboot.project.eventsourcingcqrs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.project.eventsourcingcqrs.model.AccountCreateDTO;
import com.springboot.project.eventsourcingcqrs.model.MoneyCreditDTO;
import com.springboot.project.eventsourcingcqrs.model.MoneyDebitDTO;
import com.springboot.project.eventsourcingcqrs.service.PaymentCommandService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class PaymentCommandControllerTest {

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    PaymentCommandService paymentCommandService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    public void testCreateAccount() throws Exception {
        AccountCreateDTO accountCreateDTO = new AccountCreateDTO();
        accountCreateDTO.setAccountBalance(25000L);
        accountCreateDTO.setCurrency("INR");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/payments/account")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(accountCreateDTO))).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(201, status);
    }

    @Test
    public void testCreditMoneyToAccount() throws Exception {
        MoneyCreditDTO moneyCreditDTO = new MoneyCreditDTO();
        moneyCreditDTO.setCreditAmount(25000L);
        moneyCreditDTO.setCurrency("INR");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/payments/credits/d842631ab9804bdd88f4fd00024c6ec3")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(moneyCreditDTO))).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void testDebitMoneyToAccount() throws Exception {
        MoneyDebitDTO moneyDebitDTO = new MoneyDebitDTO();
        moneyDebitDTO.setDebitAmount(25000L);
        moneyDebitDTO.setCurrency("INR");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/payments/debits/d842631ab9804bdd88f4fd00024c6ec3")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(moneyDebitDTO))).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

}
