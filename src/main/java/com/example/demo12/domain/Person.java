package com.example.demo12.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
//指定实体类和数据库文档的映射关系    默认实体类名  数据库如果没有该文档，会自动创建
@Document(value="tb_person")
public class Person {

    @Id
    private ObjectId id;

    //指定属性名和数据库域的映射关系   默认属性名
    @Field("person_name")
    private String name;
    private int age;
    private String address;
}
