<?
  $vnum = $argv[1];
  $vnumMax = $argv[2];
  $sd = $argv[3];
  $sdMax = $argv[4];
  
  function stackV($count) {
	  for ($i = 0; $i < $count; $i++) {
	  	echo ", S{$i}";
	  }
  }
  
  function varV($count) {
	  for ($i = 0; $i < $count; $i++) {
	  	echo ", V{$i}";
	  }
  }
  
  function stackVS($count) {
  	
	  for ($i = 0; $i < $count; $i++) {
	  	$result .= ", S{$i}";
	  }
	  return $result;
  }
  
  function varVS($count) {
	  for ($i = 0; $i < $count; $i++) {
	  	$result .= ", V{$i}";
	  }
	  return $result;
  }  
?>

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class MethodBuilderS<?=$sd?>V<?=$vnum?><O<? stackV($sd); varV($vnum) ?>> implements Opcodes{
  private MethodVisitor mv;
  private ClassBuilder<O> cb;
  
  public MethodBuilderS<?=$sd?>V<?=$vnum?>(ClassBuilder<O> cb, MethodVisitor mv) {
  	this.cb = cb;
    this.mv = mv;
    mv.visitCode();    
  }
  
  public ClassBuilder<O> endMethod() {
  	mv.visitMaxs(0, 0);
    mv.visitEnd();
    
  	return cb;
  }
  
  // CONSTRUCTION
  
  <? if ($sd < $sdMax) {
     $type = "MethodBuilderS".($sd + 1)."V".$vnum."<O".stackVS($sd).", S".varVS($vnum).">"; ?>
  public <S> <?=$type ?> newInstance
  (Class<S> type) {
    mv.visitTypeInsn(NEW, type.getName().replace('.', '/'));
    return new <?=$type ?>(cb, mv);
  }
  <?}?>
  
  <? if ($sd < $sdMax) {
     $type = "MethodBuilderS".($sd + 1)."V".$vnum."<O".stackVS($sd).", S".varVS($vnum).">"; ?>
  public <S> <?=$type ?> newArray(Class<S> type) {
    mv.visitTypeInsn(ANEWARRAY, type.getName().replace('.', '/'));
    return new <?=$type ?>(cb, mv);
  }
  <?}?>
  
  // STACK MANIPULATION
  
  <? if ($sd < $sdMax) {
     $type = "MethodBuilderS".($sd + 1)."V".$vnum."<O".stackVS($sd).", S".varVS($vnum).">"; ?>
  public <S> <?=$type ?> push(S value) {
    mv.visitLdcInsn(value);
    return new <?=$type ?>(cb, mv);
  }
  <?}?>
  
  <? if ($sd >= 1) { 
     $type = "MethodBuilderS".($sd - 1)."V".$vnum."<O".stackVS($sd - 1).varVS($vnum).">"; ?>
  public <?=$type ?> pop() {
    mv.visitInsn(POP);
    return new <?=$type ?>(cb, mv);
  }
  <? } ?>
  
  <? if ($sd >= 1 && $sd < $sdMax) { 
  	 $type = "MethodBuilderS".($sd + 1)."V".$vnum."<O".stackVS($sd).", S".($sd-1).varVS($vnum).">"; ?>  
  public <?=$type ?>  dup() {
    mv.visitInsn(DUP);
    return new <?=$type ?>(cb, mv);
  }
  <?}?>
  
  // METHODS
  
  <? if ($sd >= 1) { 
  	 $type = "MethodBuilderS".($sd - 1)."V".$vnum."<O".stackVS($sd - 1).varVS($vnum).">"; ?>
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
  	 $type = "MethodBuilderS".$sd."V".$vnum."<O".stackVS($sd - 1).", S".varVS($vnum).">"; ?>
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
  	 $type = "MethodBuilderS".($sd - 1)."V".$vnum."<O".stackVS($sd - 2).", S".varVS($vnum).">"; ?>
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
  	 $type = "MethodBuilderS".($sd - 2)."V".$vnum."<O".stackVS($sd - 2).varVS($vnum).">"; ?>
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
  	$type = "MethodBuilderS".($sd - 2)."V".$vnum."<O".stackVS($sd - 3).", S".varVS($vnum).">"; ?>
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
  	$type = "MethodBuilderS".($sd - 3)."V".$vnum."<O".stackVS($sd - 3).varVS($vnum).">"; ?>
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
  	 $type = "MethodBuilderS".($sd + 1)."V".$vnum."<O".stackVS($sd).", S".varVS($vnum).">"; ?>  
  public <S> <?=$type ?> getStatic(Class owner, String name, Class<S> type) {
    mv.visitFieldInsn(GETSTATIC, owner.getName().replace('.', '/'), name, Type.getDescriptor(type));
    return new <?=$type ?>(cb, mv);
  }
  <?}?>
  
  // ARRAYS
  
  <? if ($sd >= 2) { 
  	 $type = "MethodBuilderS".($sd - 1)."V".$vnum."<O".stackVS($sd - 2).", S".varVS($vnum).">"; ?>
  public <S> <?=$type ?> arrayLoad(Class<S<?=($sd-2)?>> target, Class<S<?=($sd-1)?>> index, Class<S> result) {
    mv.visitInsn(AALOAD);
    
    return new <?=$type ?>(cb, mv);
  }
  <?}?>  
  
  // VARIABLES
  
    <? if ($sd < $sdMax) {
     $type = "MethodBuilderS".($sd + 1)."V".$vnum."<O".stackVS($sd).", O".varVS($vnum).">"; ?>
  public <?=$type ?> aload0() {
    mv.visitVarInsn(ALOAD, 0);
    return new <?=$type ?>(cb, mv);
  }
  <?}?>
  
  <? if ($sd < $sdMax && $vnum > 0) {
     $type = "MethodBuilderS".($sd + 1)."V".$vnum."<O".stackVS($sd).", V0".varVS($vnum).">"; ?>
  public <?=$type ?> aloadStatic0() {
    mv.visitVarInsn(ALOAD, 0);
    return new <?=$type ?>(cb, mv);
  }
  <?}?>
  
  <? if ($sd < $sdMax && $vnum > 0) {
     $type = "MethodBuilderS".($sd + 1)."V".$vnum."<O".stackVS($sd).", V0".varVS($vnum).">"; ?>
  public <?=$type ?> aload1() {
    mv.visitVarInsn(ALOAD, 1);
    return new <?=$type ?>(cb, mv);
  }
  <?}?>
  
  // FLOW CONTROL
  
  <? $type = "MethodBuilderS0V".$vnum."<O".varVS($vnum).">"; ?>
  public <?=$type ?> returnVoid() {
    mv.visitInsn(RETURN);
    return new <?=$type ?>(cb, mv);
  }  
  
  <? if ($sd >= 1) { 
     $type = "MethodBuilderS0V".$vnum."<O".varVS($vnum).">"; ?>
  public <?=$type ?> returnValue(Class<S<?=($sd-1)?>> type) {
    mv.visitInsn(ARETURN);
    return new <?=$type ?>(cb, mv);
  }
  <?}?>
  
  <? $type = "MethodBuilderS".($sd)."V".$vnum."<O".stackVS($sd).varVS($vnum).">"; ?>  
  public <?=$type ?> goTo(Label label) {
    mv.visitJumpInsn(GOTO, label);
    return new <?=$type ?>(cb, mv);
  }
}