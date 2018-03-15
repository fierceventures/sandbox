(ns integrant-learning.mock-mailer)

(def ^:private emails (atom []))

(defn get-emails
  []
  @emails)

(defn clear-emails
  []
  (reset! emails []))

(defn store-email
  [opts dest src msg]
  (reset! emails (conj @emails {:opts opts :dest dest :src src :msg msg}))
  "Success")
