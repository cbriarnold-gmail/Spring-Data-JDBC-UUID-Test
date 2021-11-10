package com.example.UUIDTest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainObjectLongIdRepository extends PagingAndSortingRepository<DomainObjectLongId, Long> {

}
