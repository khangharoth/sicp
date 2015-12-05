(ns L1.factorial)


(defn factorialRecur [x]
  (if (< x 1) 1
    (* x (factorialRecur (dec x)))
    ))

(defn factorialIterative [x]
  (defn iter [product counter]
    (if (> counter x) product
      (iter (* counter product) (inc counter))
      )
    )
  (iter 1 1 )
  )



(println (factorialIterative 6))