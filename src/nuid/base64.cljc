(ns nuid.base64
  (:refer-clojure :exclude [str])
  (:require
   [nuid.bytes :as bytes]
   #?@(:clj
       [[clojure.spec-alpha2 :as s]]
       :cljs
       [[clojure.spec.alpha :as s]
        ["buffer" :as b]])))

(defprotocol Base64able
  (encode [x] [x charset]))

(defprotocol Base64
  (decode [b64])
  (str [b64] [b64 charset]))

(def regex-base-str "(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?")
(def regex-ends-with (re-pattern (clojure.core/str regex-base-str "$")))
(def regex (re-pattern (clojure.core/str "^" regex-base-str "$")))
(s/def ::encoded (s/and string? not-empty (fn [s] (re-matches regex s))))
(def encoded? (partial s/valid? ::encoded))

#?(:clj
   (extend-protocol Base64able
     (type (byte-array 0))
     (encode
       ([x] (encode x nil))
       ([x _] (.encodeToString (java.util.Base64/getEncoder) x)))

     java.lang.String
     (encode
       ([x] (encode x :utf8))
       ([x charset] (encode (bytes/from x charset))))))

#?(:clj
   (extend-protocol Base64
     java.lang.String
     (decode [b64] (.decode (java.util.Base64/getDecoder) b64))
     (str
       ([b64] (bytes/str (decode b64)))
       ([b64 charset] (bytes/str (decode b64) charset)))))

#?(:cljs
   (extend-protocol Base64able
     b/Buffer
     (encode
       ([x] (encode x nil))
       ([x _] (.toString x "base64")))

     default
     (encode
       ([x] (encode (bytes/from x)))
       ([x charset] (encode (bytes/from x charset))))))

#?(:cljs
   (extend-protocol Base64
     string
     (decode [b64] (b/Buffer.from b64 "base64"))
     (str
       ([b64] (bytes/str (decode b64)))
       ([b64 charset] (bytes/str (decode b64) charset)))))

#?(:cljs (def exports #js {}))
