
public class AddressBuilder<PARENT extends Builder> 
    implements Builder {
  private Address address;
  private PARENT parent;
  private Closure closure;

  public AddressBuilder() {
    this(new Address());
  }
  
  public AddressBuilder(Address address) {
    this(address, null);
  }
  
  public AddressBuilder(Address address, PARENT parent) {
    this(address, parent, null);
  }  
  
  public AddressBuilder(Address address, PARENT parent, Closure closure) {
    this.address = address;
    this.parent = parent;
    this.closure = closure;
  } 
  
  public interface Closure {
    void finish(Address address);
  }

  public AddressBuilder<PARENT> country(String country) {
    address.country = country;
    return this;
  }
  
  public AddressBuilder<PARENT> street(String street) {
    address.street = street;
    return this;
  }
  
  public AddressBuilder<PARENT> town(String town) {
    address.town = town;
    return this;
  }  
  
  public AddressBuilder<PARENT> zip(String zip) {
    address.zip = zip;
    return this;
  }    

  public PARENT finish() {
    if (closure != null) closure.finish(address);
    return parent;
  }  
}