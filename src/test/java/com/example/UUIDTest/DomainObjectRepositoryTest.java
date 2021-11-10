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
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
classes = UuidTestApplication.class)
@AutoConfigureMockMvc
@Sql(scripts = {"/domain_object.sql"})
@AutoConfigureEmbeddedDatabase
class DomainObjectRepositoryTest {

  @Autowired
  private DomainObjectRepository doRepo;

  @Autowired
  JdbcTemplate template;
  
  @Autowired
  DataSource dataSource;
  
  @Test
  void update_JDBCTemplate() {
    // Arrange
    DomainObject saveDo = new DomainObject();
    saveDo.setValue("Save");
    
    DomainObject saveDoResult = doRepo.save(saveDo);
    
    // Act
    int rowCount = template.update("UPDATE domain_object SET \"value\" = ? WHERE \"domain_object\".\"id\" = ?",  "Update", saveDoResult.getId());
    
    // Assert
    assertEquals(1, rowCount);
    
    DomainObject updateDoResult = doRepo.findById(saveDoResult.getId()).orElseThrow();
    assertEquals(saveDoResult.getId(), updateDoResult.getId());
    assertEquals("Update", updateDoResult.getValue());
  }
  
  @Test
  void update_NamedParameterTemplate() {
    // Arrange
    DomainObject saveDo = new DomainObject();
    saveDo.setValue("Save");
    
    DomainObject saveDoResult = doRepo.save(saveDo);
    
    Map<String, Object> params = new HashMap<>();
    params.put("id", saveDoResult.getId());
    params.put("value", "Update");
    
    MapSqlParameterSource paramSource = new MapSqlParameterSource(params);
    NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
    
    // Act
    int rowCount = template.update("UPDATE domain_object SET \"value\" = :value WHERE \"domain_object\".\"id\" = :id", paramSource);
    
    // Assert
    assertEquals(1, rowCount);
    
    DomainObject updateDoResult = doRepo.findById(saveDoResult.getId()).orElseThrow();
    assertEquals(saveDoResult.getId(), updateDoResult.getId());
    assertEquals("Update", updateDoResult.getValue());
  }
  
  @Test
  void update_ParamMap() {
    // Arrange
    DomainObject saveDo = new DomainObject();
    saveDo.setValue("Save");
    
    DomainObject saveDoResult = doRepo.save(saveDo);
    
    Map<String, Object> params = new HashMap<>();
    params.put("id", saveDoResult.getId());
    params.put("value", "Update");
    
    NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
    
    // Act
    int rowCount = template.update("UPDATE domain_object SET \"value\" = :value WHERE \"domain_object\".\"id\" = :id", params);
    
    // Assert
    assertEquals(1, rowCount);
    
    DomainObject updateDoResult = doRepo.findById(saveDoResult.getId()).orElseThrow();
    assertEquals(saveDoResult.getId(), updateDoResult.getId());
    assertEquals("Update", updateDoResult.getValue());
  }
  
  @Test
  void update_Repo() {
    // Arrange
    DomainObject saveDo = new DomainObject();
    saveDo.setValue("Save");
    
    DomainObject saveDoResult = doRepo.save(saveDo);
    
    DomainObject updateDo = new DomainObject();
    updateDo.setId(saveDoResult.getId());
    updateDo.setValue("Update");
    
    // Act
    DomainObject updateDoResult = doRepo.save(updateDo);
    
    // Assert
    assertEquals(saveDoResult.getId(), updateDoResult.getId());
    assertEquals("Update", updateDoResult.getValue());
  }
}
