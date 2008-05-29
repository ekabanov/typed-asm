package api;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;


public class GeneratorUtil {
  /**
   * Generates the instruction to push the given value on the stack.
   * 
   * @param value the value to be pushed on the stack.
   */
  public static void push(MethodVisitor mv, final boolean value) {
      push(mv, value ? 1 : 0);
  }

  /**
   * Generates the instruction to push the given value on the stack.
   * 
   * @param value the value to be pushed on the stack.
   */
  public static void push(MethodVisitor mv, final int value) {
      if (value >= -1 && value <= 5) {
          mv.visitInsn(Opcodes.ICONST_0 + value);
      } else if (value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE) {
          mv.visitIntInsn(Opcodes.BIPUSH, value);
      } else if (value >= Short.MIN_VALUE && value <= Short.MAX_VALUE) {
          mv.visitIntInsn(Opcodes.SIPUSH, value);
      } else {
          mv.visitLdcInsn(new Integer(value));
      }
  }

  /**
   * Generates the instruction to push the given value on the stack.
   * 
   * @param value the value to be pushed on the stack.
   */
  public static void push(MethodVisitor mv, final long value) {
      if (value == 0L || value == 1L) {
          mv.visitInsn(Opcodes.LCONST_0 + (int) value);
      } else {
          mv.visitLdcInsn(new Long(value));
      }
  }

  /**
   * Generates the instruction to push the given value on the stack.
   * 
   * @param value the value to be pushed on the stack.
   */
  public static void push(MethodVisitor mv, final float value) {
      int bits = Float.floatToIntBits(value);
      if (bits == 0L || bits == 0x3f800000 || bits == 0x40000000) { // 0..2
          mv.visitInsn(Opcodes.FCONST_0 + (int) value);
      } else {
          mv.visitLdcInsn(new Float(value));
      }
  }

  /**
   * Generates the instruction to push the given value on the stack.
   * 
   * @param value the value to be pushed on the stack.
   */
  public static void push(MethodVisitor mv, final double value) {
      long bits = Double.doubleToLongBits(value);
      if (bits == 0L || bits == 0x3ff0000000000000L) { // +0.0d and 1.0d
          mv.visitInsn(Opcodes.DCONST_0 + (int) value);
      } else {
          mv.visitLdcInsn(new Double(value));
      }
  }

  /**
   * Generates the instruction to push the given value on the stack.
   * 
   * @param value the value to be pushed on the stack. May be <tt>null</tt>.
   */
  public static void push(MethodVisitor mv, final String value) {
      if (value == null) {
          mv.visitInsn(Opcodes.ACONST_NULL);
      } else {
          mv.visitLdcInsn(value);
      }
  }

  /**
   * Generates the instruction to push the given value on the stack.
   * 
   * @param value the value to be pushed on the stack.
   */
  public static void push(MethodVisitor mv, final Type value) {
      if (value == null) {
          mv.visitInsn(Opcodes.ACONST_NULL);
      } else {
          mv.visitLdcInsn(value);
      }
  }
  
  /**
   * Generates the instruction to push a local variable on the stack.
   * 
   * @param type the type of the local variable to be loaded.
   * @param index an index in the frame's local variables array.
   */
  public static void loadInsn(MethodVisitor mv, final Type type, final int index) {
      mv.visitVarInsn(type.getOpcode(Opcodes.ILOAD), index);
  }

  /**
   * Generates the instruction to store the top stack value in a local
   * variable.
   * 
   * @param type the type of the local variable to be stored.
   * @param index an index in the frame's local variables array.
   */
  public static void storeInsn(MethodVisitor mv, final Type type, final int index) {
      mv.visitVarInsn(type.getOpcode(Opcodes.ISTORE), index);
  }
  
  /**
   * Generates the instruction to load an element from an array.
   * 
   * @param type the type of the array element to be loaded.
   */
  public static void arrayLoad(MethodVisitor mv, final Type type) {
      mv.visitInsn(type.getOpcode(Opcodes.IALOAD));
  }

  /**
   * Generates the instruction to store an element in an array.
   * 
   * @param type the type of the array element to be stored.
   */
  public static void arrayStore(MethodVisitor mv, final Type type) {
      mv.visitInsn(type.getOpcode(Opcodes.IASTORE));
  }

}
