package com.cg.service.deposit;

import com.cg.model.Deposit;
import com.cg.repository.IDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DepositServiceImpl implements IDepositService {

    @Autowired
    private IDepositRepository depositRepository;

    @Override
    public List<Deposit> findAll() {
        return null;
    }

    @Override
    public Optional<Deposit> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Deposit save(Deposit deposit) {
        return null;
    }

    @Override
    public void delete(Deposit deposit) {

    }

    @Override
    public void deleteById(Long aLong) {

    }
}
