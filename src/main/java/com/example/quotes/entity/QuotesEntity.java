package com.example.quotes.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.reflect.Array;
import java.util.List;

@Document(
        value = "quotes"
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class QuotesEntity {
    @Id
    private ObjectId id;
    private String author;
    private List<String> category;
    private String quote;
}
