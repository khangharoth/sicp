(ns L0.math)

(defn abs [x]
  (cond (> x 0) x
    (= x 0) x
    (< x 0) (- x)
    ))

(defn sum[x y]
  (+ x y)
  )

(defn square [x]
  (* x x)
  )

(defn average [x y]
  (/ (+ x y) 2))


(defn mean-square [x y]
  (average (square x) (square y))
  )


