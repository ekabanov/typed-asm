
public class CompanyBuilder<PARENT extends Builder> implements Builder {
  private Company company;
  private PARENT parent;
  
  public CompanyBuilder() {
    this(new Company());
  }
  
  public CompanyBuilder(Company company) {
    this(company, null);
  }
  
  public CompanyBuilder(Company company, PARENT parent) {
    this(company, parent, null);
  }  
  
  public CompanyBuilder(Company company, PARENT parent, Closure closure) {
    this.company = company;
    this.parent = parent;
  }  
  
  public interface Closure {
    void finish(Company company);
  }
  
  public CompanyBuilder<PARENT> name(String name) {
    company.name = name;
    return this;
  }
  
  public AddressBuilder<CompanyBuilder<PARENT>> address() {
    return new AddressBuilder<CompanyBuilder<PARENT>>(new Address(), this, new AddressBuilder.Closure() {
      public void finish(Address address) {
        company.address = address;        
      }
    });
  }
  
  public EmployeeBuilder<CompanyBuilder<PARENT>> employee() {
    return new EmployeeBuilder<CompanyBuilder<PARENT>>(new Employee(), this, new EmployeeBuilder.Closure() {
      public void finish(Employee employee) {
        company.employees.add(employee);
      }
    });
  }  
  
  public Company toCompany() {
    return company;
  }
}
