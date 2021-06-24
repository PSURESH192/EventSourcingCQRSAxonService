package com.springboot.project.eventsourcingcqrs.repository;

import com.springboot.project.eventsourcingcqrs.entity.AccountQueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountQueryEntity, String> {
}
