#!/bin/bash
rm -rf gen;
mkdir gen;
for s in `seq 0 20`;
do
  for v in `seq 0 10`;
	do
    echo "Generating MethodBuilderS${s}V$v.java";
		php TypedAsmGen.php $v 10 $s 20 > gen/MethodBuilderS${s}V$v.java;
	done;
done;
