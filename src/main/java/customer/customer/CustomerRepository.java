package customer.customer;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

	  @Transactional(readOnly = true)
	  @Query(value =
	  "select * from CC_CUSTOMER customer where (customer.STATUS)='ENABLED' and (customer.FIRST_NAME) like '%' || :substring || '%' "
	  + "and (customer.MIDDLE_NAME) like '%' || :substring || '%' " +
	  "or (customer.LAST_NAME) like '%' || :substring || '%' ", nativeQuery = true)
	  Stream<CustomerEntity> findAllByName(@Param(value = "substring") String subString);
	  
	 }
