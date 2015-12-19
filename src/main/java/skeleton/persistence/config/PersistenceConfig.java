package skeleton.persistence.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.core.io.ClassPathResource;


//@Configuration
//@EnableTransactionManagement
//@MapperScan("skeleton.persistence.mapper")
//@PropertySource("classpath:jdbc.properties")
public class PersistenceConfig {
//    @Autowired
//    private Environment env;
    
    public DataSource dataSource() throws PropertyVetoException {
        return null;
    }
    
//    @Bean
//    public DataSourceTransactionManager transactionManager() throws PropertyVetoException {
//    }
    
//    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setTypeAliasesPackage("skeleton.persistence.model");
//        sessionFactory.setConfigLocation(new ClassPathResource("mybatis.xml"));
        return sessionFactory.getObject();
    }
}
