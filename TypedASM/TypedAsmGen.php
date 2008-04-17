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
  public <S> <?=$type ?> newInstance(Class<S> type) {
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
  public <S> <?=$type ?> pushNull(Class<S> type) {
    mv.visitLdcInsn(ACONST_NULL);
    return new <?=$type ?>(cb, mv);
  }
  <?}?>

  
  <? if ($sd < $sdMax) {
     $type = "MethodBuilderS".($sd + 1)."V".$vnum."<O".stackV($sd).", S".varV($vnum).">"; ?>
  public <S> <?=$type ?> push(S value) {
    mv.visitLdcInsn(value);
    return new <?=$type ?>(cb, mv);
  }
  <?}?>
  
  <? if ($sd < $sdMax - 1) {
     $type = "MethodBuilderS".($sd + 2)."V".$vnum."<O".stackV($sd).", S, S".varV($vnum).">"; ?>
  public <S> <?=$type ?> push2(S value) {
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
  
  <? if ($sd >= 2) { 
     $type = "MethodBuilderS".($sd - 2)."V".$vnum."<O".stackV($sd - 2).varV($vnum).">"; ?>
  public <?=$type ?> pop2() {
    mv.visitInsn(POP2);
    return new <?=$type ?>(cb, mv);
  }
  <? } ?>  

  <? if ($sd >= 2) { 
  	 $type = "MethodBuilderS".($sd)."V".$vnum."<O".stackV($sd-2).", S".($sd-1).", S".($sd-2).varV($vnum).">"; ?>  
  public <?=$type ?>  swap() {
    mv.visitInsn(SWAP);
    return new <?=$type ?>(cb, mv);
  }
  <?}?>

  
  <? if ($sd >= 1 && $sd < $sdMax) { 
  	 $type = "MethodBuilderS".($sd + 1)."V".$vnum."<O".stackV($sd).", S".($sd-1).varV($vnum).">"; ?>  
  public <?=$type ?>  dup() {
    mv.visitInsn(DUP);
    return new <?=$type ?>(cb, mv);
  }
  <?}?>
  
  <? if ($sd >= 2 && $sd < $sdMax) { 
  	 $type = "MethodBuilderS".($sd + 1)."V".$vnum."<O".stackV($sd-2).", S".($sd-1).", S".($sd-2).", S".($sd-1).varV($vnum).">"; ?>  
  public <?=$type ?>  dup_x1() {
    mv.visitInsn(DUP_X1);
    return new <?=$type ?>(cb, mv);
  }
  <?}?>
  
  <? if ($sd >= 3 && $sd < $sdMax) { 
  	 $type = "MethodBuilderS".($sd + 1)."V".$vnum."<O".stackV($sd-3).", S".($sd-1).", S".($sd-3).", S".($sd-2).", S".($sd-1).varV($vnum).">"; ?>  
  public <?=$type ?>  dup_x2() {
    mv.visitInsn(DUP_X2);
    return new <?=$type ?>(cb, mv);
  }
  <?}?>
  
  
  <? if ($sd >= 2 && $sd < $sdMax - 1) { 
  	 $type = "MethodBuilderS".($sd + 2)."V".$vnum."<O".stackV($sd).", S".($sd-2).", S".($sd-1).varV($vnum).">"; ?>  
  public <?=$type ?>  dup2() {
    mv.visitInsn(DUP2);
    return new <?=$type ?>(cb, mv);
  }
  <?}?>
  
  <? if ($sd >= 3 && $sd < $sdMax - 1) { 
  	 $type = "MethodBuilderS".($sd + 2)."V".$vnum."<O".stackV($sd-3).", S".($sd-2).", S".($sd-1).", S".($sd-3).", S".($sd-2).", S".($sd-1).varV($vnum).">"; ?>  
  public <?=$type ?>  dup2_x1() {
    mv.visitInsn(DUP2_X1);
    return new <?=$type ?>(cb, mv);
  }
  <?}?>
  
  <? if ($sd >= 4 && $sd < $sdMax - 1) { 
  	 $type = "MethodBuilderS".($sd + 2)."V".$vnum."<O".stackV($sd-4).", S".($sd-2).", S".($sd-1).", S".($sd-4).", S".($sd-3).", S".($sd-2).", S".($sd-1).varV($vnum).">"; ?>  
  public <?=$type ?>  dup2_x2() {
    mv.visitInsn(DUP2_X2);
    return new <?=$type ?>(cb, mv);
  }
  <?}?>  
  
  // CONVERSION
  
  <? if ($sd >= 2) { 
     $type = "MethodBuilderS".($sd - 1)."V".$vnum."<O".stackV($sd - 2).", S".varV($vnum).">"; ?>
  public <S> <?=$type ?> big2Small(Class<<?="S".($sd-1)?>> bigType1, Class<<?="S".($sd-2)?>> bigType2, Class<S> result) {
    GenUtil.cast(mv, Type.getType(bigType1), Type.getType(result));
    return new <?=$type ?>(cb, mv);
  }
  <? } ?>
  
  <? if ($sd >= 2) { 
     $type = "MethodBuilderS".($sd)."V".$vnum."<O".stackV($sd - 2).", S, S".varV($vnum).">"; ?>
  public <S> <?=$type ?> big2Big(Class<<?="S".($sd-1)?>> bigType1, Class<<?="S".($sd-2)?>> bigType2, Class<S> result1, Class<S> result2) {
    GenUtil.cast(mv, Type.getType(bigType1), Type.getType(result1));
    return new <?=$type ?>(cb, mv);
  }
  <? } ?>
  
  <? if ($sd >= 1 && $sd < $sdMax) { 
     $type = "MethodBuilderS".($sd+1)."V".$vnum."<O".stackV($sd - 1).", S, S".varV($vnum).">"; ?>
  public <S> <?=$type ?> small2Big(Class<<?="S".($sd-1)?>> smallType, Class<S> result1, Class<S> result2) {
    GenUtil.cast(mv, Type.getType(smallType), Type.getType(result1));
    return new <?=$type ?>(cb, mv);
  }
  <? } ?>
  
  <? if ($sd >= 1) { 
     $type = "MethodBuilderS".($sd)."V".$vnum."<O".stackV($sd - 1).", S".varV($vnum).">"; ?>
  public <S> <?=$type ?> small2Small(Class<<?="S".($sd-1)?>> smallType, Class<S> result) {
    GenUtil.cast(mv, Type.getType(smallType), Type.getType(result));
    return new <?=$type ?>(cb, mv);
  }
  <? } ?>    
  
  // METHODS

  <? $type = "InvokeBuilderS".$sd."V".$vnum."<O".stackV($sd).varV($vnum).">"; ?>
  public <?=$type?> invoke() {
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
    public <S> <?=$type?> virt(Class < ? super S<?=($sd-1)?>> owner, String name, Class<S> type) {
      mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(owner), name, Type.getMethodDescriptor(Type.getType(type), args));
      return new <?=$type?>(cb, mv);
    }
    <? } ?>
    
    <? if ($sd >= 1) { 
       $type = "MethodBuilderS".($sd-1)."V".$vnum."<O".stackV($sd-1).varV($vnum).">"; ?>
    public <?=$type?> virtVoid(Class < ? super S<?=($sd-1)?>> owner, String name) {
      mv.visitMethodInsn(INVOKEVIRTUAL, Type.getInternalName(owner), name, Type.getMethodDescriptor(Type.VOID_TYPE, args));
      return new <?=$type?>(cb, mv);
    }
    <? } ?>    
    
    <? if ($sd >= 1) { 
       $type = "MethodBuilderS".($sd)."V".$vnum."<O".stackV($sd-1).", S".varV($vnum).">"; ?>
    public <S> <?=$type?> spec(Class < ? super S<?=($sd-1)?>> owner, String name, Class<S> type) {
      mv.visitMethodInsn(INVOKESPECIAL, Type.getInternalName(owner), name, Type.getMethodDescriptor(Type.getType(type), args));
      return new <?=$type?>(cb, mv);
    }
    <? } ?>
    
    <? if ($sd >= 1) { 
       $type = "MethodBuilderS".($sd-1)."V".$vnum."<O".stackV($sd-1).varV($vnum).">"; ?>
    public <?=$type?> specVoid(Class < ? super S<?=($sd-1)?>> owner, String name) {
      mv.visitMethodInsn(INVOKESPECIAL, Type.getInternalName(owner), name, Type.getMethodDescriptor(Type.VOID_TYPE, args));
      return new <?=$type?>(cb, mv);
    }
    <? } ?>      
    
    <? if ($sd >= 1) { 
       $type = "MethodBuilderS".($sd)."V".$vnum."<O".stackV($sd-1).", S".varV($vnum).">"; ?>
    public <S> <?=$type?> iface(Class < ? super S<?=($sd-1)?>> owner, String name, Class<S> type) {
      mv.visitMethodInsn(INVOKEINTERFACE, Type.getInternalName(owner), name, Type.getMethodDescriptor(Type.getType(type), args));
      return new <?=$type?>(cb, mv);
    }
    <? } ?>
    
    <? if ($sd >= 1) { 
       $type = "MethodBuilderS".($sd-1)."V".$vnum."<O".stackV($sd-1).varV($vnum).">"; ?>
    public <?=$type?> ifaceVoid(Class < ? super S<?=($sd-1)?>> owner, String name) {
      mv.visitMethodInsn(INVOKEINTERFACE, Type.getInternalName(owner), name, Type.getMethodDescriptor(Type.VOID_TYPE, args));
      return new <?=$type?>(cb, mv);
    }
    <? } ?> 
    
    <? if ($sd < $sdMax) { 
       $type = "MethodBuilderS".($sd+1)."V".$vnum."<O".stackV($sd).", S".varV($vnum).">"; ?>
    public <S> <?=$type?> stat(Class owner, String name, Class<S> type) {
      mv.visitMethodInsn(INVOKESTATIC, Type.getInternalName(owner), name, Type.getMethodDescriptor(Type.getType(type), args));
      return new <?=$type?>(cb, mv);
    }
    <? } ?>
    

       <? $type = "MethodBuilderS".($sd)."V".$vnum."<O".stackV($sd).varV($vnum).">"; ?>
    public <?=$type?> statVoid(Class owner, String name) {
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
    GenUtil.arrayLoad(mv, Type.getType(result));
    
    return new <?=$type ?>(cb, mv);
  }
  <?}?>
  
  <? if ($sd >= 2) { 
  	 $type = "MethodBuilderS".($sd)."V".$vnum."<O".stackV($sd - 2).", S".", S".varV($vnum).">"; ?>
  public <S> <?=$type ?> arrayLoadBig(Class<S<?=($sd-2)?>> target, Class<S<?=($sd-1)?>> index, Class<S> result1, Class<S> result2) {
    GenUtil.arrayLoad(mv, Type.getType(result1));
    
    return new <?=$type ?>(cb, mv);
  }
  <?}?>  
  
  <? if ($sd >= 3) { 
  	 $type = "MethodBuilderS".($sd - 3)."V".$vnum."<O".stackV($sd - 3).varV($vnum).">"; ?>
  public <?=$type ?> arrayStore(Class<S<?=($sd-3)?>> target, Class<S<?=($sd-2)?>> index, Class<S<?=($sd-1)?>> type) {
    GenUtil.arrayStore(mv, Type.getType(result));
    
    return new <?=$type ?>(cb, mv);
  }
  <?}?>  
  
  <? if ($sd >= 4) { 
  	 $type = "MethodBuilderS".($sd - 4)."V".$vnum."<O".stackV($sd - 4).varV($vnum).">"; ?>
  public <?=$type ?> arrayStore(Class<S<?=($sd-4)?>> target, Class<S<?=($sd-3)?>> index, Class<S<?=($sd-2)?>> bigType1, Class<S<?=($sd-2)?>> bigType2) {
    GenUtil.arrayStore(mv, Type.getType(result));
    
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