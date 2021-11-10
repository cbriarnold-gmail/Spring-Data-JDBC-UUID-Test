package com.example.UUIDTest;

import java.util.UUID;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainObjectRepository extends PagingAndSortingRepository<DomainObject, UUID> {

}
