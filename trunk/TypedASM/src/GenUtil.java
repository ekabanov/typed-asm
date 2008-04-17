/***
 * ASM: a very small and fast Java bytecode manipulation framework
 * Copyright (c) 2000-2005 INRIA, France Telecom
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the copyright holders nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */


import java.util.Arrays;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.LocalVariablesSorter;
import org.objectweb.asm.commons.Method;
import org.objectweb.asm.commons.TableSwitchGenerator;

public class GenUtil {
    private MethodVisitor mv;
    
    public GenUtil(MethodVisitor mv) {
      this.mv = mv;
    }

    public MethodVisitor getMethodVisitor() {
      return mv;
    }      

    private final static Type BYTE_TYPE = Type.getObjectType("java/lang/Byte");

    private final static Type BOOLEAN_TYPE = Type.getObjectType("java/lang/Boolean");

    private final static Type SHORT_TYPE = Type.getObjectType("java/lang/Short");

    private final static Type CHARACTER_TYPE = Type.getObjectType("java/lang/Character");

    private final static Type INTEGER_TYPE = Type.getObjectType("java/lang/Integer");

    private final static Type FLOAT_TYPE = Type.getObjectType("java/lang/Float");

    private final static Type LONG_TYPE = Type.getObjectType("java/lang/Long");

    private final static Type DOUBLE_TYPE = Type.getObjectType("java/lang/Double");

    private final static Type NUMBER_TYPE = Type.getObjectType("java/lang/Number");

    private final static Type OBJECT_TYPE = Type.getObjectType("java/lang/Object");

    private final static Method BOOLEAN_VALUE = Method.getMethod("boolean booleanValue()");

    private final static Method CHAR_VALUE = Method.getMethod("char charValue()");

    private final static Method INT_VALUE = Method.getMethod("int intValue()");

    private final static Method FLOAT_VALUE = Method.getMethod("float floatValue()");

    private final static Method LONG_VALUE = Method.getMethod("long longValue()");

    private final static Method DOUBLE_VALUE = Method.getMethod("double doubleValue()");

    /**
     * Constant for the {@link #math math} method.
     */
    public final static int ADD = Opcodes.IADD;

    /**
     * Constant for the {@link #math math} method.
     */
    public final static int SUB = Opcodes.ISUB;

    /**
     * Constant for the {@link #math math} method.
     */
    public final static int MUL = Opcodes.IMUL;

    /**
     * Constant for the {@link #math math} method.
     */
    public final static int DIV = Opcodes.IDIV;

    /**
     * Constant for the {@link #math math} method.
     */
    public final static int REM = Opcodes.IREM;

    /**
     * Constant for the {@link #math math} method.
     */
    public final static int NEG = Opcodes.INEG;

    /**
     * Constant for the {@link #math math} method.
     */
    public final static int SHL = Opcodes.ISHL;

    /**
     * Constant for the {@link #math math} method.
     */
    public final static int SHR = Opcodes.ISHR;

    /**
     * Constant for the {@link #math math} method.
     */
    public final static int USHR = Opcodes.IUSHR;

    /**
     * Constant for the {@link #math math} method.
     */
    public final static int AND = Opcodes.IAND;

    /**
     * Constant for the {@link #math math} method.
     */
    public final static int OR = Opcodes.IOR;

    /**
     * Constant for the {@link #math math} method.
     */
    public final static int XOR = Opcodes.IXOR;

    /**
     * Constant for the {@link #ifCmp ifCmp} method.
     */
    public final static int EQ = Opcodes.IFEQ;

    /**
     * Constant for the {@link #ifCmp ifCmp} method.
     */
    public final static int NE = Opcodes.IFNE;

    /**
     * Constant for the {@link #ifCmp ifCmp} method.
     */
    public final static int LT = Opcodes.IFLT;

    /**
     * Constant for the {@link #ifCmp ifCmp} method.
     */
    public final static int GE = Opcodes.IFGE;

    /**
     * Constant for the {@link #ifCmp ifCmp} method.
     */
    public final static int GT = Opcodes.IFGT;

    /**
     * Constant for the {@link #ifCmp ifCmp} method.
     */
    public final static int LE = Opcodes.IFLE;



    /**
     * Returns the internal names of the given types.
     * 
     * @param types a set of types.
     * @return the internal names of the given types.
     */
    private static String[] getInternalNames(final Type[] types) {
        if (types == null) {
            return null;
        }
        String[] names = new String[types.length];
        for (int i = 0; i < names.length; ++i) {
            names[i] = types[i].getInternalName();
        }
        return names;
    }

    // ------------------------------------------------------------------------
    // Instructions to push constants on the stack
    // ------------------------------------------------------------------------

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

    // ------------------------------------------------------------------------
    // Instructions to manage the stack
    // ------------------------------------------------------------------------

    /**
     * Generates a POP instruction.
     */
    public static void pop(MethodVisitor mv) {
        mv.visitInsn(Opcodes.POP);
    }

    /**
     * Generates a POP2 instruction.
     */
    public static void pop2(MethodVisitor mv) {
        mv.visitInsn(Opcodes.POP2);
    }

    /**
     * Generates a DUP instruction.
     */
    public static void dup(MethodVisitor mv) {
        mv.visitInsn(Opcodes.DUP);
    }

    /**
     * Generates a DUP2 instruction.
     */
    public static void dup2(MethodVisitor mv) {
        mv.visitInsn(Opcodes.DUP2);
    }

    /**
     * Generates a DUP_X1 instruction.
     */
    public static void dupX1(MethodVisitor mv) {
        mv.visitInsn(Opcodes.DUP_X1);
    }

    /**
     * Generates a DUP_X2 instruction.
     */
    public static void dupX2(MethodVisitor mv) {
        mv.visitInsn(Opcodes.DUP_X2);
    }

    /**
     * Generates a DUP2_X1 instruction.
     */
    public static void dup2X1(MethodVisitor mv) {
        mv.visitInsn(Opcodes.DUP2_X1);
    }

    /**
     * Generates a DUP2_X2 instruction.
     */
    public static void dup2X2(MethodVisitor mv) {
        mv.visitInsn(Opcodes.DUP2_X2);
    }

    /**
     * Generates a SWAP instruction.
     */
    public static void swap(MethodVisitor mv) {
        mv.visitInsn(Opcodes.SWAP);
    }

    /**
     * Generates the instructions to swap the top two stack values.
     * 
     * @param prev type of the top - 1 stack value.
     * @param type type of the top stack value.
     */
    public void swap(MethodVisitor mv, final Type prev, final Type type) {
        if (type.getSize() == 1) {
            if (prev.getSize() == 1) {
                swap(mv); // same as dupX1(), pop();
            } else {
                dupX2(mv);
                pop(mv);
            }
        } else {
            if (prev.getSize() == 1) {
                dup2X1(mv);
                pop2(mv);
            } else {
                dup2X2(mv);
                pop2(mv);
            }
        }
    }

    // ------------------------------------------------------------------------
    // Instructions to do mathematical and logical operations
    // ------------------------------------------------------------------------

    /**
     * Generates the instruction to do the specified mathematical or logical
     * operation.
     * 
     * @param op a mathematical or logical operation. Must be one of ADD, SUB,
     *        MUL, DIV, REM, NEG, SHL, SHR, USHR, AND, OR, XOR.
     * @param type the type of the operand(s) for this operation.
     */
    public void math(final int op, final Type type) {
        mv.visitInsn(type.getOpcode(op));
    }

    /**
     * Generates the instructions to compute the bitwise negation of the top
     * stack value.
     */
    public void not() {
        mv.visitInsn(Opcodes.ICONST_1);
        mv.visitInsn(Opcodes.IXOR);
    }

    /**
     * Generates the instruction to increment the given local variable.
     * 
     * @param local the local variable to be incremented.
     * @param amount the amount by which the local variable must be incremented.
     */
    public void iinc(final int local, final int amount) {
        mv.visitIincInsn(local, amount);
    }

    /**
     * Generates the instructions to cast a numerical value from one type to
     * another.
     * 
     * @param from the type of the top stack value
     * @param to the type into which this value must be cast.
     */
    public static void cast(MethodVisitor mv, final Type from, final Type to) {
        if (from != to) {
            if (from == Type.DOUBLE_TYPE) {
                if (to == Type.FLOAT_TYPE) {
                    mv.visitInsn(Opcodes.D2F);
                } else if (to == Type.LONG_TYPE) {
                    mv.visitInsn(Opcodes.D2L);
                } else {
                    mv.visitInsn(Opcodes.D2I);
                    cast(mv, Type.INT_TYPE, to);
                }
            } else if (from == Type.FLOAT_TYPE) {
                if (to == Type.DOUBLE_TYPE) {
                    mv.visitInsn(Opcodes.F2D);
                } else if (to == Type.LONG_TYPE) {
                    mv.visitInsn(Opcodes.F2L);
                } else {
                    mv.visitInsn(Opcodes.F2I);
                    cast(mv, Type.INT_TYPE, to);
                }
            } else if (from == Type.LONG_TYPE) {
                if (to == Type.DOUBLE_TYPE) {
                    mv.visitInsn(Opcodes.L2D);
                } else if (to == Type.FLOAT_TYPE) {
                    mv.visitInsn(Opcodes.L2F);
                } else {
                    mv.visitInsn(Opcodes.L2I);
                    cast(mv, Type.INT_TYPE, to);
                }
            } else {
                if (to == Type.BYTE_TYPE) {
                    mv.visitInsn(Opcodes.I2B);
                } else if (to == Type.CHAR_TYPE) {
                    mv.visitInsn(Opcodes.I2C);
                } else if (to == Type.DOUBLE_TYPE) {
                    mv.visitInsn(Opcodes.I2D);
                } else if (to == Type.FLOAT_TYPE) {
                    mv.visitInsn(Opcodes.I2F);
                } else if (to == Type.LONG_TYPE) {
                    mv.visitInsn(Opcodes.I2L);
                } else if (to == Type.SHORT_TYPE) {
                    mv.visitInsn(Opcodes.I2S);
                }
            }
        }
    }

    // ------------------------------------------------------------------------
    // Instructions to do boxing and unboxing operations
    // ------------------------------------------------------------------------

    /**
     * Generates the instructions to box the top stack value. This value is
     * replaced by its boxed equivalent on top of the stack.
     * 
     * @param type the type of the top stack value.
     */
    public static void box(MethodVisitor mv, final Type type) {
        if (type.getSort() == Type.OBJECT || type.getSort() == Type.ARRAY) {
            return;
        }
        if (type == Type.VOID_TYPE) {
            push(mv, (String) null);
        } else {
            Type boxed = type;
            switch (type.getSort()) {
                case Type.BYTE:
                    boxed = BYTE_TYPE;
                    break;
                case Type.BOOLEAN:
                    boxed = BOOLEAN_TYPE;
                    break;
                case Type.SHORT:
                    boxed = SHORT_TYPE;
                    break;
                case Type.CHAR:
                    boxed = CHARACTER_TYPE;
                    break;
                case Type.INT:
                    boxed = INTEGER_TYPE;
                    break;
                case Type.FLOAT:
                    boxed = FLOAT_TYPE;
                    break;
                case Type.LONG:
                    boxed = LONG_TYPE;
                    break;
                case Type.DOUBLE:
                    boxed = DOUBLE_TYPE;
                    break;
            }
            newInstance(mv, boxed);
            if (type.getSize() == 2) {
                // Pp -> Ppo -> oPpo -> ooPpo -> ooPp -> o
                dupX2(mv);
                dupX2(mv);
                pop(mv);
            } else {
                // p -> po -> opo -> oop -> o
                dupX1(mv);
                swap(mv);
            }
            invokeConstructor(mv, boxed, new Method("<init>",
                    Type.VOID_TYPE,
                    new Type[] { type }));
        }
    }

    /**
     * Generates the instructions to unbox the top stack value. This value is
     * replaced by its unboxed equivalent on top of the stack.
     * 
     * @param type the type of the top stack value.
     */
    public void unbox(final Type type) {
        Type t = NUMBER_TYPE;
        Method sig = null;
        switch (type.getSort()) {
            case Type.VOID:
                return;
            case Type.CHAR:
                t = CHARACTER_TYPE;
                sig = CHAR_VALUE;
                break;
            case Type.BOOLEAN:
                t = BOOLEAN_TYPE;
                sig = BOOLEAN_VALUE;
                break;
            case Type.DOUBLE:
                sig = DOUBLE_VALUE;
                break;
            case Type.FLOAT:
                sig = FLOAT_VALUE;
                break;
            case Type.LONG:
                sig = LONG_VALUE;
                break;
            case Type.INT:
            case Type.SHORT:
            case Type.BYTE:
                sig = INT_VALUE;
        }
        if (sig == null) {
            checkCast(mv, type);
        } else {
            checkCast(mv, t);
            invokeVirtual(mv, t, sig);
        }
    }

    // ------------------------------------------------------------------------
    // Instructions to jump to other instructions
    // ------------------------------------------------------------------------

    /**
     * Creates a new {@link Label}.
     * 
     * @return a new {@link Label}.
     */
    public Label newLabel() {
        return new Label();
    }

    /**
     * Marks the current code position with the given label.
     * 
     * @param label a label.
     */
    public void mark(final Label label) {
        mv.visitLabel(label);
    }

    /**
     * Marks the current code position with a new label.
     * 
     * @return the label that was created to mark the current code position.
     */
    public Label mark() {
        Label label = new Label();
        mv.visitLabel(label);
        return label;
    }

    /**
     * Generates the instructions to jump to a label based on the comparison of
     * the top two stack values.
     * 
     * @param type the type of the top two stack values.
     * @param mode how these values must be compared. One of EQ, NE, LT, GE, GT,
     *        LE.
     * @param label where to jump if the comparison result is <tt>true</tt>.
     */
    public void ifCmp(final Type type, final int mode, final Label label) {
        int intOp = -1;
        switch (type.getSort()) {
            case Type.LONG:
                mv.visitInsn(Opcodes.LCMP);
                break;
            case Type.DOUBLE:
                mv.visitInsn(Opcodes.DCMPG);
                break;
            case Type.FLOAT:
                mv.visitInsn(Opcodes.FCMPG);
                break;
            case Type.ARRAY:
            case Type.OBJECT:
                switch (mode) {
                    case EQ:
                        mv.visitJumpInsn(Opcodes.IF_ACMPEQ, label);
                        return;
                    case NE:
                        mv.visitJumpInsn(Opcodes.IF_ACMPNE, label);
                        return;
                }
                throw new IllegalArgumentException("Bad comparison for type "
                        + type);
            default:
                switch (mode) {
                    case EQ:
                        intOp = Opcodes.IF_ICMPEQ;
                        break;
                    case NE:
                        intOp = Opcodes.IF_ICMPNE;
                        break;
                    case GE:
                        intOp = Opcodes.IF_ICMPGE;
                        break;
                    case LT:
                        intOp = Opcodes.IF_ICMPLT;
                        break;
                    case LE:
                        intOp = Opcodes.IF_ICMPLE;
                        break;
                    case GT:
                        intOp = Opcodes.IF_ICMPGT;
                        break;
                }
                mv.visitJumpInsn(intOp, label);
                return;
        }
        int jumpMode = mode;
        switch (mode) {
            case GE:
                jumpMode = LT;
                break;
            case LE:
                jumpMode = GT;
                break;
        }
        mv.visitJumpInsn(jumpMode, label);
    }

    /**
     * Generates the instructions to jump to a label based on the comparison of
     * the top two integer stack values.
     * 
     * @param mode how these values must be compared. One of EQ, NE, LT, GE, GT,
     *        LE.
     * @param label where to jump if the comparison result is <tt>true</tt>.
     */
    public void ifICmp(final int mode, final Label label) {
        ifCmp(Type.INT_TYPE, mode, label);
    }

    /**
     * Generates the instructions to jump to a label based on the comparison of
     * the top integer stack value with zero.
     * 
     * @param mode how these values must be compared. One of EQ, NE, LT, GE, GT,
     *        LE.
     * @param label where to jump if the comparison result is <tt>true</tt>.
     */
    public void ifZCmp(final int mode, final Label label) {
        mv.visitJumpInsn(mode, label);
    }

    /**
     * Generates the instruction to jump to the given label if the top stack
     * value is null.
     * 
     * @param label where to jump if the condition is <tt>true</tt>.
     */
    public void ifNull(final Label label) {
        mv.visitJumpInsn(Opcodes.IFNULL, label);
    }

    /**
     * Generates the instruction to jump to the given label if the top stack
     * value is not null.
     * 
     * @param label where to jump if the condition is <tt>true</tt>.
     */
    public void ifNonNull(final Label label) {
        mv.visitJumpInsn(Opcodes.IFNONNULL, label);
    }

    /**
     * Generates the instruction to jump to the given label.
     * 
     * @param label where to jump if the condition is <tt>true</tt>.
     */
    public void goTo(final Label label) {
        mv.visitJumpInsn(Opcodes.GOTO, label);
    }

    /**
     * Generates a RET instruction.
     * 
     * @param local a local variable identifier, as returned by
     *        {@link LocalVariablesSorter#newLocal(Type) newLocal()}.
     */
    public void ret(final int local) {
        mv.visitVarInsn(Opcodes.RET, local);
    }

    /**
     * Generates the instructions for a switch statement.
     * 
     * @param keys the switch case keys.
     * @param generator a generator to generate the code for the switch cases.
     */
    public void tableSwitch(
        final int[] keys,
        final TableSwitchGenerator generator)
    {
        float density;
        if (keys.length == 0) {
            density = 0;
        } else {
            density = (float) keys.length
                    / (keys[keys.length - 1] - keys[0] + 1);
        }
        tableSwitch(keys, generator, density >= 0.5f);
    }

    /**
     * Generates the instructions for a switch statement.
     * 
     * @param keys the switch case keys.
     * @param generator a generator to generate the code for the switch cases.
     * @param useTable <tt>true</tt> to use a TABLESWITCH instruction, or
     *        <tt>false</tt> to use a LOOKUPSWITCH instruction.
     */
    public void tableSwitch(
        final int[] keys,
        final TableSwitchGenerator generator,
        final boolean useTable)
    {
        for (int i = 1; i < keys.length; ++i) {
            if (keys[i] < keys[i - 1]) {
                throw new IllegalArgumentException("keys must be sorted ascending");
            }
        }
        Label def = newLabel();
        Label end = newLabel();
        if (keys.length > 0) {
            int len = keys.length;
            int min = keys[0];
            int max = keys[len - 1];
            int range = max - min + 1;
            if (useTable) {
                Label[] labels = new Label[range];
                Arrays.fill(labels, def);
                for (int i = 0; i < len; ++i) {
                    labels[keys[i] - min] = newLabel();
                }
                mv.visitTableSwitchInsn(min, max, def, labels);
                for (int i = 0; i < range; ++i) {
                    Label label = labels[i];
                    if (label != def) {
                        mark(label);
                        generator.generateCase(i + min, end);
                    }
                }
            } else {
                Label[] labels = new Label[len];
                for (int i = 0; i < len; ++i) {
                    labels[i] = newLabel();
                }
                mv.visitLookupSwitchInsn(def, keys, labels);
                for (int i = 0; i < len; ++i) {
                    mark(labels[i]);
                    generator.generateCase(keys[i], end);
                }
            }
        }
        mark(def);
        generator.generateDefault();
        mark(end);
    }

    // ------------------------------------------------------------------------
    // Instructions to load and store fields
    // ------------------------------------------------------------------------

    /**
     * Generates a get field or set field instruction.
     * 
     * @param opcode the instruction's opcode.
     * @param ownerType the class in which the field is defined.
     * @param name the name of the field.
     * @param fieldType the type of the field.
     */
    private void fieldInsn(
        final int opcode,
        final Type ownerType,
        final String name,
        final Type fieldType)
    {
        mv.visitFieldInsn(opcode,
                ownerType.getInternalName(),
                name,
                fieldType.getDescriptor());
    }

    /**
     * Generates the instruction to push the value of a static field on the
     * stack.
     * 
     * @param owner the class in which the field is defined.
     * @param name the name of the field.
     * @param type the type of the field.
     */
    public void getStatic(final Type owner, final String name, final Type type)
    {
        fieldInsn(Opcodes.GETSTATIC, owner, name, type);
    }

    /**
     * Generates the instruction to store the top stack value in a static field.
     * 
     * @param owner the class in which the field is defined.
     * @param name the name of the field.
     * @param type the type of the field.
     */
    public void putStatic(final Type owner, final String name, final Type type)
    {
        fieldInsn(Opcodes.PUTSTATIC, owner, name, type);
    }

    /**
     * Generates the instruction to push the value of a non static field on the
     * stack.
     * 
     * @param owner the class in which the field is defined.
     * @param name the name of the field.
     * @param type the type of the field.
     */
    public void getField(final Type owner, final String name, final Type type) {
        fieldInsn(Opcodes.GETFIELD, owner, name, type);
    }

    /**
     * Generates the instruction to store the top stack value in a non static
     * field.
     * 
     * @param owner the class in which the field is defined.
     * @param name the name of the field.
     * @param type the type of the field.
     */
    public void putField(final Type owner, final String name, final Type type) {
        fieldInsn(Opcodes.PUTFIELD, owner, name, type);
    }

    // ------------------------------------------------------------------------
    // Instructions to invoke methods
    // ------------------------------------------------------------------------

    /**
     * Generates an invoke method instruction.
     * 
     * @param opcode the instruction's opcode.
     * @param type the class in which the method is defined.
     * @param method the method to be invoked.
     */
    private static void invokeInsn(
        MethodVisitor mv,
        final int opcode,
        final Type type,
        final Method method)
    {
        String owner = type.getSort() == Type.ARRAY
                ? type.getDescriptor()
                : type.getInternalName();
        mv.visitMethodInsn(opcode,
                owner,
                method.getName(),
                method.getDescriptor());
    }

    /**
     * Generates the instruction to invoke a normal method.
     * 
     * @param owner the class in which the method is defined.
     * @param method the method to be invoked.
     */
    public static void invokeVirtual(MethodVisitor mv, final Type owner, final Method method) {
        invokeInsn(mv, Opcodes.INVOKEVIRTUAL, owner, method);
    }

    /**
     * Generates the instruction to invoke a constructor.
     * 
     * @param type the class in which the constructor is defined.
     * @param method the constructor to be invoked.
     */
    public static void invokeConstructor(MethodVisitor mv, final Type type, final Method method) {
        invokeInsn(mv, Opcodes.INVOKESPECIAL, type, method);
    }

    /**
     * Generates the instruction to invoke a static method.
     * 
     * @param owner the class in which the method is defined.
     * @param method the method to be invoked.
     */
    public static void invokeStatic(MethodVisitor mv, final Type owner, final Method method) {
        invokeInsn(mv, Opcodes.INVOKESTATIC, owner, method);
    }

    /**
     * Generates the instruction to invoke an interface method.
     * 
     * @param owner the class in which the method is defined.
     * @param method the method to be invoked.
     */
    public static void invokeInterface(MethodVisitor mv, final Type owner, final Method method) {
        invokeInsn(mv, Opcodes.INVOKEINTERFACE, owner, method);
    }

    // ------------------------------------------------------------------------
    // Instructions to create objects and arrays
    // ------------------------------------------------------------------------

    /**
     * Generates a type dependent instruction.
     * 
     * @param opcode the instruction's opcode.
     * @param type the instruction's operand.
     */
    private static void typeInsn(MethodVisitor mv, final int opcode, final Type type) {
        String desc;
        if (type.getSort() == Type.ARRAY) {
            desc = type.getDescriptor();
        } else {
            desc = type.getInternalName();
        }
        mv.visitTypeInsn(opcode, desc);
    }

    /**
     * Generates the instruction to create a new object.
     * 
     * @param type the class of the object to be created.
     */
    public static void newInstance(MethodVisitor mv, final Type type) {
        typeInsn(mv, Opcodes.NEW, type);
    }

    /**
     * Generates the instruction to create a new array.
     * 
     * @param type the type of the array elements.
     */
    public void newArray(final Type type) {
        int typ;
        switch (type.getSort()) {
            case Type.BOOLEAN:
                typ = Opcodes.T_BOOLEAN;
                break;
            case Type.CHAR:
                typ = Opcodes.T_CHAR;
                break;
            case Type.BYTE:
                typ = Opcodes.T_BYTE;
                break;
            case Type.SHORT:
                typ = Opcodes.T_SHORT;
                break;
            case Type.INT:
                typ = Opcodes.T_INT;
                break;
            case Type.FLOAT:
                typ = Opcodes.T_FLOAT;
                break;
            case Type.LONG:
                typ = Opcodes.T_LONG;
                break;
            case Type.DOUBLE:
                typ = Opcodes.T_DOUBLE;
                break;
            default:
                typeInsn(mv, Opcodes.ANEWARRAY, type);
                return;
        }
        mv.visitIntInsn(Opcodes.NEWARRAY, typ);
    }

    // ------------------------------------------------------------------------
    // Miscelaneous instructions
    // ------------------------------------------------------------------------

    /**
     * Generates the instruction to compute the length of an array.
     */
    public void arrayLength() {
        mv.visitInsn(Opcodes.ARRAYLENGTH);
    }

    /**
     * Generates the instruction to throw an exception.
     */
    public void throwException() {
        mv.visitInsn(Opcodes.ATHROW);
    }

//    /**
//     * Generates the instructions to create and throw an exception. The
//     * exception class must have a constructor with a single String argument.
//     * 
//     * @param type the class of the exception to be thrown.
//     * @param msg the detailed message of the exception.
//     */
//    public void throwException(final Type type, final String msg) {
//        newInstance(type);
//        dup();
//        push(msg);
//        invokeConstructor(type, Method.getMethod("void <init> (String)"));
//        throwException();
//    }

    /**
     * Generates the instruction to check that the top stack value is of the
     * given type.
     * 
     * @param type a class or interface type.
     */
    public static void checkCast(MethodVisitor mv, final Type type) {
        if (!type.equals(OBJECT_TYPE)) {
            typeInsn(mv, Opcodes.CHECKCAST, type);
        }
    }

    /**
     * Generates the instruction to test if the top stack value is of the given
     * type.
     * 
     * @param type a class or interface type.
     */
    public static void instanceOf(MethodVisitor mv, final Type type) {
        typeInsn(mv, Opcodes.INSTANCEOF, type);
    }

    /**
     * Generates the instruction to get the monitor of the top stack value.
     */
    public void monitorEnter() {
        mv.visitInsn(Opcodes.MONITORENTER);
    }

    /**
     * Generates the instruction to release the monitor of the top stack value.
     */
    public void monitorExit() {
        mv.visitInsn(Opcodes.MONITOREXIT);
    }

    // ------------------------------------------------------------------------
    // Non instructions
    // ------------------------------------------------------------------------


    /**
     * Marks the start of an exception handler.
     * 
     * @param start beginning of the exception handler's scope (inclusive).
     * @param end end of the exception handler's scope (exclusive).
     * @param exception internal name of the type of exceptions handled by the
     *        handler.
     */
    public void catchException(
        final Label start,
        final Label end,
        final Type exception)
    {
        mv.visitTryCatchBlock(start, end, mark(), exception.getInternalName());
    }

    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
      return mv.visitAnnotation(desc, visible);
    }

    public AnnotationVisitor visitAnnotationDefault() {
      return mv.visitAnnotationDefault();
    }

    public void visitAttribute(Attribute attr) {
      mv.visitAttribute(attr);
    }

    public void visitCode() {
      mv.visitCode();
    }

    public void visitEnd() {
      mv.visitEnd();
    }

    public void visitFieldInsn(int opcode, String owner, String name,
        String desc) {
      mv.visitFieldInsn(opcode, owner, name, desc);
    }

    public void visitFrame(int type, int local, Object[] local2, int stack,
        Object[] stack2) {
      mv.visitFrame(type, local, local2, stack, stack2);
    }

    public void visitIincInsn(int var, int increment) {
      mv.visitIincInsn(var, increment);
    }

    public void visitInsn(int opcode) {
      mv.visitInsn(opcode);
    }

    public void visitIntInsn(int opcode, int operand) {
      mv.visitIntInsn(opcode, operand);
    }

    public void visitJumpInsn(int opcode, Label label) {
      mv.visitJumpInsn(opcode, label);
    }

    public void visitLabel(Label label) {
      mv.visitLabel(label);
    }

    public void visitLdcInsn(Object cst) {
      mv.visitLdcInsn(cst);
    }

    public void visitLineNumber(int line, Label start) {
      mv.visitLineNumber(line, start);
    }

    public void visitLocalVariable(String name, String desc, String signature,
        Label start, Label end, int index) {
      mv.visitLocalVariable(name, desc, signature, start, end, index);
    }

    public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
      mv.visitLookupSwitchInsn(dflt, keys, labels);
    }

    public void visitMaxs(int maxStack, int maxLocals) {
      mv.visitMaxs(maxStack, maxLocals);
    }

    public void visitMethodInsn(int opcode, String owner, String name,
        String desc) {
      mv.visitMethodInsn(opcode, owner, name, desc);
    }

    public void visitMultiANewArrayInsn(String desc, int dims) {
      mv.visitMultiANewArrayInsn(desc, dims);
    }

    public AnnotationVisitor visitParameterAnnotation(int parameter,
        String desc, boolean visible) {
      return mv.visitParameterAnnotation(parameter, desc, visible);
    }

    public void visitTableSwitchInsn(int min, int max, Label dflt,
        Label[] labels) {
      mv.visitTableSwitchInsn(min, max, dflt, labels);
    }

    public void visitTryCatchBlock(Label start, Label end, Label handler,
        String type) {
      mv.visitTryCatchBlock(start, end, handler, type);
    }

    public void visitTypeInsn(int opcode, String desc) {
      mv.visitTypeInsn(opcode, desc);
    }

    public void visitVarInsn(int opcode, int var) {
      mv.visitVarInsn(opcode, var);
    }
}
