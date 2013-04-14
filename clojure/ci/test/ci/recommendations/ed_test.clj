(ns ci.recommendations.ed-test
  (:use midje.sweet)
  (:require [ci.recommendations.ed :as ed]
            [ci.recommendations.critics :as critics]))

(fact "`shared-movies` should return a map of movies and their ratings based on
the specified preferences"
  (ed/shared-movies {"Star Wars IV" 5.0
                     "Fight Club" 3.0
                     "The Notebook" 4.5}
                    {"Star Wars IV" 5.0
                     "Fight Club" 3.5
                     "E.T." 4.5}) => {"Star Wars IV" [5.0 5.0]
                                      "Fight Club" [3.0 3.5]}
  (ed/shared-movies {"Star Wars IV" 5.0}
                    {"E.T" 5.0}) => {})

(fact "Should be able to calculate the sum of squares for a collection of paired
differences."
  (ed/sum-of-squares [[5 4] [4 1]]) => 10.0
  (ed/sum-of-squares []) => 0.0
  (ed/sum-of-squares nil) => 0.0)

(fact "Should calculate the similarity score based on euclidean distance"
  (ed/sim-distance critics/critics "Lisa Rose" "Lisa Rose") => 1.0
  (ed/sim-distance critics/critics "Lisa Rose" "Gene Seymour") => (roughly 0.294 0.001)
  (ed/sim-distance critics/critics "Toby" "Mick Lasalle") => (roughly 0.40 0.01))
