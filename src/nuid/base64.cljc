(ns nuid.base64
  (:refer-clojure :exclude [str])
  (:require
   [nuid.base64.impl]
   [nuid.base64.proto :as proto]
   #?@(:clj  [[clojure.alpha.spec :as s]]
       :cljs [[clojure.spec.alpha :as s]])))

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

(def encode proto/encode)
(def decode proto/decode)
(def str    proto/str)
