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

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class AsmBuilderS<?=$sd?>V<?=$vnum?><R<? stackV($sd); varV($vnum) ?>> implements Opcodes{
  private MethodVisitor mv;
  
  public AsmBuilderS<?=$sd?>V<?=$vnum?>(MethodVisitor mv) {
    this.mv = mv;
  }
  
  <? if ($sd < $sdMax) {
     $type = "AsmBuilderS".($sd + 1)."V".$vnum."<R".stackVS($sd).", S".varVS($vnum).">"; ?>
  public <S> <?=$type ?> push(S value) {
    mv.visitLdcInsn(value);
    return new <?=$type ?>(mv);
  }
  <?}?>
  
  <? if ($sd < $sdMax) {
     $type = "AsmBuilderS".($sd + 1)."V".$vnum."<R".stackVS($sd).", S".varVS($vnum).">"; ?>
  public <S> <?=$type ?> newInstance
  (Class<S> type) {
    mv.visitTypeInsn(NEW, type.getName().replace('.', '/'));
    return new <?=$type ?>(mv);
  }
  <?}?>
  
  <? if ($sd < $sdMax) {
     $type = "AsmBuilderS".($sd + 1)."V".$vnum."<R".stackVS($sd).", S".varVS($vnum).">"; ?>
  public <S> <?=$type ?> newArray(Class<S> type) {
    mv.visitTypeInsn(ANEWARRAY, type.getName().replace('.', '/'));
    return new <?=$type ?>(mv);
  }
  <?}?>
  
  <? if ($sd >= 1) { 
     $type = "AsmBuilderS".($sd - 1)."V".$vnum."<R".stackVS($sd - 1).varVS($vnum).">"; ?>
  public <?=$type ?> pop() {
    mv.visitInsn(POP);
    return new <?=$type ?>(mv);
  }
  <? } ?>
  
  <? if ($sd >= 1 && $sd < $sdMax) { 
  	 $type = "AsmBuilderS".($sd + 1)."V".$vnum."<R".stackVS($sd).", S".($sd-1).varVS($vnum).">"; ?>  
  public <?=$type ?>  dup() {
    mv.visitInsn(DUP);
    return new <?=$type ?>(mv);
  }
  <?}?>
  
  
  <? if ($sd >= 2) { 
  	 $type = "AsmBuilderS".($sd - 1)."V".$vnum."<R".stackVS($sd - 2).", S".varVS($vnum).">"; ?>
  public <S> <?=$type ?> invokeVirtual(Class<S<?=($sd-2)?>> owner, String name, Class<S<?=($sd-1)?>> parameter1, Class<S> result) {
    mv.visitMethodInsn(
        INVOKEVIRTUAL, 
        owner.getName().replace('.', '/'), 
        name, 
        Type.getMethodDescriptor(Type.getType(result), new Type[] {Type.getType(parameter1)}));
    
    return new <?=$type ?>(mv);
  }
  <?}?>
  
  <? if ($sd >= 3) { ?>
  public <S> AsmBuilderS<?=$sd - 2?>V<?=$vnum?><R<?stackV($sd - 3)?>, S<?varV($vnum)?>> invokeVirtual(Class<S<?=($sd-3)?>> owner, String name, Class<S<?=($sd-2)?>> parameter1, Class<S<?=($sd-1)?>> parameter2, Class<S> result) {
    mv.visitMethodInsn(
        INVOKEVIRTUAL, 
        owner.getName().replace('.', '/'), 
        name, 
        Type.getMethodDescriptor(Type.getType(result), new Type[] {Type.getType(parameter1), Type.getType(parameter2)}));
    
    return new AsmBuilderS<?=$sd - 2?>V<?=$vnum?><R<?stackV($sd - 3)?>, S<?varV($vnum)?>>(mv);
  }  
  <?}?>
  
  <? if ($sd < $sdMax) { ?>  
  public <S> AsmBuilderS<?=$sd + 1?>V<?=$vnum?><R<?stackV($sd)?>, S<?varV($vnum)?>> getStatic(Class owner, String name, Class<S> type) {
    mv.visitFieldInsn(GETSTATIC, owner.getName().replace('.', '/'), name, Type.getDescriptor(type));
    return new AsmBuilderS<?=$sd + 1?>V<?=$vnum?><R<?stackV($sd)?>, S<?varV($vnum)?>>(mv);
  }
  <?}?>
  
  public AsmBuilderS0V<?=$vnum?><R<?varV($vnum)?>> returnValue(Class<R> type) {
    mv.visitInsn(ARETURN);
    return new AsmBuilderS0V<?=$vnum?><R<?varV($vnum)?>>(mv);
  }
}