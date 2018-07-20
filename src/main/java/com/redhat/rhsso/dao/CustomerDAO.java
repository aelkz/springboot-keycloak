package com.redhat.rhsso.dao;

import com.redhat.rhsso.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDAO extends CrudRepository<Customer, Long> {
}
