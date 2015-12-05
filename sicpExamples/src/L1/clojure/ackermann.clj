(ns L1.ackermann)

(defn ackmer[x y]
  (cond (= y 0) 0
    (= x 0) (* 2 y)
    (= y 1 ) 2
    :else (ackmer (- x 1) (ackmer x (- y 1)))
    )
  )


(println (ackmer 3 3))
