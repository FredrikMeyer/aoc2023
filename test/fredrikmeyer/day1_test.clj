(ns fredrikmeyer.day1-test
  (:require
   [clojure.test :refer [deftest is]]
   [fredrikmeyer.day1 :as subject]))

(deftest find-numbers-test
  (is (= '(1 2 3 4 5 6 7 8 9)
         (subject/find-numbers "onetwothreefourfivesixseveneightnine")))
  (is (= '(1 1 2 3 9)
         (subject/find-numbers "1onetwothreeopopo9")))
  (is (= '(6 3)
         (subject/find-numbers "psix3")))
  (is (= '(2 7 7 5) (subject/find-numbers "2sevenpkfgj75")))
  (is (= '(3) (subject/find-numbers "f3")))
  (is (= '(5 6 9 2 7) (subject/find-numbers "five69two7sfj")))
  (is (= '(6 8 9 2) (subject/find-numbers "six8nine2")))
  (is (= '(5 8) (subject/find-numbers "fiveight")))
  (is (= '(5 2 7 8 4 4 6) (subject/find-numbers "gshhvf5twodqgdseven8fourfoursix")))
  (is (= '(2) (subject/find-numbers "m2"))))
