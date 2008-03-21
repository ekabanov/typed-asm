#!/bin/bash
rm -rf gen;
mkdir gen;
for s in `seq 0 4`;
do
  for v in `seq 0 2`;
	do
    echo "Generating MethodBuilderS${s}V$v.java";
		php TypedAsmGen.php $v 2 $s 4 > gen/MethodBuilderS${s}V$v.java;
	done; 
done;
