import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Type;


public class ClassBuilder {
  private ClassVisitor cv;

  public ClassBuilder(ClassVisitor cv, int version, int access, String name, String superName, String[] interfaces) {
    this.cv = cv;
    
    cv.visit(
        version, 
        access, 
        name, 
        null, 
        superName, 
        interfaces);
  }
  
  public MethodBuilderS0V0<Self> beginStaticMethod(int access, String name, Class returnType) {
    return new MethodBuilderS0V0<Self>(
        this,
        cv.visitMethod(
            access, 
            name, 
            Type.getMethodDescriptor(Type.getType(returnType), new Type[] {}), 
            null, 
            null));
  }
  
  public MethodBuilderS0V1<Self, Self> beginMethod(int access, String name, Class returnType) {
    return new MethodBuilderS0V1<Self, Self>(
        this,
        cv.visitMethod(
            access, 
            name, 
            Type.getMethodDescriptor(Type.getType(returnType), new Type[] {}), 
            null, 
            null));
  }
  
  public <V0> MethodBuilderS0V1<Self, V0> beginStaticMethod(int access, String name, Class returnType, Class<V0> parameter1) {
    return new MethodBuilderS0V1<Self, V0>(
        this,
        cv.visitMethod(
            access, 
            name, 
            Type.getMethodDescriptor(Type.getType(returnType), 
                new Type[] {Type.getType(parameter1)}), 
            null, 
            null));
  }
  
  public <V0> MethodBuilderS0V2<Self, Self, V0> beginMethod(int access, String name, Class returnType, Class<V0> parameter1) {
    return new MethodBuilderS0V2<Self, Self, V0>(
        this,
        cv.visitMethod(
            access, 
            name, 
            Type.getMethodDescriptor(Type.getType(returnType), 
                new Type[] {Type.getType(parameter1)}), 
            null, 
            null));
  }
  
  public <V0, V1> MethodBuilderS0V3<Self, Self, V0, V1> beginStaticMethod(int access, String name, Class returnType, Class<V0> parameter1, Class<V1> parameter2) {
    return new MethodBuilderS0V3<Self, Self, V0, V1>(
        this,
        cv.visitMethod(
            access, 
            name, 
            Type.getMethodDescriptor(Type.getType(returnType), 
                new Type[] {Type.getType(parameter1)}), 
            null, 
            null));
  }
  
  public <V0, V1> MethodBuilderS0V2<Self, V0, V1> beginMethod(int access, String name, Class returnType, Class<V0> parameter1, Class<V1> parameter2) {
    return new MethodBuilderS0V2<Self, V0, V1>(
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
