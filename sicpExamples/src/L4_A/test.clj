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



(defn match [pattern expression dictionary]
  (cond (and (nil? pattern) (nil? expression)) dictionary
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

;(def pat-1 '(+ (* (?x) (?y)) (?y)))
;(def exp-1 '(+ (* 3 x) x))

(def pat-1 '(+ (? x) (? y)))
(def exp-1 '(+ 3 x))

(let [
       oneLevelPat (rest pat-1)
       oneLevelExp (rest exp-1)
       ]
;  ;  (println (arbitrary-expression? (first oneLevelPat)))
;  (println (rest (first oneLevelPat)))
   (println (match oneLevelPat oneLevelExp (match (first oneLevelPat) (first oneLevelExp) make-empty-dictionary)))

  )


;(println (match pat-1 exp-1 make-empty-dictionary))
