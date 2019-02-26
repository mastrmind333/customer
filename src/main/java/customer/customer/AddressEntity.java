package customer.customer;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;
import static org.hibernate.annotations.CacheConcurrencyStrategy.TRANSACTIONAL;
import static customer.customer.CustomerConstant.SPACE;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.Objects;

import ch.qos.logback.core.pattern.SpacePadder;


@Entity
@Table(name = "CC_ADDRESS")
@JsonPropertyOrder({"id", "unit", "streetName", "suburb", "city", "state", "country", "pinCode", "isPrimaryAddress", "isMailingAdress", "status"})
@Cacheable
@Cache(usage = TRANSACTIONAL)
public class AddressEntity extends AbstractAuditingEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AddressEntity() {
		
	}

	@Id
    @GeneratedValue(strategy = SEQUENCE, generator = "address_seq")
    @SequenceGenerator(sequenceName = "address_seq", allocationSize = 1, name = "address_seq")
    @Column(name = "ID")
    @NotNull(groups = {CustomerUpdate.class})
    private Long id;

    @Basic
    @Column(name = "UNIT", nullable = false, length = 50)
    @NotBlank(groups = {CustomerCreate.class, CustomerUpdate.class})
    @Length(max = 50, groups = {CustomerCreate.class, CustomerUpdate.class})
    private Integer unit;

    @Basic
    @Column(name = "STREET_NAME", nullable = false, length = 100)
    @NotBlank(groups = {CustomerCreate.class, CustomerUpdate.class})
    //@NotNumeric(groups = {CustomerCreate.class, CustomerUpdate.class})
    @Length(max = 100, groups = {CustomerCreate.class, CustomerUpdate.class})
    private String streetName;

    @Basic
    @Column(name = "SUBURB", nullable = false, length = 50)
    @NotBlank(groups = {CustomerCreate.class, CustomerUpdate.class})
   // @NotNumeric(groups = {CustomerCreate.class, CustomerUpdate.class})
    @Length(max = 50, groups = {CustomerCreate.class, CustomerUpdate.class})
    private String suburb;

    @Basic
    @Column(name = "CITY", nullable = false, length = 50)
    @NotBlank(groups = {CustomerCreate.class, CustomerUpdate.class})
    @Length(max = 50, groups = {CustomerCreate.class, CustomerUpdate.class})
    private String city;

    @Basic
    @Column(name = "STATE", nullable = false, length = 50)
    @NotBlank(groups = {CustomerCreate.class, CustomerUpdate.class})
    @Length(max = 50, groups = {CustomerCreate.class, CustomerUpdate.class})
    private String state;

    @Basic
    @Column(name = "COUNTRY", nullable = false, length = 50)
    @NotBlank(groups = {CustomerCreate.class, CustomerUpdate.class})
    @Length(max = 50, groups = {CustomerCreate.class, CustomerUpdate.class})
    private String country;

    @Basic
    @Column(name = "PIN_CODE", nullable = false, length = 6)
    @NotBlank(groups = {CustomerCreate.class, CustomerUpdate.class})
    @Length(max = 6, groups = {CustomerCreate.class, CustomerUpdate.class})
    private Integer pinCode;

    @Basic
    @Column(name = "IS_PRIMARY_ADDRESS", nullable = false, length = 1)
    @NotBlank(groups = {CustomerCreate.class, CustomerUpdate.class})
    //@NotNumeric(groups = {CustomerCreate.class, CustomerUpdate.class})
    @Length(max = 1, groups = {CustomerCreate.class, CustomerUpdate.class})
    private String isPrimaryAddress;

    @Basic
    @Column(name = "IS_MAILING_ADDRESS", nullable = false, length = 1)
    @NotBlank(groups = {CustomerCreate.class, CustomerUpdate.class})
    //@NotNumeric(groups = {CustomerCreate.class, CustomerUpdate.class})
    @Length(max = 1, groups = {CustomerCreate.class, CustomerUpdate.class})
    private String isMailingAdress;

    @Basic
    @Column(name = "STATUS", nullable = false, length = 10)
    @NotBlank(groups = {CustomerCreate.class, CustomerUpdate.class})
    //@NotNumeric(groups = {CustomerCreate.class, CustomerUpdate.class})
    @Length(max = 10, groups = {CustomerCreate.class, CustomerUpdate.class})
    private Status status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "REF_CUSTOMER_ID", referencedColumnName = "ID", nullable = false)
    @JsonIgnore
    @NotNull
    CustomerEntity customer;
    
    @Override
	public String toString() {
		return id + SPACE +  unit + SPACE + streetName + SPACE + suburb + SPACE + city + SPACE + state + SPACE + country + SPACE + pinCode + SPACE + isPrimaryAddress + SPACE + isMailingAdress + SPACE + status;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(id, unit, streetName, suburb, city, state, country, pinCode, isPrimaryAddress, isMailingAdress, status);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getPinCode() {
		return pinCode;
	}

	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}

	public String getIsPrimaryAddress() {
		return isPrimaryAddress;
	}

	public void setIsPrimaryAddress(String isPrimaryAddress) {
		this.isPrimaryAddress = isPrimaryAddress;
	}

	public String getIsMailingAdress() {
		return isMailingAdress;
	}

	public void setIsMailingAdress(String isMailingAdress) {
		this.isMailingAdress = isMailingAdress;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}
    
    

}
