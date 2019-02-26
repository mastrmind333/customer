package customer.customer;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.SEQUENCE;
import static org.hibernate.annotations.CacheConcurrencyStrategy.TRANSACTIONAL;
import static org.hibernate.annotations.FetchMode.SUBSELECT;
import static customer.customer.CustomerConstant.BLANK_STRING;
import static customer.customer.CustomerConstant.SPACE;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Fetch;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.Objects;


@Entity
@Table(name = "CC_CUSTOMER")
@JsonPropertyOrder({"id", "firstName", "middleName", "lastName", "initials", "gender", "maritalStatus", "creditRating", "isExistingCustomer", "status"})
@Cacheable
@Cache(usage = TRANSACTIONAL)
public class CustomerEntity extends AbstractAuditingEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CustomerEntity() {
		
	}

	@Id
    @GeneratedValue(strategy = SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(sequenceName = "customer_seq", allocationSize = 1, name = "customer_seq")
    @Column(name = "ID")
    @NotNull(groups = {CustomerUpdate.class})
    private Long id;

    @Basic
    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    @NotBlank(groups = {CustomerCreate.class, CustomerUpdate.class})
    //@NotNumeric(groups = {CustomerCreate.class, CustomerUpdate.class})
    @Length(max = 50, groups = {CustomerCreate.class, CustomerUpdate.class})
    private String firstName;

    @Basic
    @Column(name = "MIDDLE_NAME", nullable = false, length = 50)
    //@NotNumeric(groups = {CustomerCreate.class, CustomerUpdate.class})
    @Length(max = 50, groups = {CustomerCreate.class, CustomerUpdate.class})
    private String middleName;

    @Basic
    @Column(name = "LAST_NAME", nullable = false, length = 50)
    //@NotNumeric(groups = {CustomerCreate.class, CustomerUpdate.class})
    @Length(max = 50, groups = {CustomerCreate.class, CustomerUpdate.class})
    private String lastName;

    @Basic
    @Column(name = "INITIALS", nullable = false, length = 10)
    //@NotNumeric(groups = {CustomerCreate.class, CustomerUpdate.class})
    @Length(max = 10, groups = {CustomerCreate.class, CustomerUpdate.class})
    private String initials;

    @Basic
    @Column(name = "GENDER", nullable = false, length = 6)
    //@NotNumeric(groups = {CustomerCreate.class, CustomerUpdate.class})
    @Length(max = 6, groups = {CustomerCreate.class, CustomerUpdate.class})
    @Enumerated(STRING)
    private Gender gender;

    @Basic
    @Column(name = "MARITAL_STATUS", nullable = false, length = 20)
    //@NotNumeric(groups = {CustomerCreate.class, CustomerUpdate.class})
    @Length(max = 20, groups = {CustomerCreate.class, CustomerUpdate.class})
    @Enumerated(STRING)
    private MaritalStatus maritalStatus;

    @Basic
    @Column(name = "CEDIT_RATING", nullable = false, length = 3)
    @Length(max = 3, groups = {CustomerCreate.class, CustomerUpdate.class})
    private Integer creditRating;

    @Basic
    @Column(name = "IS_EXISTING_CUSTOMER", nullable = false, length = 1)
    @Length(max = 1, groups = {CustomerCreate.class, CustomerUpdate.class})
    private Boolean isExistingCustomer;

    @Basic
    @Column(name = "STATUS", nullable = false, length = 10)
    @NotNull(groups = {CustomerCreate.class, CustomerUpdate.class})
    @Enumerated(STRING)
    private Status status;

    @OneToMany(mappedBy = "customer", cascade = ALL, fetch = EAGER, orphanRemoval = true)
    @Fetch(SUBSELECT)
    @Cache(usage = TRANSACTIONAL)
    private Set<AddressEntity> addressEntities = new HashSet<AddressEntity>();

    public String getFullName() {

        return firstName + SPACE + (StringUtils.hasText(middleName) ? middleName + SPACE : BLANK_STRING) + lastName;
    }
    
    @Override
   	public String toString() {
   		return id + SPACE + firstName + SPACE + middleName + SPACE + lastName + SPACE + initials + SPACE + gender + SPACE + maritalStatus + SPACE + creditRating + SPACE + isExistingCustomer + SPACE + status;
   	}
   	
   	@Override
   	public int hashCode() {
   		return Objects.hashCode(id, firstName, middleName, lastName, initials, gender, maritalStatus, creditRating, isExistingCustomer, status);
   	}
    
    
}
