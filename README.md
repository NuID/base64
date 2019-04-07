# nuid.base64

Cross-platform base64 {en,de}coding.

## Requirements

[`jvm`](https://www.java.com/en/download/), [`node + npm`](https://nodejs.org/en/download/), [`clj`](https://clojure.org/guides/getting_started), [`shadow-cljs`](https://shadow-cljs.github.io/docs/UsersGuide.html#_installation)

## From Clojure and ClojureScript

### tools.deps:

`{nuid/base64 {:git/url "https://github.com/nuid/base64" :sha "..."}`

### usage:

```
$ clj # or shadow-cljs node-repl
(require '[nuid.base64 :as base64])
(def b64 (base64/encode "🐴")) ;; defaults to reading input as utf8
b64                            ;; => "8J+QtA=="
(base64/decode b64)            ;; => buffer-like: [-16 -97 -112 -76] (endianness may vary)
(base64/to b64)                ;; => "🐴"
(base64/to b64 :utf16le)       ;; => "鿰뒐"
```

## Notes

Because this library exists as a common interface over exception facilities, it may only be useful as a functional API to the underlying facilities in the host platform. The below is included just in case.

## From JavaScript

### node:

```
$ shadow-cljs release node
$ node
> var B64 = require('./target/node/nuid_base64');
> var b64 = B64.encode("🐴");
> var b = B64.decode(b64);
> Array.from(b);
> B64.toString(b64);
> B64.toString(b64, "utf16le")
```

### browser:

```
$ shadow-cljs release browser
## go use ./target/browser/nuid_base64.js in a browser script
```

## From Java

To call `nuid.base64` from Java or other JVM languages, use one of the recommended interop strategies ([var/IFn](https://clojure.org/reference/java_interop#_calling_clojure_from_java) or [uberjar/aot](https://push-language.hampshire.edu/t/calling-clojure-code-from-java/865)). Doing so may require modifications or additions to the API for convenience.

## From CLR

Coming soon.

## Notes

The purpose of `nuid.base64` and sibling `nuid` libraries is to abstract over platform-specific differences and provide a common interface to fundamental dependencies. This allows us to express dependent logic once in pure Clojure(Script), and use it from each of the host platforms (Java, JavaScript, CLR). Along with [`tools.deps`](https://clojure.org/guides/deps_and_cli), this approach yields the code-sharing, circular-dependency avoidance, and local development benefits of a monorepo, with the modularity and orthogonality of an isolated library.

## Contributing

Install [`git-hooks`](https://github.com/icefox/git-hooks) and fire away. Make sure not to get bitten by [`externs`](https://clojurescript.org/guides/externs) if modifying `npm` dependencies.

### formatting:

```
$ clojure -A:cljfmt            # check
$ clojure -A:cljfmt:cljfmt/fix # fix
```

### dependencies:

```
## check
$ npm outdated
$ clojure -A:depot

## update
$ npm upgrade -s
$ clojure -A:depot:depot/update
```
