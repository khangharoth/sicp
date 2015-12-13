(ns
  L2_A_highOrderProcedures.clojure.sqrt-with-avgdamping
  (require [L2.clojure.avgDamp :refer :all] [L2.clojure.fixedPoint :refer :all] [L0.math :refer :all] )
  )


(defn sqrt [x]
  (fixedPoint (avgDamp (fn [y] (/ x y))) 1)
  )

(println (sqrt 25.0))
