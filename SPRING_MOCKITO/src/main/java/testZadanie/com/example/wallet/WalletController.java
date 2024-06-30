package testZadanie.com.example.wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping
    public ResponseEntity<?> handleOperation(@RequestBody WalletOperationRequest request) {
        try {
            Wallet wallet = walletService.performOperation(request.getWalletId(), request.getOperationType(), request.getAmount());
            return ResponseEntity.ok(wallet);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<?> getBalance(@PathVariable UUID walletId) {
        try {
            BigDecimal balance = walletService.getBalance(walletId);
            return ResponseEntity.ok(balance);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

