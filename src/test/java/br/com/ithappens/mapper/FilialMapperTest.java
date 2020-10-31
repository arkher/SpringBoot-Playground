//package br.com.ithappens.mapper;
//
//import br.com.ithappens.model.Filial;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.Assert.assertEquals;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ActiveProfiles("default, myProfile")
//public class FilialMapperTest {
//
//  @Autowired
//  private FilialMapper filialMapper;
//
//  @Test
//  public void recuperar() {
//    Filial filial = filialMapper.recuperar();
//    assertEquals(Long.valueOf(1), filial.getId());
//  }
//}