package com.example.quotes.repository;

import com.example.quotes.entity.QuotesEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotesRepo extends MongoRepository<QuotesEntity, ObjectId> {
}
