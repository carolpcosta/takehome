(ns takehome.core-test
  (:require [clojure.test :refer :all]
            [java-time :as time]
            [takehome.core :as sub]))

(deftest test-patriota
  (are [result object] (= result
                            (sub/can-access?
                             object
                             { :type :patriota :subscription-start (time/local-date-time "2019-01-24T11:46:22.811") :subscription-end (time/local-date-time "2020-01-24T11:46:22.811") }))
    
    
    true { :type :podcast :name "CORINGA - Podcast Cultura Paralela #1", :released-at "2020-03-29T20:02:34.337"}
    true { :type :debate :name "Há regras para educar os filhos?", :released-at (time/local-date-time "2020-03-29T20:02:34.356") }
    true { :type :interview :name "Congresso Brasil Paralelo - Rafael Nogueira", :released-at (time/local-date-time "2019-11-16T21:40:51.579") }
    true { :type :series :name "1964: O Brasil entre Armas e Livros", :released-at "2019-07-24T20:02:34.691"}
    false { :type :interview :name "Congresso Brasil Paralelo - Rafael Nogueira", :released-at (time/local-date-time "2020-11-16T21:40:51.579") }
    false { :type :course :name "Carlos Lacerda e a República Brasileira", :released-at "2018-11-21T18:32:53.278" } 
    false { :type :patron :name "Relatório Mecenas", :released-at "2020-08-10T20:00:00.656"}))

(deftest test-premium
  (are [result object] (= result
                            (sub/can-access?
                             object
                             { :type :premium :subscription-start (time/local-date-time "2019-01-24T11:46:22.811") :subscription-end (time/local-date-time "2020-01-24T11:46:22.811") }))
    
    
    true { :type :podcast :name "CORINGA - Podcast Cultura Paralela #1", :released-at (time/local-date-time "2020-03-29T20:02:34.337")}
    true { :type :debate :name "Há regras para educar os filhos?", :released-at (time/local-date-time "2020-03-29T20:02:34.356") }
    true { :type :interview :name "Congresso Brasil Paralelo - Rafael Nogueira", :released-at (time/local-date-time "2019-11-16T21:40:51.579") }
    true { :type :series :name "1964: O Brasil entre Armas e Livros", :released-at (time/local-date-time "2019-07-24T20:02:34.691")}
    true { :type :interview :name "Congresso Brasil Paralelo - Rafael Nogueira", :released-at (time/local-date-time "2020-11-16T21:40:51.579") }
    false { :type :course :name "Carlos Lacerda e a República Brasileira", :released-at  (time/local-date-time "2018-11-21T18:32:53.278") } 
    false { :type :patron :name "Relatório Mecenas", :released-at (time/local-date-time "2020-08-10T20:00:00.656") }))

(deftest test-mecenas
  (are [result object] (= result
                            (sub/can-access?
                             object
                             { :type :mecenas :subscription-start (time/local-date-time "2019-01-24T11:46:22.811") :subscription-end (time/local-date-time "2020-01-24T11:46:22.811") }))
    
    
    true { :type :podcast :name "CORINGA - Podcast Cultura Paralela #1", :released-at (time/local-date-time "2020-03-29T20:02:34.337")}
    true { :type :debate :name "Há regras para educar os filhos?", :released-at (time/local-date-time "2020-03-29T20:02:34.356") }
    true { :type :interview :name "Congresso Brasil Paralelo - Rafael Nogueira", :released-at (time/local-date-time "2019-11-16T21:40:51.579") }
    true { :type :series :name "1964: O Brasil entre Armas e Livros", :released-at (time/local-date-time "2019-07-24T20:02:34.691")}
    true { :type :interview :name "Congresso Brasil Paralelo - Rafael Nogueira", :released-at (time/local-date-time "2020-11-16T21:40:51.579") }
    false { :type :patron :name "Relatório Mecenas #1", :released-at (time/local-date-time "2020-08-10T20:00:00.656" )} 
    true { :type :patron :name "Relatório Mecenas #2", :released-at (time/local-date-time "2019-08-10T20:00:00.656" )}))