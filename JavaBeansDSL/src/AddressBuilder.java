public class AddressBuilder<PARENT extends Builder> 
    implements Builder {
  PARENT parent;

  public AddressBuilder(Address a, PARENT parent) {
    this.parent = parent;
  }

  //Field setters ...

  public PARENT finish() {
    return parent;
  }  
}