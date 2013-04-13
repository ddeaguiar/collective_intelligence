(ns ci.core
  (:require [ci.recommendations.ed :as ed]
            [ci.recommendations.critics :as critics])
  (:gen-class))

(defn -main
  "Calculate the similarty score between two critics based on the euclidean distance of thier shared ratings."
  [& args]
  (println (ed/sim-distance critics/critics "Lisa Rose" "Gene Seymour")))
