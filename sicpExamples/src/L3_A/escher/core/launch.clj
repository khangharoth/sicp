(ns L3_A.escher.core.launch
  (:use
   :reload-all
	[L3_A.escher.core.rectangle]
	[L3_A.escher.core.artwork]
	[L3_A.escher.core.segment]
	))

(import '(javax.swing JFrame JPanel))


(defn escher-panel []
  (proxy [JPanel] []
    (paintComponent [g]
      (proxy-super paintComponent g)
      (make-art g)
     )
    )
  )

(defn escher-frame []
  (let [
       frame (JFrame. "Escher")
    ]
    (doto frame
      (.pack)
      (.setDefaultCloseOperation JFrame/DISPOSE_ON_CLOSE)
      (.setSize 400 430)
      (.add (escher-panel))
      (.setVisible true))
    )
  )

(defn run-escher []
  (escher-frame)
)
