package com.jacob;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.UUID;

@ApplicationScope
public interface CustomerRepository extends JpaRepository<Customer, UUID> {}
