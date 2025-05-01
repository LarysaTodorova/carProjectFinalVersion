package com.example.cardataproject.repository;

import com.example.cardataproject.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProducerRepository extends JpaRepository<Producer, Integer> {

    Optional<Producer> findByName(String name);

    Optional<Producer> findById(Integer id);

   default Optional<Producer> deleteProducerById(Integer producerId) {
        Optional<Producer> optionalProducer = findById(producerId);

        if (optionalProducer.isPresent()) {
            delete(optionalProducer.get());
        }
        return optionalProducer;
    }

   default Optional<Producer> updateProducer(Integer id, String newEmail, String newPhoneNumber) {
        Optional<Producer> producerForUpdateOptional = findById(id);

        if (producerForUpdateOptional.isPresent()) {
            Producer producerForUpdate = producerForUpdateOptional.get();
            producerForUpdate.setPhoneNumber(newPhoneNumber);
            producerForUpdate.setEmail(newEmail);
            return Optional.of(save(producerForUpdate));
        }
        return Optional.empty();
    }


}
