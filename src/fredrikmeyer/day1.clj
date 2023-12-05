(ns fredrikmeyer.day1
  (:require
   [clojure.string :as str]
   [fredrikmeyer.read :refer [read-day]]))

(comment (read-day "day1.txt"))

(defn is-integer [c]
  (let [as-int (int c)]
    (and (<= as-int 57) (>= as-int 48))))

(defn find-first-digit [word]
  (->> word
       (filter is-integer)
       first))

(defn find-first-and-last-digit [word]
  (->> word
       (filter is-integer)
       (#(-> [(first %) (last %)]))))

(defn part-1 []
  (->> (read-day "day1.txt")
       (str/split-lines)
       (map find-first-and-last-digit)
       (map #(apply str %))
       (map parse-long)
       (reduce + 0)))

(defn to-number [number-string]
  #_{:pre [(contains? #{"one" "two" "three" "four" "five" "six" "seven" "eight" "nine" "1" "2" "3" "4" "5" "6" "7" "8" "9"} number-string)]
   :post [(>= % 1) (<= % 9)]}
  (if (and (= 1 (count number-string)) (is-integer (first number-string)))
    (parse-long number-string)
    (condp = number-string
      "one" 1
      "two" 2
      "three" 3
      "four" 4
      "five" 5
      "six" 6
      "seven" 7
      "eight" 8
      "nine" 9)))

(defn find-numbers [s]
  (->> s
       (re-seq #"(?=(one|two|three|four|five|six|seven|eight|nine|[1-9]))")
       (map second)
       (map to-number)))

(defn part-2 []
  (->> (read-day "day1.txt")
       (str/split-lines)
       (map find-numbers)
              (map #(-> [(first %) (last %)]))
       (map #(apply str %))
       (map parse-long)
       (reduce + 0)))
