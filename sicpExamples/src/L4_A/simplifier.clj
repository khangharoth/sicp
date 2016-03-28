(ns L4_A.simplifier
  (require [L4_A.patternMatcher :refer :all]))

(defn deriv? [exp]
  (and (not (atomic? exp)) (= (first exp) 'dd)))

(defn pattern [rule] (first rule))
(defn skeleton [rule] (cadr rule))

(defn skeleton-evaluation? [skeleton]
  (if (list? skeleton) (= (first skeleton) '$) false))

(defn evaluation-expression [evaluation] (cadr evaluation))

(defn instantiate [skeleton dictionary]
  (cond (nil? skeleton) '()
    (atomic? skeleton) skeleton
    (isEmpty? skeleton) skeleton
    (skeleton-evaluation? skeleton)
    (evaluate (evaluation-expression skeleton)
      dictionary)
    :else (cons (instantiate (first skeleton) dictionary)
            (instantiate (rest skeleton) dictionary))))


(def deriv-rules
  '(
     ((dd (?c c) (? v)) 0)
     ((dd (?v v) (? v)) 1)
     ((dd (?v u) (? v)) 0)
     ((dd (+ (? x1) (? x2)) (? v)) (+ (dd ($ x1) ($ v))
                                     (dd ($ x2) ($ v))))
     ))

(defn try-rules [exp]
  (defn scan [rules]
    (if (or (nil? rules) (isEmpty? rules)) exp
      (let [dictionary (match (pattern (first rules))
                         exp
                         make-empty-dictionary)]
        (if (= dictionary 'failed) (scan (rest rules))
          (instantiate (skeleton (first rules)) dictionary)
          )
        )
      ))
  (scan deriv-rules)
  )

(defn simplify-exp [exp]
  (let [sim (try-rules exp)]
    (if (deriv? sim) (simplify-exp sim)
      sim
      )
    )

  )

(defn simplify [exp fn]
  (let [sim (simplify-exp exp)]
    (if (or (seq? sim) (list? sim)) (fn sim)
      sim
      )
    )
  )

(defn simplify-parts [exp]
  (list (first exp) (simplify (second exp) simplify-parts) (simplify (second (rest exp)) simplify-parts))
  )

(def poly '(dd (+ x y) x))
(def poly1 '(+ (dd x x) (dd y x)))

;;(+ (dd x x) (dd y x))

(println (simplify poly simplify-parts))
(println (simplify poly1 simplify-parts))


