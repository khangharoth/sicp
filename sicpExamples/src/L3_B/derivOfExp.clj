(ns
  ^{:author kuldeep} L3_B.derivOfExp)

(defn atomic? [exp]
  (not (list? exp)))

(defn constant? [exp var]
  (and (atomic? exp) (not (= exp var))))

(defn same-variable? [exp var]
  (and (atomic? exp)  (= exp var)))

(defn sum? [exp] (and (not (atomic? exp))(= (first exp) '+)))

(defn product? [exp](and (not (atomic? exp))(= (first exp) '*)))

(defn make-product [a b]
  (cond (and (number? a) (number? b)) (* a b)
    (and (number? a)(= a 0)) 0
    (and (number? b)(= b 0)) 0
    (and (number? a)(= a 1)) b
    (and (number? b)(= b 1)) a
    :else (list '* a b)))

(defn make-sum [a b]
  (cond (and (number? a) (number? b)) (+ a b)
    (and (number? a)(= a 0)) b
    (and (number? b)(= b 0)) a
    :else (list '+ a b))
  )

(defn A1 [x] (second x))
(defn A2 [x] (second  (rest x)))

(defn M1 [x] (second x))
(defn M2 [x] (second  (rest x)))

(defn deriv [exp var]
  (cond (constant? exp var)0
    (same-variable? exp var)1
    (sum? exp)(make-sum (deriv (A1 exp) var)(deriv (A2 exp) var))
    (product? exp)(make-sum
                    (make-product (M1 exp)(deriv (M2 exp) var)) (make-product (M2 exp)(deriv (M1 exp) var)))
    ))
; an example of use : create the function x--> x^3 +2x^2 +1 and its derivatives
(def poly '(+(+(* x(* x x))(* 2(* x x ))) 1))

(println (deriv poly 'x))