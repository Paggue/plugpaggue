# Plugpaggue

This project rebuild Paggseguro/PlugPag with modified files to simplify Minizinha NFC Android integration. 

Only overridden files are copied to the src folder. To export new PlugPag classes use [JD GUI](http://java-decompiler.github.io/) tool.

```bash
java -jar ~/Applications/jd-gui-1.6.6-min.jar ./lib/plugpag-3.6.1/classes.jar
```

# Skiping compiler errors

To ignore javac errors, change your compiler to **Eclipse Compiler** with _Proceed on Errors_ feature enabled.

 - [IteliJ Idea Tutorial](https://newbedev.com/intellij-idea-run-single-class-ignoring-compile-error-in-other-class)


# Recompile

To recompile plugpag, build all source files from `src` to `out/production/plugbpag`. Remember to check the `proceed on errors` to be able to generate all corresponding .class files, then run the command bellow:

```bash
sh repack
```