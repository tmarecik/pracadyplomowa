package pl.edu.wszib.pracadyplomowa.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.pracadyplomowa.model.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {
//public interface ProductDao extends CrudRepository<Product, Long> {

    Product getById(Long id);
    List<Product> findAll();

}
