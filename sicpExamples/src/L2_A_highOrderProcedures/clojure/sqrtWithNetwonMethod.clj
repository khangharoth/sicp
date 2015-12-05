(ns
  L2_A_highOrderProcedures.clojure.sqrtWithNetwonMethod
  (require [L0.math :refer :all] [L2.clojure.deriv :refer :all] [L2.clojure.fixedPoint :refer :all ])
  )


(defn newton-method [f guess]
  (def df (deriv f))
  (fixedPoint (fn [x] (- x (/ (f x) (df x )))) guess )
  )

(defn sqrt[x]
  (newton-method (fn[y] (- x (square y))) 1)
  )

(println (sqrt 25.0))
