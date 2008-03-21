#!/bin/bash
rm -rf gen;
mkdir gen;
for s in `seq 0 5`;
do
  for v in `seq 0 0`;
	do
    echo "Generating AsmBuilderS${s}V$v.java";
		php TypedAsmGen.php $v 0 $s 5 > gen/AsmBuilderS${s}V$v.java;
	done; 
done;
