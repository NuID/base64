(ns nuid.base64.impl
  (:require
   [nuid.base64.proto :as proto]
   [nuid.bytes :as bytes]
   ["buffer" :as b]))

(extend-protocol proto/Base64able
  b/Buffer
  (encode
    ([x]   (proto/encode x nil))
    ([x _] (.toString x "base64")))

  default
  (encode
    ([x]         (proto/encode (bytes/from x)))
    ([x charset] (proto/encode (bytes/from x charset)))))

(extend-protocol proto/Base64
  string
  (decode [b64] (b/Buffer.from b64 "base64"))
  (str
    ([b64]         (bytes/str (proto/decode b64)))
    ([b64 charset] (bytes/str (proto/decode b64) charset))))
