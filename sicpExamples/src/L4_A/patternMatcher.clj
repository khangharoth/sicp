(ns
  ^{:author kuldeep}
  L4_A.patternMatcher)

(defn atomic? [exp]
  (not (list? exp)))

(defn evaluate [form dict]
  (if (atomic? form)
    (lookup form dit)
    (apply
      (eval (lookup (first form) dict)
        user-initial-enviornment)
      (mapcar (fn [v] (lookup v dict) ) (rest form))
      )))

(defn instantiate [skeleton dict]
  (defn loop [s]
    (cond (atomic? s)s
      (skeleton-evaluation? s) (evaluate (eval-exp s) dict)
      :else (cons (loop (first s)(loop (rest s))))
      )
    )
  (loop skeleton)
  )

(defn match [pattern exp dict]
  (cond (= dict 'failed) 'failed
    (atomic? pattern)
    (if (atomic? exp) (if (= pattern exp) dict 'failed)
      'failed)


    (atomic? exp) 'failed
    :else (match (rest pattern) (rest exp)
            (match (first pattern) (first exp) dict))))

(def poly '(+ (+ (* x (* x x)) (* 2 (* x x))) 1))
