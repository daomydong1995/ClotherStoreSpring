package m07.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Locale;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("m07")
@EnableJpaRepositories("m07.repository")
@PropertySource("classpath:application.properties")
public class HelloWorldConfiguration extends WebMvcConfigurerAdapter {
  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setViewClass(JstlView.class);
    viewResolver.setPrefix("/WEB-INF/jsp/");
    viewResolver.setSuffix(".jsp");
    return viewResolver;
  }


  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry
        .addResourceHandler("/resources/**")
        .addResourceLocations("/resources/");
  }

  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("i18n/language");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }
  @Bean
  public LocaleResolver localeResolver(){
    CookieLocaleResolver resolver = new CookieLocaleResolver();
    resolver.setDefaultLocale(new Locale("vi"));
    resolver.setCookieName("myAppLocaleCookie");
    resolver.setCookieMaxAge(3600);
    return resolver;
  }
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
    interceptor.setParamName("language");
    registry.addInterceptor(interceptor);
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource());
    em.setPackagesToScan(env.getProperty("entitymanager.packagesToScan"));
    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.getJpaPropertyMap();
    em.setJpaVendorAdapter(vendorAdapter);
    Properties props = new Properties();
    props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
    props.setProperty("hibernate.show_sql", "true");
    props.setProperty("hibernate.hbm2ddl.auto",env.getProperty("hibernate.hbm2ddl.auto"));
    em.setJpaProperties(props);
    return em;
  }

  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){

    JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
    jpaTransactionManager.setDataSource(dataSource());
    jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
    return jpaTransactionManager;
  }

  // upload file
  @Bean(name = "multipartResolver")
  public CommonsMultipartResolver commonsMultipartResolver(){
    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    multipartResolver.setMaxUploadSize((Integer.valueOf(env.getProperty("size.upload"))));
    return multipartResolver;
  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getProperty("db.driver"));
    dataSource.setUrl(env.getProperty("db.jdbc"));
    dataSource.setUsername(env.getProperty("db.username"));
    dataSource.setPassword(env.getProperty("db.password"));
    return dataSource;
  }
  @Bean
  public JdbcTemplate jdbcTemplate() {
    return new JdbcTemplate(dataSource());
  }

  // Bean Application Properties
  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }
  @Autowired
  private Environment env;
}


