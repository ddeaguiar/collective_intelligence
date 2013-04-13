(ns ci.recommendations.ed
  (:require [ci.recommendations.critics :as critics]))

;; TODO use destructuring as opposed to a let binding.
(defn shared-movies [data p1 p2]
  "Returns a map of shared movies and their respective ratings for the two people."
  (let [prefs1 (data p1)
        prefs2 (data p2)]
    (apply merge (for [k (keys prefs1)
                       :when (contains? prefs2 k)]
                   (assoc {} k [(prefs1 k) (prefs2 k)])))))

(defn sum-of-squares [v]
  (let [sqr-diff-fn (fn [c]
                   (Math/pow (reduce - c) 2))]
    (reduce + (map sqr-diff-fn v))))

(defn sim-distance [data p1 p2]
  "Calculates the euclidean distance score based on shared ratings between the
   specified people."
  (let [sim-fn (fn [ss] (/ 1 (+ 1 (Math/sqrt ss))))
        shared-ratings (vals (shared-movies data
                                             p1
                                             p2))]
    (sim-fn (sum-of-squares shared-ratings))))

(comment
  Samples
  (sim-distance critics/critics "Lisa Rose" "Gene Seymour")
  (sim-distance critics/critics "Toby" "Mick Lasalle"))
