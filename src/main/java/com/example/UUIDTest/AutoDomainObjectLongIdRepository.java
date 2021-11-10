package com.example.UUIDTest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoDomainObjectLongIdRepository extends PagingAndSortingRepository<AutoDomainObjectLongId, Long> {

}
