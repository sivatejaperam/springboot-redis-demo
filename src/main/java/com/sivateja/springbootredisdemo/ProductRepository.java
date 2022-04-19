package com.sivateja.springbootredisdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    @Autowired
    private RedisTemplate redisTemplate;
    private static final String HASH_KEY = "product";

    public Product save(Product product){
        redisTemplate.opsForHash().put(HASH_KEY, product.getId(),product);
        return product;
    }

    public List<Product> findAll(){
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    public Product getById(int id){
        System.out.println("getting data from db");
        return (Product) redisTemplate.opsForHash().get(HASH_KEY, id);
    }

    public String deleteById(int id){
        redisTemplate.opsForHash().delete(HASH_KEY, id);
        return "Product Removed!!";
    }

    public Product updateProduct(int id, Product product) {
        redisTemplate.opsForHash().put(HASH_KEY, product.getId(), product);
        return product;
    }
}
