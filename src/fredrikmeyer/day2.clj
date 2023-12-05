(ns fredrikmeyer.day2

  (:require
   [clojure.string :as str]
   [fredrikmeyer.read :refer [read-day]]))

(comment (read-day "day2.txt"))

(def example "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")

(defn game-id [gamep]
  (->> gamep
       (re-find #"Game (\d+)")
       (second)
       (parse-long)))

(defn parse-game-entry [game-entry]
  (let [balls (map str/trim (str/split game-entry #","))]
    (->> balls
         (map (fn [b] (str/split b #" ")))
         (map (fn [[n b]] [(keyword b) (parse-long n)]))
         (into {}))))

(defn parse-record [r]
  (let [[game-part games] (str/split r #":")
        gid (game-id game-part)
        games-splitted (map str/trim (str/split games #";"))]
    {:id gid
     :games (map parse-game-entry games-splitted)}))

(defn game-possible? [game]
  (every? (fn [g] (and (<= (or (:red g) 0) 12)
                       (<= (or (:green g) 0) 13)
                       (<= (or (:blue g) 0) 14))) (:games game)))

(defn part-1 []
  (->> (read-day "day2.txt")
       (str/split-lines)
       (map parse-record)
       (filter game-possible?)
       (map :id)
       (reduce + 0)))

(defn fewest-of-each-color [games]
  (reduce (fn  [acc curr]
            (-> acc
                (update :red (fn [v] (max v (or (:red curr) 0))))
                (update :blue (fn [v] (max v (or (:blue curr) 0))))
                (update :green (fn [v] (max v (or (:green curr) 0))))))
          {:red 0 :green 0 :blue 0} games))

(defn part-2 []
  (->> (read-day "day2.txt")
       (str/split-lines)
       (map parse-record)
       (map :games)
       (map fewest-of-each-color)
       (map (fn [r] (apply * 1 (vals r))))
       (reduce + 0)))
