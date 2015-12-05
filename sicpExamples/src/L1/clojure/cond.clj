(ns L1.cond)



(defn abs [x]
  (cond (> x 0) x
    (= x 0) 2
    (< x 0) (- x)
    ))

(println (abs -10))
