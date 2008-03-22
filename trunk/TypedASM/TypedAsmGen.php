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
  
  <? if ($sd >= 1) { 
  	 $type = "MethodBuilderS".($sd - 1)."V".$vnum."<O".stackV($sd - 1).varV($vnum).">"; ?>
  public <S> <?=$type ?> invokeVirtualVoid(int kind, Class< ? super S<?=($sd-1)?>> owner, String name) {
    mv.visitMethodInsn(
        kind, 
        owner.getName().replace('.', '/'), 
        name, 
        Type.getMethodDescriptor(Type.VOID_TYPE, new Type[] {}));
    
    return new <?=$type ?>(cb, mv);
  }
  <?}?>    
  
  <? if ($sd >= 1) { 
  	 $type = "MethodBuilderS".$sd."V".$vnum."<O".stackV($sd - 1).", S".varV($vnum).">"; ?>
  public <S> <?=$type ?> invokeVirtual(int kind, Class< ? super S<?=($sd-1)?>> owner, String name, Class<S> result) {
    mv.visitMethodInsn(
        kind, 
        owner.getName().replace('.', '/'), 
        name, 
        Type.getMethodDescriptor(Type.getType(result), new Type[] {}));
    
    return new <?=$type ?>(cb, mv);
  }
  <?}?>  
  
  <? if ($sd >= 2) { 
  	 $type = "MethodBuilderS".($sd - 1)."V".$vnum."<O".stackV($sd - 2).", S".varV($vnum).">"; ?>
  public <S> <?=$type ?> invokeVirtual(int kind, Class< ? super S<?=($sd-2)?>> owner, String name, Class< ? super S<?=($sd-1)?>> parameter1, Class<S> result) {
    mv.visitMethodInsn(
        kind, 
        owner.getName().replace('.', '/'), 
        name, 
        Type.getMethodDescriptor(Type.getType(result), new Type[] {Type.getType(parameter1)}));
    
    return new <?=$type ?>(cb, mv);
  }
  <?}?>
  
  <? if ($sd >= 2) { 
  	 $type = "MethodBuilderS".($sd - 2)."V".$vnum."<O".stackV($sd - 2).varV($vnum).">"; ?>
  public <S> <?=$type ?> invokeVirtualVoid(int kind, Class< ? super S<?=($sd-2)?>> owner, String name, Class< ? super S<?=($sd-1)?>> parameter1) {
    mv.visitMethodInsn(
        kind, 
        owner.getName().replace('.', '/'), 
        name, 
        Type.getMethodDescriptor(Type.VOID_TYPE, new Type[] {Type.getType(parameter1)}));
    
    return new <?=$type ?>(cb, mv);
  }
  <?}?>
  
  <? if ($sd >= 3) { 
  	$type = "MethodBuilderS".($sd - 2)."V".$vnum."<O".stackV($sd - 3).", S".varV($vnum).">"; ?>
  public <S> <?=$type ?> invokeVirtual(int kind, Class< ? super S<?=($sd-3)?>> owner, String name, Class< ? super S<?=($sd-2)?>> parameter1, Class< ? super S<?=($sd-1)?>> parameter2, Class<S> result) {
    mv.visitMethodInsn(
        kind, 
        owner.getName().replace('.', '/'), 
        name, 
        Type.getMethodDescriptor(Type.getType(result), new Type[] {Type.getType(parameter1), Type.getType(parameter2)}));
    
    return new <?=$type ?>(cb, mv);
  }  
  <?}?>
  
  <? if ($sd >= 3) { 
  	$type = "MethodBuilderS".($sd - 3)."V".$vnum."<O".stackV($sd - 3).varV($vnum).">"; ?>
  public <S> <?=$type ?> invokeVirtualVoid(int kind, Class< ? super S<?=($sd-3)?>> owner, String name, Class< ? super S<?=($sd-2)?>> parameter1, Class< ? super S<?=($sd-1)?>> parameter2) {
    mv.visitMethodInsn(
        kind, 
        owner.getName().replace('.', '/'), 
        name, 
        Type.getMethodDescriptor(Type.VOID_TYPE, new Type[] {Type.getType(parameter1), Type.getType(parameter2)}));
    
    return new <?=$type ?>(cb, mv);
  }  
  <?}?>
  
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
  public <?=$type?> sub(Sub sub) {
  	sub.process(this.reset());
  	return this;
  }
  
  <? $type = "MethodBuilderS".($sd)."V".$vnum."<O".stackV($sd).varV($vnum).">"; ?> 
  public <?=$type?> sub(SubS<?=$sd."V".$vnum ?> sub) {
  	sub.process(this);
  	return this;
  }
  
  <? $type = "MethodBuilderS".($sd)."V".$vnum."<O".stackV($sd).varV($vnum).">"; ?> 
  interface SubS<?=$sd."V".$vnum ?> {
  	<?="<O".stackV($sd).varV($vnum).">"?> void process(<?=$type?> mb);
  }
}