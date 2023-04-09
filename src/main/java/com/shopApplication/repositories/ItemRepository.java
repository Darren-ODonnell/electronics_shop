package com.shopApplication.repositories;

import com.shopApplication.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    Optional<ArrayList<Item>> findByCategory(String category);
    Optional<ArrayList<Item>> findByManufacturer(String manufacturer);
    Optional<Item> findByTitle(String title);
    Boolean existsByTitle(String title);


    @Query("SELECT i FROM Item i WHERE i.category LIKE %:category%")
    Optional<ArrayList<Item>> searchByCategory(String category);
    @Query("SELECT i FROM Item i WHERE i.title LIKE %:title%")
    Optional<ArrayList<Item>> searchByTitle(String title);
    @Query("SELECT i FROM Item i WHERE i.manufacturer LIKE %:manufacturer%")
    Optional<ArrayList<Item>> searchByManufacturer(String manufacturer);
//    @Query("SELECT i FROM Item i where CONVERT(i.price, char) LIKE %:price%")
//    Optional<ArrayList<Item>> searchByPrice(String price);

    @Modifying
    @Query("UPDATE Item i SET i.stock = :stock WHERE i.id = :itemId")
    void updateStockById(@Param("itemId") int itemId, @Param("stock") int stock);
}
