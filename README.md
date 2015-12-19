# skeleton-mybatis-persistence
A skeleton project of MyBatis with Freemarker scripting for Spring MVC
Author: Johnson Zhu
Email: 169565@qq.com

While MyBatis xml mapper already provides a great way to balance between sql maintenance and performance, the mapper files with large/complex queries may grow huge.
The Freemarker template engine enables a more elegant way to maintain the growing sqls. This project serves as a code skeleton which is capable of general CRUDs as well as table-join queries. In most case we only need to maintain the columns info in columns.ftl.

The template macros are based on Oracle, but it should be easy to change to adapt other RDBMS like MySQL.

The configuration is designed to work with Spring MVC. To avoid too many dependencies I commented out the references in Java config file.
