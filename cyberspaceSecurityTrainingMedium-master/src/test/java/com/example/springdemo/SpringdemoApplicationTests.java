package com.example.springdemo;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
class SpringdemoApplicationTests {
    @Resource
    private MockMvc mockMvc;

    @Test
    public void testAddUser() throws Exception {
        String userJson = "{\"userName\":\"jk\",\"passwd\":\"11113\",\"userSex\":1,\"delTag\":0,\"roleId\":2}";
        mockMvc.perform(MockMvcRequestBuilders.post("/UserController/addUser")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
        // 您可以添加更多的断言来验证响应内容
    }
}
