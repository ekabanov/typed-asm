import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Type;


public class ClassBuilder<O> {
  private ClassVisitor cv;

  public ClassBuilder(ClassVisitor cv, int version, int access, Class<O> type, Class superType, String[] interfaces) {
    this.cv = cv;
    
    cv.visit(
        version, 
        access, 
        type.getName().replace('.', '/'), 
        null, 
        superType == null ? null : superType.getName().replace('.', '/'), 
        interfaces);
  }
  
  public MethodBuilderS0V0<O> beginMethod(int access, String name, Class returnType) {
    return new MethodBuilderS0V0<O>(
        this,
        cv.visitMethod(
            access, 
            name, 
            Type.getMethodDescriptor(Type.getType(returnType), new Type[] {}), 
            null, 
            null));
  }
  
  public <V0> MethodBuilderS0V1<O, V0> beginMethod(int access, String name, Class returnType, Class<V0> parameter1) {
    return new MethodBuilderS0V1<O, V0>(
        this,
        cv.visitMethod(
            access, 
            name, 
            Type.getMethodDescriptor(Type.getType(returnType), 
                new Type[] {Type.getType(parameter1)}), 
            null, 
            null));
  }
  
  public <V0, V1> MethodBuilderS0V2<O, V0, V1> beginMethod(int access, String name, Class returnType, Class<V0> parameter1, Class<V1> parameter2) {
    return new MethodBuilderS0V2<O, V0, V1>(
        this,
        cv.visitMethod(
            access, 
            name, 
            Type.getMethodDescriptor(Type.getType(returnType), 
                new Type[] {Type.getType(parameter1)}), 
            null, 
            null));
  }
}
