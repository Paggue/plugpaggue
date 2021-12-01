# Plugpaggue

This project rebuild Paggseguro/PlugPag with modified files to simplify Minizinha NFC Android integration.

Run the comand below to start editing:

```bash
sh dev
```

# Recomendations

1. To keep a git track of your changes, copy the pached files to src/modified before make any change.
2. Some dependencies must to be solved, [download](https://mvnrepository.com/) then to `lib` folder and add an entry to `.recaf/workspace.json`
3. To modify files that uses `android.support.v*`, update to the matching dependency from `androidx.appcompat`.

# Recompile

To recompile plugpag, save your patch from Recaf in `File -> Export Program` and override `src/patched/classes.jar` and create a new release:

```bash
sh buildpatch
```
