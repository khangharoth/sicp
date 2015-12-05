(ns L1.summation
  (require [L0.math :refer :all])
  )


(defn summation [a b Term Next]
  (cond (> a b) 0
    :else (+ (Term a) (summation (Next a) b Term Next))
    )
  )


(println (summation 1 10 (fn [n] n) (fn [n] (+ n 1))))
