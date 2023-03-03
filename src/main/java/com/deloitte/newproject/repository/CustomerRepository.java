package com.deloitte.newproject.repository;

import com.deloitte.newproject.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is a repository interface wwhich is typically used to interact with a database.
 * It is an abstraction of the Data Access Object (DAO) pattern and is responsible for handling CRUD (Create, Read, Update, Delete) operations on the data stored in the database.
 * This extends one of the built-in repositories or implement your own methods i.e., Spring Data JPA to define custom queries.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}