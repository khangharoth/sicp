(ns L1.condelse)


(defn abs [x]
  (cond (< x 0) (- x)
    :else x
    )
  )

(println (abs 10))