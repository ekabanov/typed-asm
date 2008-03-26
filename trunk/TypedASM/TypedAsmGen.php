<?
  $vnum = $argv[1];
  $vnumMax = $argv[2];
  $sd = $argv[3];
  $sdMax = $argv[4];
  

  function stackV($count) {
  	
	  for ($i = 0; $i < $count; $i++) {
	  	$result .= ", S{$i}";
	  }
	  return $result;
  }
  
  function varV($count, $index = -1, $value = "") {
	  for ($i = 0; $i < $count; $i++) {
	  	if ($index == $i) {
	  		$result .= ", {$value}";
	  	}
	  	else 
	  		$result .= ", V{$i}";
	  }
	  return $result;
  }  
?>

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class MethodBuilderS<?=$sd?>V<?=$vnum?><O<? echo stackV($sd); echo varV($vnum) ?>> implements Opcodes{
  private MethodVisitor mv;
  private ClassBuilder cb;
  
  public MethodBuilderS<?=$sd?>V<?=$vnum?>(ClassBuilder cb, MethodVisitor mv) {
  	this.cb = cb;
    this.mv = mv;
    mv.visitCode();    
  }
  
  public ClassBuilder endMethod() {
  	mv.visitMaxs(0, 0);
    mv.visitEnd();
    
  	return cb;
  }
  
  // CONSTRUCTION
  
  <? if ($sd < $sdMax) {
     $type = "MethodBuilderS".($sd + 1)."V".$vnum."<O".stackV($sd).", S".varV($vnum).">"; ?>
  public <S> <?=$type ?> newInstance
  (Class<S> type) {
    mv.visitTypeInsn(NEW, type.getName().replace('.', '/'));
    return new <?=$type ?>(cb, mv);
  }
  <?}?>
  
  <? if ($sd < $sdMax) {
     $type = "MethodBuilderS".($sd + 1)."V".$vnum."<O".stackV($sd).", S".varV($vnum).">"; ?>
  public <S> <?=$type ?> newArray(Class<S> type) {
    mv.visitTypeInsn(ANEWARRAY, type.getName().replace('.', '/'));
    return new <?=$type ?>(cb, mv);
  }
  <?}?>
  
  // STACK MANIPULATION
  
  <? if ($sd < $sdMax) {
     $type = "MethodBuilderS".($sd + 1)."V".$vnum."<O".stackV($sd).", S".varV($vnum).">"; ?>
  public <S> <?=$type ?> assumePush(Class<S> type) {
    return new <?=$type ?>(cb, mv);
  }
  <?}?>
  
  <? if ($sd > 0) { 
     $type = "MethodBuilderS".($sd - 1)."V".$vnum."<O".stackV($sd - 1).varV($vnum).">"; ?>
  public <?=$type ?> assumePop() {
    return new <?=$type ?>(cb, mv);
  }
  <? } ?>
  
  
  <? if ($sd < $sdMax) {
     $type = "MethodBuilderS".($sd + 1)."V".$vnum."<O".stackV($sd).", S".varV($vnum).">"; ?>
  public <S> <?=$type ?> push(S value) {
    mv.visitLdcInsn(value);
    return new <?=$type ?>(cb, mv);
  }
  <?}?>
  
  <? if ($sd >= 1) { 
     $type = "MethodBuilderS".($sd - 1)."V".$vnum."<O".stackV($sd - 1).varV($vnum).">"; ?>
  public <?=$type ?> pop() {
    mv.visitInsn(POP);
    return new <?=$type ?>(cb, mv);
  }
  <? } ?>
  
  <? if ($sd >= 1 && $sd < $sdMax) { 
  	 $type = "MethodBuilderS".($sd + 1)."V".$vnum."<O".stackV($sd).", S".($sd-1).varV($vnum).">"; ?>  
  public <?=$type ?>  dup() {
    mv.visitInsn(DUP);
    return new <?=$type ?>(cb, mv);
  }
  <?}?>
  
  // METHODS

  <? $type = "InvokeBuilderS".$sd."V".$vnum."<O".stackV($sd).varV($vnum).">"; ?>
  public <?=$type?> invokeBuilder() {
    return new <?=$type?>(new Type[] {}, cb, mv);
  }


  <? $type = "InvokeBuilderS".$sd."V".$vnum."<O".stackV($sd).varV($vnum).">"; ?>
  
  public static class <?=$type?> {
    private final ClassBuilder cb;
    private final MethodVisitor mv;
    private final Type[] args;
    
    <?="InvokeBuilderS".$sd."V".$vnum?>(Type[] args, ClassBuilder cb, MethodVisitor mv) {
      this.args = args;
      this.mv = mv;
      this.cb = cb;
    }
    
    <? if ($sd >= 1) { 
       $type = "MethodBuilderS".($sd)."V".$vnum."<O".stackV($sd-1).", S".varV($vnum).">"; ?>
    public <S> <?=$type?> invokeVirtual(Class < ? super S<?=($sd-1)?>> owner, String name, Class<S> type) {
      mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(owner), name, Type.getMethodDescriptor(Type.getType(type), args));
      return new <?=$type?>(cb, mv);
    }
    <? } ?>
    
    <? if ($sd >= 1) { 
       $type = "MethodBuilderS".($sd-1)."V".$vnum."<O".stackV($sd-1).varV($vnum).">"; ?>
    public <?=$type?> invokeVirtualVoid(Class < ? super S<?=($sd-1)?>> owner, String name) {
      mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(owner), name, Type.getMethodDescriptor(Type.VOID_TYPE, args));
      return new <?=$type?>(cb, mv);
    }
    <? } ?>    
    
    <? if ($sd >= 1) { 
       $type = "MethodBuilderS".($sd)."V".$vnum."<O".stackV($sd-1).", S".varV($vnum).">"; ?>
    public <S> <?=$type?> invokeSpecial(Class < ? super S<?=($sd-1)?>> owner, String name, Class<S> type) {
      mv.visitMethodInsn(INVOKESPECIAL, Type.getInternalName(owner), name, Type.getMethodDescriptor(Type.getType(type), args));
      return new <?=$type?>(cb, mv);
    }
    <? } ?>
    
    <? if ($sd >= 1) { 
       $type = "MethodBuilderS".($sd-1)."V".$vnum."<O".stackV($sd-1).varV($vnum).">"; ?>
    public <?=$type?> invokeSpecialVoid(Class < ? super S<?=($sd-1)?>> owner, String name) {
      mv.visitMethodInsn(INVOKESPECIAL, Type.getInternalName(owner), name, Type.getMethodDescriptor(Type.VOID_TYPE, args));
      return new <?=$type?>(cb, mv);
    }
    <? } ?>      
    
    <? if ($sd >= 1) { 
       $type = "MethodBuilderS".($sd)."V".$vnum."<O".stackV($sd-1).", S".varV($vnum).">"; ?>
    public <S> <?=$type?> invokeInterface(Class < ? super S<?=($sd-1)?>> owner, String name, Class<S> type) {
      mv.visitMethodInsn(INVOKEINTERFACE, Type.getInternalName(owner), name, Type.getMethodDescriptor(Type.getType(type), args));
      return new <?=$type?>(cb, mv);
    }
    <? } ?>
    
    <? if ($sd >= 1) { 
       $type = "MethodBuilderS".($sd-1)."V".$vnum."<O".stackV($sd-1).varV($vnum).">"; ?>
    public <?=$type?> invokeInterfaceVoid(Class < ? super S<?=($sd-1)?>> owner, String name) {
      mv.visitMethodInsn(INVOKEINTERFACE, Type.getInternalName(owner), name, Type.getMethodDescriptor(Type.VOID_TYPE, args));
      return new <?=$type?>(cb, mv);
    }
    <? } ?> 
    
    <? if ($sd < $sdMax) { 
       $type = "MethodBuilderS".($sd+1)."V".$vnum."<O".stackV($sd).", S".varV($vnum).">"; ?>
    public <S> <?=$type?> invokeStatic(Class owner, String name, Class<S> type) {
      mv.visitMethodInsn(INVOKESTATIC, Type.getInternalName(owner), name, Type.getMethodDescriptor(Type.getType(type), args));
      return new <?=$type?>(cb, mv);
    }
    <? } ?>
    

       <? $type = "MethodBuilderS".($sd)."V".$vnum."<O".stackV($sd).varV($vnum).">"; ?>
    public <?=$type?> invokeStaticVoid(Class owner, String name) {
      mv.visitMethodInsn(INVOKESTATIC, Type.getInternalName(owner), name, Type.getMethodDescriptor(Type.VOID_TYPE, args));
      return new <?=$type?>(cb, mv);
    }        

    
    <? if ($sd >= 1) {
    	 $type = "MethodBuilderS".($sd-1)."V".$vnum.".InvokeBuilderS".($sd-1)."V".$vnum."<O".stackV($sd - 1).varV($vnum).">"; ?>
    public <?=$type?> param(Class< ? super S<?=$sd-1?>> type) {
      Type[] newArgs = new Type[args.length + 1];
      newArgs[newArgs.length - 1] = Type.getType(type);
      System.arraycopy(args, 0, newArgs, 0, args.length);
      return new <?=$type?>(newArgs, cb, mv);
    }
    <? } ?>
  }
  
  
  // FIELDS
  
  <? if ($sd < $sdMax) { 
  	 $type = "MethodBuilderS".($sd + 1)."V".$vnum."<O".stackV($sd).", S".varV($vnum).">"; ?>  
  public <S> <?=$type ?> getStatic(Class owner, String name, Class<S> type) {
    mv.visitFieldInsn(GETSTATIC, owner.getName().replace('.', '/'), name, Type.getDescriptor(type));
    return new <?=$type ?>(cb, mv);
  }
  <?}?>
  
  // ARRAYS
  
  <? if ($sd >= 2) { 
  	 $type = "MethodBuilderS".($sd - 1)."V".$vnum."<O".stackV($sd - 2).", S".varV($vnum).">"; ?>
  public <S> <?=$type ?> arrayLoad(Class<S<?=($sd-2)?>> target, Class<S<?=($sd-1)?>> index, Class<S> result) {
    mv.visitInsn(AALOAD);
    
    return new <?=$type ?>(cb, mv);
  }
  <?}?>  
  
  // VARIABLES
  
  <? for ($i = 0; $i <= $vnum; $i++) { ?>
	  <? 
	  if ($i < $vnum) {
	     $type = "MethodBuilderS".($sd)."V".$vnum."<O".stackV($sd).varV($vnum, $i, V).">"; ?>
	  public <V> <?=$type ?> assumeVar<?=$i?>(Class<V> type) {
	    return new <?=$type ?>(cb, mv);
	  }
	  <?} else  if ($vnum < $vnumMax) {
	    $type = "MethodBuilderS".($sd)."V".($vnum+1)."<O".stackV($sd).varV($vnum).", V>"; ?>
	  public <V> <?=$type ?> assumeVar<?=$i?>(Class<V> type) {
	    return new <?=$type ?>(cb, mv);
	  }  		  	
	  <?}?>  	
  	
  	  	  	  	
	  <? if ($sd < $sdMax && $i < $vnum) {
	     $type = "MethodBuilderS".($sd + 1)."V".$vnum."<O".stackV($sd).", V".($i).varV($vnum).">"; ?>
	  public <?=$type ?> loadVar<?=$i?>(Class<<?="V".($i)?>> type) {
	    mv.visitVarInsn(ALOAD, <?=$i?>);
	    return new <?=$type ?>(cb, mv);
	  }
	  <?}?>
	  	  	  
	  <? if ($sd > 0 && $i < $vnum) { 
	     $type = "MethodBuilderS".($sd)."V".$vnum."<O".stackV($sd).varV($vnum, $i, "S".($sd-1)).">"; ?>
	  public <?=$type ?> storeVar<?=$i?>(Class<<?="S".($sd-1)?>> type) {
	    mv.visitVarInsn(ASTORE, <?=$i?>);
	    return new <?=$type ?>(cb, mv);
	  }
	  <?} else if ($sd > 0 && $vnum < $vnumMax) { 
	    $type = "MethodBuilderS".($sd)."V".($vnum+1)."<O".stackV($sd).varV($vnum).", S".($sd-1).">"; ?>
	  public <?=$type ?> storeVar<?=$i?>(Class<<?="S".($sd-1)?>> type) {
	    mv.visitVarInsn(ASTORE, <?=$i?>);
	    return new <?=$type ?>(cb, mv);
	  }  	
	  <?}?>
  <?}?>
  
  // FLOW CONTROL
  
  <? $type = "MethodBuilderS0V".$vnum."<O".varV($vnum).">"; ?>
  public <?=$type ?> returnVoid() {
    mv.visitInsn(RETURN);
    return new <?=$type ?>(cb, mv);
  }  
  
  <? if ($sd >= 1) { 
     $type = "MethodBuilderS0V".$vnum."<O".varV($vnum).">"; ?>
  public <?=$type ?> returnValue(Class<S<?=($sd-1)?>> type) {
    mv.visitInsn(ARETURN);
    return new <?=$type ?>(cb, mv);
  }
  <?}?>
  
  <? $type = "MethodBuilderS".($sd)."V".$vnum."<O".stackV($sd).varV($vnum).">"; ?>  
  public <?=$type ?> goTo(Label label) {
    mv.visitJumpInsn(GOTO, label);
    return new <?=$type ?>(cb, mv);
  }
  
  // SUPPORT
  
  <? $type = "MethodBuilderS0V0<O>"; ?>
  public <?=$type ?> reset() {
    return new <?=$type ?>(cb, mv);
  }
  
  <? $type = "MethodBuilderS0V".$vnum."<O".varV($vnum).">"; ?>
  public <?=$type ?> resetStack() {
    return new <?=$type ?>(cb, mv);
  }     
  
  <? $type = "MethodBuilderS".($sd)."V".$vnum."<O".stackV($sd).varV($vnum).">"; ?> 
  public <?=$type?> closure(Closure closure) {
  	closure.apply(this.reset());
  	return this;
  }
  
  <? $type = "MethodBuilderS".($sd)."V".$vnum."<O".stackV($sd).varV($vnum).">"; ?> 
  public <?=$type?> closure(ClosureS<?=$sd."V".$vnum ?> closure) {
  	closure.apply(this);
  	return this;
  }
  
  <? $type = "MethodBuilderS".($sd)."V".$vnum."<O".stackV($sd).varV($vnum).">"; ?> 
  interface ClosureS<?=$sd."V".$vnum ?> {
  	<?="<O".stackV($sd).varV($vnum).">"?> void apply(<?=$type?> mb);
  }
}