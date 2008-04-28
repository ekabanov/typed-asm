public class EmployeeBuilder<PARENT extends Builder> 
    implements Builder {
  PARENT parent;

  public EmployeeBuilder(Employee e, PARENT parent) {
    this.parent = parent;
  }

  //Field setters ...

  public AddressBuilder<EmployeeBuilder<PARENT>> 
      addAddress() {
    return 
      new AddressBuilder<EmployeeBuilder<PARENT>>(new Address(), this);
  }
  
  public PARENT finish() {
    return parent;
  }  
}

