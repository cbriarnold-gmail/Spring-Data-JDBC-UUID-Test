package com.example.UUIDTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
classes = UuidTestApplication.class)
@AutoConfigureMockMvc
@Sql(scripts = {"/domain_object.sql"})
@AutoConfigureEmbeddedDatabase
class DomainObjectLongIdRepositoryTest {

  @Autowired
  private DomainObjectLongIdRepository doRepo;

  @Autowired
  JdbcTemplate template;
  
  @Autowired
  DataSource dataSource;

  @Autowired
  PlatformTransactionManager txMan;
  
  @Test
  void update_JDBCTemplate() {
    // Arrange
    DomainObjectLongId saveDo = new DomainObjectLongId();
    saveDo.setValue("Save");
    
    DomainObjectLongId saveDoResult = doRepo.save(saveDo);
    
    // Act
    int rowCount = template.update("UPDATE domain_object_long_id SET \"value\" = ? WHERE \"domain_object_long_id\".\"id\" = ?",  "Update", saveDoResult.getId());
    
    // Assert
    assertEquals(1, rowCount);
    
    DomainObjectLongId updateDoResult = doRepo.findById(saveDoResult.getId()).orElseThrow();
    assertEquals(saveDoResult.getId(), updateDoResult.getId());
    assertEquals("Update", updateDoResult.getValue());
  }
  
  @Test
  void update_NamedParameterTemplate() {
    // Arrange
    DomainObjectLongId saveDo = new DomainObjectLongId();
    saveDo.setValue("Save");
    
    DomainObjectLongId saveDoResult = doRepo.save(saveDo);
    
    Map<String, Object> params = new HashMap<>();
    params.put("id", saveDoResult.getId());
    params.put("value", "Update");
    
    MapSqlParameterSource paramSource = new MapSqlParameterSource(params);
    NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
    
    // Act
    int rowCount = template.update("UPDATE domain_object_long_id SET \"value\" = :value WHERE \"domain_object_long_id\".\"id\" = :id", paramSource);
    
    // Assert
    assertEquals(1, rowCount);
    
    DomainObjectLongId updateDoResult = doRepo.findById(saveDoResult.getId()).orElseThrow();
    assertEquals(saveDoResult.getId(), updateDoResult.getId());
    assertEquals("Update", updateDoResult.getValue());
  }
  
  @Test
  void update_ParamMap() {
    // Arrange
    DomainObjectLongId saveDo = new DomainObjectLongId();
    saveDo.setValue("Save");
    
    DomainObjectLongId saveDoResult = doRepo.save(saveDo);
    
    Map<String, Object> params = new HashMap<>();
    params.put("id", saveDoResult.getId());
    params.put("value", "Update");
    
    NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
    
    // Act
    int rowCount = template.update("UPDATE domain_object_long_id SET \"value\" = :value WHERE \"domain_object_long_id\".\"id\" = :id", params);
    
    // Assert
    assertEquals(1, rowCount);
    
    DomainObjectLongId updateDoResult = doRepo.findById(saveDoResult.getId()).orElseThrow();
    assertEquals(saveDoResult.getId(), updateDoResult.getId());
    assertEquals("Update", updateDoResult.getValue());
  }
  
  @Test
  void update_Repo() {
    // Arrange
    DomainObjectLongId saveDo = new DomainObjectLongId();
    saveDo.setValue("Save");
    
    DomainObjectLongId saveDoResult = doRepo.save(saveDo);
    
    DomainObjectLongId updateDo = new DomainObjectLongId();
    updateDo.setId(saveDoResult.getId());
    saveDoResult.setValue("Update");
    
    // Act
    DomainObjectLongId updateDoResult = doRepo.save(saveDoResult);
    
    // Assert
    assertEquals(saveDoResult.getId(), updateDoResult.getId());
    assertEquals("Update", updateDoResult.getValue());
  }
}
