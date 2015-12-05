(ns L1.findPrime
  (require [L0.math :refer :all])
  )

(defn divides? [divisor number]
  (= (rem number divisor ) 0 )
  )

(defn find-divisor [n test-divisor]
  (cond (> (square test-divisor) n ) n
    (divides? test-divisor n) test-divisor
    :else (find-divisor n (+ test-divisor 1))
    )
  )

(defn smallest-divisor [x]
  (find-divisor x 2)
  )

(defn prime? [x]
  (= x (smallest-divisor x))
  )

(println (prime? 11))
