package com.test.account;

import com.test.account.entity.Customer;
import com.test.account.repository.CustomerRepository;
import com.test.account.service.CustomerService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class AccountApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    void contextLoads() {

    }

    @Test
    void queryCustomerTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/account/1"))
                .andExpect(MockMvcResultMatchers.status().isFound());
    }

    @Test
    void qeuryFindCusterById(){
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setCustomerName("Jose Rizal");
        customer.setCustomerMobile("0906000000");
        customer.setCustomerEmail("jose.rizal.100@gmail.com");
        customer.setAddress1("obando laguna");
        customer.setAddress2("luneta park");

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer foundCustomer = customerService.getCustomerById(1L);

        assertThat(foundCustomer).isNotNull();
        assertThat(foundCustomer.getCustomerName()).isEqualTo("Jose Rizal");
        assertThat(foundCustomer.getCustomerEmail()).isEqualTo("jose.rizal.100@gmail.com");
    }

}
