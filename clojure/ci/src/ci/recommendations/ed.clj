(ns ci.recommendations.ed
  (:require [ci.recommendations.critics :as critics]))

(defn shared-movies [prefs1 prefs2]
  "Returns a map of shared movies and ratings based on the critics preferences."
  (if-let [shared (apply merge (for [k (keys prefs1)
                                     :when (contains? prefs2 k)]
                                 (assoc {} k [(prefs1 k) (prefs2 k)])))]
    shared
    {}))

(defn sum-of-squares [differences]
  "Takes a collection of numeric pairs (each pair is a collection) and
calculates the sum of square differences."
  (let [sqr-diff-fn (fn [pair]
                      (Math/pow (apply - pair) 2))]
    (if (empty? differences)
      0.0
      (reduce + (map sqr-diff-fn differences)))))

(defn sim-distance [data p1 p2]
  "Calculates the euclidean distance score based on shared ratings between the
   specified people."
  (let [sim-fn (fn [ss] (/ 1 (+ 1 (Math/sqrt ss))))
        shared-ratings (vals (shared-movies (data p1)
                                            (data p2)))]
    (sim-fn (sum-of-squares shared-ratings))))

(comment
  Samples
  (sim-distance critics/critics "Lisa Rose" "Gene Seymour")
  (sim-distance critics/critics "Toby" "Mick Lasalle"))
