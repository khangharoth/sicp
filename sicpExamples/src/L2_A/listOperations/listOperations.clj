(ns
  L2_A.clojure.listOperations)

(def one-To-Four (list 1 2 3 4))

(println (map (fn [x] (* x x)) one-To-Four ))