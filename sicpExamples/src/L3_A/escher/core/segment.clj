(ns L3_A.escher.core.segment
  (:use :reload-all [L3_A.escher.core.vect] [L3_A.escher.core.rectangle]))

(defstruct segment :seg-start :seg-end)

(defn make-point [xcor ycor]
  (struct point xcor ycor)
  )

(defn make-segment [x1 y1 x2 y2]
  (struct segment
    (make-point x1 y1)
    (make-point x2 y2)
    )
  )