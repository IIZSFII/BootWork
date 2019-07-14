package com.lottery.dao;


import com.lottery.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * JpaRepository<实体类类型，主键类型>：用来完成基本CRUD操作
 * JpaSpecificationExecutor<实体类类型>：用于复杂查询（分页等查询操作）
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>{

@Query(value="from Customer")
public List<Customer> findSome();

@Query(value="from Customer where custName like ?1")
public List<Customer> findSomeBy(String custName);

@Query(value="update Customer set custName = ?1 where custId = ?2")
@Modifying
public void upSome(String custName, Long custId);

/**
 * nativeQuery : 使用本地sql的方式查询
 */
@Query(value="select * from cst_customer",nativeQuery=true)
public List<Customer> findSql();

//方法命名规则查询
public Customer findByCustNameAndCustAddressEquals(String CustName, String CustAddress);

//方法命名规则查询
public Customer findByCustName(String CustName);

}
