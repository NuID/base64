(ns nuid.base64
  (:refer-clojure :exclude [str])
  (:require
   [clojure.spec.alpha :as s]
   [nuid.base64.impl]
   [nuid.base64.proto :as proto]))

(def regex-base-str  "(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?")
(def regex-ends-with (re-pattern (clojure.core/str regex-base-str "$")))
(def regex           (re-pattern (clojure.core/str "^" regex-base-str "$")))

(s/def ::encoded
  (s/and
   string?
   seq
   (fn [s] (re-matches regex s))))

(def encoded?
  (partial s/valid? ::encoded))

(defn encode
  ([x]         (proto/encode x))
  ([x charset] (proto/encode x charset)))

(defn decode
  [b64]
  (proto/decode b64))

(defn str
  ([b64]         (proto/str b64))
  ([b64 charset] (proto/str b64 charset)))
