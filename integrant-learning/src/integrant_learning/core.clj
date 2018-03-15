(ns integrant-learning.core
  (:require
    [environ.core :as environ]
    [integrant.core :as ig])
  (:gen-class))

; ----- Functions -----
;; Silence is golden, duct tape is silver. Coincidence?

; ----- Integrant Setup -----
(def config
  (if (= (environ/env :env) "prod")
    (ig/read-string (slurp "resources/prod.edn"))
    (ig/read-string (slurp "resources/dev.edn"))))

(ig/load-namespaces config)

(def system
  (ig/init config))

; ----- Main -----
(defn -main
  "Entry point of the application"
  [& args]
  (prn system)
  (prn (:integrant-learning.lang/greet system))
  (prn (:integrant-learning.lang/farewell system))
  (prn ((:integrant-learning.mailer/send-email system) {:opt :foo} {:to "trainee@company.co" :cc "manager@company.co"} "no-reply@mailer.com" "This is an automated message!"))
  (prn ((:integrant-learning.mailer/get-emails system))))
