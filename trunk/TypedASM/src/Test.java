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
  public static void main(String[] args) throws FileNotFoundException, IOException {
    ClassWriter cw = new ClassWriter(COMPUTE_MAXS);
    
    new ClassBuilder<HelloWorld>(cw, V1_4, ACC_PUBLIC, HelloWorld.class, Object.class, null)    
    .beginMethod(ACC_PUBLIC, "<init>", void.class)
    .aload0()
    .invokeVirtualVoid(INVOKESPECIAL, Object.class, "<init>")
    .returnVoid()
    .endMethod()
    
    .beginMethod(ACC_PUBLIC | ACC_STATIC, "main", void.class, String[].class)
    .getStatic(System.class, "out", PrintStream.class)
    .aloadStatic0()
    .push(0)
    .arrayLoad(String[].class, Integer.class, String.class)
    .invokeVirtualVoid(INVOKEVIRTUAL, PrintStream.class, "println", String.class)
    .returnVoid()
    .endMethod();
    
    new FileOutputStream("HelloWorld.class").write(cw.toByteArray());
    
    System.out.println(classToString(cw.toByteArray()));
    
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
     * - What type should aload0 return?
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
