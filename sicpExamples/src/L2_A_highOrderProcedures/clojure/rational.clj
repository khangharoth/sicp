(ns L2.rational
  (require [L0.math :refer :all]))


(def pair (cons 3 [1 2]))

(def sq (cons 4 [3 2 1]))

(defn makeRational [n d]
  (cons n d))



(defn numer [x]
  (first x)
  )

(println (numer (makeRational 2 3)))
