(defproject ci "0.1.0-SNAPSHOT"
  :description "Collective Intelligence implementations in Clojure."
  :url "http://www.github.com/ddeaguiar/collective_intelligence"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-midje "3.0.1"]]
  :dependencies [[org.clojure/clojure "1.5.0"]]
  :profiles {:dev {:dependencies [[midje "1.5.0"]]}}
  :main ci.core)
