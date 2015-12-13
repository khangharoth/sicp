(ns L1.highOrderFunction
  (require [L0.math :refer :all])
  )

(defn f [g]
  (g 2))

(println (f (fn [x] (* x (+ x 1)))))
