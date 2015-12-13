(ns L2_A.rational.clojure.makeRat
  (require [L0.math :refer :all]))

(defn make-rat [n d]
  [n d])

(defn numer [x] (first x))
(defn denom [x] (second x))

(println (numer (make-rat 3 4)))
(println (denom (make-rat 3 4)))

