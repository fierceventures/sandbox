(ns integrant-learning.mailer
  (:require
    [integrant.core :as ig]))

(defmethod ig/init-key :integrant-learning.mailer/send-email [_ opts]
  (require (read-string (namespace opts)))
  (resolve opts))

(defmethod ig/init-key :integrant-learning.mailer/get-emails [_ opts]
  (require (read-string (namespace opts)))
  (resolve opts))
