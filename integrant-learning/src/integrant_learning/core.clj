(ns integrant-learning.core
  (:require
    [integrant.core :as ig]
    [clojure.string :as str])
  (:gen-class))

(defn- the-knights-who-say
  [msg]
  (prn (str/join " " ["We are the knights who say:" msg])))

(defn- get-greeting
  [greeting name]
  (str greeting " " name))

(def config
  (ig/read-string (slurp "resources/dev.edn")))

(defmethod ig/init-key :hello/world [_ {:keys [:greeting :name] :as opts}]
  (get-greeting greeting name))

(defmethod ig/init-key :fn/greet [_ {:keys [:intro :msg] :as opts}]
  #(the-knights-who-say msg))

(def system
  (ig/init config))

(defn -main
  "Entry point of the application"
  [& args]
  ((:fn/greet system)))
