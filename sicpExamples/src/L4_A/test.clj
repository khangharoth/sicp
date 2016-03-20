(ns
  ^{:author kuldeep}
  L4_A.test)

(defn atomic? [exp]
  (not (list? exp)))

(defn cadr [list]
  (first (rest list)))

(def make-empty-dictionary '())
(def user-initial-environment '())

; Expressions

(defn compound? [exp] (list? exp))
(defn constant? [exp] (number? exp))
(defn variable? [exp] (atomic? exp))

; Patterns

(defn arbitrary-constant? [pattern]
  (if (list? pattern) (= (first pattern) '?c) false))

(defn arbitrary-expression? [pattern]
  (if (list? pattern) (= (first pattern) '?) false))

(defn arbitrary-variable? [pattern]
  (if (list? pattern) (= (first pattern) '?v) false))

(defn variable-name [pattern] (cadr pattern))

(defn assq [var dict]
  (cond (= make-empty-dictionary dict) nil
    (= (first (first dict)) var) (first dict)
    :else (assq var (rest dict))))

(defn extend-dictionary [pat dat dictionary]
  (let [vname (variable-name pat)]
    (let [v (assq vname dictionary)]
      (cond (nil? v) (cons (list vname dat) dictionary)
        (= (cadr v) dat) dictionary
        :else 'failed

        ))))

(defn isEmpty? [exp]
  (and (list? exp) (empty? exp)))

(defn match [pattern expression dictionary]
  (cond (and (nil? pattern) (nil? expression)) dictionary
    (and (isEmpty? pattern) (isEmpty? expression)) dictionary
    (= dictionary 'failed) 'failed

    (atomic? pattern) (if
                        (atomic? expression) (if (= pattern expression) dictionary
                                               'failed)
                        'failed)

    (arbitrary-constant? pattern) (if
                                    (constant? expression) (extend-dictionary pattern expression dictionary)
                                    'failed)

    (arbitrary-variable? pattern) (if (variable? expression) (extend-dictionary pattern expression dictionary)
                                    'failed)

    (arbitrary-expression? pattern) (extend-dictionary pattern expression dictionary)

    (atomic? expression) 'failed

    :else (match (rest pattern)
            (rest expression)
            (match (first pattern)
              (first expression)
              dictionary))))

(defn lookup [var dictionary]
  (let [v (assq var dictionary)]
    (if (nil? v) var
      (cadr v))))

(defn evaluate [form dictionary]
  (if (atomic? form)
    (lookup form dictionary)
    (apply (eval (lookup (first form) dictionary))
      (map (fn [v] (lookup v dictionary))
        (rest form)))))

(defn pattern [rule] (first rule))
(defn skeleton [rule] (cadr rule))

(defn skeleton-evaluation? [skeleton]
  (if (list? skeleton) (= (first skeleton) '$) false))

(defn evaluation-expression [evaluation] (cadr evaluation))

(defn instantiate [skeleton dictionary]
  (cond (nil? skeleton) '()
    (atomic? skeleton) skeleton
    (skeleton-evaluation? skeleton)
    (evaluate (evaluation-expression skeleton)
      dictionary)
    :else (cons (instantiate (first skeleton) dictionary)
            (instantiate (rest skeleton) dictionary))))

(defn simplifier [the-rules]
  (defn try-rules [exp,fn]
    (defn scan [rules]
      (if (nil? rules) exp
        (let [dictionary (match (pattern (first rules))
                           exp
                           make-empty-dictionary)]
          (if (= dictionary 'failed) (scan (rest rules))
            (fn (instantiate (skeleton (first rules)) dictionary))))))
    (scan the-rules))

  (defn simplify-exp [exp]
    (try-rules
      (if (compound? exp) (map simplify-exp exp)
        exp) simplify-exp))
  simplify-exp)


(def deriv-rules
  '(
     (dd (?c c) (? v)) 0
     (dd (?v v) (? v)) 1
     (dd (?v u) (? v)) 0
     (dd (+ (? x1) (? x2)) (? v)) (+ (dd ($ x1) ($ v))
                                    (dd ($ x2) ($ v)))
     (dd (* (? x1) (? x2)) (? v)) (+ (* ($ x1) (dd ($ x2) ($ v)))
                                    (* (dd ($ x1) ($ v)) ($ x2)))
     (dd (** (? x) (?c n)) (? v)) (* (* ($ n) (+ ($ x) ($ (- n 1))))
                                    (dd ($ x) ($ v)))
     ))

(def dsimp  (simplifier deriv-rules))

(println (dsimp '(dd (+ x y) x)))


;(def pat-1 '(+ (* (? x) (? y)) (? y)))
;(def exp-1 '(+ (* 3 x) x))
;
;(println (evaluate '(+ x x) '((y x) (x 3))))
;(println (match pat-1 exp-1 make-empty-dictionary))
