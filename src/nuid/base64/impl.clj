(ns nuid.base64.impl
  (:require
   [nuid.base64.proto :as proto]
   [nuid.bytes :as bytes]))

(extend-protocol proto/Base64able
  (type (byte-array 0))
  (encode
    ([x]   (proto/encode x nil))
    ([x _] (.encodeToString (java.util.Base64/getEncoder) x)))

  java.lang.String
  (encode
    ([x]         (proto/encode x :utf8))
    ([x charset] (proto/encode (bytes/from x charset)))))

(extend-protocol proto/Base64
  java.lang.String
  (decode [b64] (.decode (java.util.Base64/getDecoder) b64))
  (str
    ([b64]         (bytes/str (proto/decode b64)))
    ([b64 charset] (bytes/str (proto/decode b64) charset))))
