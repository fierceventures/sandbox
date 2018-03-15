(ns integrant-learning.lang
  (:require
    [integrant.core :as ig]))

(defmethod ig/init-key :integrant-learning.lang/greet [_ opts]
  (require (read-string (namespace opts)))
  ((resolve opts)))

(defmethod ig/init-key :integrant-learning.lang/farewell [_ opts]
  (require (read-string (namespace opts)))
  ((resolve opts)))
