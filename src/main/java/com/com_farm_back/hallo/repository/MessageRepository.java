package com.com_farm_back.hallo.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.com_farm_back.hallo.model.messages.Message;

public interface MessageRepository extends MongoRepository<Message, String> {
    // Ajoutez des méthodes personnalisées si nécessaire
}
