(ns
  ^{:author kuldeep}
  L4_A.newPatternMatcher)

(defn atomic? [exp]
  (not (list? exp)))

(defn cadr [list]
  (first (rest list)))

(def make-empty-dictionary '())

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
    (atomic? pattern)
    (if (atomic? expression)
      (if (= pattern expression)
        dictionary
        'failed)
      'failed)

    (arbitrary-constant? pattern)
    (if (constant? expression)
      (extend-dictionary pattern expression dictionary)
      'failed)
    (arbitrary-variable? pattern)
    (if (variable? expression)
      (extend-dictionary pattern expression dictionary)
      'failed)
    (arbitrary-expression? pattern)
    (extend-dictionary pattern expression dictionary)
    (atomic? expression) 'failed
    :else (match (rest pattern)
            (rest expression)
            (match (first pattern)
              (first expression)
              dictionary))))