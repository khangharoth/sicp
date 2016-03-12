(ns
  L2_A.clojure.listOperations)

(def one-To-Four (list 1 2 3 4))


(defn scale-list1 [items factor]
  (map (fn [x] (* x factor)) items))

(defn scale-list [items factor]
  (if (empty? items)
      nil
    (list (* (first items) factor)
      (scale-list (rest items) factor)
      )
    )
  )

(println (scale-list one-To-Four 4))
(println (scale-list1 one-To-Four 4))