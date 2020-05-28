package pl.edu.wszib.pracadyplomowa.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.pracadyplomowa.model.Product;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {

    Product getById(Long id);
    List<Product> findAll();

}
