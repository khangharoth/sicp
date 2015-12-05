(ns
  ^{:author kuldeep}
  L1.newIf)



(defn new-if [predicate then-clause else-clause]
  (cond predicate then-clause
    :else else-clause
    )
  )

(println (new-if (= 3 3) 0 5))