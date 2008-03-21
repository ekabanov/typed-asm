import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;


public class AsmBuilderS2V0<R, T1, T2> implements Opcodes{
  private MethodVisitor mv;
  
  public AsmBuilderS2V0(MethodVisitor mv) {
    this.mv = mv;
  }
  
  public <T> AsmBuilderS3V0<R, T1, T2, T>  push(T value) {
    mv.visitLdcInsn(value);
    return new AsmBuilderS3V0<R, T1, T2, T>(mv);
  }
  
  public AsmBuilderS1V0<R, T1> pop() {
    mv.visitInsn(POP);
    return new AsmBuilderS1V0<R, T1>(mv);
  }
  
  public AsmBuilderS3V0<R, T1, T2, T2>  dup() {
    mv.visitInsn(DUP);
    return new AsmBuilderS3V0<R, T1, T2, T2>(mv);
  }
  
  public <T> AsmBuilderS1V0<R, T> invokeVirtual(Class<T1> owner, String name, Class<T2> parameter1, Class<T> result) {
    mv.visitMethodInsn(
        INVOKEVIRTUAL, 
        owner.getName().replace('.', '/'), 
        name, 
        Type.getMethodDescriptor(Type.getType(result), new Type[] {Type.getType(parameter1)}));
    
    return new AsmBuilderS1V0<R, T>(mv);
  }
  
  public <T> AsmBuilderS3V0<R, T1, T2, T> getStatic(Class owner, String name, Class<T> type) {
    mv.visitFieldInsn(GETSTATIC, owner.getName().replace('.', '/'), name, Type.getDescriptor(type));
    return new AsmBuilderS3V0<R, T1, T2, T>(mv);
  }
  
  public AsmBuilderS0V0<R> returnValue(Class<R> type) {
    mv.visitInsn(ARETURN);
    return new AsmBuilderS0V0<R>(mv);
  }
}
