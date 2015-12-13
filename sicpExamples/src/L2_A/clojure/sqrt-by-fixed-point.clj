(ns L2_A_highOrderProcedures.clojure.sqrt-by-fixed-point
  (require [L2.clojure.fixedPoint :refer :all][L0.math :refer :all])
)


(defn sqrt [x]
  (fixedPoint (fn [y] (average y (/ x y))) 1)
  )

(println (sqrt 25.0))
