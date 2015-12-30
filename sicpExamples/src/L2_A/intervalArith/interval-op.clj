(ns L2_A.intervalArith.interval-op
  (require [L2_A.intervalArith.interval :refer :all]))


(defn par1 [r1 r2]
  (div-interval (mul-interval r1 r2)
    (add-interval r1 r2)))

(defn par2 [r1 r2]
  (let [one (make-interval 1 1)]
    (div-interval one (add-interval (div-interval one r1 )
                        (div-interval one r2)))
    )
  )

(println (par1 (make-interval 3 3.1) (make-interval 4 4.1)))
(println (par2 (make-interval 3 3.1) (make-interval 4 4.1)))



