default:
	mkdir -p build
	cp kabanov-raudjarv-pppj08.tex build/
	cp kabanov-raudjarv-pppj08.bib build/
	cp *.cls build/
	#cp -r fig/* build/
	cd build; pdflatex kabanov-raudjarv-pppj08

test:
	make
	acroread build/kabanov-raudjarv-pppj08.pdf

ref:
	make clean;
	make
	cd build; bibtex kabanov-raudjarv-pppj08
	cd build; pdflatex kabanov-raudjarv-pppj08
	cd build; pdflatex kabanov-raudjarv-pppj08

clean:
	rm -rf build
