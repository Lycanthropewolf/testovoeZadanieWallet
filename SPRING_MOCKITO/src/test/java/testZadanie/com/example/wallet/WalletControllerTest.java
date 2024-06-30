package testZadanie.com.example.wallet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class WalletControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetBalance() throws Exception {
        UUID walletId = UUID.randomUUID();

        // Create wallet with initial balance before testing GET balance (For integration tests)

        mockMvc.perform(get("/api/v1/wallet/" + walletId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"balance\":0}"));
    }

    @Test
    public void testDeposit() throws Exception {
        String json = "{ \"walletId\": \"UUID\", \"operationType\":\"DEPOSIT\", \"amount\":1000}";

        mockMvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void testWithdraw() throws Exception {
        String json = "{ \"walletId\": \"UUID\", \"operationType\":\"WITHDRAW\", \"amount\":1000}";

        mockMvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }
}