import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;


public class AsmBuilderS0V0<R> implements Opcodes {
  private MethodVisitor mv;
  
  public AsmBuilderS0V0(MethodVisitor mv) {
    this.mv = mv;
  }
  
  public <T> AsmBuilderS1V0<R, T>  push(T value) {
    mv.visitLdcInsn(value);
    return new AsmBuilderS1V0<R, T>(mv);
  }
  
  public AsmBuilderS0V0<R> pop() {
    mv.visitInsn(POP);
    return this;
  }
  
  public AsmBuilderS0V0<R> dup() {
    mv.visitInsn(DUP);
    return this;
  }
  
  public <T> AsmBuilderS1V0<R, T> getStatic(Class owner, String name, Class<T> type) {
    mv.visitFieldInsn(GETSTATIC, owner.getName().replace('.', '/'), name, Type.getDescriptor(type));
    return new AsmBuilderS1V0<R, T>(mv);
  }
  
  public AsmBuilderS0V0<R> returnValue(Class<R> type) {
    mv.visitInsn(ARETURN);
    return new AsmBuilderS0V0<R>(mv);
  }
}
