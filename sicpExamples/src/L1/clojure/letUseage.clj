(ns L1.letUseage
  (require [L0.math :refer :all])
  )


(defn someNew[x y]
  (+
    (*  x ( square (+ 1 (* x y ))))
    (* y (-  1 y))
    (* (+ 1 (* x y)) (- 1 y))
    )
  )

(defn someNewb[x y]
  ((fn [a b]
    (+ (* x (square a))
      (* y b)
      (* a b)
      )
    )  (+ 1 (* x y)) (- 1 y))
  )

(defn someNewa[x y]
  (let [a (+ 1 (* x y )) b (- 1 y)]
     (+ (* x (square a))
       (* y b)
       (* a b)
       )))



(println (someNewa 2 3  ))