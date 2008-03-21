import java.io.PrintStream;


public class Test {
  public static void main(String[] args) {
    new AsmBuilderS0V0<String>(null)
    .getStatic(System.class, "out", PrintStream.class)
    .push("Hello, world!")
    .invokeVirtual(PrintStream.class, "println", String.class, String.class)
    .returnValue(String.class);
    
    Class<int[]> s = int[].class;
    
    /*
     * Problems:
     * - Primitives
     * - Variables (same as stack)
     * - Null type (must give explicit type?)
     * - Return
     * - Reuse (reset to initial, lose information)
     * - Control flow (reset to initial, lose information) 
     * - Pass counter to disallow reusing obsolete instances
     * - Deprecate methods with wrong stack depth, etc
     * - Different JARs for development (typesafe) and production (unsafe)
     */
  }
}
