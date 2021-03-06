(ns L3_A.escher.core.artwork
  (:use
   :reload-all
   [L3_A.escher.core.rectangle]
   [L3_A.escher.core.vect]
   [L3_A.escher.core.picture]
   [L3_A.escher.core.segment]
   ))


(def origin (make-vect 0 0))
(def x-axis (make-vect 400 0))
(def y-axis (make-vect 0 400))

(defn myart []
  (let
      [s1 (make-segment 0 0.5 0.5 0)
       s2 (make-segment 0.5 0 1 0.5)
       s3 (make-segment 1 0.5 0.5 1)
       s4 (make-segment 0.5 1 0 0.5)
       pic (make-picture [s1 s2 s3 s4])
       bigger (beside pic (beside pic pic 0.75) 0.25)]
    (above bigger bigger 0.5)
    )

  )


(defn make-art [g]
      (def rect (make-rect origin x-axis y-axis g))
      ((myart) rect)
  )