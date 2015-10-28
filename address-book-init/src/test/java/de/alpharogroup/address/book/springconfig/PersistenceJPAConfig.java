package de.alpharogroup.address.book.springconfig;

//import java.util.Properties;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Configuration
//@EnableTransactionManagement
public class PersistenceJPAConfig {
 
//   @Bean
//   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//      em.setDataSource(dataSource());
//      em.setPackagesToScan(new String[] { "address.book.model" });
// 
//      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//      em.setJpaVendorAdapter(vendorAdapter);
//      em.setJpaProperties(additionalProperties());
// 
//      return em;
//   }
// 
//   @Bean
//   public DataSource dataSource(){
//      DriverManagerDataSource dataSource = new DriverManagerDataSource();
//      dataSource.setDriverClassName("org.postgresql.Driver");
//      dataSource.setUrl("jdbc:postgresql://localhost:5432/addressbook");
//      dataSource.setUsername( "postgres" );
//      dataSource.setPassword( "secret" );
//      return dataSource;
//   }
// 
//   @Bean
//   public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
//      JpaTransactionManager transactionManager = new JpaTransactionManager();
//      transactionManager.setEntityManagerFactory(emf); 
//      return transactionManager;
//   }
// 
//   @Bean
//   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
//      return new PersistenceExceptionTranslationPostProcessor();
//   }
// 
//   Properties additionalProperties() {
//      Properties properties = new Properties();
//      properties.setProperty("hibernate.show_sql", "true");
//      properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//      return properties;
//   }
}