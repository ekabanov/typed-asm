import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;


public class AsmBuilderS5V0<R, T1, T2, T3, T4, T5> implements Opcodes{
  private MethodVisitor mv;
  
  public AsmBuilderS5V0(MethodVisitor mv) {
    this.mv = mv;
  }
  
  public <T> AsmBuilderS5V0<R, T2, T3, T4, T5, T> push(T value) {
    mv.visitLdcInsn(value);
    return new AsmBuilderS5V0<R, T2, T3, T4, T5, T>(mv);
  }
  
  public <T> AsmBuilderS4V0<R, T1, T2, T3, T4> pop(T value) {
    mv.visitInsn(POP);
    return new AsmBuilderS4V0<R, T1, T2, T3, T4>(mv);
  }
  
  public AsmBuilderS5V0<R, T2, T3, T4, T5, T5>  dup() {
    mv.visitInsn(DUP);
    return new AsmBuilderS5V0<R, T2, T3, T4, T5, T5>(mv);
  }
  
  public <T> AsmBuilderS4V0<R, T1, T2, T3, T> invokeVirtual(Class<T4> owner, String name, Class<T5> parameter1, Class<T> result) {
    mv.visitMethodInsn(
        INVOKEVIRTUAL, 
        owner.getName().replace('.', '/'), 
        name, 
        Type.getMethodDescriptor(Type.getType(result), new Type[] {Type.getType(parameter1)}));
    
    return new AsmBuilderS4V0<R, T1, T2, T3, T>(mv);
  }
  
  public <T> AsmBuilderS3V0<R, T1, T2, T> invokeVirtual(Class<T3> owner, String name, Class<T4> parameter1, Class<T5> parameter2, Class<T> result) {
    mv.visitMethodInsn(
        INVOKEVIRTUAL, 
        owner.getName().replace('.', '/'), 
        name, 
        Type.getMethodDescriptor(Type.getType(result), new Type[] {Type.getType(parameter1), Type.getType(parameter2)}));
    
    return new AsmBuilderS3V0<R, T1, T2, T>(mv);
  }
  
  public <T> AsmBuilderS5V0<R, T2, T3, T4, T5, T> getStatic(Class owner, String name, Class<T> type) {
    mv.visitFieldInsn(GETSTATIC, owner.getName().replace('.', '/'), name, Type.getDescriptor(type));
    return new AsmBuilderS5V0<R, T2, T3, T4, T5, T>(mv);
  }
  
  public AsmBuilderS0V0<R> returnValue(Class<R> type) {
    mv.visitInsn(ARETURN);
    return new AsmBuilderS0V0<R>(mv);
  }
}
