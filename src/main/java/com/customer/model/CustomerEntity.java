package com.customer.model;

import static com.customer.config.CustomerConstant.BLANK_STRING;
import static com.customer.config.CustomerConstant.SPACE;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.SEQUENCE;
import static org.hibernate.annotations.CacheConcurrencyStrategy.TRANSACTIONAL;
import static org.hibernate.annotations.FetchMode.SUBSELECT;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Fetch;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.StringUtils;

import com.customer.config.CustomerCreate;
import com.customer.config.CustomerUpdate;
import com.customer.config.Gender;
import com.customer.config.MaritalStatus;
import com.customer.config.Status;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.base.Objects;


@SuppressWarnings("deprecation")
@Entity
@Table(name = "CC_CUSTOMER")
@JsonPropertyOrder({"id","fullName", "initials", "gender", "maritalStatus", "creditRating", "isExistingCustomer", "status"})
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
	@JsonView
    private Long id;

    @Basic
    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    @NotBlank(groups = {CustomerCreate.class, CustomerUpdate.class})
    @Length(max = 50, groups = {CustomerCreate.class, CustomerUpdate.class})
    private String firstName;

    @Basic
    @Column(name = "MIDDLE_NAME", nullable = false, length = 50)
    @Length(max = 50, groups = {CustomerCreate.class, CustomerUpdate.class})
    private String middleName;

    @Basic
    @Column(name = "LAST_NAME", nullable = false, length = 50)
    @Length(max = 50, groups = {CustomerCreate.class, CustomerUpdate.class})
    private String lastName;

    @Basic
    @Column(name = "INITIALS", length = 10)
    @Length(max = 10, groups = {CustomerCreate.class, CustomerUpdate.class})
	@JsonView
    private String initials;

    @Basic
    @Column(name = "GENDER", nullable = false, length = 6)
    @Enumerated(STRING)
    @JsonView
    private Gender gender;

    @Basic
    @Column(name = "MARITAL_STATUS", length = 20)
    @Enumerated(STRING)
    @JsonView
    private MaritalStatus maritalStatus;

    @Basic
    @Column(name = "CEDIT_RATING", length = 3)
    @JsonView
    private Integer creditRating;

    @Basic
    @Column(name = "IS_EXISTING_CUSTOMER", nullable = false)
    @JsonView
    private Boolean isExistingCustomer;

    @Basic
    @Column(name = "STATUS", nullable = false, length = 10)
    @NotNull(groups = {CustomerCreate.class, CustomerUpdate.class})
    @Enumerated(STRING)
    @JsonView
    private Status status;

    @OneToMany(mappedBy = "customer", cascade = ALL, fetch = EAGER, orphanRemoval = true)
    @Fetch(SUBSELECT)
    @Cache(usage = TRANSACTIONAL)
    @JsonView
    private List<AddressEntity> addressEntities = new ArrayList<AddressEntity>();

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Integer getCreditRating() {
		return creditRating;
	}

	public void setCreditRating(Integer creditRating) {
		this.creditRating = creditRating;
	}

	public Boolean getIsExistingCustomer() {
		return isExistingCustomer;
	}

	public void setIsExistingCustomer(Boolean isExistingCustomer) {
		this.isExistingCustomer = isExistingCustomer;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<AddressEntity> getAddressEntities() {
		return addressEntities;
	}

	public void setAddressEntities(List<AddressEntity> addressEntities) {
		this.addressEntities = addressEntities;
	}
    
    
}
