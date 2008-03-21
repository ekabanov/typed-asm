import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;


public class AsmBuilderS4V0<R, T1, T2, T3, T4> implements Opcodes{
  private MethodVisitor mv;
  
  public AsmBuilderS4V0(MethodVisitor mv) {
    this.mv = mv;
  }
  
  public <T> AsmBuilderS5V0<R, T1, T2, T3, T4, T> push(T value) {
    mv.visitLdcInsn(value);
    return new AsmBuilderS5V0<R, T1, T2, T3, T4, T>(mv);
  }
  
  public <T> AsmBuilderS3V0<R, T1, T2, T3> pop(T value) {
    mv.visitInsn(POP);
    return new AsmBuilderS3V0<R, T1, T2, T3>(mv);
  }
  
  public AsmBuilderS5V0<R, T1, T2, T3, T4, T4>  dup() {
    mv.visitInsn(DUP);
    return new AsmBuilderS5V0<R, T1, T2, T3, T4, T4>(mv);
  }
  
  public <T> AsmBuilderS3V0<R, T1, T2, T> invokeVirtual(Class<T3> owner, String name, Class<T4> parameter1, Class<T> result) {
    mv.visitMethodInsn(
        INVOKEVIRTUAL, 
        owner.getName().replace('.', '/'), 
        name, 
        Type.getMethodDescriptor(Type.getType(result), new Type[] {Type.getType(parameter1)}));
    
    return new AsmBuilderS3V0<R, T1, T2, T>(mv);
  }
  
  public <T> AsmBuilderS2V0<R, T1, T> invokeVirtual(Class<T2> owner, String name, Class<T3> parameter1, Class<T4> parameter2, Class<T> result) {
    mv.visitMethodInsn(
        INVOKEVIRTUAL, 
        owner.getName().replace('.', '/'), 
        name, 
        Type.getMethodDescriptor(Type.getType(result), new Type[] {Type.getType(parameter1), Type.getType(parameter2)}));
    
    return new AsmBuilderS2V0<R, T1, T>(mv);
  }
  
  public <T> AsmBuilderS5V0<R, T1, T2, T3, T4, T> getStatic(Class owner, String name, Class<T> type) {
    mv.visitFieldInsn(GETSTATIC, owner.getName().replace('.', '/'), name, Type.getDescriptor(type));
    return new AsmBuilderS5V0<R, T1, T2, T3, T4, T>(mv);
  }
  
  public AsmBuilderS0V0<R> returnValue(Class<R> type) {
    mv.visitInsn(ARETURN);
    return new AsmBuilderS0V0<R>(mv);
  }
}
