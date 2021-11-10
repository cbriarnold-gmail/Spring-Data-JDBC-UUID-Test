package com.example.UUIDTest;

import java.util.UUID;
import org.springframework.data.relational.core.conversion.MutableAggregateChange;
import org.springframework.data.relational.core.mapping.event.BeforeSaveCallback;
import org.springframework.stereotype.Component;

@Component
public class DomainObjectBeforeSaveCallback implements BeforeSaveCallback<DomainObject> {

  @Override
  public DomainObject onBeforeSave(DomainObject aggregate, MutableAggregateChange<DomainObject> aggregateChange) {
    aggregate.setId(UUID.randomUUID());
    return aggregate;
  }
}