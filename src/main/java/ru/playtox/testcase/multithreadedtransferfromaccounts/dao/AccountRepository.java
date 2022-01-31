package ru.playtox.testcase.multithreadedtransferfromaccounts.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AccountRepository {

    private final Map<String, Account> repository = new ConcurrentHashMap<>();

    public AccountRepository() {
    }

    public void put(Account account) {
        this.repository.put(account.getId(), account);
    }

    public Account getByID(String id) {
        return repository.get(id);
    }

    public List<Account> getAll() {
        return new ArrayList<>(this.repository.values());
    }
}
