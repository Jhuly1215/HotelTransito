package com.example.hoteltransito.service;
import com.example.hoteltransito.model.Payment;
import com.example.hoteltransito.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> findById(Long id) {
        return paymentRepository.findById(id);
    }

    @Transactional
    public Payment create(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Transactional
    public Optional<Payment> update(Long id, Payment payment) {
        return paymentRepository.findById(id)
                .map(existing -> {
                    existing.setReservation(payment.getReservation());
                    existing.setAmount(payment.getAmount());
                    existing.setPaymentMethod(payment.getPaymentMethod());
                    existing.setPaymentDate(payment.getPaymentDate());
                    existing.setStatus(payment.getStatus());
                    existing.setTransactionReference(payment.getTransactionReference());
                    existing.setCreatedAt(payment.getAmount().compareTo(payment.getAmount())==0 /* placeholder */ ? existing.getCreatedAt(): existing.getCreatedAt());
                    return paymentRepository.save(existing);
                });
    }


    
    @Transactional
    public boolean delete(Long id) {
        return paymentRepository.findById(id)
                .map(existing -> {
                    paymentRepository.delete(existing);
                    return true;
                }).orElse(false);
    }
}

