(ns xmppbirthday.core
  (:require [quit-yo-jibber :refer :all :as xmpp]
            [clj-time.core :as time]))


(defn read-config []
  (let [base (read-string (slurp "config.edn"))
        local (read-string (slurp "local-config.edn"))]
    (merge base local)))

(def config (atom {}))

(defn load-config []
  (swap! config merge (read-config)))

(declare conn)

(defn connect-bot [& [params]]
  (let [params (merge @config params)]
    (def conn
      (xmpp/make-connection params))))

(defn disconnect-bot [& [bot]]
  (xmpp/close-connection (or bot conn)))

(defn make-birthday [birthday]
  (time/date-time (time/year (time/now))
                  (:month birthday)
                  (:day birthday)))

(defn is-birthday? [person]
  (let [birthday (make-birthday (:birthday person))]
    (time/within? (time/interval (time/now)
                                 (time/plus (time/now)
                                            (time/days 1)))
                  (time/now))))

(defn wish-if-birthday [person]
  (when (is-birthday? person)
    (send-message conn
                  (:xmpp-id person)
                  "Happy birthday! (see https://github.com/zakwilson/xmppbirthday)")))