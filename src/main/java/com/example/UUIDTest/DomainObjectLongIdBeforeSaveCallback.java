package com.example.UUIDTest;

import java.util.Random;
import org.springframework.data.relational.core.conversion.MutableAggregateChange;
import org.springframework.data.relational.core.mapping.event.BeforeSaveCallback;
import org.springframework.stereotype.Component;

@Component
public class DomainObjectLongIdBeforeSaveCallback implements BeforeSaveCallback<DomainObjectLongId> {

  @Override
  public DomainObjectLongId onBeforeSave(DomainObjectLongId aggregate, MutableAggregateChange<DomainObjectLongId> aggregateChange) {
    aggregate.setId(new Random().nextLong());
    return aggregate;
  }
}