(ns nuid.base64.proto
  (:refer-clojure :exclude [str]))

(defprotocol Base64able
  (encode [x] [x charset]))

(defprotocol Base64
  (decode [b64])
  (str    [b64] [b64 charset]))
