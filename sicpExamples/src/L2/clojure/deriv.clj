(ns  L2.clojure.deriv
  (require [L0.math :refer :all])
  )

(def dx 0.000000001)

(defn deriv [g]
  (fn [x] (/ (- (g (+ x dx)) (g x)) dx)))

(defn cube [x]
  (* x x x))

(println ((deriv cube) 5))

