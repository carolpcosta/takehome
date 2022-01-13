(ns takehome.core
  (:require [java-time :as time]))
(def patriota-types #{:series :podcast :debate})
(def premium-types (conj patriota-types :interview))
(def mecenas-types (conj premium-types :course))

(defn patriota-access? [object purchase]
  (let [object-type (:type object)]
    (or
     (contains? patriota-types object-type)
     (and (= object-type :interview)
          (time/before? (:subscription-start purchase)
                        (:released-at object)
                        (:subscription-end purchase))))))

(defn premium-access? [object purchase]
  (let [object-type (:type object)]
    (or
     (contains? premium-types object-type)
     (and (= (:type object) :course)
          (time/before? (:subscription-start purchase)
                        (:released-at object)
                        (:subscription-end purchase))))))

(defn mecenas-access? [object purchase]
  (let [object-type (:type object)]
    (or
     (contains? mecenas-types object-type)
     (and (= (:type object) :patron)
          (time/before? (:subscription-start purchase)
                        (:released-at object)
                        (:subscription-end purchase))))))

(defn can-access? [object purchase]
  (cond
    (= (:type purchase) :patriota) (patriota-access? object purchase)
    (= (:type purchase) :premium) (premium-access? object  purchase)
    (= (:type purchase) :mecenas) (mecenas-access? object purchase)))
