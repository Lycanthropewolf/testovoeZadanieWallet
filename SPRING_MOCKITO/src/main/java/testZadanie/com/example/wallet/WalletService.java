package testZadanie.com.example.wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.UUID;


@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;



    @Transactional
    public Wallet performOperation(UUID walletId, String operationType, BigDecimal amount) {
        Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new NoSuchElementException("Кошелек не найден"));
        if ("Депозит".equalsIgnoreCase(operationType)) {
            wallet.setBalance(wallet.getBalance().add(amount));
        } else if ("Отзывать".equalsIgnoreCase(operationType)) {
            if (wallet.getBalance().compareTo(amount) < 0) {
                throw new IllegalArgumentException("Недостаточно средств");
            }
            wallet.setBalance(wallet.getBalance().subtract(amount));
        } else {
            throw new IllegalArgumentException("Неизвестный тип операции");
        }
        return walletRepository.save(wallet);
    }

    public BigDecimal getBalance(UUID walletId) {
        Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new NoSuchElementException("Кошелек не найден"));
        return wallet.getBalance();
    }
}