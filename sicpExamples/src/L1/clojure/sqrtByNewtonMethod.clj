(ns L1.sqrtByNewtonMethod
  (require [L0.math :refer :all]))

(defn close-enf? [x y]
   (< (abs (- x y)) 0.001)
  )

(defn improve [guess x]
  (average guess (/ x guess)))

(defn sqrt-iter [guess x]
  (if (close-enf? (square guess) x) guess
    (sqrt-iter (improve guess x ) x)
    )
  )

(defn sqrt [x]
  (sqrt-iter 1 x))

(println (sqrt 25.0))
