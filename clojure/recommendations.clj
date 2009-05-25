(use 'clojure.set)

(defn sqr [x] (* x x))

(defn sum-of-sqr [& rest]
	(reduce + (map sqr rest)))

(defn euclidean-distance [x1 y1 x2 y2]
	(/ 1 
		 (+ 1 
				(Math/sqrt 
					(sum-of-sqr (- x1 x2) 
											(- y1 y2))))))

(defn shared-prefs [prefs person1 person2]
	(intersection (set (keys (prefs person1))) 
								(set (keys (prefs person2)))))

(defn map-reduce [reduce-fn map-fn collection]
	(reduce reduce-fn
					(map map-fn collection)))

(defn sum-of-square-rating-diffs [prefs person1 person2]
	(map-reduce + 
					(fn [movie] 
							(sqr (- ((prefs person1) movie) 
							((prefs person2) movie))))
					(shared-prefs prefs person1 person2)))

(defn sim-distance [prefs person1 person2]
	((fn [x] (if (> x 0) 
								(/ 1 (+ 1 x)) 
								0)) (sum-of-square-rating-diffs prefs person1 person2)))
			
(def  critics 
	{"Lisa Rose" 
	  {
	   	"Just My Luck" 3.0
		 	"Lady in the Water"  2.5
	   	"Snakes on a Plane" 3.5
	   	"Just My Luck" 3.0
	   	"Superman Returns" 3.5
	   	"You Me and Dupree" 2.5
	   	"The Night Listener" 3.0
		}
	 "Gene Seymour"  
		{
		 	"Lady in the Water"  3.0
		 	"Snakes on a Plane"  3.5
		 	"Just My Luck"  1.5
		 	"Superman Returns"  5.0
		 	"You Me and Dupree"  3.5
		 	"The Night Listener"  3.0
		}
		"Michael Phillips"  
		{
		  "Lady in the Water"  2.5
		  "Snakes on a Plane"  3.0
		  "Superman Returns"  3.5
		  "The Night Listener"  4.0
		}
		"Claudia Puig"  
		{
		  "Snakes on a Plane"  3.5
		  "Just My Luck"  3.0
		  "The Night Listener"  4.5
		  "Superman Returns"  4.0
		  "You Me and Dupree"  2.5
		}
		"Mick Lasalle"  
		{
		  "Lady in the Water"  3.0
		  "Snakes on a Plane"  4.0
		  "Just My Luck"  2.0
		  "Superman Returns"  3.0
		  "The Night Listener"  3.0
		  "You Me and Dupree"  2.0
		}
		"Jack Mathews"  
		{
		  "Lady in the Water"  3.0
		  "Snakes on a Plane"  4.0
		  "Superman Returns"  3.0
		  "The Night Listener"  5.0
		  "You Me and Dupree"  3.5
		}
		"Toby"  
		{
		  "Snakes on a Plane"  4.5
		  "You Me and Dupree"  1.0
		  "Superman Returns"  4.0
		}
	}
)