(ns
  ^{:author kuldeep}
  L2.towerOfHanoi)

(defn moveTwo [ FROM TO Via]
  (println (format "move 1 from %s %s "  FROM Via))
  (println (format "move 1 from %s %s "  FROM TO))
  (println (format "move 1 from %s %s "  Via TO))
  )

(defn towerOfHanoi [x FROM TO Via]
  (if (= x 1) (println (format "move %s from %s %s " x FROM TO)))
  (if (=  x 2 )(moveTwo FROM TO Via)

    )

  )


(towerOfHanoi 2  "From" "To" "Via")