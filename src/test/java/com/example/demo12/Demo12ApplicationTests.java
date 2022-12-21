package com.example.demo12;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.Digester;
import com.example.demo12.domain.Person;
import com.spring4all.mongodb.EnableMongoPlus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.Resource;
import java.util.List;
@EnableMongoPlus
@SpringBootTest
class Demo12ApplicationTests {

    @Test
    void contextLoads() {
        String content = "admin";
//        final SM2 sm2 = SmUtil.sm2();
//        String sign = sm2.signHex(HexUtil.encodeHexStr(content));
//        String sign = "304402205440b7cab449537d82f355b66d3121b2a4e89f647c34a42b5d2d0d2d62f4d70202205a9942de90be476269b963e86a5f8a40921055b436170aedea6009aebca674b5";
//        System.out.println(sign);
// true
//        boolean verify = sm2.verifyHex(HexUtil.encodeHexStr(content), sign);
        Digester digester = DigestUtil.digester("sm3");
        String digestHex = digester.digestHex(content);
        System.out.println(digestHex);
    }

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 多条件查询
     */
    @Test
    public void find() {
        //设置查询条件 age小于30,且person_name="张三"
        Criteria criteria = Criteria.where("age").lt(30)
                .and("person_name").is("张三");

        //设置查询条件
        Query query = new Query(criteria);
        //查询
        List<Person> list = mongoTemplate.find(query, Person.class);

        for (Person person : list) {
            System.out.println(person);
        }
    }

    @Test
    public void createMg(){
        Person student=new Person();
        student.setAge(1);
        student.setName("张三");
        mongoTemplate.save(student);
        System.out.println("查询MG的数据："+student);
    }


    @Test
    public void insertsMg(){
        Person person = new Person();
        person.setName("asda");
        person.setAge(123);
        mongoTemplate.save(person);
    }

    @Test
    public void selectInfo(){
        Criteria criteria = Criteria.where("name").is("asda").andOperator(Criteria.where("age").ne(1));
        Query query = new Query(criteria);
//        List<T> ts = mongoTemplate.find(criteria, Person.class);
        List<Person> people = mongoTemplate.find(query, Person.class);
        people.stream().forEach(person -> {
            System.out.println(person.toString());
        });
    }


}
