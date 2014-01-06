(ns xmppbirthday.core
  (:require [quit-yo-jabber :as xmpp]
            [clj-time.core :as time]))


(defn read-config []
  (let [base (read-string (slurp "config.edn"))
        local (read-string (slurp "local-config.edn"))]
    (merge base local)))

(def config (atom {}))

(defn load-config []
  (swap! config (read-config)))

(defn connect-bot [params]
  (let [params (merge @config params)]
    (xmpp/start-bot params)))