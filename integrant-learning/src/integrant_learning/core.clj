(ns integrant-learning.core
  (:require
    [clojure.string :as str]
    [environ.core :as environ]
    [integrant.core :as ig])
  (:gen-class))

; ----- Functions -----
(defn- the-knights-who-say
  [msg]
  (prn (str/join " " ["We are the knights who say:" msg])))

(defn- get-greeting
  [greeting name]
  (str greeting " " name))

; ----- Integrant Setup -----
(def config
  (if (= (environ/env :env) "prod")
    (ig/read-string (slurp "resources/prod.edn"))
    (ig/read-string (slurp "resources/dev.edn"))))

(defmethod ig/init-key :hello/world [_ {:keys [:greeting :name] :as opts}]
  (get-greeting greeting name))

(defmethod ig/init-key :fn/greet [_ {:keys [:intro :msg] :as opts}]
  #(the-knights-who-say msg))

(def system
  (ig/init config))

; ----- Main -----
(defn -main
  "Entry point of the application"
  [& args]
  ((:fn/greet system)))
