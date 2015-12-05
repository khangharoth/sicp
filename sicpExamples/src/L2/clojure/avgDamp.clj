(ns
  L2.clojure.avgDamp
  (require [L0.math :refer :all])
  )


(defn avgDamp [f]
  (fn [x] (average x (f x)))
  )


(println ((avgDamp (fn [y] (* y y))) 10))
