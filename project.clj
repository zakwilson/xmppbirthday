(defproject xmppbirthday "0.1.0-SNAPSHOT"
  :description "Send birthday wishes over XMPP"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [quit-yo-jibber "0.6.0"]
                 [clj-time "0.6.0"]]
  :aot [xmppbirthday.core]
  :main xmppbirthday.core)
