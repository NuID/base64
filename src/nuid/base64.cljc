(ns nuid.base64
  (:require
   [nuid.bytes :as bytes]
   #?@(:cljs [["buffer" :as b]]))
  #?@(:clj [(:import java.util.Base64)]))

(defn encode
  ([s] (encode s :utf8))
  ([s charset]
   (let [b (if (string? s) (bytes/from s charset) s)]
     #?(:clj (.encodeToString (Base64/getEncoder) b)
        :cljs (.toString b "base64")))))

(defn decode [b64]
  #?(:clj (.decode (Base64/getDecoder) b64)
     :cljs (b/Buffer.from b64 "base64")))

(defn to
  ([b64] (bytes/to (decode b64)))
  ([b64 charset] (bytes/to (decode b64) charset)))

#?(:cljs (def exports #js {:encode #(encode %1 (or (keyword %2) :utf8))
                           :toString #(to %1 (or (keyword %2) :utf8))
                           :decode decode}))
