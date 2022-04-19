package com.sivateja.springbootredisdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository repo;

    @PostMapping
    public Product save(@RequestBody Product product){
        return repo.save(product);
    }

    @GetMapping
    public List<Product> findAll(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id", value = "product")
    public Product findById(@PathVariable int id){
        return repo.getById(id);
    }

    @PutMapping("/{id}")
    @CachePut(key = "#id", value = "product")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product){
        return repo.updateProduct(id,product);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id", value = "product")
    public String deleteById(@PathVariable int id){
        return repo.deleteById(id);
    }

}
