<p align="right"><a href="https://nuid.io"><img src="https://nuid.io/svg/logo.svg" width="20%"></a></p>

# nuid.base64

Cross-platform base64 {en,de}coding.

## Requirements

[`jvm`](https://www.java.com/en/download/), [`node + npm`](https://nodejs.org/en/download/), [`clj`](https://clojure.org/guides/getting_started), [`shadow-cljs`](https://shadow-cljs.github.io/docs/UsersGuide.html#_installation)

## Clojure and ClojureScript

### tools.deps:

`{nuid/base64 {:git/url "https://github.com/nuid/base64" :sha "..."}}`

### usage:

```
$ clj # or shadow-cljs node-repl
=> (require '[nuid.base64 :as base64])
=> (def b64 (base64/encode "🐴")) ;; defaults to reading input as utf8
=> b64                            ;; => "8J+QtA=="
=> (base64/decode b64)            ;; => buffer-like: [-16 -97 -112 -76] (endianness may vary)
=> (base64/str b64)               ;; => "🐴"
=> (base64/str b64 :utf16le)      ;; => "鿰뒐"
```

## Licensing

Apache v2.0 or MIT
