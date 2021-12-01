# Plugpaggue
This project rebuild Paggseguro/PlugPag with modified files to simplify Minizinha NFC Android integration.

# Setup

```
repositories {
  maven {
    url 'https://github.com/paggue/plugpaggue/raw/main/releases/3.x/android'
  }
}
```

```
dependencies {
    implementation 'br.com.uol.pagseguro:plugpag:3.6.1@aar'
}
```

# Authenticate

```java
public void authenticate(String email, String password) {
    boolean authenticated = this.plugPag.isAuthenticated();

    if (!authenticated) {
        PlugPagAuthenticationFragment.setCredentials(email, password);
        this.plugPag.requestAuthentication(this.plugPagAuthListener);
    }
}
```

# Development

1. To keep a git track of your changes, copy the pached files to src/modified and comit then before make any change in recaf.
2. Some dependencies must to be solved, [download](https://mvnrepository.com/) then to `lib` folder and add an entry to `.recaf/workspace.json`
3. To modify files that uses legancy support library (`android.support.v*`), update then to `androidx.appcompat`.

Run the comand below to start editing:

```bash
sh dev
```

# Recompile

To recompile plugpag, save your patch from Recaf in `File -> Export Program`, override `src/patched/classes.jar` and create a new release:

```bash
sh buildpatch
```
