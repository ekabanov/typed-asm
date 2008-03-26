import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.TraceClassVisitor;
import static org.objectweb.asm.ClassWriter.*;
import static org.objectweb.asm.Opcodes.*;


public class Test {
  private static <O> void genSayHello(MethodBuilderS0V0<O> mb) {
    mb.assumeVar0(String[].class)
    .getStatic(System.class, "out", PrintStream.class)
    .loadVar0(String[].class)
    .push(0)
    .arrayLoad(String[].class, Integer.class, String.class)
    .invoke().param(String.class).virtVoid(PrintStream.class, "println");
  }
  
  public static void main(String[] args) throws FileNotFoundException, IOException {
    ClassWriter cw = new ClassWriter(COMPUTE_MAXS);
    
    new ClassBuilder(cw, V1_4, ACC_PUBLIC, "HelloWorld", "java/lang/Object", null)    
    .beginMethod(ACC_PUBLIC, "<init>", void.class)
    .loadVar0(Self.class)
    .invoke().specVoid(Object.class, "<init>")
    .returnVoid()
    .endMethod()
    
    .beginStaticMethod(ACC_PUBLIC | ACC_STATIC, "main", void.class, String[].class)
//    .getStatic(System.class, "out", PrintStream.class)
//    .closure(new Closure() {
//      public <O> void apply(MethodBuilderS0V0<O> mb) {
//        genSayHello(mb);
//      }
//    })
    .getStatic(System.class, "out", PrintStream.class)
    .loadVar0(String[].class)
    .push(0)
    .arrayLoad(String[].class, Integer.class, String.class)    
    .invoke().param(String.class).virtVoid(PrintStream.class, "println")
    .returnVoid()
    .endMethod();
    
    new FileOutputStream("HelloWorld.class").write(cw.toByteArray());
    
    System.out.println(classToString(cw.toByteArray()));
    
    /*
     * Problems:
     * - Primitives
     * - Null type (must give explicit type?)
     * - Reuse (reset to initial, lose information)
     * - Control flow (reset to initial, lose information) 
     *  - Pass counter to disallow reusing obsolete instances
     * - Deprecate methods with wrong stack depth, etc
     *   - Instead of just missing them
     *   - Shift stack info when adding
     *   - Ignore stack info when missed
     *   - Both variants should produce a warning
     * - Different JARs for development (typesafe) and production (unsafe)
     *   - Source compatible (no binary)
     * - What type should aload0 return in a virtual method?
     *   - Placeholder type? (Self.class)
     *   - Also allow other renamable subtypes
     * - Different variable layout in virtual/static methods
     *   - Handle by lifting "this" type to V0
     *   - Also remove the "this" (O) from prefix
     */
  }
  
  public static String classToString(byte[] bytes) {
    ClassReader reader = new ClassReader(bytes);
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    reader.accept(new TraceClassVisitor(pw), ClassReader.EXPAND_FRAMES);
    pw.flush();
    pw.close();
    sw.flush();

    return sw.getBuffer().toString();
  }
}
