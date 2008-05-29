package api;
import static org.objectweb.asm.ClassWriter.COMPUTE_MAXS;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_STATIC;
import static org.objectweb.asm.Opcodes.V1_4;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.TraceClassVisitor;


public class Example2 {
  public static void main(String[] args) throws FileNotFoundException, IOException {
    ClassWriter cw = new ClassWriter(COMPUTE_MAXS);
    
    new ClassBuilder(cw, V1_4, ACC_PUBLIC, "HelloWorld", "java/lang/Object", null)    
    .beginMethod(ACC_PUBLIC, "<init>", void.class)
    .loadVar0(Self.class)
    .invoke().specVoid(Object.class, "<init>")
    .returnVoid()
    .endMethod()
    
    .beginStaticMethod(ACC_PUBLIC | ACC_STATIC, "main", void.class, String[].class)
    .closure(new Closure() {
      public <O> void apply(MethodBuilderS0V0<O> mb) {
        genSayHello(mb);
      }
    })
    .returnVoid()
    .endMethod();
    
    new FileOutputStream("HelloWorld.class").write(cw.toByteArray());
    
    System.out.println(classToString(cw.toByteArray()));
  }
  
  private static <O> void genSayHello(MethodBuilderS0V0<O> mb) {
    mb.assumeVar0(String[].class)
    .getStatic(System.class, "out", PrintStream.class)
    .loadVar0(String[].class)
    .push(0)
    .arrayLoad(String[].class, Integer.class, String.class)
    .invoke().param(String.class).virtVoid(PrintStream.class, "println");
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
