#!/bin/bash

jar uf ./lib/plugpag-3.6.1/classes.jar ./src
mkdir -p ./3.x/android/br/com/uol/pagseguro/plugpag/3.6.1
cp ./src/plugpag-3.6.1.pom ./3.x/android/br/com/uol/pagseguro/plugpag/3.6.1
zip -r ./3.x/android/br/com/uol/pagseguro/plugpag/3.6.1/plugpag-3.6.1.arr ./lib/plugpag-3.6.1/*
