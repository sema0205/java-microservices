package org.example.banks.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.banks.domain.bank.Bank;
import org.example.banks.domain.bank.IBank;
import org.example.banks.repository.BankRepository;
import org.example.banks.service.CentralBankService;

import java.util.Map;

@RequiredArgsConstructor
@AllArgsConstructor
public class CentralBankServiceImpl implements CentralBankService {

    private BankRepository bankRepository;

    public IBank registerBank(
            IBank bank
    ) {
        return bankRepository.create(bank);
    }


    public void notifyBanks() {
        bankRepository.initCharge();
    }

    public void notifyUsers() {
        var banks = bankRepository.getAll();
        for (Map.Entry<Long, IBank> entry : banks.entrySet()) {
            var bank = entry.getValue();
            bank.notifyUsers();
        }
    }
}
