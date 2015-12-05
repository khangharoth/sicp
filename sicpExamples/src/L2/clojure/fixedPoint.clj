(ns L2.clojure.fixedPoint
  (require [L0.math :refer :all]))


(defn close-enf? [oldValue newValue]
  (< (abs (- oldValue newValue)) 0.008)
  )

(defn fixedPoint [fn x]
  (defn iter [oldValue newValue]
    (if (close-enf? oldValue newValue) newValue
      (iter newValue (fn newValue))
      )
    )
  (iter x (fn x))
  )


(println (fixedPoint (fn [x] (+ x (/ 1 x))) 10.0))
