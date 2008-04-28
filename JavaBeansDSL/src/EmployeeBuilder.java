
public class EmployeeBuilder<PARENT extends Builder> 
    implements Builder {
  private Closure closure;
  private Employee employee;
  private PARENT parent;
  
  public EmployeeBuilder() {
    this(new Employee());
  }
  
  public EmployeeBuilder(Employee employee) {
    this(employee, null);
  }
  
  public EmployeeBuilder(Employee employee, PARENT parent) {
    this(employee, parent, null);
  }  
  
  public EmployeeBuilder(Employee employee, PARENT parent, Closure closure) {
    this.employee = employee;
    this.parent = parent;
    this.closure = closure;
  }  
  
  public interface Closure {
    void finish(Employee employee);
  }

  public EmployeeBuilder<PARENT> firstName(String firstName) {
    employee.firstName = firstName;
    return this;
  }
  
  public EmployeeBuilder<PARENT> lastName(String lastName) {
    employee.lastName = lastName;
    return this;
  }
  
  public AddressBuilder<EmployeeBuilder<PARENT>> 
      address() {
    return 
      new AddressBuilder<EmployeeBuilder<PARENT>>(new Address(), this, new AddressBuilder.Closure() {
        public void finish(Address address) {
          employee.address = address;
        }
      });
  }
  
  public PARENT finish() {
    if (closure != null) closure.finish(employee);
    return parent;
  }  
}

