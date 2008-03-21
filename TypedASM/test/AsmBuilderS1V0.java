import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;


public class AsmBuilderS1V0 <R, T1> implements Opcodes{
  private MethodVisitor mv;
  
  public AsmBuilderS1V0(MethodVisitor mv) {
    this.mv = mv;
  }
  
  public <T> AsmBuilderS2V0<R, T1, T>  push(T value) {
    mv.visitLdcInsn(value);
    return new AsmBuilderS2V0<R, T1, T>(mv);
  }
  
  public AsmBuilderS0V0<R>  pop() {
    mv.visitInsn(POP);
    return new AsmBuilderS0V0<R>(mv);
  }
  
  public AsmBuilderS2V0<R, T1, T1>  dup() {
    mv.visitInsn(DUP);
    return new AsmBuilderS2V0<R, T1, T1>(mv);
  }
  
  public <T> AsmBuilderS2V0<R, T1, T> getStatic(Class owner, String name, Class<T> type) {
    mv.visitFieldInsn(GETSTATIC, owner.getName().replace('.', '/'), name, Type.getDescriptor(type));
    return new AsmBuilderS2V0<R, T1, T>(mv);
  }
  
  public AsmBuilderS0V0<R> returnValue(Class<R> type) {
    mv.visitInsn(ARETURN);
    return new AsmBuilderS0V0<R>(mv);
  }
}
