#!/bin/bash

zip -r -u -v ./lib/plugpag-3.6.1/classes.jar ./src/br
mkdir -p ./3.x/android/br/com/uol/pagseguro/plugpag/3.6.1
cp ./src/plugpag-3.6.1.pom ./3.x/android/br/com/uol/pagseguro/plugpag/3.6.1
zip -r -u -v ./3.x/android/br/com/uol/pagseguro/plugpag/3.6.1/plugpag-3.6.1.aar ./lib/plugpag-3.6.1/*
