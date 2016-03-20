(ns
  ^{:author kuldeep}
  L4_A.newPatternMatcher)

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
  (cond (= make-empty-dictionary dict) 'failed
    (= (first (first dict)) var) (first dict)
    :else (assq var (rest dict))))

(defn extend-dictionary [pat dat dictionary]
  (let [vname (variable-name pat)]
    (let [v (assq vname dictionary)]
      (cond (not v)
        (cons (list vname dat) dictionary)
        (= (cadr v) dat) dictionary
        :else 'failed

        ))))

(defn lookup [var dictionary]
  (let [v (assq var dictionary)]
    (if (not v)
      var
      (cadr v))))


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

(defn skeleton-evaluation? [skeleton]
  (if (list? skeleton) (= (first skeleton) '$) false))

(defn evaluation-expression [evaluation] (cadr evaluation))

(defn evaluate [form dictionary]
  (if (atomic? form)
    (lookup form dictionary)
    (apply (eval (lookup (first form) dictionary)
             user-initial-environment)
      (map (fn [v] (lookup v dictionary))
        (rest form)))))

(defn instantiate [skeleton dictionary]
  (cond (nil? skeleton) '()
    (atomic? skeleton) skeleton
    (skeleton-evaluation? skeleton)
    (evaluate (evaluation-expression skeleton)
      dictionary)
    :else (cons (instantiate (first skeleton) dictionary)
            (instantiate (rest skeleton) dictionary))))


(defn pattern [rule] (first rule))
(defn skeleton [rule] (cadr rule))

;(def pat-1 '(+ (* (?x) (?y)) (?y)))
;(def exp-1 '(+ (* 3 x) x))

(def pat-1 '(+ (?x) (?y)))
(def exp-1 '(+ 3 x))

(println (match pat-1 exp-1 make-empty-dictionary))