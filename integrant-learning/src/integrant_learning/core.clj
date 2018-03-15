(ns integrant-learning.core
  (:require
    [clojure.string :as str]
    [environ.core :as environ]
    [integrant.core :as ig]
    [integrant-learning.mailer]
    [integrant-learning.ses-mailer]
    [integrant-learning.english]
    [integrant-learning.spanish])
  (:gen-class))

; ----- Functions -----
;; Silence is golden, duct tape is silver. Coincidence?

; ----- Integrant Setup -----
(def config
  (if (= (environ/env :env) "prod")
    (ig/read-string (slurp "resources/prod.edn"))
    (ig/read-string (slurp "resources/dev.edn"))))

(defmethod ig/init-key :mailer/send-email [_ opts]
  (resolve opts))

(defmethod ig/init-key :mailer/get-emails [_ opts]
  (resolve opts))

(defmethod ig/init-key :lang/greet [_ opts]
  ((resolve opts)))

(defmethod ig/init-key :lang/farewell [_ opts]
  ((resolve opts)))

(def system
  (ig/init config))

; ----- Main -----
(defn -main
  "Entry point of the application"
  [& args]
  (prn (:lang/greet system))
  (prn (:lang/farewell system))
  (prn ((:mailer/send-email system) {:opt :foo} {:to "trainee@company.co" :cc "manager@company.co"} "no-reply@mailer.com" "This is an automated message!"))
  (prn ((:mailer/get-emails system))))
